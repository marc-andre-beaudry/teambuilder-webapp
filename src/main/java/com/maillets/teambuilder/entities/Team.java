package com.maillets.teambuilder.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
	private boolean isPublic;

	@Column(nullable = false)
	private boolean isNhl;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ownerId")
	private User owner;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "team_player", joinColumns = { @JoinColumn(name = "player_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "team_id", referencedColumnName = "id") })
	private Set<Player> teamPlayers = new HashSet<Player>();

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

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Set<Player> getTeamPlayers() {
		return teamPlayers;
	}

	public void setTeamPlayers(Set<Player> teamPlayers) {
		this.teamPlayers = teamPlayers;
	}
}
