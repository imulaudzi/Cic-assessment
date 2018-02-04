package com.eoh.communication.Cic.service.impl;

import com.eoh.communication.Cic.Utils.EmailValidator;
import com.eoh.communication.Cic.generated.GenericResponse;
import com.eoh.communication.Cic.generated.entity.CreateEntityResponse;
import com.eoh.communication.Cic.generated.entity.EntityObject;
import com.eoh.communication.Cic.generated.entity.EntityRequest;
import com.eoh.communication.Cic.generated.entity.EntityResponse;
import com.eoh.communication.Cic.repository.CicEntityRepository;
import com.eoh.communication.Cic.repository.model.CicEntity;
import com.eoh.communication.Cic.service.CicEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CicEntityServiceImpl implements CicEntityService {

    @Autowired
    private CicEntityRepository cicEntityRepository;

    @Override
    public CreateEntityResponse createEntity(EntityRequest entityRequest) {
        CreateEntityResponse genericResponse = new CreateEntityResponse();
        EmailValidator emailValidator = new EmailValidator();
        if (emailValidator.validateEmail(entityRequest.getEmailAddress())) {
            CicEntity cicEntity = new CicEntity();
            cicEntity.setEntityName(entityRequest.getEntityName());
            cicEntity.setEmailAddress(entityRequest.getEmailAddress());

            CicEntity entityFromDB = cicEntityRepository.save(cicEntity);
            if (entityFromDB != null) {
                genericResponse.setEmailAddress(entityFromDB.getEmailAddress());
                genericResponse.setEntityId(entityFromDB.getEntityId());
                genericResponse.setResponseCode("200");
                genericResponse.setReason("Success");
            } else {
                genericResponse.setResponseCode("997");
                genericResponse.setReason("Failed to create Entity");
            }
        } else {

            genericResponse.setResponseCode("998");
            genericResponse.setReason("Email address is invalid");
        }
        return genericResponse;
    }

    @Override
    public EntityResponse getAllEntities() {
        EntityResponse entityResponse = new EntityResponse();
        List<EntityObject> entityList = new ArrayList<>();

        List<CicEntity> cicEntities = cicEntityRepository.findAllEntities();
        if (cicEntities.size() > 0) {
            for (CicEntity cicEntity : cicEntities) {
                EntityObject entity = new EntityObject();
                entity.setEntityId(cicEntity.getEntityId());
                entity.setEntityName(cicEntity.getEntityName());
                entity.setEmailAddress(cicEntity.getEmailAddress());

                entityList.add(entity);
            }
            entityResponse.setEntityObject(entityList);
        } else {
            entityResponse.setResponseCode(996);
            entityResponse.setReason("No Entities in the Database");
        }
        return entityResponse;
    }
}
