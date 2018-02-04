package com.eoh.communication.Cic.repository;

import com.eoh.communication.Cic.repository.model.CicEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CicEntityRepository extends CrudRepository<CicEntity,Long>{

  @Query("SELECT c from CicEntity c where c.emailAddress = :emailAddress")
  CicEntity findByEmailAddress(@Param("emailAddress")String emailAddress);

  @Query("SELECT c from CicEntity c")
  List<CicEntity> findAllEntities();
}