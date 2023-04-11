package org.elsys.infrastructuremonitorserver.model;

import jakarta.persistence.*;

@Entity
@Table(name = "machine")
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "cpu_usage")
    private int cpuUsage;

    @Column(name = "ram_usage")
    private int ramUsage;

    @Column(name = "disk_usage")
    private int diskUsage;

    public Machine(String name) {
        this.name = name;
    }

    public Machine() {
    }

    public String getName() {
        return name;
    }

    public int getCpuUsage() {
        return cpuUsage;
    }

    public int getRamUsage() {
        return ramUsage;
    }

    public int getDiskUsage() {
        return diskUsage;
    }

    public void setCpuUsage(int cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public void setRamUsage(int ramUsage) {
        this.ramUsage = ramUsage;
    }

    public void setDiskUsage(int diskUsage) {
        this.diskUsage = diskUsage;
    }
}
