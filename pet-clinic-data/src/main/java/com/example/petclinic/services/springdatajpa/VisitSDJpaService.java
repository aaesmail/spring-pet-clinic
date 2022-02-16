package com.example.petclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import com.example.petclinic.model.Visit;
import com.example.petclinic.repositories.VisitRepository;
import com.example.petclinic.services.VisitService;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("springdatajpa")
@Service
public class VisitSDJpaService implements VisitService {

    private final VisitRepository visitRepository;

    public VisitSDJpaService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public Set<Visit> findAll() {
        Set<Visit> visits = new HashSet<>();

        this.visitRepository.findAll().forEach(visits::add);

        return visits;
    }

    @Override
    public Visit findById(Long id) {
        return this.visitRepository.findById(id).orElse(null);
    }

    @Override
    public Visit save(Visit visit) {
        return this.visitRepository.save(visit);
    }

    @Override
    public void delete(Visit visit) {
        this.visitRepository.delete(visit);
    }

    @Override
    public void deleteById(Long id) {
        this.visitRepository.deleteById(id);
    }
}
