package com.umesh.owetrack.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.umesh.owetrack.Entity.Balance;
import com.umesh.owetrack.Entity.User;

public interface BalanceRepository extends JpaRepository<Balance, Long> {

	@Query("SELECT b FROM Balance b WHERE b.user1 = :userOne AND b.user2 = :userTwo")
    Optional<Balance> findUsers(@Param("userOne") User userOne, @Param("userTwo") User userTwo);
	
	@Query("SELECT b FROM Balance b WHERE b.user1.id = :userId OR b.user2.id = :userId")
    List<Balance> findByUserId(@Param("userId") Long userId);
}