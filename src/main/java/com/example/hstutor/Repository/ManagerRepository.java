package com.example.hstutor.Repository;

import com.example.hstutor.Model.Employee;
import com.example.hstutor.Model.Manager;
import org.springframework.data.repository.CrudRepository;

public interface ManagerRepository extends CrudRepository<Manager, Integer> {
    Manager findManagerById(Integer id);
    boolean existsManagerByEmployee(Employee employee);
}
