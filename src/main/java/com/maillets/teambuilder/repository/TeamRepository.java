package com.maillets.teambuilder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maillets.teambuilder.entities.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {
}
