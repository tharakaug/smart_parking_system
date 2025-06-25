package org.example.vehicleservice.service.impl;

import org.example.vehicleservice.client.UserClient;
import org.example.vehicleservice.dto.VehicleDTO;
import org.example.vehicleservice.dto.VehicleResponse;
import org.example.vehicleservice.entity.Vehicle;
import org.example.vehicleservice.repo.VehicleRepo;
import org.example.vehicleservice.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepo vehicleRepo;

    @Autowired
    private UserClient userClient;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<VehicleResponse> getVehiclesByUserId(Long userId) {
        List<Vehicle> vehicles = vehicleRepo.findByUserId(userId);
        return vehicles.stream()
                .map(vehicle -> modelMapper.map(vehicle, VehicleResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public Vehicle registerVehicle(Vehicle vehicle) {
        if (!userClient.isUserExists(vehicle.getUserId())) {
            throw new RuntimeException("User ID " + vehicle.getUserId() + " not found in user-service.");
        }
        vehicle.setStatus("exited");
        return vehicleRepo.save(vehicle);
    }


    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepo.findAll();
    }

    @Override
    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepo.findById(id);
    }


    @Override
    public Vehicle updateVehicleStatus(Long id, String status) {
        Vehicle vehicle = vehicleRepo.findById(id).orElseThrow();
        vehicle.setStatus(status);
        return vehicleRepo.save(vehicle);
    }

    @Override
    public Vehicle updateVehicle(Long id, VehicleDTO dto) {
        Vehicle vehicle = vehicleRepo.findById(id).orElseThrow();

        vehicle.setLicensePlate(dto.getLicensePlate());
        vehicle.setType(dto.getType());
        vehicle.setStatus(dto.getStatus());
        vehicle.setUserId(dto.getUserId());
        vehicle.setEntryTime(dto.getEntryTime());
        vehicle.setExitTime(dto.getExitTime());

        return vehicleRepo.save(vehicle);
    }

    @Override
    public void deleteVehicleById(Long id) {
        vehicleRepo.deleteById(id);
    }
}
