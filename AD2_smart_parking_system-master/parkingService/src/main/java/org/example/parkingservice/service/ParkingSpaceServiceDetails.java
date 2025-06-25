package org.example.parkingservice.service;

import org.example.parkingservice.dto.ParkingSpaceDetailsDTO;
import org.example.parkingservice.entity.ParkingSpace;
import org.example.parkingservice.entity.ParkingSpaceDetails;

import java.util.List;
import java.util.Optional;

public interface ParkingSpaceServiceDetails {

    public ParkingSpaceDetails addParkingDetail(Long spaceId, ParkingSpaceDetailsDTO dto);
    List<ParkingSpaceDetails> getDetailsBySpace(Long spaceId);
    void deleteDetail(Long detailId);
    ParkingSpaceDetails getDetailById(Long detailId);

    public ParkingSpaceDetails updateParkingDetail(Long spaceId, Long detailId, ParkingSpaceDetailsDTO dto);



}
