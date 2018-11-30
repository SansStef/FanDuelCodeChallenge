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
import com.fanduel.models.Game;
import com.fanduel.repositories.GameRepository;

@RestController
public class GamesController {
	
	@Autowired
	private GameRepository gamesRepository;
	
	@GetMapping("/{sport}/games/{id}")
	public ResponseEntity<Game> getGameBySportAndID(@PathVariable String sport, @PathVariable("id") int id) 
			throws UnsupportedSportException 
	{
		return new ResponseEntity<Game>(gamesRepository.get(Sport.valueOf(sport.toUpperCase()),id), HttpStatus.OK);
	}

	
	@GetMapping("/{sport}/games")
	public ResponseEntity<List<Game>> getGamesBySport(@PathVariable String sport, @RequestParam(value = "date", required = false) String date) 
			throws UnsupportedSportException 
	{
		if(date!=null) {
			return new ResponseEntity<List<Game>>(gamesRepository.getAll(Sport.valueOf(sport.toUpperCase()), date), HttpStatus.OK);
		}
		
		return new ResponseEntity<List<Game>>(gamesRepository.getAll(Sport.valueOf(sport.toUpperCase())), HttpStatus.OK);
	}
}
