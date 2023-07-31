package com.mongodb.service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.model.Transport;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MongoServiceImpl implements MongoService {
    @Inject
    MongoClient mongoClient;
    @Override
    public List<Transport> getAllTranspostList() {
        MongoDatabase database = mongoClient.getDatabase("transport");
        MongoCollection<Document> collection = database.getCollection("list");
        List<Transport> myDataList = new ArrayList<>();
        for (Document document : collection.find()) {
            Transport myData = new Transport();
            myData.setName(document.getString("name"));
            myData.setAddress(document.getString("address"));
            myData.setDept(document.getString("dept"));
            myData.setType(document.getString("type"));
            myDataList.add(myData);
        }

        return myDataList;
    }

    @Override
    public List<Transport> getListByTransport(String transport) {
        MongoDatabase database = mongoClient.getDatabase("transport");
        MongoCollection<Document> collection = database.getCollection("list");

        Document filter = new Document("type", new Document("$regex", transport).append("$options", "i"));

        List<Transport> myDataList = new ArrayList<>();
        for (Document document : collection.find(filter)) {
            Transport myData = new Transport();
            myData.setName(document.getString("name"));
            myData.setAddress(document.getString("address"));
            myData.setDept(document.getString("dept"));
            myData.setType(document.getString("type"));
            myDataList.add(myData);
        }

        return myDataList;
    }
}
