package com.example.petclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import com.example.petclinic.model.Speciality;
import com.example.petclinic.repositories.SpecialityRepository;
import com.example.petclinic.services.SpecialityService;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("springdatajpa")
@Service
public class SpecialitySDJpaService implements SpecialityService {

    private final SpecialityRepository specialityRepository;

    public SpecialitySDJpaService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> specialities = new HashSet<>();

        this.specialityRepository.findAll().forEach(specialities::add);

        return specialities;
    }

    @Override
    public Speciality findById(Long id) {
        return this.specialityRepository.findById(id).orElse(null);
    }

    @Override
    public Speciality save(Speciality speciality) {
        return this.specialityRepository.save(speciality);
    }

    @Override
    public void delete(Speciality speciality) {
        this.specialityRepository.delete(speciality);
    }

    @Override
    public void deleteById(Long id) {
        this.specialityRepository.deleteById(id);
    }
}
