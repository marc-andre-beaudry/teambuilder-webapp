package com.maillets.teambuilder.controller.utils;

import com.maillets.teambuilder.dto.PlayerDto;
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
		Team team = player.getTeam();
		if (team != null) {
			dto.setTeam(player.getTeam().getName());
			dto.setTeamAbb(player.getTeam().getAbb());
		}
		return dto;
	}
}
