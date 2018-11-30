package com.fanduel.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerStats {
	private int id;
	@JsonProperty("game_id")
	private int gameId;
	@JsonProperty("player_id")
	private int playerId;
	@JsonProperty("team_id")
	private int teamId;
	private int points;
	private int assists;
	private int rebounds;
	private int nerd;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getAssists() {
		return assists;
	}
	public void setAssists(int assists) {
		this.assists = assists;
	}
	public int getRebounds() {
		return rebounds;
	}
	public void setRebounds(int rebounds) {
		this.rebounds = rebounds;
	}
	public int getNerd() {
		return nerd;
	}
	public void setNerd(int nerd) {
		this.nerd = nerd;
	}
}
