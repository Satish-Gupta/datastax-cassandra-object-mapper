package com.skg.objectmapperpoc.entity;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import java.util.UUID;

@Table(keyspace = "objmapperpoc", name = "users",
//       readConsistency = "QUORUM",
//       writeConsistency = "QUORUM",
       caseSensitiveKeyspace = false,
       caseSensitiveTable = false)
public class User {
    @PartitionKey
    @Column(name = "id")
    private UUID id;
    private String name;

    public UUID getId() {
        return id;
    }

    public void setId(UUID userId) {
        this.id = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  "User Details---\nId: " + id +
                "\nFirstName: " + name;
    }
}