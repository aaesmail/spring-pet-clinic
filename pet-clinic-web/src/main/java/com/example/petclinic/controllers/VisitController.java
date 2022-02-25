package com.example.petclinic.controllers;

import com.example.petclinic.model.Owner;
import com.example.petclinic.model.Pet;
import com.example.petclinic.model.Visit;
import com.example.petclinic.services.OwnerService;
import com.example.petclinic.services.PetService;
import com.example.petclinic.services.VisitService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/owners/{ownerId}/pets/{petId}/visits")
@Controller
public class VisitController {

    public final String VIEW_CREATE_OR_UPDATE_VISIT_FORM = "pets/createOrUpdateVisitForm";

    private final OwnerService ownerService;
    private final VisitService visitService;
    private final PetService petService;

    public VisitController(OwnerService ownerService, VisitService visitService, PetService petService) {
        this.ownerService = ownerService;
        this.visitService = visitService;
        this.petService = petService;
    }

    @InitBinder
    public void setDisallowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable Long ownerId, @PathVariable Long petId, Model model) {
        Pet pet = this.petService.findById(petId);
        model.addAttribute("pet", pet);

        Owner owner = this.ownerService.findById(ownerId);
        owner.addPet(pet);
        model.addAttribute("owner", owner);

        Visit visit = new Visit();
        pet.addVisit(visit);
        return visit;
    }

    @GetMapping("/new")
    public String initNewVisitForm() {
        return VIEW_CREATE_OR_UPDATE_VISIT_FORM;
    }

    @PostMapping("/new")
    public String processNewVisitForm(@Validated Visit visit, BindingResult result, @PathVariable Long ownerId) {
        if (result.hasErrors()) {
            return VIEW_CREATE_OR_UPDATE_VISIT_FORM;
        }

        this.visitService.save(visit);

        return OwnerController.VIEW_REDIRECT_TO_OWNER_ID + ownerId;
    }
}
