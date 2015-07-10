package com.maillets.teambuilder.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.maillets.teambuilder.controller.exceptions.BadRequestException;
import com.maillets.teambuilder.controller.exceptions.EntityNotFoundException;
import com.maillets.teambuilder.controller.utils.ConvertUtils;
import com.maillets.teambuilder.dto.TeamDto;
import com.maillets.teambuilder.entities.Team;
import com.maillets.teambuilder.repository.TeamRepository;

@RestController
@RequestMapping("/teams")
public class TeamController {

	@Autowired
	private TeamRepository teamRepository;

	@RequestMapping(value = "", method = { RequestMethod.GET })
	public List<TeamDto> getTeams() {
		List<Team> teams = teamRepository.findAll();
		List<TeamDto> dtos = new ArrayList<>();
		for (Team team : teams) {
			dtos.add(ConvertUtils.teamConverter(team));
		}
		return dtos;
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public TeamDto getTeam(@PathVariable(value = "id") String id) {
		Team team = validateAndGetTeam(id);
		return ConvertUtils.teamConverter(team);
	}

	private Team validateAndGetTeam(String id) {
		int parsedInt = 0;
		try {
			parsedInt = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			throw new BadRequestException();
		}

		Team team = teamRepository.findOne(parsedInt);
		if (team == null) {
			throw new EntityNotFoundException(id);
		}
		return team;
	}
}
