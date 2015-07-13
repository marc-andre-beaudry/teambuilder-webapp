package com.maillets.teambuilder.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.maillets.teambuilder.controller.exceptions.BadRequestException;
import com.maillets.teambuilder.controller.exceptions.EntityNotFoundException;
import com.maillets.teambuilder.controller.utils.ConvertUtils;
import com.maillets.teambuilder.dto.PlayerDto;
import com.maillets.teambuilder.dto.TeamDto;
import com.maillets.teambuilder.entities.Player;
import com.maillets.teambuilder.entities.Team;
import com.maillets.teambuilder.entities.User;
import com.maillets.teambuilder.repository.PlayerRepository;
import com.maillets.teambuilder.repository.TeamRepository;
import com.maillets.teambuilder.repository.UserRepository;

@RestController
@RequestMapping("/teams")
public class TeamController {

	private static final String TEAM_BASE_PATH = "";
	private static final String TEAM_ID_PATH = "/{teamId}";
	private static final String TEAM_PLAYERS_PATH = "/{teamId}/players";
	private static final String TEAM_PLAYERS_ID_PATH = "/{teamId}/players/{playerId}";

	private static final String TEAM_ID_PATH_VARIABLE = "teamId";
	private static final String PLAYER_ID_PATH_VARIABLE = "playerId";

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = TEAM_BASE_PATH, method = { RequestMethod.GET })
	public List<TeamDto> getTeams() {
		List<Team> teams = teamRepository.findAll();
		List<TeamDto> dtos = teams.stream()
				.map(team -> ConvertUtils.teamConverter(team))
				.collect(Collectors.toList());
		return dtos;
	}

	@RequestMapping(value = TEAM_ID_PATH, method = { RequestMethod.GET })
	public TeamDto getTeam(@PathVariable(value = TEAM_ID_PATH_VARIABLE) String id) {
		Team team = validateAndGetTeam(id);
		return ConvertUtils.teamConverter(team);
	}

	@RequestMapping(value = TEAM_PLAYERS_PATH, method = { RequestMethod.GET })
	public List<PlayerDto> getTeamPlayers(@PathVariable(value = TEAM_ID_PATH_VARIABLE) String id) {
		Team team = validateAndGetTeam(id);
		List<PlayerDto> dtos = team.getTeamPlayers().stream()
				.map(player -> ConvertUtils.playerConverter(player))
				.collect(Collectors.toList());
		return dtos;
	}

	@RequestMapping(value = TEAM_BASE_PATH, method = { RequestMethod.POST })
	public Integer createTeam(@RequestBody TeamDto dto) {

		// We do not accept id when we send a POST
		if (dto.getId() != null) {
			throw new BadRequestException();
		}

		// TODO, get user identity when we do a post
		User user = getUserForIdentify("");

		// Ignore dto ownerUserName and ownerId properties
		Team newEntity = new Team();
		newEntity.setName(dto.getName());
		newEntity.setAbb(dto.getAbb());
		newEntity.setIsNhl(dto.getIsNhl());
		newEntity.setIsPublic(dto.getIsPublic());
		newEntity.setOwner(user);

		Team savedEntity = teamRepository.saveAndFlush(newEntity);
		return savedEntity.getId();
	}

	@RequestMapping(value = TEAM_PLAYERS_PATH, method = { RequestMethod.POST })
	public void addTeamPlayer(@PathVariable(value = TEAM_ID_PATH_VARIABLE) String id, @RequestBody PlayerDto dto) {

		Team team = validateAndGetTeam(id);
		Player player = playerRepository.findOne(dto.getId());
		if (player == null) {
			throw new EntityNotFoundException(id);
		}

		if (!team.getTeamPlayers().stream().anyMatch(p -> p.getId() == player.getId())) {
			team.getTeamPlayers().add(player);
		}
	}

	@RequestMapping(value = TEAM_ID_PATH, method = { RequestMethod.PUT })
	public void updateTeam(@PathVariable(value = TEAM_ID_PATH_VARIABLE) String id, @RequestBody TeamDto dto) {

		// TODO, get user identity when we do a put
		User user = getUserForIdentify("");

		Team team = validateAndGetTeam(id);

		// Ignore dto ownerUserName and ownerId properties
		team.setName(dto.getName());
		team.setAbb(dto.getAbb());
		team.setIsNhl(dto.getIsNhl());
		team.setIsPublic(dto.getIsPublic());
		teamRepository.saveAndFlush(team);
	}

	@RequestMapping(value = TEAM_ID_PATH, method = { RequestMethod.DELETE })
	public void deleteTeam(@PathVariable(value = TEAM_ID_PATH_VARIABLE) String id) {
		Team team = validateAndGetTeam(id);
		teamRepository.delete(team);
	}

	@RequestMapping(value = TEAM_PLAYERS_ID_PATH, method = { RequestMethod.DELETE })
	public void removeTeamPlayer(@PathVariable(value = TEAM_ID_PATH_VARIABLE) String teamId, @PathVariable(value = PLAYER_ID_PATH_VARIABLE) String playerId) {

		Team team = validateAndGetTeam(teamId);

		int parsedTeamPlayer = ControllerUtils.parseToId(playerId);

		team.getTeamPlayers().removeIf(p -> p.getId() == parsedTeamPlayer);
		teamRepository.saveAndFlush(team);
	}

	private User getUserForIdentify(String identity) {
		return userRepository.findAll().get(0);
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
