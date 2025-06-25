package org.example.vehicleservice.service;

import org.example.vehicleservice.dto.VehicleDTO;
import org.example.vehicleservice.dto.VehicleResponse;
import org.example.vehicleservice.entity.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    Vehicle registerVehicle(Vehicle vehicle);
    List<Vehicle> getAllVehicles();
    Optional<Vehicle> getVehicleById(Long id);
    public List<VehicleResponse> getVehiclesByUserId(Long userId);
    Vehicle updateVehicleStatus(Long id, String status);
    Vehicle updateVehicle(Long id, VehicleDTO dto);
    void deleteVehicleById(Long id);
}
