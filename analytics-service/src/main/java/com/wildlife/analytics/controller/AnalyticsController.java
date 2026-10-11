package com.wildlife.analytics.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final RestTemplate restTemplate;

    public AnalyticsController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        Map<String, String> response = new LinkedHashMap<>();
        response.put("service", "Analytics Service");
        response.put("status", "UP");
        return response;
    }

    @GetMapping("/summary")
    public Map<String, Object> getSummary() {

        List<?> wildlife = getRecords(
                "http://localhost:8081/api/wildlife");

        List<?> incidents = getRecords(
                "http://localhost:8082/api/incidents");

        List<?> patrols = getRecords(
                "http://localhost:8083/api/patrols");

        List<?> rescues = getRecords(
                "http://localhost:8084/api/rescues");

        List<?> protectedAreas = getRecords(
                "http://localhost:8086/api/protected-areas");

        List<?> habitats = getRecords(
                "http://localhost:8086/api/habitats");

        List<?> staff = getRecords(
                "http://localhost:8087/api/staff");

        List<?> resources = getRecords(
                "http://localhost:8087/api/resources");

        List<?> programmes = getRecords(
                "http://localhost:8088/api/conservation-programmes");

        long openIncidents = incidents.stream()
                .filter(record -> "OPEN".equalsIgnoreCase(
                        getField(record, "status")))
                .count();

        long activePatrols = patrols.stream()
                .filter(record -> "IN_PROGRESS".equalsIgnoreCase(
                        getField(record, "status")))
                .count();

        long activeRescues = rescues.stream()
                .filter(record -> {
                    String status = getField(record, "status");
                    return "REPORTED".equalsIgnoreCase(status)
                            || "IN_PROGRESS".equalsIgnoreCase(status);
                })
                .count();

        long activeStaff = staff.stream()
                .filter(record -> "AVAILABLE".equalsIgnoreCase(
                        getField(record, "availabilityStatus")))
                .count();

        long allocatedResources = resources.stream()
                .filter(record -> "ALLOCATED".equalsIgnoreCase(
                        getField(record, "allocationStatus")))
                .count();

        Map<String, Object> summary = new LinkedHashMap<>();

        summary.put("totalWildlife", wildlife.size());
        summary.put("totalIncidents", incidents.size());
        summary.put("openIncidents", openIncidents);
        summary.put("totalPatrols", patrols.size());
        summary.put("activePatrols", activePatrols);
        summary.put("totalRescues", rescues.size());
        summary.put("activeRescues", activeRescues);
        summary.put("totalProtectedAreas", protectedAreas.size());
        summary.put("totalHabitats", habitats.size());
        summary.put("totalStaff", staff.size());
        summary.put("activeStaff", activeStaff);
        summary.put("totalResources", resources.size());
        summary.put("allocatedResources", allocatedResources);
        summary.put("totalProgrammes", programmes.size());

        return summary;
    }

    private List<?> getRecords(String url) {
        try {
            List<?> records = restTemplate.getForObject(
                    url, List.class);
            return records == null ? List.of() : records;
        } catch (Exception e) {
            return List.of();
        }
    }

    private String getField(Object record, String fieldName) {
        if (record instanceof Map<?, ?> map) {
            Object value = map.get(fieldName);
            return value == null ? "" : value.toString();
        }
        return "";
    }
}