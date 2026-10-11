package com.wildlife.service.controller;

import com.wildlife.service.entity.Wildlife;
import com.wildlife.service.repository.WildlifeRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wildlife")
public class WildlifeController {

    private final WildlifeRepository wildlifeRepository;

    public WildlifeController(WildlifeRepository wildlifeRepository) {
        this.wildlifeRepository = wildlifeRepository;
    }

    @GetMapping
    public List<Wildlife> getAllWildlife() {
        return wildlifeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Wildlife getWildlifeById(@PathVariable Long id) {
        return wildlifeRepository.findById(id)
                .orElseThrow(() ->
                        new org.springframework.web.server.ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Wildlife record not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Wildlife addWildlife(@RequestBody Wildlife wildlife) {
        wildlife.setId(null);
        return wildlifeRepository.save(wildlife);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWildlife(@PathVariable Long id) {
        if (!wildlifeRepository.existsById(id)) {
            throw new org.springframework.web.server.ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Wildlife record not found");
        }
        wildlifeRepository.deleteById(id);
    }
}