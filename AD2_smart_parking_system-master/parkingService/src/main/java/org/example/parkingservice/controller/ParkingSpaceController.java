package org.example.parkingservice.controller;

import org.example.parkingservice.entity.ParkingSpace;
import org.example.parkingservice.entity.ParkingSpaceDetails;
import org.example.parkingservice.service.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking-spaces")
public class ParkingSpaceController {

    @Autowired
    private ParkingSpaceService parkingSpaceService;

    // --- ParkingSpace CRUD ---

    @PostMapping("/register")
    public ResponseEntity<ParkingSpace> registerSpace(@RequestBody ParkingSpace space) {
        ParkingSpace created = parkingSpaceService.createParkingSpace(space);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<ParkingSpace>> getAllSpaces() {
        return ResponseEntity.ok(parkingSpaceService.getAllParkingSpaces());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingSpace> getSpaceById(@PathVariable Long id) {
        return parkingSpaceService.getParkingSpaceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ParkingSpace> updateStatus(@PathVariable Long id, @RequestParam ParkingSpace.Status status) {
        ParkingSpace updated = parkingSpaceService.updateStatus(id, status);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpace(@PathVariable Long id) {
        parkingSpaceService.deleteParkingSpace(id);
        return ResponseEntity.noContent().build();
    }

    // --- ParkingSpaceDetails CRUD ---


}
