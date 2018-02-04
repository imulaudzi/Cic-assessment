package com.eoh.communication.Cic.repository;

import com.eoh.communication.Cic.repository.model.Cic;
import com.eoh.communication.Cic.repository.model.CicEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CicRepository extends CrudRepository<Cic,Long>{

    @Query("SELECT c from Cic c where c.entity = :entityId")
    List<Cic> findByEntityId(@Param("entityId")CicEntity entityId);
}
