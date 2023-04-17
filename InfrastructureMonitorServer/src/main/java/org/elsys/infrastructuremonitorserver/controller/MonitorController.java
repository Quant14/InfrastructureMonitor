package org.elsys.infrastructuremonitorserver.controller;

import org.elsys.infrastructuremonitorserver.model.Machine;
import org.elsys.infrastructuremonitorserver.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
@RequestMapping("/api")
public class MonitorController {
    @Autowired
    MonitorService service;

    @Value("${servers.list}")
    private String[] servers;

    @RequestMapping(value = "/connect" , method = RequestMethod.POST)
    public void connectMachines(@RequestParam(value="name") String name){
        if (service.connectMachine(name) == 0) {
            LinkedList<RequestThread> threads = new LinkedList<>();

            for (String server : servers) {
                threads.add(new RequestThread("text/plain", "", "http://" + server + "/api/connect?name=" + name, "POST"));
            }
            service.handleThreads(threads);
        }
    }

    @RequestMapping(value = "/disconnect" , method = RequestMethod.DELETE)
    public int disconnectMachine(@RequestParam(value="mainServerName") String mainServerName,
                               @RequestParam(value="backUpNames") List<String> backUpNames) {
        Map<String, Object> backUpStatuses = new HashMap<>();

        // Execute the disconnectMachine method for all backup server names sequentially
        for (String name : backUpNames) {
            int machineResult = service.disconnectMachine(name);
            backUpStatuses.put(name, machineResult);
        }

        return service.disconnectMachine(mainServerName);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<Machine> getMachines() {
        return service.getMachines();
    }


    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public int updateMachine(@RequestBody Machine machine){
        return service.updateMachine(machine);
    }

}
