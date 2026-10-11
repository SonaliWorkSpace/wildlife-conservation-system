
package com.wildlife.habitat.controller;

import com.wildlife.habitat.entity.Habitat;
import com.wildlife.habitat.repository.HabitatRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/habitats")
@CrossOrigin(origins = "*")
public class HabitatController {

    private final HabitatRepository repository;

    public HabitatController(HabitatRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Habitat> getAllHabitats() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Habitat getHabitatById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Habitat not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Habitat createHabitat(@RequestBody Habitat habitat) {
        habitat.setId(null);
        return repository.save(habitat);
    }

    @PutMapping("/{id}")
    public Habitat updateHabitat(
            @PathVariable Long id,
            @RequestBody Habitat updatedHabitat) {

        Habitat habitat = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Habitat not found"));

        habitat.setHabitatName(updatedHabitat.getHabitatName());
        habitat.setHabitatType(updatedHabitat.getHabitatType());
        habitat.setLocation(updatedHabitat.getLocation());
        habitat.setAreaSizeSqKm(updatedHabitat.getAreaSizeSqKm());
        habitat.setWaterSource(updatedHabitat.getWaterSource());
        habitat.setConservationStatus(updatedHabitat.getConservationStatus());
        habitat.setDescription(updatedHabitat.getDescription());

        return repository.save(habitat);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHabitat(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Habitat not found");
        }

        repository.deleteById(id);
    }
}
