package org.example.vehicleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehicleDTO {
    private Long id;
    private String licensePlate;
    private String type;
    private String status;
    private Long userId;

    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
}
