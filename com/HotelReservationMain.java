package com;

import java.text.ParseException;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class HotelReservationMain {
	final Hotel LAKEWOOD = new Hotel("LakeWood", 110);
	final Hotel BRIDGEWOOD = new Hotel("BridgeWood", 150);
	final Hotel RIDGEWOOD = new Hotel("RidgeWood", 220);
	Date checkinDate;
	Date checkoutDate;

	List<Hotel> hotelList = new ArrayList<>();

	public HotelReservationMain() {
		hotelList.add(LAKEWOOD);
		hotelList.add(BRIDGEWOOD);
		hotelList.add(RIDGEWOOD);
	}
	
	public void convertToDates(String checkin, String checkout) throws ParseException {
		this.checkinDate = new SimpleDateFormat("ddMMMyyyy").parse(checkin);
		this.checkoutDate = new SimpleDateFormat("ddMMMyyyy").parse(checkout);
	}
	
	public String findCheapestHotelForRegularCustomersInWeekDayRate(String start, String finish) throws ParseException {
		convertToDates(start, finish);
		long days = getTotalDays(checkinDate, checkoutDate);
		List<Long> hotelRentList = hotelList.parallelStream().map(hotelName -> hotelName.getRegularCustomerWeekdayRate() * days).collect(Collectors.toList());
		long minRent = Collections.min(hotelRentList);
		Hotel cheapestHotel = hotelList.stream().filter(hotel -> hotel.getRegularCustomerWeekdayRate() * days == minRent).findFirst()
				.orElse(null);
		System.out.println("Hotel : "+ cheapestHotel.getHotelName()+" Cost : "+ minRent);
		return cheapestHotel.getHotelName();
	 }

	
	private long getTotalDays(Date startDate, Date endDate) {
		return ((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24)) + 1;
	}
}