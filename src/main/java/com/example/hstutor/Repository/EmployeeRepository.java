package com.example.hstutor.Repository;

import com.example.hstutor.Model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    Employee findEmployeeById(Integer id);
}
