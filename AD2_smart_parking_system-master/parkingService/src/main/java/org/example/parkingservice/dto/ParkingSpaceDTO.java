package org.example.parkingservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.parkingservice.entity.ParkingSpace;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSpaceDTO {
    private Long id;

    @NotBlank(message = "Location is mandatory")
    private String location;

    private String zone;

    private Long ownerId;

    private ParkingSpace.Status status;

}
