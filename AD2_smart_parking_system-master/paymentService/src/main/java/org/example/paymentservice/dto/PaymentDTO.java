package org.example.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private Long id;
    private LocalDateTime paidDate;
    private double paidAmount;
    private Long vehicleId;
    private Long parkingDetailsId;
    private Status status;

    public enum Status {
        SUCCESS,
        FAIL
    }
}
