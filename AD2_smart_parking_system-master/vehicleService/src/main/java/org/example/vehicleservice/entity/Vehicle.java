package org.example.vehicleservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licensePlate;
    private String type;
    private String status;
    private Long userId;

    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
}


//{
//        "username": "Mandy Smith",
//        "email": "mandy@example.com",
//        "password": "password123",
//        "full_name": "Himesha",
//        "phone_number": "+0987654321",
//        "address": "Kalutara",
//        "registration_date": "2018-10-12",
//        "role": "ADMIN"
//        }
