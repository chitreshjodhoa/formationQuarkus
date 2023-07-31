package com.mongodb.repo;

import com.mongodb.service.MongoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import com.mongodb.model.Transport;

import java.util.List;

@Path("/my-resources")
public class MongoResource {
    @Inject
    MongoService mongoService;

    @GET
    @Path("/get-list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Transport> getMyData(@QueryParam("transport") String transport) {
        if((transport != null && !transport.isEmpty()) && (transport.equalsIgnoreCase("Perso") || transport.equalsIgnoreCase("Taxi"))) {
            return mongoService.getListByTransport(transport);
        } else {
            return mongoService.getAllTranspostList();
        }
    }
}