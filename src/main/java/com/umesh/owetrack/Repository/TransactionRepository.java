package com.umesh.owetrack.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.umesh.owetrack.Entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
	// Fetch all transactions where the payer's user ID matches
    @Query("SELECT t FROM Transaction t WHERE t.payer.id = :userId")
    List<Transaction> findAllByPayerId(@Param("userId") Long userId);

    // Fetch all transactions where the user is one of the participants
    @Query("SELECT t FROM Transaction t JOIN t.participants p WHERE p.id = :userId")
    List<Transaction> findAllByParticipantId(@Param("userId") Long userId);
}