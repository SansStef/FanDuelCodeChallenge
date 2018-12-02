package com.fanduel;

import java.util.Arrays;

/**
 * Sports supported by the system are included in this Enum
 * with a csv String of the supported positions of that sport
 *
 */
public enum Sport {
	
	NFL("QB,WR,RB,TE,K,P,KR,PR"),
	MLB("SP,RP,C,1B,2B,3B,SS,LF,RF,CF,DH"),
	NBA("PG,SG,SF,PF,C");
	
	private String positions;

	private static class Defaults{
		private static final String DELIM = ",";
	}
	
	private Sport(String positions) {
		this.positions = positions;
	}
	
	public String getPositions() {
		return positions;
	}
	
	public String[] getSupportedPositions() {
		return positions.split(Defaults.DELIM);
	}
	
	public boolean isSupportedPosition(String position) {
		return Arrays.stream(getSupportedPositions()).anyMatch(p -> p.equals(position));
	}
}
