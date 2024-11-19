package com.study.carDealershipsServer.application.manager.controller;

import com.study.carDealershipsServer.application.manager.useCase.EngineManagerFacade;
import com.study.carDealershipsServer.domain.vehicle.dto.CreateEngineRequest;
import com.study.carDealershipsServer.domain.vehicle.dto.EditEngineRequest;
import com.study.carDealershipsServer.domain.vehicle.dto.EngineResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.study.carDealershipsServer.common.Constants.*;

@RestController
@RequestMapping(MANAGER_PREFIX + ENGINES_PREFIX)
@RequiredArgsConstructor
public class EngineManagerController {

    private final EngineManagerFacade engineManagerFacade;

    @PostMapping
    public ResponseEntity<Void> createEngine(@RequestBody CreateEngineRequest createEngineRequest) {
        engineManagerFacade.createEngine(createEngineRequest);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{engineName}")
    public ResponseEntity<EngineResource> getEngine(@PathVariable String engineName) {
        return ResponseEntity.ok(engineManagerFacade.getEngine(engineName));
    }

    @GetMapping("/all")
    public ResponseEntity<List<EngineResource>> getAllEngines() {
        return ResponseEntity.ok(engineManagerFacade.getEngines());
    }

    @PutMapping
    public ResponseEntity<Void> updateEngine(@RequestBody EditEngineRequest editEngineRequest) {
        engineManagerFacade.editEngine(editEngineRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{engineName}")
    public ResponseEntity<Void> deleteEngine(@PathVariable String engineName) {
        engineManagerFacade.deleteEngine(engineName);
        return ResponseEntity.noContent().build();
    }
}
