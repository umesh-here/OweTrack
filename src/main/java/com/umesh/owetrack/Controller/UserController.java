package com.umesh.owetrack.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umesh.owetrack.Entity.Balance;
import com.umesh.owetrack.Entity.Transaction;
import com.umesh.owetrack.Entity.User;
import com.umesh.owetrack.Repository.BalanceRepository;
import com.umesh.owetrack.Repository.TransactionRepository;
import com.umesh.owetrack.Repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private BalanceRepository balanceRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody User user) {
		
		Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

	    if (existingUser.isPresent()) {
	        return new ResponseEntity<>("Email is already in use!", HttpStatus.CONFLICT);
	    }
        User createdUser = userRepository.save(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
	
	@GetMapping("/detail/{id}")
	public Optional<User> getUser(@PathVariable Long id) {
		return userRepository.findById(id);
	}
	

	@GetMapping("/due/{id}")
	public List<String> getBalancesByUserId(@PathVariable Long id) {
	    List<Balance> balances = balanceRepository.findByUserId(id);
	    List<String> res = new ArrayList<>();

	    for (Balance balance : balances) {
	        StringBuilder st = new StringBuilder();
	        String user1Name = balance.getUser1().getName();
	        String user2Name = balance.getUser2().getName();
	        Double amount = balance.getAmount();

	        if (amount > 0) {
	            // user2 owes user1
	            st.append(user2Name).append(" owes ").append(user1Name).append(" ").append(amount);
	        } else if (amount < 0) {
	            // user1 owes user2
	            st.append(user1Name).append(" owes ").append(user2Name).append(" ").append(Math.abs(amount));
	        }

	        res.add(st.toString());
	    }

	    return res;
	}
    
    @GetMapping("/transactions/{id}")
    public ResponseEntity<?> getTransactionsByUserId(@PathVariable Long id) {
        List<Transaction> transactionsAsPayer = transactionRepository.findAllByPayerId(id);
        
        List<Transaction> transactionsAsParticipant = transactionRepository.findAllByParticipantId(id);

        transactionsAsPayer.addAll(transactionsAsParticipant);

        return ResponseEntity.ok(transactionsAsPayer);
    }
    

}
