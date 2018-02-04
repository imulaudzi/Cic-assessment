package com.eoh.communication.Cic.service;


import com.eoh.communication.Cic.generated.entity.CreateEntityResponse;
import com.eoh.communication.Cic.generated.entity.EntityRequest;
import com.eoh.communication.Cic.generated.entity.EntityResponse;

public interface CicEntityService {

    CreateEntityResponse createEntity(EntityRequest entityRequest);

    EntityResponse getAllEntities();

}
