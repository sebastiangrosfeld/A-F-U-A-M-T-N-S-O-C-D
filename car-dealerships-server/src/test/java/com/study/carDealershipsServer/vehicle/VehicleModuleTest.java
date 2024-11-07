package com.study.carDealershipsServer.vehicle;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.carDealershipsServer.common.FuelType;
import com.study.carDealershipsServer.common.VehicleBrand;
import com.study.carDealershipsServer.common.VehicleType;
import com.study.carDealershipsServer.domain.client.dto.CreatePreferenceVehicleRequest;
import com.study.carDealershipsServer.domain.client.dto.PreferenceVehicleResource;
import com.study.carDealershipsServer.domain.client.entity.Client;
import com.study.carDealershipsServer.domain.vehicle.dto.CreateEngineRequest;
import com.study.carDealershipsServer.domain.vehicle.dto.CreateModelRequest;
import com.study.carDealershipsServer.domain.vehicle.dto.CreateVehicleRequest;
import com.study.carDealershipsServer.domain.vehicle.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.study.carDealershipsServer.common.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class VehicleModuleTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private VehicleRepository vehicleRepository;


    @Test
    void should_createGetAndDeleteVehicle_WhenClientExist() throws Exception {

        String URI = URL_PREFIX + MANAGER_PREFIX + VEHICLE_PREFIX ;

        CreateEngineRequest createEngineRequest = CreateEngineRequest.builder()
                .engineName("engine1")
                .typeOfFuel(FuelType.DIESEL)
                .numberOfCylinders(3)
                .horsePower(200)
                .torque(300)
                .build();

        CreateVehicleRequest request = CreateVehicleRequest.builder()
                .vinNumber("12345")
                .vehicleType(VehicleType.CAR)
                .color("red")
                .bodyLine("coupe")
                .yearOfProduction(2010)
                .registrationDate(new Date(1000L))
                .modelName("")
                .newModel(CreateModelRequest.builder()
                        .modelName("new mode 1")
                        .vehicleBrand(VehicleBrand.OPEL)
                        .startProduction(1999)
                        .endProduction(2020)
                        .build())
                .newEngines(List.of(createEngineRequest))
                .build();

        var requestAsJson = mapper.writeValueAsString(request);

        mockMvc.perform(post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestAsJson)).andExpect(status().isCreated());

        assertEquals(1, vehicleRepository.count());

//        var resultList = mockMvc.perform(get(URI+ "/" + clientId + "/all")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpectAll(
//                        status().isOk()
//
//                ).andReturn();
//
//        var preferences = mapper.readValue(resultList.getResponse().getContentAsString(), new TypeReference<List<PreferenceVehicleResource>>() {
//        });
//
//        assertFalse(preferences.isEmpty());
//
//        PreferenceVehicleResource preference =  preferences.getFirst();
//
//        var preferenceId = preference.id();
//
//        var result = mockMvc.perform(get(URI+ "/" + preferenceId)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpectAll(
//                        status().isOk()
//
//                ).andReturn();
//
//        var oncePreference = mapper.readValue(result.getResponse().getContentAsString(), PreferenceVehicleResource.class);
//
//        System.out.println(result.getResponse().getContentAsString());
//
//        assertEquals(preferenceId, oncePreference.id());
//        assertEquals(preference, oncePreference);
//
//        mockMvc.perform(delete(URI+ "/" + preferenceId)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpectAll(
//                        status().isNoContent()
//
//                ).andReturn();
//
//        assertEquals(0, vehiclePreferenceRepository.count());
    }
}
