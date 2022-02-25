package com.example.petclinic.formatters;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import com.example.petclinic.model.PetType;
import com.example.petclinic.services.PetTypeService;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        Collection<PetType> findPetTypes = this.petTypeService.findAll();

        for (PetType petType : findPetTypes) {
            if (petType.getName().equals(text)) {
                return petType;
            }
        }

        throw new ParseException("Pet Type not found: " + text, 0);
    }
}
