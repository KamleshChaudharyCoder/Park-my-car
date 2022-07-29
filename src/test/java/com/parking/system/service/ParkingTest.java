package com.parking.system.service;



import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import com.parking.system.exception.InvalidSlotNumberException;
import com.parking.system.exception.InvalidVehicleNumber;
import com.parking.system.exception.NoEmptySlotAvailable;
import com.parking.system.exception.NoSlotAvailableWithColour;
import com.parking.system.exception.NoSlotAvailableWithRegNo;
import com.parking.system.model.Vehicle;


public class ParkingTest {

	private Parking parking;
	

	@Before
	public void setUp() {
		parking = Parking.getParkingFloor();
		parking.createParkingSLot(2);
		
	}

	@Test
	public void parkVehicleInvalidTest() {
		Assert.assertFalse(parking.createParkingSLot(-1));
	}

	@Test
	public void parkVehicleInvalidValidTest() {
		Assert.assertFalse(parking.createParkingSLot(2));
	}
	
	@Test(expected = NoEmptySlotAvailable.class)
    public void parkVehicleNoSlotAvailableTest() throws NoEmptySlotAvailable {
        parking.parkVehicle(new Vehicle("MH-17-5663","white"));
        parking.parkVehicle(new Vehicle("MH-13-5123","black"));
        parking.parkVehicle(new Vehicle("MH-14-2342","black"));
    }

	@Test(expected = InvalidSlotNumberException.class)
	public void UnparkVehicleSlotAvailableTest() throws InvalidSlotNumberException {
		parking.unPark(2);
	}
	
    @Test
	public void TestPrintStatus() {
		parking.printStatus();
	}
	
	@Test(expected = InvalidSlotNumberException.class)
	public void UnparkVehicleSlotNotAvailableTest() throws InvalidSlotNumberException {
		parking.unPark(5);
	}
	
	@Test(expected = InvalidVehicleNumber.class)
	public void testGetSlotByIVehicleNumber() throws InvalidVehicleNumber, NoEmptySlotAvailable{
       String expectedValue = parking.getSlotByVehicleNumber("MH-17-5663");
       String actualValue = "1";
       assertEquals(expectedValue, actualValue);
	}
	
	@Test(expected = InvalidVehicleNumber.class)
	public void testGetSlotByInvalidVehicleNumber() throws InvalidVehicleNumber {
        parking.getSlotByVehicleNumber("PHS-17-78787");
	}
	
	
	@Test(expected = NoSlotAvailableWithColour.class)
	public void testGetSlotByvalidColour() throws NoSlotAvailableWithColour, NoEmptySlotAvailable {
		String expectedValue = parking.getSlotByColour("white");
		String actualValue = "1";
		assertEquals(expectedValue, actualValue);
	}
	

	@Test(expected = NoSlotAvailableWithRegNo.class)
	public void  testGetRegNoByvalidColour() throws NoSlotAvailableWithRegNo, NoEmptySlotAvailable {
		String expectedValue = parking.getRegNoByColour("white");
		String actualValue = "MH-17-5663";
		assertEquals(expectedValue, actualValue);
	}
	
	@Test(expected = NoSlotAvailableWithRegNo.class)
	public void  testGetRegNoByInvalidColour() throws NoSlotAvailableWithRegNo {
		parking.getRegNoByColour("pink");
	}

}
