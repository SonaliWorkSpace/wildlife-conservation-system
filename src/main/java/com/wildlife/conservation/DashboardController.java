
package com.wildlife.conservation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final IncidentRepository incidentRepository;
    private final WildlifeRepository wildlifeRepository;
    private final PatrolRepository patrolRepository;
    private final RescueRepository rescueRepository;

    public DashboardController(
            IncidentRepository incidentRepository,
            WildlifeRepository wildlifeRepository,
            PatrolRepository patrolRepository,
            RescueRepository rescueRepository) {

        this.incidentRepository = incidentRepository;
        this.wildlifeRepository = wildlifeRepository;
        this.patrolRepository = patrolRepository;
        this.rescueRepository = rescueRepository;
    }

    @GetMapping("/")
    public String showDashboard(Model model) {

        long totalIncidents = incidentRepository.count();

        long openIncidents = incidentRepository.findAll()
                .stream()
                .filter(incident ->
                        "OPEN".equals(incident.getStatus()))
                .count();

        long totalWildlife = wildlifeRepository.count();

        long activePatrols =
                patrolRepository.countByStatus("IN_PROGRESS");

        long activeRescues =
                rescueRepository.countByStatus("IN_PROGRESS");

        long highPriorityCases =
                rescueRepository.countByPriority("HIGH");

        long reportedCases =
                rescueRepository.countByStatus("REPORTED");

        model.addAttribute("totalIncidents", totalIncidents);
        model.addAttribute("openIncidents", openIncidents);
        model.addAttribute("totalWildlife", totalWildlife);
        model.addAttribute("activePatrols", activePatrols);
        model.addAttribute("activeRescues", activeRescues);
        model.addAttribute("highPriorityCases", highPriorityCases);
        model.addAttribute("reportedCases", reportedCases);

        return "dashboard";
    }
}
