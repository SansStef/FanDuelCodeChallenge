package com.fanduel.repositories;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import com.fanduel.Sport;
import com.fanduel.models.PlayerStats;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class PlayerStatsRepository extends FanDuelRepository<PlayerStats> {

	private static final String PLAYER_STATS_JSON_FILENAME = "player_stats.json";

	public PlayerStatsRepository() throws JsonMappingException, FileNotFoundException, IOException, ParseException {
		super();
	}

	/**
	 * Creates PlayerStats objects from json file
	 */
	@Override
	protected void setup() throws FileNotFoundException, IOException, ParseException, JsonMappingException {
		JSONParser parser = new JSONParser();
		ObjectMapper objectMapper = new ObjectMapper();
		
		//TODO - need to create all .json files for all sports before we can loop here
//		for(Sport sport : Sport.values()){
		Sport sport = Sport.NBA;
		
			HashMap<Integer, PlayerStats> sportPlayers = new HashMap<Integer, PlayerStats>();
			dataModels.put(sport, sportPlayers);
		
			JSONArray jsonArray = readJsonFile(sport, parser, PLAYER_STATS_JSON_FILENAME);
			
			PlayerStats playerStats;
			for(Object json : jsonArray) {
				playerStats = objectMapper.readValue(json.toString(), PlayerStats.class);
				
				sportPlayers.put(playerStats.getId(), playerStats);
			}
	}

	/**
	 * Gets all PlayerStats objects for a specific Player
	 */
	public List<PlayerStats> getPlayerStats(final Sport sport, final int playerId) {
		return dataModels.get(sport).values().stream().filter(stats -> stats.getPlayerId() == playerId).collect(Collectors.toList());
	}

}
