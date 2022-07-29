package com.parking.system.model;

public class Vehicle {

	private String vehicleNumber;
	private String vehicleColour;

	public Vehicle(String vehicleNumber, String vehicleColour) {
		this.vehicleNumber = vehicleNumber;
		this.vehicleColour = vehicleColour;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getVehicleColour() {
		return vehicleColour;
	}

	public void setVehicleColour(String vehicleColour) {
		this.vehicleColour = vehicleColour;
	}

}
