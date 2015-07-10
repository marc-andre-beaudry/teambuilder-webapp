package com.maillets.teambuilder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maillets.teambuilder.entities.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
}
