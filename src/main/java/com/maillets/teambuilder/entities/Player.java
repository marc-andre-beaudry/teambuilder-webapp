package com.maillets.teambuilder.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "player")
public class Player {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	private Integer position;

	private Integer salary;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nhlTeamId")
	private Team nhlTeam;

	@ManyToMany(mappedBy = "teamPlayers")
	private Set<Team> teams = new HashSet<Team>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public Team getNhlTeam() {
		return nhlTeam;
	}

	public void setNhlTeam(Team team) {
		this.nhlTeam = team;
	}

	public Set<Team> getTeams() {
		return teams;
	}

	public void setTeams(Set<Team> customTeams) {
		this.teams = customTeams;
	}
}
