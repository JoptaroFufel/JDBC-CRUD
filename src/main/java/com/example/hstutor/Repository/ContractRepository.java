package com.example.hstutor.Repository;

import com.example.hstutor.Model.Contract;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContractRepository extends CrudRepository<Contract, Integer> {
    Contract findContractById(Integer id);
    List<Contract> findContractsBy();
}
