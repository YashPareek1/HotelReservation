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
	final Hotel LAKEWOOD = new Hotel("LakeWood", 110, 90);
	final Hotel BRIDGEWOOD = new Hotel("BridgeWood", 150, 50);
	final Hotel RIDGEWOOD = new Hotel("RidgeWood", 220, 150);
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
		List<Long> hotelRentList = hotelList.parallelStream().map(hotel -> hotel.getRegularCustomerWeekdayRate() * days)
				 .collect(Collectors.toList());
		long minRent = Collections.min(hotelRentList);
		Hotel cheapestHotel = hotelList.stream().filter(hotel -> hotel.getRegularCustomerWeekdayRate() * days == minRent).findFirst()
				.orElse(null);
		System.out.println("Hotel : "+ cheapestHotel.getHotelName()+" Cost : "+ minRent);
		return cheapestHotel.getHotelName();
	 }

	public long calculateHotelCost(Hotel hotel, long weekDay, long weekEnds) {
		return hotel.getRegularCustomerWeekdayRate() * weekDay + hotel.getRegularCustomerWeekendRate() * weekEnds;
	}
	
	public List<String> findCheapestHotelForRegularCustomersConsideringWeekdayAndWeekend(String start, String finish) throws ParseException {
		convertToDates(start, finish);
		long days = getTotalDays(checkinDate, checkoutDate);
		long weekendDays = getWeekendDays(checkinDate, checkoutDate);
		long weekDays = days - weekendDays;
		List<Long> hotelRentList = hotelList.stream()
				.map(hotel -> calculateHotelCost(hotel, weekDays, weekendDays)).collect(Collectors.toList());
		long minRent = Collections.min(hotelRentList);
		List<String> cheapHotelList = hotelList.stream()
				.filter(hotel -> calculateHotelCost(hotel, weekDays, weekendDays) == minRent)
				.map(hotel -> hotel.getHotelName()).collect(Collectors.toList());
		for (String hotel : cheapHotelList)
			System.out.println("Hotel : " + hotel + " Cost : " + minRent);
        return cheapHotelList;
	}
	
	private long getWeekendDays(Date checkinDate, Date checkoutDate) {
		long weekendDays = 0;
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(checkinDate);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(checkoutDate);
		for (; startCalendar.compareTo(endCalendar) <= 0; startCalendar.add(Calendar.DATE, 1)) {
			int dayOfWeek = startCalendar.get(Calendar.DAY_OF_WEEK);
			if (dayOfWeek == 0 || dayOfWeek == 6)
				weekendDays++;
		}
		return weekendDays;
	}
	
	private long getTotalDays(Date startDate, Date endDate) {
		return ((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24)) + 1;
	}
}