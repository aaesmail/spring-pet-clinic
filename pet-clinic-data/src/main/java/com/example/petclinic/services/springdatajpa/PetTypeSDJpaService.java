package com.example.petclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import com.example.petclinic.model.PetType;
import com.example.petclinic.repositories.PetTypeRepository;
import com.example.petclinic.services.PetTypeService;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("springdatajpa")
@Service
public class PetTypeSDJpaService implements PetTypeService {

    private final PetTypeRepository petTypeRepository;

    public PetTypeSDJpaService(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Set<PetType> findAll() {
        Set<PetType> petTypes = new HashSet<>();

        this.petTypeRepository.findAll().forEach(petTypes::add);

        return petTypes;
    }

    @Override
    public PetType findById(Long id) {
        return this.petTypeRepository.findById(id).orElse(null);
    }

    @Override
    public PetType save(PetType petType) {
        return this.petTypeRepository.save(petType);
    }

    @Override
    public void delete(PetType petType) {
        this.petTypeRepository.delete(petType);
    }

    @Override
    public void deleteById(Long id) {
        this.petTypeRepository.deleteById(id);
    }
}
