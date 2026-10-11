
package com.wildlife.conservation.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "conservation_programmes")
public class ConservationProgramme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String programmeName;

    @Column(length = 1000)
    private String objectives;

    private String assignedArea;
    private Double budget;
    private Integer targetValue;
    private Integer achievedValue;
    private String status;

    public ConservationProgramme() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProgrammeName() {
        return programmeName;
    }

    public void setProgrammeName(String programmeName) {
        this.programmeName = programmeName;
    }

    public String getObjectives() {
        return objectives;
    }

    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }

    public String getAssignedArea() {
        return assignedArea;
    }

    public void setAssignedArea(String assignedArea) {
        this.assignedArea = assignedArea;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public Integer getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(Integer targetValue) {
        this.targetValue = targetValue;
    }

    public Integer getAchievedValue() {
        return achievedValue;
    }

    public void setAchievedValue(Integer achievedValue) {
        this.achievedValue = achievedValue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
