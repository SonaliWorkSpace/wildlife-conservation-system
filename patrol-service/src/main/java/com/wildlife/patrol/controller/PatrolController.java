
package com.wildlife.patrol.controller;

import com.wildlife.patrol.entity.Patrol;
import com.wildlife.patrol.repository.PatrolRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/patrols")
public class PatrolController {

    private final PatrolRepository patrolRepository;

    public PatrolController(PatrolRepository patrolRepository) {
        this.patrolRepository = patrolRepository;
    }

    @GetMapping
    public List<Patrol> getAllPatrols() {
        return patrolRepository.findAll();
    }

    @GetMapping("/{id}")
    public Patrol getPatrolById(@PathVariable Long id) {
        return patrolRepository.findById(id)
                .orElseThrow(() ->
                        new org.springframework.web.server.ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Patrol not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Patrol createPatrol(@RequestBody Patrol patrol) {
        patrol.setId(null);

        if (patrol.getStatus() == null || patrol.getStatus().isBlank()) {
            patrol.setStatus("PLANNED");
        }

        if (patrol.getStartTime() == null) {
            patrol.setStartTime(LocalDateTime.now());
        }

        return patrolRepository.save(patrol);
    }

    @PutMapping("/{id}/status")
    public Patrol updatePatrolStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        Patrol patrol = patrolRepository.findById(id)
                .orElseThrow(() ->
                        new org.springframework.web.server.ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Patrol not found"));

        patrol.setStatus(status.toUpperCase());

        if ("COMPLETED".equals(patrol.getStatus())
                && patrol.getEndTime() == null) {
            patrol.setEndTime(LocalDateTime.now());
        }

        return patrolRepository.save(patrol);
    }
    @PutMapping("/{id}")
    public Patrol updatePatrol(
            @PathVariable Long id,
            @RequestBody Patrol updatedPatrol) {

        Patrol patrol = patrolRepository.findById(id)
                .orElseThrow(() ->
                        new org.springframework.web.server.ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Patrol not found"));

        patrol.setRangerName(updatedPatrol.getRangerName());
        patrol.setAssignedArea(updatedPatrol.getAssignedArea());
        patrol.setPatrolType(updatedPatrol.getPatrolType());
        patrol.setStatus(updatedPatrol.getStatus());

        return patrolRepository.save(patrol);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatrol(@PathVariable Long id) {
        Patrol patrol = patrolRepository.findById(id)
                .orElseThrow(() ->
                        new org.springframework.web.server.ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Patrol not found"));

        patrolRepository.delete(patrol);
    }
}
