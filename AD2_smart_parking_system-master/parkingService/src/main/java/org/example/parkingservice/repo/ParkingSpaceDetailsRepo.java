package org.example.parkingservice.repo;

import org.example.parkingservice.entity.ParkingSpace;
import org.example.parkingservice.entity.ParkingSpaceDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingSpaceDetailsRepo extends JpaRepository<ParkingSpaceDetails, Long> {
    List<ParkingSpaceDetails> findByParkingSpaceId(Long spaceId);
}
