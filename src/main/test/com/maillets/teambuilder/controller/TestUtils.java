package com.maillets.teambuilder.controller;

import com.maillets.teambuilder.dto.PlayerDto;
import com.maillets.teambuilder.dto.TeamDto;
import com.maillets.teambuilder.entities.Player;
import com.maillets.teambuilder.entities.Team;
import com.maillets.teambuilder.entities.User;

public class TestUtils {

	public static Player generatePlayer(String firstName, String lastName) {
		Player player = new Player();
		player.setFirstName(firstName);
		player.setLastName(lastName);
		return player;
	}

	public static Team generateTeam(User owner, String name) {
		Team team = new Team();
		team.setOwner(owner);
		team.setName(name);
		return team;
	}

	public static TeamDto generateTeamDto(String name) {
		TeamDto dto = new TeamDto();
		dto.setName("name");
		return dto;
	}

	public static PlayerDto generateUserDto(String firstName, String lastName) {
		PlayerDto dto = new PlayerDto();
		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		return dto;
	}

	public static User generateUser(String userName) {
		User user = new User();
		user.setUserName(userName);
		return user;
	}
}
