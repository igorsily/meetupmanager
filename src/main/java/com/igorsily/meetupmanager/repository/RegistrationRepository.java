package com.igorsily.meetupmanager.repository;

import com.igorsily.meetupmanager.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Integer> {

    boolean existsByRegistration(String registration);

    Optional<Registration> findByRegistration(String registrationAtrb);
}