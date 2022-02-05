package com.example.petclinic.services;

import java.util.Set;

import com.example.petclinic.model.Pet;

public interface PetService {
    Pet findById(Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();
}
