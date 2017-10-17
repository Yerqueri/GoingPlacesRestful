package com.stackroute.goingPlaces.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.security.Timestamp;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.goingPlaces.GoingPlacesRestfulApplication;
import com.stackroute.goingPlaces.domain.Markers;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GoingPlacesRestfulApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MarkerControllerTest {
	
	@LocalServerPort
    private int port;
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	
	HttpHeaders headers = new HttpHeaders();
	
	Markers markers;
	Markers markers1;
	
	@Before
    public void setUp() throws Exception {
         markers = new Markers(123.123,321.321,"kormangla");
         markers1 = new Markers(null,321.321,"kormangla");
    }
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port +"/v1.0/goingPlaces"+ uri;
    }
    
    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void testaddMarker() throws Exception {
        HttpEntity<Markers> entity = new HttpEntity<Markers>(markers, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/markers"),
                HttpMethod.POST, entity, String.class);
        assertNotNull(response);
        String actual = response.getBody();
        System.out.println(actual);
        assertEquals("success",actual);
    }
    
    @Test
    public void testaddMarker2() throws Exception {
        HttpEntity<Markers> entity = new HttpEntity<Markers>(markers1, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/markers"),
                HttpMethod.POST, entity, String.class);
        assertNotNull(response);
        String actual = response.getBody();
        System.out.println(actual);
        assertEquals("Invalid request",actual);
    }
    
    @Test
    public void testGetMarkerList() throws Exception {
        HttpEntity<Markers> entity = new HttpEntity<Markers>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/markers"),
                HttpMethod.POST, entity, String.class);
        response = restTemplate.exchange(
                createURLWithPort("/markers"),
                HttpMethod.POST, entity, String.class);
        System.out.println(response);
        response = restTemplate.exchange(
                createURLWithPort("/markers"),
                HttpMethod.POST, entity, String.class);
        response = restTemplate.exchange(
                createURLWithPort("/markers"),
                HttpMethod.POST, entity, String.class);
        response = restTemplate.exchange(
                createURLWithPort("/markers"),
                HttpMethod.GET, entity, String.class);
        assertNotNull(response);
        String actual = response.getBody();
        System.out.println(actual);
        assertEquals(true, actual.contains("latitude\":123.123,\"longitude\":321.321,\"name\":\"ayush"));
    }
    
    @Test
    public void testDeleteMarker() throws Exception {
        HttpEntity<Markers> entity = new HttpEntity<Markers>(null,headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/markers"),
                HttpMethod.POST, entity, String.class);
        response = restTemplate.exchange(
                createURLWithPort("/markers?name=Ayush"),
                HttpMethod.DELETE, entity, String.class);
        response = restTemplate.exchange(
                createURLWithPort("/markers"),
                HttpMethod.GET, entity, String.class);
        assertNotNull(response);
        String actual = response.getBody();
        System.out.println(actual);
        assertEquals("[]",actual);
    }
    
    
}
