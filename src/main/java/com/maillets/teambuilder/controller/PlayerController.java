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
import com.maillets.teambuilder.dto.PlayerDto;
import com.maillets.teambuilder.entities.Player;
import com.maillets.teambuilder.repository.PlayerRepository;

@RestController
@RequestMapping("/players")
public class PlayerController {

	@Autowired
	private PlayerRepository playerRepository;

	@RequestMapping(value = "", method = { RequestMethod.GET })
	public List<PlayerDto> getPlayers() {
		List<Player> players = playerRepository.findAll();
		List<PlayerDto> dtos = new ArrayList<>();
		for (Player player : players) {
			dtos.add(ConvertUtils.playerConverter(player));
		}
		return dtos;
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public PlayerDto getPlayer(@PathVariable(value = "id") String id) {
		Player player = validateAndGetPlayer(id);
		return ConvertUtils.playerConverter(player);
	}

	private Player validateAndGetPlayer(String id) {
		int parsedInt = 0;
		try {
			parsedInt = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			throw new BadRequestException();
		}

		Player player = playerRepository.findOne(parsedInt);
		if (player == null) {
			throw new EntityNotFoundException(id);
		}
		return player;
	}
}
