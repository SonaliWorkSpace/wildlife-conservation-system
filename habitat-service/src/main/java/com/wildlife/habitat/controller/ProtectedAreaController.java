
package com.wildlife.habitat.controller;

import com.wildlife.habitat.entity.ProtectedArea;
import com.wildlife.habitat.repository.ProtectedAreaRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/protected-areas")
@CrossOrigin(origins = "*")
public class ProtectedAreaController {

    private final ProtectedAreaRepository repository;

    public ProtectedAreaController(ProtectedAreaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<ProtectedArea> getAllAreas() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ProtectedArea getAreaById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Protected area not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProtectedArea createArea(@RequestBody ProtectedArea area) {
        area.setId(null);
        return repository.save(area);
    }

    @PutMapping("/{id}")
    public ProtectedArea updateArea(
            @PathVariable Long id,
            @RequestBody ProtectedArea updatedArea) {

        ProtectedArea area = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Protected area not found"));

        area.setAreaName(updatedArea.getAreaName());
        area.setState(updatedArea.getState());
        area.setLocation(updatedArea.getLocation());
        area.setAreaSizeSqKm(updatedArea.getAreaSizeSqKm());
        area.setProtectionStatus(updatedArea.getProtectionStatus());
        area.setDescription(updatedArea.getDescription());

        return repository.save(area);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArea(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Protected area not found");
        }

        repository.deleteById(id);
    }
}
