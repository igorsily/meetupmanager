package com.igorsily.meetupmanager.service.impl;

import com.igorsily.meetupmanager.exception.BusinessException;
import com.igorsily.meetupmanager.model.Registration;
import com.igorsily.meetupmanager.repository.RegistrationRepository;
import com.igorsily.meetupmanager.service.RegistrationService;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    RegistrationRepository repository;

    public RegistrationServiceImpl(RegistrationRepository repository) {
        this.repository = repository;
    }


    public Registration save(Registration registration) {
        if (repository.existsByRegistration(registration.getRegistration())) {
            throw new BusinessException("Registration Already created");
        }
        return repository.save(registration);
    }
}
