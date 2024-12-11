package com.example.ticketingSystem.controller;

import com.example.ticketingSystem.dto.ConfigurationDTO;
import com.example.ticketingSystem.dto.ResponseDto;
import com.example.ticketingSystem.service.ConfigurationService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "configuration/v1")
public class ConfigurationController {

    @Autowired
    ConfigurationService configurationService;

    @GetMapping
    public ResponseEntity<ResponseDto<ConfigurationDTO>> getConfiguration() {


        ConfigurationDTO config = new ConfigurationDTO(
                configurationService.getMaxCapacity(),
                configurationService.getNumVipCustomers(),
                configurationService.getRegularCustomers(),
                configurationService.getNumberOfVendors(),
                configurationService.getTicketReleaseRate(),
                configurationService.getCustomerRetrievalRate()
        );

        ResponseDto<ConfigurationDTO> response = new ResponseDto<>(200, "success", config);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/update")
    public ResponseDto<Void> updateConfiguration(@RequestBody ConfigurationDTO configurationDTO) {
        configurationService.updateConfigurations(configurationDTO);
        return new ResponseDto<>(2, "superb", null);
    }
}
