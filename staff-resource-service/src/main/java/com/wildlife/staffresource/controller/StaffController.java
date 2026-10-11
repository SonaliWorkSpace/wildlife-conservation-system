
package com.wildlife.staffresource.controller;

import com.wildlife.staffresource.entity.Staff;
import com.wildlife.staffresource.repository.StaffRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
@CrossOrigin(origins = "*")
public class StaffController {

    private final StaffRepository repository;

    public StaffController(StaffRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Staff> getAllStaff() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Staff getStaffById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Staff member not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Staff createStaff(@RequestBody Staff staff) {
        staff.setId(null);
        return repository.save(staff);
    }

    @PutMapping("/{id}")
    public Staff updateStaff(
            @PathVariable Long id,
            @RequestBody Staff updatedStaff) {

        Staff staff = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Staff member not found"));

        staff.setEmployeeId(updatedStaff.getEmployeeId());
        staff.setFullName(updatedStaff.getFullName());
        staff.setRole(updatedStaff.getRole());
        staff.setPhone(updatedStaff.getPhone());
        staff.setAssignedArea(updatedStaff.getAssignedArea());
        staff.setAvailabilityStatus(updatedStaff.getAvailabilityStatus());

        return repository.save(staff);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStaff(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Staff member not found");
        }

        repository.deleteById(id);
    }
}
