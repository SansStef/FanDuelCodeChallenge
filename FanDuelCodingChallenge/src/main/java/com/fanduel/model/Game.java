package com.fanduel.model;

import java.util.Date;

public class Game {

	private int id;
	private int home_team_id;
	private int away_team_id;
	private Date date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getHome_team_id() {
		return home_team_id;
	}
	public void setHome_team_id(int home_team_id) {
		this.home_team_id = home_team_id;
	}
	public int getAway_team_id() {
		return away_team_id;
	}
	public void setAway_team_id(int away_team_id) {
		this.away_team_id = away_team_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
