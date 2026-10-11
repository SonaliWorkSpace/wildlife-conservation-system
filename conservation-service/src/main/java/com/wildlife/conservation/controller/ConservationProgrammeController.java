
package com.wildlife.conservation.controller;

import com.wildlife.conservation.entity.ConservationProgramme;
import com.wildlife.conservation.repository.ConservationProgrammeRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/conservation-programmes")
@CrossOrigin(origins = "*")
public class ConservationProgrammeController {

    private final ConservationProgrammeRepository repository;

    public ConservationProgrammeController(
            ConservationProgrammeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<ConservationProgramme> getAllProgrammes() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ConservationProgramme getProgrammeById(
            @PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Programme not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConservationProgramme createProgramme(
            @RequestBody ConservationProgramme programme) {
        programme.setId(null);
        return repository.save(programme);
    }

    @PutMapping("/{id}")
    public ConservationProgramme updateProgramme(
            @PathVariable Long id,
            @RequestBody ConservationProgramme updatedProgramme) {

        ConservationProgramme programme = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Programme not found"));

        programme.setProgrammeName(updatedProgramme.getProgrammeName());
        programme.setObjectives(updatedProgramme.getObjectives());
        programme.setAssignedArea(updatedProgramme.getAssignedArea());
        programme.setBudget(updatedProgramme.getBudget());
        programme.setTargetValue(updatedProgramme.getTargetValue());
        programme.setAchievedValue(updatedProgramme.getAchievedValue());
        programme.setStatus(updatedProgramme.getStatus());

        return repository.save(programme);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProgramme(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Programme not found");
        }

        repository.deleteById(id);
    }
}
