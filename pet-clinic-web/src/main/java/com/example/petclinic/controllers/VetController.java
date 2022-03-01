package com.example.petclinic.controllers;

import java.util.Set;

import com.example.petclinic.model.Vet;
import com.example.petclinic.services.VetService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VetController {

    public static final String VIEW_VETS_INDEX = "vets/index";

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @GetMapping({ "/vets", "/vets/", "/vets/index", "/vets/index.html", "/vets.html" })
    public String listVets(Model model) {
        model.addAttribute("vets", this.vetService.findAll());

        return VIEW_VETS_INDEX;
    }

    @GetMapping("/api/vets")
    public @ResponseBody Set<Vet> getVetsJson() {
        return this.vetService.findAll();
    }
}
