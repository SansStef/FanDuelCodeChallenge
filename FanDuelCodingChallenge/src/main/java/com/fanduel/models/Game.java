package com.fanduel.models;

import java.util.Date;

import com.fanduel.Sport;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Game {

	private int id;
	private int homeTeamId;
	private int awayTeamId;
	private Date date;
	private Sport sport;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@JsonProperty("home_team_id")
	public int getHomeTeamId() {
		return homeTeamId;
	}
	public void setHomeTeamId(int homeTeamId) {
		this.homeTeamId = homeTeamId;
	}
	@JsonProperty("away_team_id")
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
}
