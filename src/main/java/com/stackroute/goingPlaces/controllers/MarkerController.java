package com.stackroute.goingPlaces.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.goingPlaces.Services.MarkerServicesDef;
import com.stackroute.goingPlaces.domain.*;

@RestController
@RequestMapping("/v1.0/goingPlaces/markers")
public class MarkerController {
	
	public boolean ValidateRequestParams(Double lat,Double lng,String name) {
		if(lat !=null && lng !=null && name!=null) {
			return true;
		}
		else return false;
	}
	
	@Autowired
	MarkerServicesDef markerservice;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> addMarker(@RequestBody Markers marker) {
		String s= "SERVER NOT RESPONDING";
		if(ValidateRequestParams(marker.getLatitude(),marker.getLongitude(),marker.getName())==false) {
			s="Invalid request parameters";
			return new ResponseEntity<String>(s, HttpStatus.CONFLICT);
		}
		try {
			s=markerservice.AddnewMarkers(marker);
			return new ResponseEntity<String>(s, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(s, HttpStatus.CONFLICT);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity getAllMarkers() {
		List<Markers> markerlist = new ArrayList<>() ;
		try {
			markerlist=markerservice.getAllMarkers();
		}catch (Exception e) {
			
		}
		return new ResponseEntity<List<Markers>>(markerlist, HttpStatus.OK) ;
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteMarker(@RequestParam(value="id") int id) {
		String s= "SERVER NOT RESPONDING";
		try {
			s=markerservice.deleteMarker(id);
			return new ResponseEntity<String>(s, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(s, HttpStatus.CONFLICT);
	}
}
