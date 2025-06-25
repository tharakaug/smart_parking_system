package org.example.parkingservice.repo;

import org.example.parkingservice.entity.ParkingSpace;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingSpaceRepo extends JpaRepository<ParkingSpace, Long> {

}
