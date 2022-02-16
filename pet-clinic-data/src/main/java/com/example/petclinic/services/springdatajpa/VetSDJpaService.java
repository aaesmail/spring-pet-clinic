package com.example.petclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import com.example.petclinic.model.Vet;
import com.example.petclinic.repositories.VetRepository;
import com.example.petclinic.services.VetService;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("springdatajpa")
@Service
public class VetSDJpaService implements VetService {

    private final VetRepository vetRepository;

    public VetSDJpaService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public Set<Vet> findAll() {
        Set<Vet> vets = new HashSet<>();

        this.vetRepository.findAll().forEach(vets::add);

        return vets;
    }

    @Override
    public Vet findById(Long id) {
        return this.vetRepository.findById(id).orElse(null);
    }

    @Override
    public Vet save(Vet vet) {
        return this.vetRepository.save(vet);
    }

    @Override
    public void delete(Vet vet) {
        this.vetRepository.delete(vet);
    }

    @Override
    public void deleteById(Long id) {
        this.vetRepository.deleteById(id);
    }
}
