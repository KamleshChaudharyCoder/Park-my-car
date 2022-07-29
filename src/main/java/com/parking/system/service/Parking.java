package com.parking.system.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.parking.system.exception.InvalidSlotNumberException;
import com.parking.system.exception.InvalidVehicleNumber;
import com.parking.system.exception.NoEmptySlotAvailable;
import com.parking.system.exception.NoSlotAvailableWithColour;
import com.parking.system.exception.NoSlotAvailableWithRegNo;
import com.parking.system.model.Slot;
import com.parking.system.model.Vehicle;
import com.parking.system.validator.InputValidator;

public class Parking implements ParkingFloor {
	private static Parking parkingLot;
	private final List<Slot> slots;

	private Parking() {
		this.slots = new ArrayList<>();
	}

	public static Parking getParkingFloor() {
		if (parkingLot == null)
			parkingLot = new Parking();
		return parkingLot;
	}

	/**
	 * this method find nextEmpty slot on the floor
	 * 
	 * @author kamlesh chaudhary
	 * 
	 */
	private Slot getNextEmptySlotOnFloor() throws NoEmptySlotAvailable {
		for (Slot slot : slots) {
			if (slot.isEmpty()) {
				return slot;
			}
		}
		throw new NoEmptySlotAvailable("No Empty Slot available");
	}

	/**
	 * this method create new parking slot on floor return true
	 * 
	 * @author kamlesh chaudhary
	 * 
	 */
	public boolean createParkingSLot(int numberOfSlots) {
		if (inputValidator(InputValidator.isValidSlotNumber(numberOfSlots)) || slots.size() > 0)
			return false;

		for (int i = 1; i <= numberOfSlots; i++) {
			slots.add(new Slot(UUID.randomUUID().toString(), i, true));
		}
		System.out.printf("Created a parking lot with %s slots %n", numberOfSlots);
		return true;
	}

	/**
	 * this method use for validate input return true
	 * 
	 * @author kamlesh chaudhary
	 * 
	 */
	private boolean inputValidator(boolean validSlotNumber) {
		if (!validSlotNumber) {
			return true;
		}
		return false;
	}

	/**
	 * this method use for parking vehicle with their details return true
	 * 
	 * @author kamlesh chaudhary
	 * 
	 */
	public boolean parkVehicle(Vehicle vehicle) throws NoEmptySlotAvailable {
		Slot nextEmptySlotOnFloor = getNextEmptySlotOnFloor();
		nextEmptySlotOnFloor.placeVehicle(vehicle);
		System.out.printf("Allocated slot number: %d \n", nextEmptySlotOnFloor.getSlotNumber());
		return true;
	}

	/**
	 * this method use for Unpark vehicle with their details
	 * 
	 * @author kamlesh chaudhary
	 * 
	 */
	public void unPark(int slotNumber) throws InvalidSlotNumberException {
		slots.forEach(slot -> {
			if (slot.getSlotNumber().equals(slotNumber)) {
				System.out.println("Slot number " + slotNumber + " is free");
				slot.removeVehicle();
			}
		});

	}

	/**
	 * this method use for showing parking details
	 * 
	 * @author kamlesh chaudhary
	 * 
	 */
	public void printStatus() {
		System.out.println("Slot No.  Registration No.  Colour");
		slots.forEach(slot -> {
			if (!slots.isEmpty()) {
				Vehicle parkVehicle = slot.getParkVehicle();
				if (parkVehicle != null)
					System.out.printf("   %d      %s      %s\n", slot.getSlotNumber(), parkVehicle.getVehicleNumber(),
							parkVehicle.getVehicleColour());
			}
		});
	}

	/**
	 * this method use for getSlotByVehicleNumber return String
	 * 
	 * @author kamlesh chaudhary
	 * 
	 */
	public String getSlotByVehicleNumber(String vehicleNumber) throws InvalidVehicleNumber {
		String slotValue = slots.stream().filter(i -> i.getParkVehicle().getVehicleNumber().equals(vehicleNumber))
				.map(i -> i.getSlotNumber().toString()).collect(Collectors.joining(","));
		Optional<String> values = Optional.ofNullable(slotValue);

		if (values.isPresent() && !values.get().isEmpty()) {
			return values.get();
		} else {
			throw new InvalidVehicleNumber(vehicleNumber);
		}

	}

	/**
	 * this method use for getSlotByColour return String
	 * 
	 * @author kamlesh chaudhary
	 * 
	 */
	public String getSlotByColour(String colour) throws NoSlotAvailableWithColour {
		String slotValue = slots.stream().filter(i -> i.getParkVehicle().getVehicleColour().equals(colour))
				.map(i -> i.getSlotNumber().toString()).collect(Collectors.joining(","));

		Optional<String> values = Optional.ofNullable(slotValue);

		if (values.isPresent() && !values.get().isEmpty()) {
			return values.get();
		} else {
			throw new NoSlotAvailableWithColour(colour);
		}

	}

	/**
	 * this method use for getRegNoByColour return String
	 * 
	 * @author kamlesh chaudhary
	 * 
	 */
	public String getRegNoByColour(String colour) throws NoSlotAvailableWithRegNo {
		String regNo = slots.stream().filter(i -> i.getParkVehicle().getVehicleColour().equals(colour))
				.map(i -> i.getParkVehicle().getVehicleNumber().toString()).collect(Collectors.joining(","));
		Optional<String> values = Optional.ofNullable(regNo);

		if (values.isPresent() && !values.get().isEmpty()) {
			return values.get();
		} else {
			throw new NoSlotAvailableWithRegNo(colour);
		}

	}

}