
package com.wildlife.rescue.controller;

import com.wildlife.rescue.entity.Rescue;
import com.wildlife.rescue.repository.RescueRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/rescues")
public class RescueController {

    private final RescueRepository rescueRepository;

    public RescueController(RescueRepository rescueRepository) {
        this.rescueRepository = rescueRepository;
    }

    @GetMapping
    public List<Rescue> getAllRescues() {
        return rescueRepository.findAll();
    }

    @GetMapping("/{id}")
    public Rescue getRescueById(@PathVariable Long id) {
        return rescueRepository.findById(id)
                .orElseThrow(() ->
                        new org.springframework.web.server.ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Rescue not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Rescue createRescue(@RequestBody Rescue rescue) {
        rescue.setId(null);

        if (rescue.getStatus() == null || rescue.getStatus().isBlank()) {
            rescue.setStatus("REPORTED");
        }

        if (rescue.getRescueTime() == null) {
            rescue.setRescueTime(LocalDateTime.now());
        }

        return rescueRepository.save(rescue);
    }

    @PutMapping("/{id}/status")
    public Rescue updateRescueStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        Rescue rescue = rescueRepository.findById(id)
                .orElseThrow(() ->
                        new org.springframework.web.server.ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Rescue not found"));

        rescue.setStatus(status.toUpperCase());
        return rescueRepository.save(rescue);
    }
}
