package com.fanduel.repositories;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.fanduel.Sport;
import com.fasterxml.jackson.databind.JsonMappingException;

public abstract class FanDuelRepository<F> {
	
	protected Map<Sport, Map<Integer,F>> dataModels;
	
	public FanDuelRepository(){
		dataModels = new HashMap<Sport, Map<Integer,F>>(Sport.values().length);
	}
	
	abstract protected void setup() throws FileNotFoundException, IOException, ParseException, JsonMappingException;
	
	public F get(final Sport sport, final int id) { 
		
		return dataModels.get(sport).get(id);
	}

	public List<F> getAll(final Sport sport) {
		
		return dataModels.get(sport).values().stream().collect(Collectors.toList());
	}
	
	protected JSONArray readJsonFile(final Sport sport, final JSONParser parser, final String filename) throws IOException, ParseException {
		Resource resource = new ClassPathResource(getJsonFilePath(sport, filename));
		InputStream resourceInputStream = resource.getInputStream();
		
		JSONArray jsonArray = (JSONArray) parser.parse(new InputStreamReader(resourceInputStream));
		
		return jsonArray;
	}
	
	protected String getJsonFilePath(final Sport sport, final String file) {
		return sport.name() + "/" + file;
	}
}
