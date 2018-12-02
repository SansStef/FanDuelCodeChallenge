package com.fanduel.exception;

public class UnsupportedPositionException extends Exception {
	private static final String MESSAGE = "Position: %s, not supported";
	
	public UnsupportedPositionException(String position) {
		super(String.format(MESSAGE, position));
	}
}
