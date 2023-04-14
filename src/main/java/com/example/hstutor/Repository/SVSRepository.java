package com.example.hstutor.Repository;

import com.example.hstutor.Model.SVS;
import com.example.hstutor.Model.SVSPK;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SVSRepository extends CrudRepository<SVS, Integer> {
    List<SVS> findAllByPk_Shipment_Id(Integer id);
    boolean existsByPk(SVSPK svspk);
}
