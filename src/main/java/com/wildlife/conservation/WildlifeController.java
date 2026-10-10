
package com.wildlife.conservation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class WildlifeController {

    private final WildlifeRepository wildlifeRepository;

    public WildlifeController(WildlifeRepository wildlifeRepository) {
        this.wildlifeRepository = wildlifeRepository;
    }

    @GetMapping("/wildlife")
    public String showWildlife(Model model) {
        model.addAttribute("wildlifeList", wildlifeRepository.findAll());
        model.addAttribute("wildlife", new Wildlife());
        return "wildlife";
    }

    @PostMapping("/wildlife")
    public String saveWildlife(@ModelAttribute Wildlife wildlife) {
        wildlifeRepository.save(wildlife);
        return "redirect:/wildlife";
    }
}
