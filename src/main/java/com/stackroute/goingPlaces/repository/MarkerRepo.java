package com.stackroute.goingPlaces.repository;

//import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.goingPlaces.domain.Markers;
@Repository
public interface MarkerRepo extends CrudRepository<Markers,Integer>{
	
}
