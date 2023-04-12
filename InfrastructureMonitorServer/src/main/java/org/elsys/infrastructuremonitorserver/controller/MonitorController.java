package org.elsys.infrastructuremonitorserver.controller;

import org.elsys.infrastructuremonitorserver.model.Machine;
import org.elsys.infrastructuremonitorserver.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/api")
public class MonitorController {
    @Autowired
    MonitorService service;

    @RequestMapping(value = "/connect" , method = RequestMethod.POST)
    public int connectMachine(@RequestParam(value="name") String name) {
        return service.connectMachine(name);
    }

    @RequestMapping(value = "/disconnect", method = RequestMethod.DELETE)
    public int disconnectMachine(@RequestParam(value="name") String name) {
        return service.disconnectMachine(name);
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
