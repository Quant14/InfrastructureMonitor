import org.elsys.InfrastructureMonitorServer.Model.Machine;
import org.elsys.InfrastructureMonitorServer.Service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Provider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
@RequestMapping("/api")
public class MonitorController {
    @Autowired
    MonitorService service;
    @RequestMapping(value = "/connect" , method = RequestMethod.POST)
    public int connectMachines(@RequestParam(value="mainServerName") String mainServerName,
                                               @RequestParam(value="backUpNames") List<String> backUpNames){
        Map<String, Object> backUpStatuses = new HashMap<>();

        // Execute the connectMachine method for all backup server names sequentially
        for (String name : backUpNames) {
            int machineResult = service.connectMachine(name);
            backUpStatuses.put(name, machineResult);
        }

        return service.connectMachine(mainServerName);
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
