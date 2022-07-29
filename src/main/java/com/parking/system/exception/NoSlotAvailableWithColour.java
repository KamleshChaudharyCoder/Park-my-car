package com.parking.system.exception;

public class NoSlotAvailableWithColour extends Exception {
	 private static final long serialVersionUID = 1L;

		public NoSlotAvailableWithColour(String message) {
	        super(message);
	    }
}
