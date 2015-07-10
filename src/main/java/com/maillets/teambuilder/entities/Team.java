package com.maillets.teambuilder.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "team")
public class Team {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false)
	private String name;

	private String abb;

	@Column(nullable = false)
	private Boolean isPublic;

	@Column(nullable = false)
	private Boolean isNhl;

	@OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
	private Set<Player> players;
	
	public Team() {
		players = new HashSet<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbb() {
		return abb;
	}

	public void setAbb(String abb) {
		this.abb = abb;
	}

	public Boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	public Boolean getIsNhl() {
		return isNhl;
	}

	public void setIsNhl(Boolean isNhl) {
		this.isNhl = isNhl;
	}

	public Set<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}
}
