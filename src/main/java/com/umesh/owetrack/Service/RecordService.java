package com.umesh.owetrack.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umesh.owetrack.Entity.Balance;
import com.umesh.owetrack.Entity.Transaction;
import com.umesh.owetrack.Entity.User;
import com.umesh.owetrack.Repository.BalanceRepository;
import com.umesh.owetrack.Repository.TransactionRepository;
import com.umesh.owetrack.Repository.UserRepository;

@Service
public class RecordService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private BalanceRepository balanceRepository;

	public void recordPayment(Long payerId, Double amount, String title, List<Long> participantIds) {
		User payer = userRepository.findById(payerId).orElseThrow();
		List<User> participants = userRepository.findAllById(participantIds);

		Transaction transaction = new Transaction();
		transaction.setPayer(payer);
		transaction.setAmount(amount);
		transaction.setDate(LocalDateTime.now());
		transaction.setTitle(title);
		transaction.setParticipants(participants);
		transactionRepository.save(transaction);

		Double splitAmount = amount / participants.size();

		for (User participant : participants) {
			if (!participant.equals(payer)) {
				updateBalance(payer, participant, splitAmount);
			}
		}
	}
	
	public void recordUnequalPayment(Long payerId, Double totalAmount, String title, Map<Long, Double> participantAmounts) {
		User payer = userRepository.findById(payerId).orElseThrow();
		List<User> participants = userRepository.findAllById(participantAmounts.keySet());

		Transaction transaction = new Transaction();
		transaction.setPayer(payer);
		transaction.setAmount(totalAmount);
		transaction.setDate(LocalDateTime.now());
		transaction.setTitle(title);
		transaction.setParticipants(participants);
		transactionRepository.save(transaction);

		for (User participant : participants) {
			if (!participant.equals(payer)) {
				Double splitAmount = participantAmounts.get(participant.getId());
				updateBalance(payer, participant, splitAmount);
			}
		}
	}

	private void updateBalance(User payer, User participant, Double amount) {

		if (payer.getId() > participant.getId()) {
			Balance balance = balanceRepository.findUsers(participant, payer)
					.orElse(new Balance(null, participant, payer, 0.0));
			balance.setAmount(balance.getAmount() - amount);
			balanceRepository.save(balance);

		} else {
			Balance balance = balanceRepository.findUsers(payer, participant)
					.orElse(new Balance(null, payer, participant, 0.0));

			balance.setAmount(balance.getAmount() + amount);
			balanceRepository.save(balance);
		}
	}
}










