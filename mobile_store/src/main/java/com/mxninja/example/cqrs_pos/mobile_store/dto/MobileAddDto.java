package com.mxninja.example.cqrs_pos.mobile_store.dto;

import lombok.Data;

import java.util.UUID;

/**
 * 8/19/2018
 *
 * @author Mohammad Ali
 */

@Data
public class MobileAddDto {

    private UUID brandId;
    private String name;
    private String serialNumber;
    private Double price;

}
