package com.study.carDealershipsServer.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.carDealershipsServer.domain.client.dto.PreferenceVehicleDTO;
import com.study.carDealershipsServer.domain.client.entity.Client;
import com.study.carDealershipsServer.domain.client.repository.ClientRepository;
import com.study.carDealershipsServer.domain.client.repository.VehiclePreferenceRepository;
import org.checkerframework.checker.lock.qual.EnsuresLockHeld;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static com.study.carDealershipsServer.common.Constants.CLIENT_PREFIX;
import static com.study.carDealershipsServer.common.Constants.PREFERENCES_PREFIX;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class VehiclePreferencesModuleTest {

    private final ObjectMapper mapper = new ObjectMapper();
    private final String URL_PREFIX = "http://localhost:8080/";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private VehiclePreferenceRepository vehiclePreferenceRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Test
    void should_createVehiclePreferences_WhenClientExist() throws Exception {

        UUID clientId = UUID.randomUUID();
        String URI = URL_PREFIX + CLIENT_PREFIX + PREFERENCES_PREFIX ;

        Client client = Client.builder()
                .id(clientId)
                .firstName("clientName")
                .phone("6888869")
                .active(true)
                .email("email")
                .surname("surname")
                .build();
        clientRepository.save(client);

        PreferenceVehicleDTO preferenceVehicleDTO = PreferenceVehicleDTO.builder()
                .clientId(clientId)
                .vehicleType("Car")
                .vehicleBrand("Volvo")
                .color("red")
                .build();

        String preferenceJson = mapper.writeValueAsString(preferenceVehicleDTO);

        mockMvc.perform(post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                .content(preferenceJson)).andExpect(status().isCreated());

        assertEquals(1, vehiclePreferenceRepository.count());

    }

    @Test
    void should_returnEmptyList_WhenPreferencesNotExists() throws Exception {
        UUID clientId = UUID.randomUUID();
        String URI = URL_PREFIX + CLIENT_PREFIX + PREFERENCES_PREFIX + "/" + clientId + "/all";

        Client client = Client.builder()
                .id(clientId)
                .firstName("clientName")
                .phone("68888691")
                .active(true)
                .email("email2")
                .surname("surname")
                .build();
        clientRepository.save(client);

        var result = mockMvc.perform(get(URI)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk()

                ).andReturn();

        assertTrue(mapper.readValue(result.getResponse().getContentAsString(), List.class).isEmpty());
    }
}
