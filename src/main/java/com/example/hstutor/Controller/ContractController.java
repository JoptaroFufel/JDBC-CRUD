package com.example.hstutor.Controller;

import com.example.hstutor.Model.*;
import com.example.hstutor.Repository.ContractRepository;
import com.example.hstutor.Repository.EmployeeRepository;
import com.example.hstutor.Repository.HirerRepository;
import com.example.hstutor.Repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/contracts")
public class ContractController {
    private final ContractRepository contractRepository;
    private final HirerRepository hirerRepository;
    private final ManagerRepository managerRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public ContractController(ContractRepository contractRepository,
                              HirerRepository hirerRepository,
                              ManagerRepository managerRepository,
                              EmployeeRepository employeeRepository
    ) {
        this.contractRepository = contractRepository;
        this.hirerRepository = hirerRepository;
        this.managerRepository = managerRepository;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("list/all")
    public Iterable<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    @GetMapping("list/{id}")
    public Contract getContractsById(@PathVariable Integer id) {
        return contractRepository.findContractById(id);
    }

    @GetMapping("list/hirers")
    public Iterable<Hirer> getAllHirers() {
        return hirerRepository.findAll();
    }

    @PostMapping("add/hirer")
    public ResponseEntity<?> addHirer(@RequestBody Hirer hirer) {
        hirerRepository.save(hirer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("add/contract")
    public ResponseEntity<?> addContract(@RequestBody Contract contract) {
        Hirer hirer = hirerRepository.findById(contract.getHirer().getId()).orElseThrow();
        Manager manager = managerRepository.findById(contract.getManager().getId()).orElseThrow();
        contract.setManager(manager);
        contract.setHirer(hirer);
        contractRepository.save(contract);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
