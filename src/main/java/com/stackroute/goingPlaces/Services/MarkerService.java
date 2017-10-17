package com.stackroute.goingPlaces.Services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.goingPlaces.domain.Markers;
import com.stackroute.goingPlaces.repository.MarkerRepo;

@Service
public class MarkerService implements MarkerServicesDef{
	
	@Autowired
	MarkerRepo markerdao;
	
	public MarkerRepo getMarkerdao() {
		return markerdao;
	}

	public void setMarkerdao(MarkerRepo markerdao) {
		this.markerdao = markerdao;
	}

	public String AddnewMarkers(Markers marker) {
		System.out.println(marker.getLatitude());
		System.out.println(marker.getLongitude());
		System.out.println(marker.getName());
		markerdao.save(marker);
		return "success";
	}
	
	public String deleteMarker(int id){
		System.out.println(id + " sucessfully deleted");
		markerdao.delete(id);
		return "Success";
	}

	@Override
	public List<Markers> getAllMarkers() {
		List<Markers> data= new ArrayList<Markers>();
		data.addAll( (Collection<? extends Markers>) markerdao.findAll());
		return data;
	}

}
