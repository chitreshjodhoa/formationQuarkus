package com.mongodb.repo;

import com.mongodb.model.Transport;
import com.mongodb.service.MongoService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class MongoResourceTest {
    @Mock
    private MongoService mongoService;

    @InjectMocks
    private MongoResource mongoResource;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetMyDataWithValidTransport() {
        List<Transport> expectedList = new ArrayList<>();
        Transport transport1 = new Transport("Name1", "Address1", "Dept1", "Perso");
        Transport transport2 = new Transport("Name2", "Address2", "Dept2", "Perso");
        expectedList.add(transport1);
        expectedList.add(transport2);

        when(mongoService.getListByTransport("Perso")).thenReturn(expectedList);
        List<Transport> actualList = mongoResource.getMyData("Perso");
        assertEquals(expectedList.size(), actualList.size());
        assertEquals(expectedList.get(0).getName(), actualList.get(0).getName());
        assertEquals(expectedList.get(1).getName(), actualList.get(1).getName());
    }

    @Test
    public void testGetMyDataWithInvalidTransport() {
        // Arrange
        List<Transport> expectedList = new ArrayList<>();
        Transport transport1 = new Transport("Name1", "Address1", "Dept1", "Perso");
        Transport transport2 = new Transport("Name2", "Address2", "Dept2", "Taxi");
        expectedList.add(transport1);
        expectedList.add(transport2);

        when(mongoService.getAllTranspostList()).thenReturn(expectedList);

        // Act
        List<Transport> actualList = mongoResource.getMyData(null);

        // Assert
        assertEquals(expectedList.size(), actualList.size());
        assertEquals(expectedList.get(0).getName(), actualList.get(0).getName());
        assertEquals(expectedList.get(1).getName(), actualList.get(1).getName());
    }
}