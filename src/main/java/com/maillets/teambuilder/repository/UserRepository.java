package com.maillets.teambuilder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maillets.teambuilder.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
