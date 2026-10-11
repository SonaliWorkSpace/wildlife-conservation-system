
package com.wildlife.incident.controller;

import com.wildlife.incident.entity.Incident;
import com.wildlife.incident.repository.IncidentRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    private final IncidentRepository incidentRepository;

    public IncidentController(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    @GetMapping
    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Incident getIncidentById(@PathVariable Long id) {
        return incidentRepository.findById(id)
                .orElseThrow(() -> new org.springframework.web.server
                        .ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Incident not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Incident createIncident(@RequestBody Incident incident) {
        incident.setId(null);

        if (incident.getReportedAt() == null) {
            incident.setReportedAt(LocalDateTime.now());
        }

        if (incident.getStatus() == null || incident.getStatus().isBlank()) {
            incident.setStatus("OPEN");
        }

        return incidentRepository.save(incident);
    }

    @PutMapping("/{id}/status")
    public Incident updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        Incident incident = incidentRepository.findById(id)
                .orElseThrow(() -> new org.springframework.web.server
                        .ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Incident not found"));

        incident.setStatus(status.toUpperCase());
        return incidentRepository.save(incident);
    }
}
