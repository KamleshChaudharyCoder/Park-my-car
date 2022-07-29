package com.parking.system.commands;

import com.parking.system.exception.InvalidSlotNumberException;
import com.parking.system.exception.InvalidVehicleNumber;
import com.parking.system.exception.NoEmptySlotAvailable;
import com.parking.system.exception.NoSlotAvailableWithColour;
import com.parking.system.exception.NoSlotAvailableWithRegNo;
import com.parking.system.model.Vehicle;
import com.parking.system.service.Parking;


public enum Command implements CommandI {
	
	create_parking_lot {
		@Override
		public void executeCommand(String[] details) {
			floor.createParkingSLot(Integer.parseInt(details[1]));
		}
	},
	park {
		@Override
		public void executeCommand(String[] details) {
			try {
				floor.parkVehicle(new Vehicle(details[1], details[2]));
			} catch (NoEmptySlotAvailable noEmptySlotAvailable) {
				System.out.println("Sorry, parking lot is full");
			}
		}
	},
	leave {
		@Override
		public void executeCommand(String[] details) {
			try {
				floor.unPark(Integer.parseInt(details[1]));
			} catch (InvalidSlotNumberException invalidSlotNumberException) {
				System.out.println("Sorry, this slot number is not present");
			}
		}
	},
	status {
		@Override
		public void executeCommand(String[] details) {
			floor.printStatus();

		}
	},
	registration_numbers_for_cars_with_colour {
		@Override
		public void executeCommand(String[] details) {
			try {
				System.out.println(floor.getRegNoByColour(details[1]));
			} catch (NoSlotAvailableWithRegNo noSlotAvailableWithRegNo) {
				System.out.println("No slot available with color: " + details[1]);
			}
			;

		}
	},
	slot_numbers_for_cars_with_colour {
		@Override
		public void executeCommand(String[] details) {
			try {
				System.out.println(floor.getSlotByColour(details[1]));
			} catch (NoSlotAvailableWithColour noSlotAvailableWithColour) {
				System.out.println("No slot available with color: " + details[1]);
			}
			;

		}
	},
	slot_number_for_registration_number {
		@Override
		public void executeCommand(String[] details) {
			try {
				System.out.println(floor.getSlotByVehicleNumber(details[1]));
			} catch (InvalidVehicleNumber invalidVehicleNumber) {
				System.out.println("Slot Not found with this car number : "+details[1]);
			}

		}
	},
	exit {
		@Override
		public void executeCommand(String[] details) {

		}
	};

	Parking floor = Parking.getParkingFloor();
}

interface CommandI {
	void executeCommand(String[] details);
}