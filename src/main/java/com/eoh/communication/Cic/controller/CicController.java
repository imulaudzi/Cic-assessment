package com.eoh.communication.Cic.controller;

import com.eoh.communication.Cic.generated.cic.CicListResponse;
import com.eoh.communication.Cic.generated.cic.CicRequest;
import com.eoh.communication.Cic.generated.cic.CicResponse;
import com.eoh.communication.Cic.generated.GenericResponse;
import com.eoh.communication.Cic.service.CicService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping
public class CicController {
    @Autowired
    private CicService cicService;

    @PostMapping(value = "/cic")
    @ResponseBody
    public ResponseEntity createCic(@RequestBody CicRequest cicRequest) {

        GenericResponse genericResponse = cicService.createCic(cicRequest);

        return ResponseEntity.ok(genericResponse);
    }

    @GetMapping(value = "/cic/{cicId}")
    @ResponseBody
    public CicResponse getCicById(@PathVariable("cicId") Long cicId) {
        return cicService.getCicById(cicId);
    }

    @GetMapping(value = "/cic/email")
    @ResponseBody
    public CicListResponse getListOfCicByEmailAddress(@RequestParam("emailAddress") String emailAddress) {
        return cicService.getListOfCicByEmailAddress(emailAddress);
    }
}
