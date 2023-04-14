package com.example.hstutor.Repository;

import com.example.hstutor.Model.Vehicle;
import org.springframework.data.repository.CrudRepository;

public interface VehicleRepository extends CrudRepository<Vehicle, Integer> {
    Vehicle findVehicleById(Integer id);
}
