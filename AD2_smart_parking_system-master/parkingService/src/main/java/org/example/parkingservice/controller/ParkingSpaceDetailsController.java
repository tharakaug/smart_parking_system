package org.example.parkingservice.controller;

import org.example.parkingservice.dto.ParkingDetailsResponse;
import org.example.parkingservice.dto.ParkingSpaceDetailsDTO;
import org.example.parkingservice.entity.ParkingSpace;
import org.example.parkingservice.entity.ParkingSpaceDetails;
import org.example.parkingservice.service.ParkingSpaceService;
import org.example.parkingservice.service.ParkingSpaceServiceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking-spaces/details")
public class ParkingSpaceDetailsController {

    @Autowired
    private ParkingSpaceServiceDetails parkingSpaceDetailsService;


    // --- ParkingSpaceDetails CRUD ---

    @PostMapping("/{spaceId}/details")
    public ResponseEntity<ParkingSpaceDetails> addDetail(
            @PathVariable Long spaceId,
            @RequestBody ParkingSpaceDetailsDTO dto) {

        ParkingSpaceDetails created = parkingSpaceDetailsService.addParkingDetail(spaceId, dto);

        return ResponseEntity.ok(created);
    }

    @PutMapping("/{spaceId}/details/{detailId}")
    public ResponseEntity<ParkingSpaceDetails> updateDetail(
            @PathVariable Long spaceId,
            @PathVariable Long detailId,
            @RequestBody ParkingSpaceDetailsDTO dto) {
        try {
            ParkingSpaceDetails updated = parkingSpaceDetailsService.updateParkingDetail(spaceId, detailId, dto);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping("/{spaceId}/details")
    public ResponseEntity<List<ParkingSpaceDetails>> getDetails(@PathVariable Long spaceId) {
        return ResponseEntity.ok(parkingSpaceDetailsService.getDetailsBySpace(spaceId));
    }

    @DeleteMapping("/details/{detailId}")
    public ResponseEntity<Void> deleteDetail(@PathVariable Long detailId) {
        parkingSpaceDetailsService.deleteDetail(detailId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/detail/{detailId}")
    public ResponseEntity<ParkingDetailsResponse> getDetailById(@PathVariable Long detailId) {
        ParkingSpaceDetails detail = parkingSpaceDetailsService.getDetailById(detailId);

        ParkingDetailsResponse response = new ParkingDetailsResponse();
        response.setId(detail.getId());
        response.setNumberPlate(detail.getNumberPlate());
        response.setEntryTime(detail.getEntryTime());
        response.setExitTime(detail.getExitTime());
        response.setDuration(detail.getDuration());

        return ResponseEntity.ok(response);
    }

}
