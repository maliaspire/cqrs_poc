package com.mxninja.example.cqrs_pos.mobile_store.controllers;

import com.mxninja.example.cqrs_pos.mobile_store.domains.Brand;
import com.mxninja.example.cqrs_pos.mobile_store.domains.Mobile;
import com.mxninja.example.cqrs_pos.mobile_store.repositories.BrandDao;
import com.mxninja.example.cqrs_pos.mobile_store.repositories.MobileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * 8/19/2018
 *
 * @author Mohammad Ali
 */

@RestController
@RequestMapping(value = "mobile", produces = MediaType.APPLICATION_JSON_VALUE)
public class MobileQueryController {

    private final MobileDao MOBILE_DAO;
    private final BrandDao BRAND_DAO;

    @Autowired
    public MobileQueryController(MobileDao mobileDao, BrandDao brandDao) {
        MOBILE_DAO = mobileDao;
        BRAND_DAO = brandDao;
    }

    @GetMapping
    public ResponseEntity<Mono<List<Mobile>>> getAll() {
        return new ResponseEntity<>(Mono.just(MOBILE_DAO.findAll()), HttpStatus.ACCEPTED);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Mono<Mobile>> getById(@PathVariable("id") UUID id) {
        return MOBILE_DAO.findById(id).map(
                mobile -> new ResponseEntity<>(Mono.just(mobile), HttpStatus.ACCEPTED))
                .orElseGet(() -> new ResponseEntity<>(Mono.empty(), HttpStatus.ACCEPTED));
    }

    @GetMapping("brandId/{brandId}")
    public ResponseEntity<Mono<List<Mobile>>> getByBrandId(@PathVariable("brandId") UUID brandId) {
        Optional<Brand> optionalBrand = BRAND_DAO.findById(brandId);
        if (!optionalBrand.isPresent()){
            return new ResponseEntity<>(Mono.empty(), HttpStatus.ACCEPTED);
        }
        List<Mobile> list = MOBILE_DAO.findAllByBrand(optionalBrand.get());
        if (list.size() == 0){
            return new ResponseEntity<>(Mono.empty(), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(Mono.just(list), HttpStatus.ACCEPTED);
    }

}
