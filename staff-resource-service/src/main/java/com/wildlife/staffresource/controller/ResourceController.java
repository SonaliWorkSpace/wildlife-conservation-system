
package com.wildlife.staffresource.controller;

import com.wildlife.staffresource.entity.Resource;
import com.wildlife.staffresource.repository.ResourceRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
@CrossOrigin(origins = "*")
public class ResourceController {

    private final ResourceRepository repository;

    public ResourceController(ResourceRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Resource> getAllResources() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Resource getResourceById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Resource not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Resource createResource(@RequestBody Resource resource) {
        resource.setId(null);
        return repository.save(resource);
    }

    @PutMapping("/{id}")
    public Resource updateResource(
            @PathVariable Long id,
            @RequestBody Resource updatedResource) {

        Resource resource = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Resource not found"));

        resource.setResourceName(updatedResource.getResourceName());
        resource.setResourceType(updatedResource.getResourceType());
        resource.setQuantity(updatedResource.getQuantity());
        resource.setConditionStatus(updatedResource.getConditionStatus());
        resource.setAssignedArea(updatedResource.getAssignedArea());
        resource.setAllocationStatus(updatedResource.getAllocationStatus());

        return repository.save(resource);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteResource(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Resource not found");
        }

        repository.deleteById(id);
    }
}
