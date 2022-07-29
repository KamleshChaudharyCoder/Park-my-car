package com.parking.system.exception;

public class VehicleNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public VehicleNotFoundException(String format) {
		super(format);
	}
}
