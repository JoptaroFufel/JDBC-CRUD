package com.example.hstutor.Repository;

import com.example.hstutor.Model.Employee;
import com.example.hstutor.Model.Shipper;
import org.springframework.data.repository.CrudRepository;

public interface ShipperRepository extends CrudRepository<Shipper, Integer> {
    Shipper findShipperById(Integer id);
    boolean existsShipperByEmployee(Employee employee);
}
