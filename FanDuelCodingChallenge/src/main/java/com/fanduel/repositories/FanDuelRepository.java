package com.fanduel.repositories;

import com.fanduel.Sport;
import com.fanduel.exception.UnsupportedSportException;

public class FanDuelRepository {
	
	public void checkSupportedSport(final String sport) throws UnsupportedSportException {
		try {
			Sport.valueOf(sport.toUpperCase());
		}
		catch(IllegalArgumentException e) {
			throw new UnsupportedSportException(sport);
		}
	}
	
}
