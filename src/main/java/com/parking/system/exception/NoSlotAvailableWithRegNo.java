package com.parking.system.exception;

public class NoSlotAvailableWithRegNo extends Exception {
	 private static final long serialVersionUID = 1L;

		public NoSlotAvailableWithRegNo(String message) {
	        super(message);
	    }
}
