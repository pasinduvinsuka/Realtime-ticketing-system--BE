package com.example.ticketingSystem.controller;

import com.example.ticketingSystem.dto.ConfigurationDTO;
import com.example.ticketingSystem.dto.ResponseDto;
import com.example.ticketingSystem.service.ConfigurationService;
import com.example.ticketingSystem.service.TicketingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "configuration/v1")
public class ConfigurationController {

    @Autowired
    ConfigurationService configurationService;
    @Autowired
    TicketingService ticketingService;

    @GetMapping
    public ResponseEntity<ResponseDto<ConfigurationDTO>> getConfiguration() {

        ConfigurationDTO config = new ConfigurationDTO(configurationService.getMaxCapacity(), configurationService.getTotalTickets(), configurationService.getNumVipCustomers(), configurationService.getNumRegularCustomers(), configurationService.getNumberOfVendors(), configurationService.getTicketReleaseRate(), configurationService.getCustomerRetrievalRate());

        ResponseDto<ConfigurationDTO> response = new ResponseDto<>(200, false, "Successfully Retrieved", config);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto<ConfigurationDTO>> updateConfiguration(@RequestBody ConfigurationDTO configurationDTO) {
        configurationService.updateConfigurations(configurationDTO);
        ticketingService.restart(); // Start ticketing process
        ResponseDto<ConfigurationDTO> response = new ResponseDto<>(200, false, "Successfully Updated", configurationDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
