package com.fanduel.repositories;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fanduel.Sport;
import com.fanduel.models.Game;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class GameRepository  {

	private static final String JSON_FILENAME = "games.json";
	private static final String JSON_DATE_FORMAT = "M/d/yyyy";
	
	private Map<Sport, Map<Integer,Game>> games;
	
	public GameRepository() throws FileNotFoundException, IOException, ParseException {
		games = new HashMap<Sport, Map<Integer,Game>>(Sport.values().length);
		setupGames();
	}
	
	private void setupGames() throws FileNotFoundException, IOException, ParseException, JsonMappingException {
		JSONParser parser = new JSONParser();
		ObjectMapper objectMapper = new ObjectMapper();
		DateFormat df = new SimpleDateFormat(JSON_DATE_FORMAT);
		objectMapper.setDateFormat(df);
		
		//TODO - need to create all .json files for all sports before we can loop here
//		for(Sport sport : Sport.values()){
		Sport sport = Sport.NBA;
		
			HashMap<Integer, Game> sportGames = new HashMap<Integer, Game>();
			games.put(sport, sportGames);
		
			Resource resource = new ClassPathResource(getJsonFilePath(sport));
			InputStream resourceInputStream = resource.getInputStream();
		
			JSONArray jsonArray = (JSONArray) parser.parse(new InputStreamReader(resourceInputStream));
			
			Game game;
			for(Object json : jsonArray) {
				game = objectMapper.readValue(json.toString(), Game.class);
				game.setSport(sport);
				
				sportGames.put(game.getId(), game);
			}
			
//		}
		
	}

	private String getJsonFilePath(final Sport sport) {
		return sport.name() + "/" + JSON_FILENAME;
	}
	
	public Game get(final Sport sport, final int id) { 
				
		return games.get(sport).get(id);
	}

	public List<Game> getAll(final Sport sport) {
		
		return games.get(sport).values().stream().collect(Collectors.toList());
	}

}
