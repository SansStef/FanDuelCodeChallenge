package com.fanduel.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GameState {
	private int id;
	@JsonProperty("game_id")
	private int gameId;
	@JsonProperty("home_team_score")
	private int homeTeamScore;
	@JsonProperty("away_team_score")
	private int awayTeamScore;
	private String broadcast;
	private int quarter;
	@JsonProperty("time_left_in_quarter")
	private String timeLeftInQuarter;
	@JsonProperty("game_status")
	private GameStatus gameStatus;
	
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
	
	public int getHomeTeamScore() {
		return homeTeamScore;
	}
	public void setHomeTeamScore(int homeTeamScore) {
		this.homeTeamScore = homeTeamScore;
	}

	public int getAwayTeamScore() {
		return awayTeamScore;
	}
	public void setAwayTeamScore(int awayTeamScore) {
		this.awayTeamScore = awayTeamScore;
	}
	
	public String getBroadcast() {
		return broadcast;
	}
	public void setBroadcast(String broadcast) {
		this.broadcast = broadcast;
	}
	
	public int getQuarter() {
		return quarter;
	}
	public void setQuarter(int quarter) {
		this.quarter = quarter;
	}
	
	public String getTimeLeftInQuarter() {
		return timeLeftInQuarter;
	}
	public void setTimeLeftInQuarter(String timeLeftInQuarter) {
		this.timeLeftInQuarter = timeLeftInQuarter;
	}
	
	public GameStatus getGameStatus() {
		return gameStatus;
	}
	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}
}
