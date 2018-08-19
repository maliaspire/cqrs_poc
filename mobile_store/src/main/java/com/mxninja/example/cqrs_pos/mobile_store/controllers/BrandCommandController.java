package com.mxninja.example.cqrs_pos.mobile_store.controllers;

import com.mxninja.example.cqrs_pos.mobile_store.domains.Brand;
import com.mxninja.example.cqrs_pos.mobile_store.dto.BrandAddDto;
import com.mxninja.example.cqrs_pos.mobile_store.repositories.BrandDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * 8/19/2018
 *
 * @author Mohammad Ali
 */

@RestController
@RequestMapping(value = "brand", consumes = MediaType.APPLICATION_JSON_VALUE)
public class BrandCommandController {

    private final BrandDao BRAND_DAO;

    @Autowired
    public BrandCommandController(BrandDao brandDao) {
        BRAND_DAO = brandDao;
    }

    @PostMapping
    public ResponseEntity<Mono<Boolean>> addBrand(@RequestBody BrandAddDto body) {

        if (StringUtils.isEmpty(body.getName())) {
            return new ResponseEntity<>(Mono.just(false), HttpStatus.BAD_REQUEST);
        }

        Brand brand = new Brand();
        brand.setId(UUID.randomUUID());
        brand.setName(body.getName());
        BRAND_DAO.save(brand);
        return new ResponseEntity<>(Mono.just(true), HttpStatus.CREATED);
    }

}
