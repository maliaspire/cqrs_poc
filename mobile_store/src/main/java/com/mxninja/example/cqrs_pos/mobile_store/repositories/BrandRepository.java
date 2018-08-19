package com.mxninja.example.cqrs_pos.mobile_store.repositories;

import com.mxninja.example.cqrs_pos.mobile_store.domains.Brand;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * 8/19/2018
 *
 * @author Mohammad Ali
 */

@Repository
interface BrandRepository extends MongoRepository<Brand, UUID> {

    List<Brand> findAllByNameContainingIgnoreCase(String name);

}
