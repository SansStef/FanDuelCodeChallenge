package com.fanduel.repositories;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fanduel.Sport;
import com.fanduel.models.Game;
import com.fanduel.models.Player;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class PlayerRepository extends FanDuelRepository<Player> {

	@Autowired
	private PlayerStatsRepository playerStatsRepository;
	@Autowired
	private GameRepository gameRepository;
	
	public PlayerRepository() throws JsonMappingException, FileNotFoundException, IOException, ParseException {
	}

	private static final String PLAYERS_JSON_FILENAME = "players.json";
	
	@Override
	protected void setup() throws FileNotFoundException, IOException, ParseException, JsonMappingException {
		JSONParser parser = new JSONParser();
		ObjectMapper objectMapper = new ObjectMapper();
		
		//TODO - need to create all .json files for all sports before we can loop here
//		for(Sport sport : Sport.values()){
		Sport sport = Sport.NBA;
		
			HashMap<Integer, Player> sportPlayers = new HashMap<Integer, Player>();
			dataModels.put(sport, sportPlayers);
		
			JSONArray jsonArray = readJsonFile(sport, parser, PLAYERS_JSON_FILENAME);
			
			Player player;
			for(Object json : jsonArray) {
				player = objectMapper.readValue(json.toString(), Player.class);
				
				sportPlayers.put(player.getId(), player);
			}
	}

	/**
	 * Ideally the data in in a database so we could query like below for it:
	 * 
	 * select p.id, p.player_name, p.team_id from players p
	 * join player_stats ps on ps.player_id = p.id
	 * join games g on g.id = ps.game_id
	 * where game.date = {datePlayed}
	 */
	public List<Player> getAll(final Sport sport, final String datePlayed) {
		//Players who have PlayerStats which have Games played on datePlayed
		
		List<Integer> playedGames = gameRepository.getAll(sport, datePlayed).stream().map(game -> game.getId()).collect(Collectors.toList());
		List<Integer> playersPlayed = playerStatsRepository.getAll(sport).stream().filter(stats -> playedGames.contains(stats.getGameId())).map(stats -> stats.getPlayerId()).collect(Collectors.toList());

		return dataModels.get(sport).values().stream().filter(player -> playersPlayed.contains(player.getId())).collect(Collectors.toList());
	}

}
