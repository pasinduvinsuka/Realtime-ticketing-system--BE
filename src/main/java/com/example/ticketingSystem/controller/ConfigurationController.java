package com.example.ticketingSystem.controller;

import com.example.ticketingSystem.dto.ConfigurationDTO;
import com.example.ticketingSystem.dto.ResponseDto;
import com.example.ticketingSystem.service.ConfigurationService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "v1/configuration")
public class ConfigurationController {

    @Autowired
    ConfigurationService configurationService;
    @GetMapping()
    public ResponseDto<ConfigurationDTO> getConfiguration(){
        configurationService.run();
        return new ResponseDto<>(1,"test",null);
    }

    @PutMapping("/{id}")
    public ResponseDto<Void> updateConfiguration(@PathParam("id") int id, @RequestBody ConfigurationDTO){
        return new ResponseDto<>(2,"superb", null);
    }
}
