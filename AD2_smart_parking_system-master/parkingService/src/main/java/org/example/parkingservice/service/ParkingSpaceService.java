package org.example.parkingservice.service;

import org.example.parkingservice.entity.ParkingSpace;
import org.example.parkingservice.entity.ParkingSpaceDetails;

import java.util.List;
import java.util.Optional;

public interface ParkingSpaceService {
    ParkingSpace createParkingSpace(ParkingSpace space);
    List<ParkingSpace> getAllParkingSpaces();
    Optional<ParkingSpace> getParkingSpaceById(Long id);
    ParkingSpace updateStatus(Long id, ParkingSpace.Status status);
    void deleteParkingSpace(Long id);

}
