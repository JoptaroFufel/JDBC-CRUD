package com.example.hstutor.Controller;

import com.example.hstutor.Model.Employee;
import com.example.hstutor.Model.Manager;
import com.example.hstutor.Model.Shipper;
import com.example.hstutor.Repository.EmployeeRepository;
import com.example.hstutor.Repository.ManagerRepository;
import com.example.hstutor.Repository.ShipperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final ManagerRepository managerRepository;
    private final ShipperRepository shipperRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository, ManagerRepository managerRepository, ShipperRepository shipperRepository) {
        this.employeeRepository = employeeRepository;
        this.managerRepository = managerRepository;
        this.shipperRepository = shipperRepository;
    }

    @GetMapping("list/all")
    public Iterable<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("list/managers")
    public List<Employee> getAllManagers() {
        List<Manager> managers = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        managerRepository.findAll().forEach(managers::add);
        for (Manager m:
                managers) {
            employees.add(employeeRepository.findEmployeeById(m.getEmployee().getId()));
        }
        return employees;
    }

    @GetMapping("list/shippers")
    public List<Employee> getAllShippers() {
        List<Shipper> shippers = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        shipperRepository.findAll().forEach(shippers::add);
        for (Shipper s:
             shippers) {
            employees.add(employeeRepository.findEmployeeById(s.getEmployee().getId()));
        }
        return employees;
    }

    @PostMapping("add/employee")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
        employeeRepository.save(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("add/manager")
    public ResponseEntity<?> addManager(@RequestBody Integer id) {
        Employee employee = employeeRepository.findEmployeeById(id);
        if (employee == null) {
            return new ResponseEntity<>("There is no employee with this ID", HttpStatus.CONFLICT);
        }
        if (managerRepository.existsManagerByEmployee(employee)) {
            return new ResponseEntity<>("Already exists", HttpStatus.CONFLICT);
        }
        Manager manager = new Manager();
        manager.setEmployee(employee);
        managerRepository.save(manager);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("add/shipper")
    public ResponseEntity<?> addShipper(@RequestBody Integer id) {
        Employee employee = employeeRepository.findEmployeeById(id);
        if (employee == null) {
            return new ResponseEntity<>("There is no employee with this ID", HttpStatus.CONFLICT);
        }
        if (shipperRepository.existsShipperByEmployee(employee)) {
            return new ResponseEntity<>("Already exists", HttpStatus.CONFLICT);
        }
        Shipper shipper = new Shipper();
        shipper.setEmployee(employee);
        shipperRepository.save(shipper);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
