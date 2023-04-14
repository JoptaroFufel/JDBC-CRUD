package com.example.hstutor.Repository;

import com.example.hstutor.Model.Hirer;
import org.springframework.data.repository.CrudRepository;

public interface HirerRepository extends CrudRepository<Hirer, Integer> {
    Hirer findHirerById(Integer id);
    boolean existsById(Integer id);
}
