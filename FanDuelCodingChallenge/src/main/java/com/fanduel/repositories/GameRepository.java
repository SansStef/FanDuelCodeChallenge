package com.fanduel.repositories;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import com.fanduel.Sport;
import com.fanduel.models.Game;
import com.fanduel.models.GameState;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class GameRepository extends FanDuelRepository<Game> {

	private static final String GAMES_JSON_FILENAME = "games.json";
	private static final String GAME_STATES_JSON_FILENAME = "game_states.json";
	private static final String JSON_DATE_FORMAT = "M/d/yyyy";
	private static final String FILTER_DATE_FORMAT = "MMddyyyy";
	
	private DateFormat filterDateFormat = new SimpleDateFormat(FILTER_DATE_FORMAT );
	
	public GameRepository() throws FileNotFoundException, IOException, ParseException {
		setup();
	}
	
	protected void setup() throws FileNotFoundException, IOException, ParseException, JsonMappingException {
		JSONParser parser = new JSONParser();
		ObjectMapper objectMapper = new ObjectMapper();
		DateFormat dateFormat = new SimpleDateFormat(JSON_DATE_FORMAT);
		objectMapper.setDateFormat(dateFormat);
		
		//TODO - need to create all .json files for all sports before we can loop here
//		for(Sport sport : Sport.values()){
		Sport sport = Sport.NBA;
		
			HashMap<Integer, Game> sportGames = new HashMap<Integer, Game>();
			dataModels.put(sport, sportGames);
		
			JSONArray jsonArray = readJsonFile(sport, parser, GAMES_JSON_FILENAME);
			
			Game game;
			for(Object json : jsonArray) {
				game = objectMapper.readValue(json.toString(), Game.class);
				game.setSport(sport);
				
				sportGames.put(game.getId(), game);
			}

			jsonArray = readJsonFile(sport, parser, GAME_STATES_JSON_FILENAME);
						
			GameState gameState;
			for(Object json : jsonArray) {
				gameState = objectMapper.readValue(json.toString(), GameState.class);
				sportGames.get(gameState.getGameId()).setGameState(gameState);
				
			}
			
//		}
		
	}
	
	public List<Game> getAll(final Sport sport, final String filterDate) {
		
		return dataModels.get(sport).values().stream()
				.filter(g -> sameDay(g.getDate(),filterDate))
				.collect(Collectors.toList());
	}

	private boolean sameDay(final Date gameDate, final String filterDate) {
		
		return filterDate.equals(filterDateFormat.format(gameDate));
	}
}
