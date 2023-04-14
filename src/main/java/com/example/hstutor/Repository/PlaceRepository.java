package com.example.hstutor.Repository;

import com.example.hstutor.Model.Place;
import org.springframework.data.repository.CrudRepository;

public interface PlaceRepository extends CrudRepository<Place, Integer> {
    Place findPlaceById(Integer id);
    boolean existsById(Integer id);
    boolean existsByAddress(String address);
}
