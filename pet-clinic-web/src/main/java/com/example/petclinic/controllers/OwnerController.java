package com.example.petclinic.controllers;

import java.util.List;

import com.example.petclinic.model.Owner;
import com.example.petclinic.services.OwnerService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    public static final String VIEW_FIND_OWNERS = "owners/findOwners";
    public static final String VIEW_OWNERS_LIST = "owners/ownersList";
    public static final String VIEW_OWNER_DETAILS = "owners/ownerDetails";
    public static final String VIEW_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    public static final String VIEW_REDIRECT_TO_OWNER_ID = "redirect:/owners/";

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setDisallowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/find")
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());

        return VIEW_FIND_OWNERS;
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model) {

        if (owner.getLastName() == null) {
            owner.setLastName("");
        }

        List<Owner> results = this.ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");

        if (results.isEmpty()) {
            result.rejectValue("lastName", "notFound", "not found");
            return VIEW_FIND_OWNERS;
        }

        if (results.size() == 1) {
            owner = results.iterator().next();
            return VIEW_REDIRECT_TO_OWNER_ID + owner.getId();
        }

        model.addAttribute("listOwners", results);
        return VIEW_OWNERS_LIST;
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable Long ownerId) {
        ModelAndView mav = new ModelAndView(VIEW_OWNER_DETAILS);

        mav.addObject(this.ownerService.findById(ownerId));

        return mav;
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {

        model.addAttribute("owner", Owner.builder().build());

        return VIEW_CREATE_OR_UPDATE_OWNER_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(@Validated Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return VIEW_CREATE_OR_UPDATE_OWNER_FORM;
        }

        Owner savedOwner = this.ownerService.save(owner);
        return VIEW_REDIRECT_TO_OWNER_ID + savedOwner.getId();
    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model) {

        model.addAttribute("owner", this.ownerService.findById(ownerId));

        return VIEW_CREATE_OR_UPDATE_OWNER_FORM;
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(@Validated Owner owner, BindingResult result,
            @PathVariable Long ownerId) {

        if (result.hasErrors()) {
            return VIEW_CREATE_OR_UPDATE_OWNER_FORM;
        }

        owner.setId(ownerId);
        Owner savedOwner = this.ownerService.save(owner);

        return VIEW_REDIRECT_TO_OWNER_ID + savedOwner.getId();
    }
}
