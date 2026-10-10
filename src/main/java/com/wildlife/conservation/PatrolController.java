
package com.wildlife.conservation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PatrolController {

    private final PatrolRepository patrolRepository;

    public PatrolController(PatrolRepository patrolRepository) {
        this.patrolRepository = patrolRepository;
    }

    @GetMapping("/patrols")
    public String showPatrols(Model model) {
        model.addAttribute("patrolList", patrolRepository.findAll());
        model.addAttribute("patrol", new Patrol());

        return "patrols";
    }

    @PostMapping("/patrols")
    public String savePatrol(@ModelAttribute Patrol patrol) {
        if (patrol.getStatus() == null || patrol.getStatus().isBlank()) {
            patrol.setStatus("SCHEDULED");
        }

        patrolRepository.save(patrol);

        return "redirect:/patrols";
    }
    

@PostMapping("/patrols/{id}/status")
public String updatePatrolStatus(
        @PathVariable Long id,
        @RequestParam String status) {

    Patrol patrol = patrolRepository.findById(id).orElse(null);

    if (patrol != null &&
            java.util.Arrays.asList(
                    "SCHEDULED", "IN_PROGRESS", "COMPLETED"
            ).contains(status)) {

        patrol.setStatus(status);
        patrolRepository.save(patrol);
    }

    return "redirect:/patrols";
}

}
