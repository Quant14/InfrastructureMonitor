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
    private float cpuUsage;

    @Column(name = "ram_usage")
    private float ramUsage;

    @Column(name = "disk_usage")
    private float diskUsage;

    public Machine(String name) {
        this.name = name;
    }

    public Machine() {
    }

    public String getName() {
        return name;
    }

    public float getCpuUsage() {
        return cpuUsage;
    }

    public float getRamUsage() {
        return ramUsage;
    }

    public float getDiskUsage() {
        return diskUsage;
    }

    public void setCpuUsage(float cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public void setRamUsage(float ramUsage) {
        this.ramUsage = ramUsage;
    }

    public void setDiskUsage(float diskUsage) {
        this.diskUsage = diskUsage;
    }
}
