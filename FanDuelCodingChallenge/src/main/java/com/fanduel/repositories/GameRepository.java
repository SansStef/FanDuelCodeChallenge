package com.fanduel.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fanduel.exception.UnsupportedSportException;
import com.fanduel.model.Game;

@Component
public class GameRepository  {

	public Game get(final String sport, final int id) { 
		Game g = new Game();
		g.setId(1);
		g.setHome_team_id(2);
		g.setAway_team_id(3);
		g.setDate(new Date());
		
		return g;
	}

	public List<Game> getAll(String sport) {
		return null;
	}

}
