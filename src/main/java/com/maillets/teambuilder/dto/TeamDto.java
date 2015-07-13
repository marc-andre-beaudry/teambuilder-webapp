package com.maillets.teambuilder.dto;

public class TeamDto {

	private Integer id;
	private String name;
	private String abb;
	private Integer ownerId;
	private Integer ownerUserName;
	private boolean isPublic;
	private boolean isNhl;

	public TeamDto() {
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

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public Integer getOwnerUserName() {
		return ownerUserName;
	}

	public void setOwnerUserName(Integer ownerUserName) {
		this.ownerUserName = ownerUserName;
	}

	public boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public boolean getIsNhl() {
		return isNhl;
	}

	public void setIsNhl(boolean isNhl) {
		this.isNhl = isNhl;
	}
}
