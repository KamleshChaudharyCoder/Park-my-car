package com.parking.system.exception;

public class InvalidSlotNumberException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidSlotNumberException(String message) {
		super(message);
	}
}
