package com.mongo.service;

import com.mongodb.client.*;
import com.mongodb.model.Transport;
import com.mongodb.service.MongoServiceImpl;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class MongoServiceImplTest {
    @Mock
    private MongoClient mongoClient;

    @Mock
    private MongoDatabase database;

    @Mock
    private MongoCollection<Document> collection;

    @Mock
    private FindIterable<Document> Iterables;

    @InjectMocks
    private MongoServiceImpl mongoService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllTranspostList() {
        // Arrange
        List<Transport> expectedList = new ArrayList<>();
        Transport transport1 = new Transport("Name1", "Address1", "Dept1", "Type1");
        Transport transport2 = new Transport("Name2", "Address2", "Dept2", "Type2");
        expectedList.add(transport1);
        expectedList.add(transport2);

        Document document1 = new Document()
                .append("name", "Name1")
                .append("address", "Address1")
                .append("dept", "Dept1")
                .append("type", "Type1");
        Document document2 = new Document()
                .append("name", "Name2")
                .append("address", "Address2")
                .append("dept", "Dept2")
                .append("type", "Type2");

        when(mongoClient.getDatabase("transport")).thenReturn(database);
        when(database.getCollection("list")).thenReturn(collection);
        when(collection.find()).thenReturn(Iterables);

        MongoCursor<Document> cursor = mock(MongoCursor.class);
        when(Iterables.iterator()).thenReturn(cursor);
        when(cursor.hasNext()).thenReturn(true, true, false);
        when(cursor.next()).thenReturn(document1, document2);

        // Act
        List<Transport> actualList = mongoService.getAllTranspostList();

        // Assert
        assertEquals(expectedList.size(), actualList.size());
        assertEquals(expectedList.get(0).getName(), actualList.get(0).getName());
        assertEquals(expectedList.get(1).getName(), actualList.get(1).getName());
    }

    @Test
    public void testGetListByTransport() {
        List<Transport> expectedList = new ArrayList<>();
        Transport transport1 = new Transport("Name1", "Address1", "Dept1", "Type1");
        expectedList.add(transport1);

        Document document1 = new Document()
                .append("name", "Name1")
                .append("address", "Address1")
                .append("dept", "Dept1")
                .append("type", "Type1");
        Document document2 = new Document()
                .append("name", "Name2")
                .append("address", "Address2")
                .append("dept", "Dept2")
                .append("type", "Type2");

        when(mongoClient.getDatabase("transport")).thenReturn(database);
        when(database.getCollection("list")).thenReturn(collection);
        when(collection.find(any(Document.class))).thenReturn(Iterables);

        MongoCursor<Document> cursor = mock(MongoCursor.class);
        when(Iterables.iterator()).thenReturn(cursor);
        when(cursor.hasNext()).thenReturn(true, false);
        when(cursor.next()).thenReturn(document1);

        List<Transport> actualList = mongoService.getListByTransport("Type1");

        assertEquals(expectedList.size(), actualList.size());
        assertEquals(expectedList.get(0).getName(), actualList.get(0).getName());
    }
}