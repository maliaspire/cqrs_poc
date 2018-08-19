package com.mxninja.example.cqrs_pos.mobile_store.controllers;

import com.mxninja.example.cqrs_pos.mobile_store.domains.Brand;
import com.mxninja.example.cqrs_pos.mobile_store.domains.Mobile;
import com.mxninja.example.cqrs_pos.mobile_store.dto.MobileAddDto;
import com.mxninja.example.cqrs_pos.mobile_store.repositories.BrandDao;
import com.mxninja.example.cqrs_pos.mobile_store.repositories.MobileDao;
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

import java.util.Optional;
import java.util.UUID;

/**
 * 8/19/2018
 *
 * @author Mohammad Ali
 */

@RestController
@RequestMapping(value = "mobile", consumes = MediaType.APPLICATION_JSON_VALUE)
public class MobileCommandController {

    private final MobileDao MOBILE_DAO;
    private final BrandDao BRAND_DAO;

    @Autowired
    public MobileCommandController(MobileDao mobileDao, BrandDao brandDao) {
        MOBILE_DAO = mobileDao;
        BRAND_DAO = brandDao;
    }

    @PostMapping()
    public ResponseEntity<Mono<Boolean>> addMobile(@RequestBody MobileAddDto body) {
        if (body.getBrandId() == null
                || StringUtils.isEmpty(body.getName())
                || StringUtils.isEmpty(body.getSerialNumber())
                || body.getPrice() == null || body.getPrice() < 1) {
            return new ResponseEntity<>(Mono.just(false), HttpStatus.BAD_REQUEST);
        }

        Optional<Brand> optionalBrand = BRAND_DAO.findById(body.getBrandId());
        if (!optionalBrand.isPresent()) {
            return new ResponseEntity<>(Mono.just(false), HttpStatus.BAD_REQUEST);
        }

        Mobile mobile = new Mobile();
        mobile.setId(UUID.randomUUID());
        mobile.setBrand(optionalBrand.get());
        mobile.setName(body.getName());
        mobile.setPrice(body.getPrice());
        mobile.setSerialNumber(body.getSerialNumber());
        MOBILE_DAO.save(mobile);
        return new ResponseEntity<>(Mono.just(true), HttpStatus.CREATED);
    }

}
