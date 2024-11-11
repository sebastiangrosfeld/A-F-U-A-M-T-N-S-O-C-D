package com.study.carDealershipsServer.vehicle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.carDealershipsServer.common.enums.FuelType;
import com.study.carDealershipsServer.common.enums.VehicleBrand;
import com.study.carDealershipsServer.common.enums.VehicleType;
import com.study.carDealershipsServer.domain.vehicle.dto.CreateEngineRequest;
import com.study.carDealershipsServer.domain.vehicle.dto.CreateModelRequest;
import com.study.carDealershipsServer.domain.vehicle.dto.CreateVehicleRequest;
import com.study.carDealershipsServer.domain.vehicle.dto.VehicleResource;
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

import static com.study.carDealershipsServer.common.Constants.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

       mockMvc.perform(get(URI)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.content", hasSize(1)),
                        jsonPath("$.content[0].vinNumber", is("12345"))
                ).andReturn();

        var vehicleVin = request.vinNumber();

        var result = mockMvc.perform(get(URI+ "/" + vehicleVin)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk()
                ).andReturn();

        var onceVehicle = mapper.readValue(result.getResponse().getContentAsString(), VehicleResource.class);

        assertEquals(vehicleVin, onceVehicle.vinNumber());

        mockMvc.perform(delete(URI+ "/" + vehicleVin)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isNoContent()

                ).andReturn();

        assertEquals(0, vehicleRepository.count());
    }
}
