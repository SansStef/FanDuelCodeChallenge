package com.fanduel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fanduel.exception.UnsupportedSportException;
import com.fanduel.model.Game;
import com.fanduel.repositories.GameRepository;

@RestController
public class GamesController {
	
	@Autowired
	private GameRepository gamesRepository;
	
	@GetMapping("/{sport}/games/{id}")
	public ResponseEntity<Game> getGameBySportAndID(@PathVariable String sport, @PathVariable("id") int id) 
			throws UnsupportedSportException 
	{
		return new ResponseEntity<Game>(gamesRepository.get(sport,id), HttpStatus.OK);
	}
	
	@GetMapping("/{sport}/games/}")
	public ResponseEntity<List<Game>> getGamesBySport(@PathVariable String sport) 
			throws UnsupportedSportException 
	{
		return new ResponseEntity<List<Game>>(gamesRepository.getAll(sport), HttpStatus.OK);
	}
}
