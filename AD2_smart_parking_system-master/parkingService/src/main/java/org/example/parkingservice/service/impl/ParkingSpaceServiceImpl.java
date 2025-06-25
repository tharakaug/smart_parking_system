package org.example.parkingservice.service.impl;

import org.example.parkingservice.entity.ParkingSpace;
import org.example.parkingservice.entity.ParkingSpaceDetails;
import org.example.parkingservice.repo.ParkingSpaceDetailsRepo;
import org.example.parkingservice.repo.ParkingSpaceRepo;
import org.example.parkingservice.service.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingSpaceServiceImpl implements ParkingSpaceService {

    @Autowired
    private ParkingSpaceRepo parkingSpaceRepo;

    @Autowired
    private ParkingSpaceDetailsRepo parkingSpaceDetailsRepo;


    @Override
    public ParkingSpace createParkingSpace(ParkingSpace space) {
        if (space.getStatus() == null) {
            space.setStatus(ParkingSpace.Status.AVAILABLE);
        }
        return parkingSpaceRepo.save(space);
    }

    @Override
    public List<ParkingSpace> getAllParkingSpaces() {
        return parkingSpaceRepo.findAll();
    }

    @Override
    public Optional<ParkingSpace> getParkingSpaceById(Long id) {
        return parkingSpaceRepo.findById(id);
    }

    @Override
    public ParkingSpace updateStatus(Long id, ParkingSpace.Status status) {
        ParkingSpace space = parkingSpaceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Parking space not found"));
        space.setStatus(status);
        return parkingSpaceRepo.save(space);
    }

    @Override
    public void deleteParkingSpace(Long id) {
        parkingSpaceRepo.deleteById(id);
    }

    // --- ParkingSpaceDetails logic ---

}
