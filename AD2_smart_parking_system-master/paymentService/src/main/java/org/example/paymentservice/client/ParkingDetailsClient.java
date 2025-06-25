package org.example.paymentservice.client;

import org.example.paymentservice.dto.ParkingDetailsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "parkingService") // use this exact name as shown in Eureka
public interface ParkingDetailsClient {
    @GetMapping("/api/parking-spaces/details/detail/{detailId}")
    ParkingDetailsResponse getParkingDetailsById(@PathVariable("detailId") Long detailId);
}


