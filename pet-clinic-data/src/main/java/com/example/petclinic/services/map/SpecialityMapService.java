package com.example.petclinic.services.map;

import java.util.Set;

import com.example.petclinic.model.Speciality;
import com.example.petclinic.services.SpecialityService;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile({ "default", "map" })
@Service
public class SpecialityMapService extends AbstractMapService<Speciality> implements SpecialityService {

    @Override
    public Set<Speciality> findAll() {
        return super.findAll();
    }

    @Override
    public Speciality findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Speciality save(Speciality speciality) {
        return super.save(speciality);
    }

    @Override
    public void delete(Speciality speciality) {
        super.delete(speciality);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
