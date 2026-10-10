
package com.wildlife.conservation;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RescueController {

    private final RescueRepository rescueRepository;

    public RescueController(RescueRepository rescueRepository) {
        this.rescueRepository = rescueRepository;
    }

    @GetMapping("/rescues")
    public String showRescues(Model model) {
        model.addAttribute("rescueList", rescueRepository.findAll());
        model.addAttribute("rescue", new Rescue());
        return "rescues";
    }

    @PostMapping("/rescues")
    public String saveRescue(@ModelAttribute Rescue rescue) {

        if (rescue.getStatus() == null || rescue.getStatus().isBlank()) {
            rescue.setStatus("REPORTED");
        }

        if (rescue.getReportedDate() == null) {
            rescue.setReportedDate(LocalDate.now());
        }

        rescueRepository.save(rescue);

        return "redirect:/rescues";
    }

    @PostMapping("/rescues/{id}/status")
    public String updateRescueStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        Rescue rescue = rescueRepository.findById(id).orElse(null);

        if (rescue != null &&
                Arrays.asList(
                        "REPORTED", "IN_PROGRESS", "RESCUED", "CLOSED"
                ).contains(status)) {

            rescue.setStatus(status);
            rescueRepository.save(rescue);
        }

        return "redirect:/rescues";
    }
}
