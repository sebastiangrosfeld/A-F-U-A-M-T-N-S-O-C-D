package com.study.carDealershipsServer.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.carDealershipsServer.common.enums.Roles;
import com.study.carDealershipsServer.common.enums.VehicleBrand;
import com.study.carDealershipsServer.common.enums.VehicleType;
import com.study.carDealershipsServer.domain.auth.entity.AppUser;
import com.study.carDealershipsServer.domain.client.vehiclePreference.dto.CreatePreferenceVehicleRequest;
import com.study.carDealershipsServer.domain.client.vehiclePreference.dto.PreferenceVehicleResource;
import com.study.carDealershipsServer.domain.client.entity.Client;
import com.study.carDealershipsServer.domain.client.repository.ClientRepository;
import com.study.carDealershipsServer.domain.client.vehiclePreference.repository.VehiclePreferenceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static com.study.carDealershipsServer.common.Constants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@WithMockUser
public class VehiclePreferencesModuleTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private VehiclePreferenceRepository vehiclePreferenceRepository;
    @Autowired
    private ClientRepository clientRepository;

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void should_createGetAndDeleteVehiclePreferences_WhenClientExist() throws Exception {

        UUID clientId = UUID.randomUUID();

        AppUser appUser = new AppUser();
        appUser.setRole(Roles.ROLE_CLIENT);
        appUser.setUserId(clientId);
        Authentication authentication = new UsernamePasswordAuthenticationToken(appUser, null, appUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String URI = URL_PREFIX + CLIENT_PREFIX + PREFERENCES_PREFIX ;

        Client client = Client.builder()
                .id(clientId)
                .firstName("clientName")
                .phone("6888869")
                .personalNumber("09543")
                .active(true)
                .email("email")
                .surname("surname")
                .build();
        clientRepository.save(client);

        CreatePreferenceVehicleRequest createPreferenceVehicleRequest = CreatePreferenceVehicleRequest.builder()
                .clientId(clientId)
                .vehicleType(VehicleType.valueOf("CAR"))
                .vehicleBrand(VehicleBrand.valueOf("VOLVO"))
                .color("red")
                .build();

        String preferenceJson = mapper.writeValueAsString(createPreferenceVehicleRequest);

        mockMvc.perform(post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                .content(preferenceJson)).andExpect(status().isCreated());

        assertEquals(1, vehiclePreferenceRepository.count());

        var resultList = mockMvc.perform(get(URI + "/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk()

                ).andReturn();

        var preferences = mapper.readValue(resultList.getResponse().getContentAsString(), new TypeReference<List<PreferenceVehicleResource>>() {
        });

         assertFalse(preferences.isEmpty());

      PreferenceVehicleResource preference =  preferences.getFirst();

       var preferenceId = preference.id();

       var result = mockMvc.perform(get(URI+ "/" + preferenceId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk()

                ).andReturn();

        var oncePreference = mapper.readValue(result.getResponse().getContentAsString(), PreferenceVehicleResource.class);

        assertEquals(preferenceId, oncePreference.id());
        assertEquals(preference, oncePreference);

        mockMvc.perform(delete(URI+ "/" + preferenceId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isNoContent()

                ).andReturn();

        assertEquals(0, vehiclePreferenceRepository.count());
    }

    @Test
    void should_returnEmptyList_WhenPreferencesNotExists() throws Exception {
        UUID clientId = UUID.randomUUID();

        AppUser appUser = new AppUser();
        appUser.setRole(Roles.ROLE_CLIENT);
        appUser.setUserId(clientId);
        Authentication authentication = new UsernamePasswordAuthenticationToken(appUser, null, appUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String URI = URL_PREFIX + CLIENT_PREFIX + PREFERENCES_PREFIX + "/all";

        Client client = Client.builder()
                .id(clientId)
                .firstName("clientName")
                .phone("68888691")
                .personalNumber("8654")
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
