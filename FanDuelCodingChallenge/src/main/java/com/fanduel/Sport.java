package com.fanduel;

import java.util.Arrays;

public enum Sport {
	
	NFL("QB,WR,RB,TE,K,P,KR,PR"),
	MLB("SP,RP,C,1B,2B,3B,SS,LF,RF,CF,DH");
	
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
