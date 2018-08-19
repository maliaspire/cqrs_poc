package com.mxninja.example.cqrs_pos.mobile_store.domains;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;

/**
 * 8/19/2018
 *
 * @author Mohammad Ali
 */

@Data
public class Brand {

    @Id
    private UUID id;

    private String name;

}
