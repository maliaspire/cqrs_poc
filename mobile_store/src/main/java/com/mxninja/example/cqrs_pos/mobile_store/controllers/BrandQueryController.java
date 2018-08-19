package com.mxninja.example.cqrs_pos.mobile_store.controllers;

import com.mxninja.example.cqrs_pos.mobile_store.domains.Brand;
import com.mxninja.example.cqrs_pos.mobile_store.repositories.BrandDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

/**
 * 8/19/2018
 *
 * @author Mohammad Ali
 */

@RestController
@RequestMapping(value = "brand", produces = MediaType.APPLICATION_JSON_VALUE)
public class BrandQueryController {

    private final BrandDao BRAND_DAO;

    @Autowired
    public BrandQueryController(BrandDao brandDao) {
        BRAND_DAO = brandDao;
    }

    @GetMapping
    public ResponseEntity<Mono<List<Brand>>> getAll() {
        return new ResponseEntity<>(Mono.just(BRAND_DAO.findAll()), HttpStatus.ACCEPTED);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Mono<Brand>> getById(@PathVariable("id") UUID id) {
        return BRAND_DAO.findById(id).map(
                brand -> new ResponseEntity<>(Mono.just(brand), HttpStatus.ACCEPTED))
                .orElseGet(() -> new ResponseEntity<>(Mono.empty(), HttpStatus.ACCEPTED));
    }

}
