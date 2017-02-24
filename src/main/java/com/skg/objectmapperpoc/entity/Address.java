package com.skg.objectmapperpoc.entity;

import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.UDT;

@UDT(keyspace = "objmapperpoc", name = "address")
class Address {
    private String street;
    @Field(name = "zip_code")
    private int zipCode;

}