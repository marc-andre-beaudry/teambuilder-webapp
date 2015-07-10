package com.maillets.teambuilder.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.maillets.teambuilder.TestApplication;
import com.maillets.teambuilder.repository.PlayerRepository;
import com.maillets.teambuilder.repository.TeamRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestApplication.class)
@WebAppConfiguration
public class TeamControllerTest extends BaseRestControllerTest {

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private PlayerRepository playerRepository;

	@Before
	public void setup() throws Exception {
		playerRepository.deleteAllInBatch();
		teamRepository.deleteAllInBatch();
		mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void get_all_on_empty_repo_then_is_ok() throws Exception {
		mockMvc.perform(get("/teams")).andExpect(status().isOk());
	}

	@Test
	public void get_all_on_empty_repo_then_is_empty() throws Exception {
		mockMvc.perform(get("/teams")).andExpect(status().isOk());
	}

	@Test
	public void get_id_on_empty_repo_then_is_not_found() throws Exception {
		mockMvc.perform(get("/teams/1")).andExpect(status().isNotFound());
	}

	@Test
	public void get_id_with_invalid_id_format_then_bad_request() throws Exception {
		mockMvc.perform(get("/teams/one")).andExpect(status().isBadRequest());
	}
}
