package com;
import org.junit.Assert;
import org.junit.Test;
import com.HotelReservationMain;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HotelTest {
    
	@Test
	public void hotel()  throws ParseException {
		HotelReservationMain hotelReservationMain = new HotelReservationMain();
		String hotel = hotelReservationMain.findCheapestHotelForRegularCustomersInWeekDayRate("10Sep2020","11Sep2020");
	    Assert.assertEquals("LakeWood", hotel);
	}
	

}