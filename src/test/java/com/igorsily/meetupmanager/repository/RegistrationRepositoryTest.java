package com.igorsily.meetupmanager.repository;


import com.igorsily.meetupmanager.model.Registration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class RegistrationRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    RegistrationRepository repository;

    @Test
    @DisplayName("Should return true when exist an registration already created.")
    public void returnTrueWhenRegistrationExist(){

        String registration = "123";

        Registration registration_class_attribute = createNewRegistration(registration);

        testEntityManager.persist(registration_class_attribute);

        boolean exists = repository.existsByRegistration(registration);

        assertThat(exists).isTrue();

    }

    @Test
    @DisplayName("Should return false when exist an registration already created.")
    public void returFalseWhenRegistrationExist(){

        String registration = "123";

        boolean exists = repository.existsByRegistration(registration);

        assertThat(exists).isFalse();

    }

    //TODO Implementar os teste do findByRegistration

    public static Registration createNewRegistration(String registration) {
        return Registration.builder()
                .name("Igor")
                .dateOfRegistration("12/09/2022")
                .registration(registration).build();
    }
}
