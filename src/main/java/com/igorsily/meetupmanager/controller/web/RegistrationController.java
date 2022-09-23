package com.igorsily.meetupmanager.controller.web;

import com.igorsily.meetupmanager.controller.dto.RegistrationDTO;
import com.igorsily.meetupmanager.model.Registration;
import com.igorsily.meetupmanager.service.RegistrationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    private final ModelMapper modelMapper;


    @Autowired
    public RegistrationController(RegistrationService registrationService, ModelMapper modelMapper) {
        this.registrationService = registrationService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RegistrationDTO create(@RequestBody @Valid RegistrationDTO dto) {

        Registration entity = modelMapper.map(dto, Registration.class);
        entity = registrationService.save(entity);

        return modelMapper.map(entity, RegistrationDTO.class);
    }
}

