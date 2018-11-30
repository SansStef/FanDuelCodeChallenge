package com.fanduel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fanduel.Sport;
import com.fanduel.exception.UnsupportedSportException;
import com.fanduel.models.Player;
import com.fanduel.models.PlayerStats;
import com.fanduel.repositories.PlayerRepository;
import com.fanduel.repositories.PlayerStatsRepository;

@RestController
public class PlayersController {
	
	@Autowired
	private PlayerRepository playerRepository;
	@Autowired
	private PlayerStatsRepository playerStatsRepository;
	
	@GetMapping("/{sport}/players/{id}")
	public ResponseEntity<Player> getPlayerBySportAndID(@PathVariable String sport, @PathVariable("id") int id) 
			throws UnsupportedSportException 
	{
		return new ResponseEntity<Player>(playerRepository.get(Sport.valueOf(sport.toUpperCase()),id), HttpStatus.OK);
	}

	@GetMapping("/{sport}/players/{id}/stats")
	public ResponseEntity<List<PlayerStats>> getPlayerStatsBySportAndID(@PathVariable String sport, @PathVariable("id") int id) 
			throws UnsupportedSportException 
	{
		return new ResponseEntity<List<PlayerStats>>(playerStatsRepository.getPlayerStats(Sport.valueOf(sport.toUpperCase()),id), HttpStatus.OK);
	}

	
	@GetMapping("/{sport}/players")
	public ResponseEntity<List<Player>> getPlayersBySport(@PathVariable String sport, @RequestParam(value = "date", required = false) String date) 
			throws UnsupportedSportException 
	{
		if(date!=null) {
			return new ResponseEntity<List<Player>>(playerRepository.getAll(Sport.valueOf(sport.toUpperCase()), date), HttpStatus.OK);
		}
		
		return new ResponseEntity<List<Player>>(playerRepository.getAll(Sport.valueOf(sport.toUpperCase())), HttpStatus.OK);
	}
}
