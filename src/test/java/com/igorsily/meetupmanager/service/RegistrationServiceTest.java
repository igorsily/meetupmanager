package com.igorsily.meetupmanager.service;

import com.igorsily.meetupmanager.model.Registration;
import com.igorsily.meetupmanager.repository.RegistrationRepository;
import com.igorsily.meetupmanager.service.impl.RegistrationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class RegistrationServiceTest {

    @MockBean
    RegistrationRepository repository;

    RegistrationService registrationService;

    @BeforeEach
    public void setUp() { this.registrationService = new RegistrationServiceImpl(repository); }

    @Test
    @DisplayName("Should save an registration")
    public void saveRegistration() {

        Registration registration = createValidRegistration();

        Mockito.when(repository.existsByRegistration(Mockito.anyString())).thenReturn(false);
        Mockito.when(repository.save(registration)).thenReturn(createValidRegistration());

        Registration savedRegistration = registrationService.save(registration);

        assertThat(savedRegistration.getId()).isEqualTo(101);
        assertThat(savedRegistration.getName()).isEqualTo("Igor Sily");
        assertThat(savedRegistration.getDateOfRegistration()).isEqualTo("01/04/2022");
        assertThat(savedRegistration.getRegistration()).isEqualTo("001");
    }

    private Registration createValidRegistration() {
        return Registration.builder()
                .id(101)
                .name("Igor Sily")
                .dateOfRegistration("01/04/2022")
                .registration("001")
                .build();
    }

}
