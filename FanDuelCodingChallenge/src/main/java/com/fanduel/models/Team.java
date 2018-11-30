package com.fanduel.models;

import com.fanduel.Sport;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Team {
	private int id;
	private String name;
	private String city;
	@JsonProperty("full_name")
	private String fullName;
	@JsonProperty("abbrev")
	private String abbreviation;
	private Sport sport;

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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public Sport getSport() {
		return sport;
	}
	public void setSport(Sport sport) {
		this.sport = sport;
	}
}
