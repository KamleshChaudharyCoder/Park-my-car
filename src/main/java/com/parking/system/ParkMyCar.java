package com.parking.system;

import java.util.Scanner;

import com.parking.system.commands.Command;
import com.parking.system.validator.InputValidator;

/**
 * Start point of the park-my-car-system
 * 
 * @author kamlesh chaudhary
 * 
 */
public class ParkMyCar {

	public static void main(String[] args) {
		printCommands();
		readCommandAndExecute();
	}

	/**
	 * Take command input from user and validate input
	 * 
	 * @author kamlesh chaudhary
	 * 
	 */

	private static void readCommandAndExecute() {
		String inputLine;
		try (Scanner scanner = new Scanner(System.in)) {
			do {
				inputLine = scanner.nextLine();
				if (InputValidator.isValidInput(inputLine)) {
					String[] input = inputLine.split(" ");
					Command cmd;
					try {
						cmd = Command.valueOf(input[0]);
						cmd.executeCommand(input);
					} catch (Exception e) {
						System.out.println("Please provide valid command....");
					}
				}
			} while (!inputLine.equals("exit"));
		}

	}

	/**
	 * print command for user reference
	 * 
	 * @author kamlesh chaudhary
	 * 
	 */

	private static void printCommands() {
		System.out.println("***************************************************************************************");
		System.out.println("**********************  WELCOME TO PARK MY CAR SYSTEM  ************************");
		System.out.println("***************************************************************************************");
		System.out.println("*******************************  SAMPLE INPUT COMMANDS  *******************************");
		System.out.println("1. create_parking_lot {capacity}");
		System.out.println("2. park {car_number} {colour}");
		System.out.println("3. leave {slot_number}");
		System.out.println("4. status");
		System.out.println("5. registration_numbers_for_cars_with_colour {colour}");
		System.out.println("6. slot_numbers_for_cars_with_colour {colour}");
		System.out.println("7. slot_number_for_registration_number {car_number}");
	}

}
