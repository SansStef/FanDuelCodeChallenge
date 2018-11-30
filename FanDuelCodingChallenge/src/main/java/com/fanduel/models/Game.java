package com.fanduel.models;

import java.util.Date;

import com.fanduel.Sport;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Game {
	
	private int id;
	@JsonProperty("home_team_id")
	private int homeTeamId;
	@JsonProperty("away_team_id")
	private int awayTeamId;
	private Date date;
	private Sport sport;
	private GameState gameState;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getHomeTeamId() {
		return homeTeamId;
	}
	public void setHomeTeamId(int homeTeamId) {
		this.homeTeamId = homeTeamId;
	}
	public int getAwayTeamId() {
		return awayTeamId;
	}
	public void setAwayTeamId(int awayTeamId) {
		this.awayTeamId = awayTeamId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Sport getSport() {
		return sport;
	}
	public void setSport(Sport sport) {
		this.sport = sport;
	}
	public GameState getGameState() {
		return gameState;
	}
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
}
