package com.mxninja.example.cqrs_pos.mobile_store.repositories;

import com.mxninja.example.cqrs_pos.mobile_store.domains.Brand;
import com.mxninja.example.cqrs_pos.mobile_store.domains.Mobile;
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
public class MobileDao {

    @Autowired
    private final MobileRepository MOBILE_REPOSITORY;

    public MobileDao(MobileRepository mobileRepository){
        MOBILE_REPOSITORY = mobileRepository;
    }

    public List<Mobile> findAllByBrand(Brand brand) {
        return MOBILE_REPOSITORY.findAllByBrand(brand);
    }

    public List<Mobile> findAll() {
        return MOBILE_REPOSITORY.findAll();
    }

    public Mobile save(Mobile s) {
        return MOBILE_REPOSITORY.save(s);
    }

    public Optional<Mobile> findById(UUID uuid) {
        return MOBILE_REPOSITORY.findById(uuid);
    }

    public void deleteById(UUID uuid) {
        MOBILE_REPOSITORY.deleteById(uuid);
    }
}
