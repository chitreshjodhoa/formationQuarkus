package com.mongodb.service;

import com.mongodb.model.Transport;

import java.util.List;

public interface MongoService {
    public List<Transport> getAllTranspostList();

    public List<Transport> getListByTransport(String transport);
}
