package org.example.parkingservice.dto;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ParkingDetailsResponse {
    private Long id;
    private String numberPlate;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private Long duration;
    private Long parkingSpaceId;
}
