package com.mxninja.example.cqrs_pos.mobile_store.repositories;

import com.mxninja.example.cqrs_pos.mobile_store.domains.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * 8/19/2018
 *
 * @author Mohammad Ali
 */

@Component
public class BrandDao {

    private final BrandRepository BRAND_REPOSITORY;

    @Autowired
    public BrandDao(BrandRepository brandRepository){
        BRAND_REPOSITORY = brandRepository;
    }

    public List<Brand> findAllByNameContainingIgnoreCase(String name) {
        return BRAND_REPOSITORY.findAllByNameContainingIgnoreCase(name);
    }

    public List<Brand> findAll() {
        return BRAND_REPOSITORY.findAll();
    }

    public Brand save(Brand s) {
        return BRAND_REPOSITORY.save(s);
    }

    public boolean existsById(UUID uuid) {
        return BRAND_REPOSITORY.existsById(uuid);
    }

    public void deleteById(UUID uuid) {
        BRAND_REPOSITORY.deleteById(uuid);
    }

    public Optional<Brand> findById(UUID uuid) {
        return BRAND_REPOSITORY.findById(uuid);
    }
}
