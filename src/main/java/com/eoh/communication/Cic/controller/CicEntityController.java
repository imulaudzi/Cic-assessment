package com.eoh.communication.Cic.controller;

import com.eoh.communication.Cic.generated.entity.CreateEntityResponse;
import com.eoh.communication.Cic.generated.entity.EntityRequest;
import com.eoh.communication.Cic.generated.entity.EntityResponse;
import com.eoh.communication.Cic.service.CicEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class CicEntityController {

    @Autowired
    private CicEntityService cicEntityService;

    @PostMapping(value = "/entity/create")
    @ResponseBody
    public CreateEntityResponse createEntity(@RequestBody EntityRequest entityRequest){
        return cicEntityService.createEntity(entityRequest);
    }

    @GetMapping(value = "/entity/listEntities")
    @ResponseBody
    public EntityResponse getAllEntities(){
        return cicEntityService.getAllEntities();
    }
}
