package org.elsys.infrastructuremonitorserver.service;

import org.elsys.infrastructuremonitorserver.model.Machine;
import org.elsys.infrastructuremonitorserver.repository.MonitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitorService {
    @Autowired
    MonitorRepository repository;

    public int connectMachine(String name) {
        if (findByName(name) == null) {
            repository.save(new Machine(name));
            return 0;
        }
        return 1;
    }

    private Machine findByName(String name) {
        List<Machine> machines = repository.findAll();
        for (Machine machine : machines) {
            if (machine.getName().equals(name))
                return machine;
        }
        return null;
    }

    public int updateMachine(Machine machine) {
        Machine updated = findByName(machine.getName());
        if (updated != null) {
            updated.setCpuUsage(machine.getCpuUsage());
            updated.setRamUsage(machine.getRamUsage());
            updated.setDiskUsage(machine.getDiskUsage());
            repository.save(updated);
            return 0;
        }
        return 1;
    }

    public int disconnectMachine(String name) {
        Machine machine = findByName(name);
        if (machine != null) {
            repository.delete(machine);
            return 0;
        }
        return 1;
    }

    public List<Machine> getMachines() {
        return repository.findAll();
    }
}
