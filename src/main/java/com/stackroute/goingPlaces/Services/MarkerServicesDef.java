package com.stackroute.goingPlaces.Services;

import java.util.List;

import com.stackroute.goingPlaces.domain.Markers;
import com.stackroute.goingPlaces.repository.MarkerRepo;

public interface MarkerServicesDef {
	public String AddnewMarkers(Markers marker);
	public String deleteMarker(int id);
	public List<Markers> getAllMarkers();
	public void setMarkerdao(MarkerRepo markerdao) ;
}
