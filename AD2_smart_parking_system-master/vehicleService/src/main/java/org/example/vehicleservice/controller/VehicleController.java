package org.example.vehicleservice.controller;

import org.example.vehicleservice.dto.VehicleDTO;
import org.example.vehicleservice.dto.VehicleResponse;
import org.example.vehicleservice.entity.Vehicle;
import org.example.vehicleservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;


    @PostMapping("/register")
    public ResponseEntity<Vehicle> register(@RequestBody VehicleDTO dto) {
        Vehicle vehicle = Vehicle.builder()
                .licensePlate(dto.getLicensePlate())
                .type(dto.getType())
                .status("exited")
                .userId(dto.getUserId())
                .entryTime(dto.getEntryTime())
                .exitTime(dto.getExitTime())
                .build();
        Vehicle saved = vehicleService.registerVehicle(vehicle);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<VehicleResponse>> getVehiclesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(vehicleService.getVehiclesByUserId(userId));
    }



    @GetMapping
    public ResponseEntity<List<Vehicle>> getAll() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getById(@PathVariable Long id) {
        return vehicleService.getVehicleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<Vehicle>> getByUser(@PathVariable Long userId) {
//        List<Vehicle> vehicles = vehicleService.getVehiclesByUserId(userId);
//        return ResponseEntity.ok(vehicles);
//    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Vehicle> updateStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            Vehicle updated = vehicleService.updateVehicleStatus(id, status);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id, @RequestBody VehicleDTO dto) {
        try {
            Vehicle updated = vehicleService.updateVehicle(id, dto);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        try {
            vehicleService.deleteVehicleById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/status")
    public ResponseEntity<String> statusCheck() {
        return ResponseEntity.ok("Vehicle Service is running");
    }
}
