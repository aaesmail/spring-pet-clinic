package com.example.petclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.petclinic.model.Owner;
import com.example.petclinic.repositories.OwnerRepository;
import com.example.petclinic.services.OwnerService;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("springdatajpa")
@Service
public class OwnerSDJpaService implements OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerSDJpaService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> owners = new HashSet<>();

        this.ownerRepository.findAll().forEach(owners::add);

        return owners;
    }

    @Override
    public Owner findById(Long id) {
        return this.ownerRepository.findById(id).orElse(null);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return this.ownerRepository.findByLastName(lastName);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        return this.ownerRepository.findAllByLastNameLike(lastName);
    }

    @Override
    public Owner save(Owner owner) {
        return this.ownerRepository.save(owner);
    }

    @Override
    public void delete(Owner owner) {
        this.ownerRepository.delete(owner);
    }

    @Override
    public void deleteById(Long id) {
        this.ownerRepository.deleteById(id);
    }
}
