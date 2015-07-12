package com.maillets.teambuilder.controller.utils;

import com.maillets.teambuilder.dto.PlayerDto;
import com.maillets.teambuilder.dto.TeamDto;
import com.maillets.teambuilder.entities.Player;
import com.maillets.teambuilder.entities.Team;

public final class ConvertUtils {

	public static PlayerDto playerConverter(Player player) {
		PlayerDto dto = new PlayerDto();
		dto.setId(player.getId());
		dto.setFirstName(player.getFirstName());
		dto.setLastName(player.getLastName());
		dto.setPosition(player.getPosition());
		dto.setSalary(player.getSalary());
		Team team = player.getNhlTeam();
		if (team != null) {
			dto.setTeam(player.getNhlTeam().getName());
			dto.setTeamAbb(player.getNhlTeam().getAbb());
		}
		return dto;
	}

	public static TeamDto teamConverter(Team team) {
		TeamDto dto = new TeamDto();
		dto.setId(team.getId());
		dto.setAbb(team.getAbb());
		dto.setIsNhl(team.getIsNhl());
		dto.setIsPublic(team.getIsPublic());
		dto.setName(team.getName());
		return dto;
	}
}
