package com.umesh.owetrack.DTO;

import java.util.List;
import lombok.*;


@Getter
@Setter
public class PaymentRequest {

    private Long payerId;
    private Double amount;
    private String title;
    private List<Long> participants;

    // Getters and setters
}