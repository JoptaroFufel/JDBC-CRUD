package com.example.hstutor.Repository;

import com.example.hstutor.Model.Shipment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShipmentRepository extends CrudRepository<Shipment, Integer> {
    Shipment findShipmentById(Integer id);
    List<Shipment> findAllByContract_Id(Integer id);
}
