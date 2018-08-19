package com.mxninja.example.cqrs_pos.mobile_store.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * 8/15/2018
 *
 * @author Mohammad Ali
 * <p>
 * Entity class to handle mongoDb Properties
 */
@Data
@Validated
@ConfigurationProperties("cqrs.pos.service.mobile.mongodb")
@Component
public class MongoDbPropertiesConfigLoader {

    private String host;

    private String database;


}






