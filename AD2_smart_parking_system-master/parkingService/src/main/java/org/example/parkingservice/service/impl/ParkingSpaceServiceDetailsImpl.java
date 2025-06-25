package org.example.parkingservice.service.impl;

import org.example.parkingservice.dto.ParkingSpaceDetailsDTO;
import org.example.parkingservice.entity.ParkingSpace;
import org.example.parkingservice.entity.ParkingSpaceDetails;
import org.example.parkingservice.repo.ParkingSpaceDetailsRepo;
import org.example.parkingservice.repo.ParkingSpaceRepo;
import org.example.parkingservice.service.ParkingSpaceService;
import org.example.parkingservice.service.ParkingSpaceServiceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingSpaceServiceDetailsImpl implements ParkingSpaceServiceDetails {

    @Autowired
    private ParkingSpaceRepo parkingSpaceRepo;

    @Autowired
    private ParkingSpaceDetailsRepo parkingSpaceDetailsRepo;


    // --- ParkingSpaceDetails logic ---

    @Override
    public ParkingSpaceDetails addParkingDetail(Long spaceId, ParkingSpaceDetailsDTO dto) {
        ParkingSpace space = parkingSpaceRepo.findById(spaceId)
                .orElseThrow(() -> new RuntimeException("Parking space not found"));

        ParkingSpaceDetails detail = new ParkingSpaceDetails();
        detail.setNumberPlate(dto.getNumberPlate());
        detail.setEntryTime(dto.getEntryTime());
        detail.setExitTime(dto.getExitTime());
        detail.setParkingSpace(space);

        if (dto.getEntryTime() != null && dto.getExitTime() != null) {
            long duration = Duration.between(dto.getEntryTime(), dto.getExitTime()).toMinutes();
            detail.setDuration(duration);
        }

        return parkingSpaceDetailsRepo.save(detail);
    }

    public ParkingSpaceDetails updateParkingDetail(Long spaceId, Long detailId, ParkingSpaceDetailsDTO dto) {
        ParkingSpaceDetails existingDetail = parkingSpaceDetailsRepo.findById(detailId)
                .orElseThrow(() -> new RuntimeException("Parking detail not found"));

        ParkingSpace space = parkingSpaceRepo.findById(spaceId)
                .orElseThrow(() -> new RuntimeException("Parking space not found"));

        // Update fields
        existingDetail.setNumberPlate(dto.getNumberPlate());
        existingDetail.setEntryTime(dto.getEntryTime());
        existingDetail.setExitTime(dto.getExitTime());
        existingDetail.setParkingSpace(space);

        if (dto.getEntryTime() != null && dto.getExitTime() != null) {
            long duration = Duration.between(dto.getEntryTime(), dto.getExitTime()).toMinutes();
            existingDetail.setDuration(duration);
        }

        return parkingSpaceDetailsRepo.save(existingDetail);
    }



    @Override
    public List<ParkingSpaceDetails> getDetailsBySpace(Long spaceId) {
        return parkingSpaceDetailsRepo.findByParkingSpaceId(spaceId);
    }

    @Override
    public void deleteDetail(Long detailId) {
        parkingSpaceDetailsRepo.deleteById(detailId);
    }


    public ParkingSpaceDetails getDetailById(Long detailId) {
        return parkingSpaceDetailsRepo.findById(detailId)
                .orElseThrow(() -> new RuntimeException("Parking detail not found with id: " + detailId));
    }

}
