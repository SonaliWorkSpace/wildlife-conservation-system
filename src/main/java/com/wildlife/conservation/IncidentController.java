
package com.wildlife.conservation;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IncidentController {

    private final IncidentRepository incidentRepository;

    public IncidentController(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    @GetMapping("/incidents")
    public String showIncidents(
            @RequestParam(required = false) String severity,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String location,
            Model model) {

        List<Incident> incidents = incidentRepository.findAll();

        // Filter by severity
        if (severity != null && !severity.isBlank()) {
            incidents = incidents.stream()
                    .filter(i -> severity.equals(i.getSeverity()))
                    .collect(Collectors.toList());
        }

        // Filter by status
        if (status != null && !status.isBlank()) {
            incidents = incidents.stream()
                    .filter(i -> status.equals(i.getStatus()))
                    .collect(Collectors.toList());
        }

        // Filter by location
        if (location != null && !location.isBlank()) {
            incidents = incidents.stream()
                    .filter(i -> i.getLocation() != null
                            && i.getLocation().toLowerCase()
                                    .contains(location.toLowerCase()))
                    .collect(Collectors.toList());
        }

        model.addAttribute("incidents", incidents);
        model.addAttribute("incident", new Incident());
        model.addAttribute("selectedSeverity", severity);
        model.addAttribute("selectedStatus", status);
        model.addAttribute("searchLocation", location);

        return "incidents";
    }

    @PostMapping("/incidents")
    public String saveIncident(@ModelAttribute Incident incident) {
        incident.setStatus("OPEN");
        incidentRepository.save(incident);
        return "redirect:/incidents";
    }

    @PostMapping("/incidents/{id}/status")
    public String updateIncidentStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        Incident incident = incidentRepository.findById(id).orElse(null);

        if (incident != null &&
                Arrays.asList("OPEN", "IN_PROGRESS", "RESOLVED")
                        .contains(status)) {

            incident.setStatus(status);
            incidentRepository.save(incident);
        }

        return "redirect:/incidents";
    }
}
