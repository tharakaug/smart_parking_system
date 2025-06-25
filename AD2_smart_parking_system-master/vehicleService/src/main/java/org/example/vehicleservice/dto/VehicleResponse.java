package org.example.vehicleservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VehicleResponse {
    private Long id;
    private String licensePlate;
    private String type;
    private String status;
    private Long userId;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
}
