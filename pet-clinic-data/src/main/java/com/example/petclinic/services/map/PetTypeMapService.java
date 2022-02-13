package com.example.petclinic.services.map;

import java.util.Set;

import com.example.petclinic.model.PetType;
import com.example.petclinic.services.PetTypeService;

import org.springframework.stereotype.Service;

@Service
public class PetTypeMapService extends AbstractMapService<PetType> implements PetTypeService {

    @Override
    public Set<PetType> findAll() {
        return super.findAll();
    }

    @Override
    public PetType findById(Long id) {
        return super.findById(id);
    }

    @Override
    public PetType save(PetType petType) {
        return super.save(petType);
    }

    @Override
    public void delete(PetType petType) {
        super.delete(petType);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
