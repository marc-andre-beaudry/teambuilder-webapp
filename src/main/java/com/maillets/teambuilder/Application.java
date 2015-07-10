package com.maillets.teambuilder;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maillets.teambuilder.dto.PlayerDto;
import com.maillets.teambuilder.entities.Player;
import com.maillets.teambuilder.entities.Team;
import com.maillets.teambuilder.repository.PlayerRepository;
import com.maillets.teambuilder.repository.TeamRepository;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

	@Bean
	CommandLineRunner init(final TeamRepository teamRepository, final PlayerRepository playerRepository) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				InputStream is = TestApplication.class.getResourceAsStream("seed.json");
				ObjectMapper mapper = new ObjectMapper();

				List<PlayerDto> dtos = mapper.readValue(is, new TypeReference<List<PlayerDto>>() {
				});

				Map<String, Team> teamMap = new HashMap<>();

				int teamIds = -1;
				for (PlayerDto dto : dtos) {
					Team team = null;
					if (!teamMap.containsKey(dto.getTeam())) {
						team = new Team();
						team.setId(teamIds--);
						team.setAbb(dto.getTeamAbb());
						team.setName(dto.getTeam());
						team.setIsPublic(true);
						team.setIsNhl(true);
						team = teamRepository.saveAndFlush(team);
						teamMap.put(dto.getTeam(), team);
					} else {
						team = teamMap.get(dto.getTeam());
					}
					Player player = new Player();
					player.setFirstName(dto.getFirstName());
					player.setLastName(dto.getLastName());
					player.setSalary(dto.getSalary());
					player.setPosition(dto.getPosition());
					player.setTeam(team);
					playerRepository.saveAndFlush(player);
				}

				System.out.println("Init done!");
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}
}