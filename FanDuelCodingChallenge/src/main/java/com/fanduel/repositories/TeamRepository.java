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
import org.springframework.stereotype.Component;

import com.fanduel.Sport;
import com.fanduel.models.Team;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class TeamRepository extends FanDuelRepository<Team> {

	public TeamRepository() throws JsonMappingException, FileNotFoundException, IOException, ParseException {
	}

	private static final String TEAMS_JSON_FILENAME = "teams.json";
	
	protected void setup() throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		ObjectMapper objectMapper = new ObjectMapper();
		
		//TODO - need to create all .json files for all sports before we can loop here
//		for(Sport sport : Sport.values()){
		Sport sport = Sport.NBA;
		
			HashMap<Integer, Team> sportTeams = new HashMap<Integer, Team>();
			dataModels.put(sport, sportTeams);
		
			JSONArray jsonArray = readJsonFile(sport, parser, TEAMS_JSON_FILENAME);
			
			Team team;
			for(Object json : jsonArray) {
				team = objectMapper.readValue(json.toString(), Team.class);
				team.setSport(sport);
				
				sportTeams.put(team.getId(), team);
			}
	}

}
