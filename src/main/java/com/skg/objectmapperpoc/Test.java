package com.skg.objectmapperpoc;

import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.skg.objectmapperpoc.entity.User;

import java.util.UUID;

/**
 * Created by satish on 2/22/17.
 */
public class Test {
    public static void main(String[] args) {
        final CassandraConnector client = new CassandraConnector();
        final String ipAddress = args.length > 0 ? args[0] : "localhost";
        final int port = args.length > 1 ? Integer.parseInt(args[1]) : 9042;
        System.out.println("Connecting to IP Address " + ipAddress + ":" + port + "...");
        client.connect(ipAddress, port);

        String keyspaceName = "objmapperpoc";
        final String createKeySpaceCql = "CREATE KEYSPACE IF NOT EXISTS " + keyspaceName + " with replication = {'class':'SimpleStrategy', 'replication_factor':3};";
        client.getSession().execute(createKeySpaceCql);

        final String creteTableCql = "CREATE TABLE IF NOT EXISTS " + keyspaceName + ".users (id uuid primary key, name varchar)";
        client.getSession().execute(creteTableCql);

        MappingManager manager = new MappingManager(client.getSession());
        Mapper<User> mapper = manager.mapper(User.class);

        UUID userId = UUID.randomUUID();
        User user = new User();
        user.setId(userId);
        user.setName("satish");

        mapper.save(user);

        User foundUser = mapper.get(userId);
        System.out.println("Found User: " + foundUser);

        foundUser.setName("satish" + userId.toString().toCharArray()[0]);

        mapper.save(foundUser);

        System.out.println("Updated User: " + foundUser);

        /*mapper.delete(UUID.fromString("8da8bbf8-886f-47a8-bf5c-a0dc35c453ff"));
        User toBeDeletedUser = mapper.get(UUID.fromString("d1c15617-450f-4401-8762-34ea44122018"));
        mapper.delete(toBeDeletedUser);*/
    }
}
