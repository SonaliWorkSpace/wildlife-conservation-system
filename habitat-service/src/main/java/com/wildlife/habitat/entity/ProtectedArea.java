
package com.wildlife.habitat.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "protected_areas")
public class ProtectedArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String areaName;
    private String state;
    private String location;
    private Double areaSizeSqKm;
    private String protectionStatus;
    private String description;

    public ProtectedArea() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getAreaSizeSqKm() {
        return areaSizeSqKm;
    }

    public void setAreaSizeSqKm(Double areaSizeSqKm) {
        this.areaSizeSqKm = areaSizeSqKm;
    }

    public String getProtectionStatus() {
        return protectionStatus;
    }

    public void setProtectionStatus(String protectionStatus) {
        this.protectionStatus = protectionStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
