package com;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class HotelTest {
    
	@Test
	public void hotel()  throws ParseException {
		HotelReservationMain hotelReservationMain = new HotelReservationMain();
		String hotel = hotelReservationMain.findCheapestHotelForRegularCustomersInWeekDayRate("10Sep2020","11Sep2020");
	    Assert.assertEquals("LakeWood", hotel);
	}
	 
	 
	@Test
	public void checkCheapestHotelForRegularCustomerConsideringWeekdayAndWeekend() throws ParseException {
		HotelReservationMain hotelReservationMain = new HotelReservationMain();
		List<String> hotelList = hotelReservationMain.findCheapestHotelForRegularCustomersConsideringWeekdayAndWeekend("10Sep2020","11Sep2020");
	    List<String> expectedList = new ArrayList<>(Arrays.asList("LakeWood","BridgeWood"));
	    Assert.assertEquals(expectedList, hotelList);
	}
}