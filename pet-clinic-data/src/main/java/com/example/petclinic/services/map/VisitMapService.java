package com.example.petclinic.services.map;

import java.util.Set;

import com.example.petclinic.model.Visit;
import com.example.petclinic.services.VisitService;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile({ "default", "map" })
@Service
public class VisitMapService extends AbstractMapService<Visit> implements VisitService {

    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Visit save(Visit visit) {

        if (visit.getPet() == null || visit.getPet().getOwner() == null || visit.getPet().getId() == null
                || visit.getPet().getOwner().getId() == null) {
            throw new RuntimeException("Invalid visit");
        }

        return super.save(visit);
    }

    @Override
    public void delete(Visit visit) {
        super.delete(visit);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
