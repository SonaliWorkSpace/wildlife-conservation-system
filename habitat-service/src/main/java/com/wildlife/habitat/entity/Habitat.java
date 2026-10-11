
package com.wildlife.habitat.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "habitats")
public class Habitat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String habitatName;
    private String habitatType;
    private String location;
    private Double areaSizeSqKm;
    private String waterSource;
    private String conservationStatus;
    private String description;

    public Habitat() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHabitatName() {
        return habitatName;
    }

    public void setHabitatName(String habitatName) {
        this.habitatName = habitatName;
    }

    public String getHabitatType() {
        return habitatType;
    }

    public void setHabitatType(String habitatType) {
        this.habitatType = habitatType;
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

    public String getWaterSource() {
        return waterSource;
    }

    public void setWaterSource(String waterSource) {
        this.waterSource = waterSource;
    }

    public String getConservationStatus() {
        return conservationStatus;
    }

    public void setConservationStatus(String conservationStatus) {
        this.conservationStatus = conservationStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
