package com.fanduel.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Player {
	private int id;
	private String name;
	@JsonProperty("team_id")
	private int teamId;
	private List<PlayerStats> playerStats;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public List<PlayerStats> getPlayerStats() {
		return playerStats;
	}
	public void setPlayerStats(List<PlayerStats> playerStats) {
		this.playerStats = playerStats;
	}
}
