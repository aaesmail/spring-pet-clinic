package com.example.petclinic.controllers;

import java.util.Collection;

import com.example.petclinic.model.Owner;
import com.example.petclinic.model.Pet;
import com.example.petclinic.model.PetType;
import com.example.petclinic.services.OwnerService;
import com.example.petclinic.services.PetService;
import com.example.petclinic.services.PetTypeService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/owners/{ownerId}/pets")
@Controller
public class PetController {

    public static final String VIEW_CREATE_OR_UPDATE_PET_FORM = "pets/createOrUpdatePetForm";

    private final PetService petService;
    private final PetTypeService petTypeService;
    private final OwnerService ownerService;

    public PetController(PetService petService, PetTypeService petTypeService, OwnerService ownerService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setDisallowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return this.petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId) {
        return this.ownerService.findById(ownerId);
    }

    @GetMapping("/new")
    public String initCreationForm(Owner owner, Model model) {
        Pet pet = new Pet();
        owner.addPet(pet);
        model.addAttribute("pet", pet);

        return VIEW_CREATE_OR_UPDATE_PET_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(@Validated Pet pet, Owner owner, BindingResult result, Model model,
            @PathVariable Long ownerId) {
        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null) {
            result.rejectValue("name", "duplicate", "already exists");
        }

        owner.addPet(pet);

        if (result.hasErrors()) {
            model.addAttribute("pet", pet);
            return VIEW_CREATE_OR_UPDATE_PET_FORM;
        }

        this.petService.save(pet);

        return OwnerController.VIEW_REDIRECT_TO_OWNER_ID + ownerId;
    }

    @GetMapping("/{petId}/edit")
    public String initUpdateForm(@PathVariable Long petId, Model model) {
        model.addAttribute("pet", this.petService.findById(petId));
        return VIEW_CREATE_OR_UPDATE_PET_FORM;
    }

    @PostMapping("/{petId}/edit")
    public String processUpdateForm(@Validated Pet pet, BindingResult result, Owner owner, Model model,
            @PathVariable Long ownerId, @PathVariable Long petId) {

        if (result.hasErrors()) {
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return VIEW_CREATE_OR_UPDATE_PET_FORM;
        }

        pet.setId(petId);
        pet.setOwner(owner);
        this.petService.save(pet);

        return OwnerController.VIEW_REDIRECT_TO_OWNER_ID + ownerId;
    }
}
