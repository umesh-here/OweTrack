package com.umesh.owetrack.DTO;

import java.util.Map;

import lombok.*;


@Getter
@Setter
public class ManualPaymentRequest {

    private Long payerId;
    private Double totalAmount;
    private String title;
    private Map<Long, Double> participantAmounts;

    // Getters and setters
}