package com.eoh.communication.Cic.service.impl;

import com.eoh.communication.Cic.Utils.EmailValidator;
import com.eoh.communication.Cic.generated.GenericResponse;
import com.eoh.communication.Cic.generated.cic.*;
import com.eoh.communication.Cic.repository.CicEntityRepository;
import com.eoh.communication.Cic.repository.CicRepository;
import com.eoh.communication.Cic.repository.model.Cic;
import com.eoh.communication.Cic.repository.model.CicEntity;
import com.eoh.communication.Cic.service.CicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CicServiceImpl implements CicService {

    @Autowired
    private CicRepository cicRepository;
    @Autowired
    private CicEntityRepository cicEntityRepository;
    private EmailValidator emailValidator;

    @Override
    public GenericResponse createCic(CicRequest cicRequest) {
        emailValidator = new EmailValidator();
        GenericResponse genericResponse = new GenericResponse();
        if (emailValidator.validateEmail(cicRequest.getEmailAddress())) {
            CicEntity cicEntity = cicEntityRepository.findByEmailAddress(cicRequest.getEmailAddress());

            if (cicEntity != null) {
                log.debug("Found Entity with ID [{}]", cicEntity.getEntityId());
                Cic cic = new Cic();
                cic.setCicType(cicRequest.getCicType());
                cic.setSubject(cicRequest.getSubject());
                cic.setBody(cicRequest.getBody());
                cic.setSourceSystem(cicRequest.getSourceSystem());
                cic.setCicTimeStamp(LocalDateTime.now());
                cic.setEntity(cicEntity);
                cicRepository.save(cic);
                genericResponse.setResponseCode("200");
                genericResponse.setReason("Success");
            } else {

                genericResponse.setResponseCode("999");
                genericResponse.setReason("Entity Not Found. Email address does not exist");
                log.warn("Entity Not Found. Email address [{}] does not exist", cicRequest.getEmailAddress());
            }
        } else {

            genericResponse.setResponseCode("998");
            genericResponse.setReason("Email address is invalid");
            log.error("Email address [{}] is invalid", cicRequest.getEmailAddress());
        }
        return genericResponse;
    }

    @Override
    public CicResponse getCicById(Long cicId) {
        CicResponse cicResponse = new CicResponse();
        Cic cic = cicRepository.findOne(cicId);

        if (cic != null) {
            cicResponse.setEmailAddress(cic.getEntity().getEmailAddress());
            cicResponse.setCicId(cic.getCicId());
            cicResponse.setCicType(cic.getCicType());
            cicResponse.setSubject(cic.getSubject());
            cicResponse.setBody(cic.getBody());
            cicResponse.setSourceSystem(cic.getSourceSystem());
            cicResponse.setCicTimeStamp(cic.getCicTimeStamp().toString());
        } else {
            cicResponse.setResponseCode("995");
            cicResponse.setReason("Cic Not Found. Cic ID does not exist");
            log.warn("Cic Not Found. Cic ID [{}] does not exist", cicId);
        }
        return cicResponse;
    }

    @Override
    public CicListResponse getListOfCicByEmailAddress(String emailAddress) {
        emailValidator = new EmailValidator();
        CicListResponse cicResponse = new CicListResponse();
        if (emailValidator.validateEmail(emailAddress)) {
            CicEntity cicEntity = cicEntityRepository.findByEmailAddress(emailAddress);

            if (cicEntity != null) {
                List<Cic> cicListFromDB = cicRepository.findByEntityId(cicEntity);

                List<CicObject> cicListObj = new ArrayList<>();

                for (Cic cic : cicListFromDB) {
                    CicObject aCicObj = new CicObject();

                    aCicObj.setCicId(cic.getCicId());
                    aCicObj.setCicType(cic.getCicType());
                    aCicObj.setSubject(cic.getSubject());
                    aCicObj.setBody(cic.getBody());
                    aCicObj.setSourceSystem(cic.getSourceSystem());
                    aCicObj.setCicTimeStamp(cic.getCicTimeStamp().toString());
                    cicListObj.add(aCicObj);
                }
                cicResponse.setEmailAddress(cicListFromDB.get(0).getEntity().getEmailAddress());
                cicResponse.setCicObject(cicListObj);

            } else {

                cicResponse.setResponseCode("999");
                cicResponse.setReason("Entity Not Found. Email address does not exist");
                log.warn("Entity Not Found. Email address [{}] does not exist", emailAddress);
            }
        } else {

            cicResponse.setResponseCode("998");
            cicResponse.setReason("Email address is invalid");
            log.error("Email address [{}] is invalid", emailAddress);
        }

        return cicResponse;
    }
}
