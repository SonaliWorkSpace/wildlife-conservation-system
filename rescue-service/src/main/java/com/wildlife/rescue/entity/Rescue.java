
package com.wildlife.rescue.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "rescues")
public class Rescue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String animalSpecies;
    private String rescueLocation;
    private String rescueTeam;
    private String condition;
    private String treatment;
    private String status;
    private LocalDateTime rescueTime;

    public Rescue() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnimalSpecies() {
        return animalSpecies;
    }

    public void setAnimalSpecies(String animalSpecies) {
        this.animalSpecies = animalSpecies;
    }

    public String getRescueLocation() {
        return rescueLocation;
    }

    public void setRescueLocation(String rescueLocation) {
        this.rescueLocation = rescueLocation;
    }

    public String getRescueTeam() {
        return rescueTeam;
    }

    public void setRescueTeam(String rescueTeam) {
        this.rescueTeam = rescueTeam;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getRescueTime() {
        return rescueTime;
    }

    public void setRescueTime(LocalDateTime rescueTime) {
        this.rescueTime = rescueTime;
    }
}
