package com.eoh.communication.Cic.service;

import com.eoh.communication.Cic.generated.cic.CicListResponse;
import com.eoh.communication.Cic.generated.cic.CicRequest;
import com.eoh.communication.Cic.generated.cic.CicResponse;
import com.eoh.communication.Cic.generated.GenericResponse;


public interface CicService {

    GenericResponse createCic(CicRequest cicRequest);
    CicResponse getCicById(Long cicId);
    CicListResponse getListOfCicByEmailAddress(String emailAddress);
}
