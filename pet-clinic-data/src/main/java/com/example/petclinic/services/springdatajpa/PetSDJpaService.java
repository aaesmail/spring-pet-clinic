package com.example.petclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import com.example.petclinic.model.Pet;
import com.example.petclinic.repositories.PetRepository;
import com.example.petclinic.services.PetService;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("springdatajpa")
@Service
public class PetSDJpaService implements PetService {

    private final PetRepository petRepository;

    public PetSDJpaService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Set<Pet> findAll() {
        Set<Pet> pets = new HashSet<>();

        this.petRepository.findAll().forEach(pets::add);

        return pets;
    }

    @Override
    public Pet findById(Long id) {
        return this.petRepository.findById(id).orElse(null);
    }

    @Override
    public Pet save(Pet pet) {
        return this.petRepository.save(pet);
    }

    @Override
    public void delete(Pet pet) {
        this.petRepository.delete(pet);
    }

    @Override
    public void deleteById(Long id) {
        this.petRepository.deleteById(id);
    }
}
