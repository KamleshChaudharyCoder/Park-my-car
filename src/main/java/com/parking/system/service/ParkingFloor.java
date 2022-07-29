package com.parking.system.service;

import com.parking.system.exception.InvalidSlotNumberException;
import com.parking.system.exception.InvalidVehicleNumber;
import com.parking.system.exception.NoEmptySlotAvailable;
import com.parking.system.exception.NoSlotAvailableWithColour;
import com.parking.system.exception.NoSlotAvailableWithRegNo;
import com.parking.system.model.Vehicle;

public interface ParkingFloor {

	boolean createParkingSLot(int numberOfSlots);

	boolean parkVehicle(Vehicle vehicle) throws NoEmptySlotAvailable;

	void unPark(int slotNumber) throws InvalidSlotNumberException;

	String getSlotByVehicleNumber(String carNumber) throws InvalidVehicleNumber;

	String getSlotByColour(String colour) throws NoSlotAvailableWithColour;

	String getRegNoByColour(String colour) throws NoSlotAvailableWithRegNo;
}
