package org.elsys.infrastructuremonitorserver.controller;

import org.elsys.infrastructuremonitorserver.model.Machine;
import org.elsys.infrastructuremonitorserver.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/api")
public class MonitorController {
    @Autowired
    MonitorService service;

    @Value("${servers.list}")
    private String[] servers;

    private boolean isMaster = false;

    @RequestMapping(value = "/switch-master", method = RequestMethod.POST)
    public int switchMaster() {
        isMaster = true;
        return 0;
    }

    @RequestMapping(value = "/connect" , method = RequestMethod.POST)
    public void connectMachines(@RequestParam(value="name") String name){
        if (service.connectMachine(name) == 0 && isMaster) {
            LinkedList<RequestThread> threads = new LinkedList<>();

            for (String server : servers) {
                threads.add(new RequestThread("text/plain", "", "http://" + server + "/api/connect?name=" + name, "POST"));
            }
            service.handleThreads(threads);
        }
    }

    @RequestMapping(value = "/disconnect" , method = RequestMethod.DELETE)
    public void disconnectMachine(@RequestParam(value="name") String name) {
        if (service.disconnectMachine(name) == 0 && isMaster) {
            LinkedList<RequestThread> threads = new LinkedList<>();

            for (String server : servers) {
                threads.add(new RequestThread("text/plain", "", "http://" + server + "/api/disconnect?name=" + name, "DELETE"));
            }
            service.handleThreads(threads);
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<Machine> getMachines() {
        return service.getMachines();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void updateMachine(@RequestBody Machine machine){
        if (service.updateMachine(machine) == 0 && isMaster) {
            LinkedList<RequestThread> threads = new LinkedList<>();

            for (String server : servers) {
                threads.add(new RequestThread("application/json",
                        "{\n \"name\": \"" + machine.getName() + "\",\n \"cpuUsage\": \"" + machine.getCpuUsage() + "\",\n \"ramUsage\": \"" + machine.getRamUsage() + "\",\n \"diskUsage\": \"" + machine.getDiskUsage() + "\"\n}", "http://" + server + "/api/update", "PUT"));
            }
            service.handleThreads(threads);
        }
    }

}
