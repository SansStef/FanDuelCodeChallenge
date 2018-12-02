package com.fanduel.repositories;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	
	/**
	 * Data structure for holding generic model objects,
	 * separated by sport
	 * Map of [Sport] -> [object_id] -> [Object]
	 */
	protected Map<Sport, Map<Integer,F>> dataModels;
	
	private static final String FILTER_DATE_FORMAT = "MMddyyyy";
	private DateFormat filterDateFormat;
	
	public FanDuelRepository() throws JsonMappingException, FileNotFoundException, IOException, ParseException{
		dataModels = new HashMap<Sport, Map<Integer,F>>(Sport.values().length);
		filterDateFormat = new SimpleDateFormat(FILTER_DATE_FORMAT );
		setup();
	}
	
	/**
	 * Abstract method to be implemented in child repositories.
	 * Should handle creating and adding model objects to dataModels structure
	 */
	abstract protected void setup() throws FileNotFoundException, IOException, ParseException, JsonMappingException;
	
	/**
	 * Gets one generic model object by sport and object id
	 */
	public F get(final Sport sport, final int id) { 
		
		return dataModels.get(sport).get(id);
	}

	/**
	 * Gets all generic model objects for a specific sport
	 */
	public List<F> getAll(final Sport sport) {
		
		return dataModels.get(sport).values().stream().collect(Collectors.toList());
	}
	
	/**
	 * @return JSONArray of json objects found in specified filename
	 */
	protected JSONArray readJsonFile(final Sport sport, final JSONParser parser, final String filename) throws IOException, ParseException {
		Resource resource = new ClassPathResource(getJsonFilePath(sport, filename));
		InputStream resourceInputStream = resource.getInputStream();
		
		JSONArray jsonArray = (JSONArray) parser.parse(new InputStreamReader(resourceInputStream));
		
		return jsonArray;
	}
	
	/**
	 * json data files stored in folders named by sport
	 * (i.e.) nba/players.json
	 */
	protected String getJsonFilePath(final Sport sport, final String file) {
		return sport.name() + "/" + file;
	}
	
	/**
	 * @param gameDate
	 * @param filterDate (in FILTER_DATE_FORMAT)
	 * @return true/false if gameDate and filterDate are same day
	 */
	protected boolean sameDay(final Date gameDate, final String filterDate) {
		
		return filterDate.equals(filterDateFormat.format(gameDate));
	}
}
