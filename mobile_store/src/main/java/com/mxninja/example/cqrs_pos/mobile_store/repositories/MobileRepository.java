package com.mxninja.example.cqrs_pos.mobile_store.repositories;

import com.mxninja.example.cqrs_pos.mobile_store.domains.Brand;
import com.mxninja.example.cqrs_pos.mobile_store.domains.Mobile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

/**
 * 8/19/2018
 *
 * @author Mohammad Ali
 */
interface MobileRepository extends MongoRepository<Mobile, UUID> {

    List<Mobile> findAllByBrand(Brand brand);



}
