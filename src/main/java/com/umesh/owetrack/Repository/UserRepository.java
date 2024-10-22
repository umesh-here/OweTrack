package com.umesh.owetrack.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umesh.owetrack.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);
}