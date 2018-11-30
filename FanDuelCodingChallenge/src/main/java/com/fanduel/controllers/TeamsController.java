package com.fanduel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fanduel.Sport;
import com.fanduel.exception.UnsupportedSportException;
import com.fanduel.models.Team;
import com.fanduel.repositories.TeamRepository;

@RestController
public class TeamsController {
	
	@Autowired
	private TeamRepository teamRepository;
	
	@GetMapping("/{sport}/teams/{id}")
	public ResponseEntity<Team> getTeamBySportAndID(@PathVariable String sport, @PathVariable("id") int id) 
			throws UnsupportedSportException 
	{
		return new ResponseEntity<Team>(teamRepository.get(Sport.valueOf(sport.toUpperCase()),id), HttpStatus.OK);
	}

	
	@GetMapping("/{sport}/teams")
	public ResponseEntity<List<Team>> getTeamsBySport(@PathVariable String sport) 
			throws UnsupportedSportException 
	{
		return new ResponseEntity<List<Team>>(teamRepository.getAll(Sport.valueOf(sport.toUpperCase())), HttpStatus.OK);
	}
}