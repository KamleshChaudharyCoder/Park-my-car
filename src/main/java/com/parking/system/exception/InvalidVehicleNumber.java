package com.parking.system.exception;

public class InvalidVehicleNumber extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidVehicleNumber(String s) {
        super(s);
    }
}
