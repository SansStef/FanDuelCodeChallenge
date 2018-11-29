package com.fanduel.exception;

public class UnsupportedSportException extends Exception {

	private static final String MESSAGE = "Sport: %s, not supported";
	
	public UnsupportedSportException(String sport) {
		super(String.format(MESSAGE, sport));
	}
}
