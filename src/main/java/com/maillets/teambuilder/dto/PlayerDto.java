package com.maillets.teambuilder.dto;

public class PlayerDto {

	private Integer id;
	private String firstName;
	private String lastName;
	private Integer position;
	private Integer salary;
	private String team;
	private String teamAbb;

	public PlayerDto() {
	}

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

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getTeamAbb() {
		return teamAbb;
	}

	public void setTeamAbb(String teamAbb) {
		this.teamAbb = teamAbb;
	}
}
