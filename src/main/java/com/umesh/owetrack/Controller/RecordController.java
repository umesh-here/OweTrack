package com.umesh.owetrack.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.umesh.owetrack.DTO.ManualPaymentRequest;
import com.umesh.owetrack.DTO.PaymentRequest;
import com.umesh.owetrack.Service.RecordService;

@RestController
@RequestMapping("/api/record")
public class RecordController {

	@Autowired
	private RecordService recordService;

	@PostMapping("/equal-payment")
	public ResponseEntity<?> recordPayment(@RequestBody PaymentRequest paymentRequest) {
		recordService.recordPayment(paymentRequest.getPayerId(), paymentRequest.getAmount(), paymentRequest.getTitle(),
				paymentRequest.getParticipants());
		return ResponseEntity.ok("Payment recorded");
	}

	@PostMapping("/unequal-payment")
	public ResponseEntity<?> unequalPayment(@RequestBody ManualPaymentRequest manualPaymentRequest) {
		recordService.recordUnequalPayment(manualPaymentRequest.getPayerId(), manualPaymentRequest.getTotalAmount(), manualPaymentRequest.getTitle(),
				manualPaymentRequest.getParticipantAmounts());
		return ResponseEntity.ok("Non-equal payment recorded");
	}

}