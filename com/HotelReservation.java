package com;

public class HotelReservation
{
	public static void main(String[] args) 
	{
        System.out.println("Welcome to Hotel Reservation Program");

        Hotel Lakewood = new  Hotel("Lakewood",110);
        Hotel Bridgewood = new Hotel("Bridgewood",160);
        Hotel Ridgewood = new Hotel("Ridgewood",220);

        System.out.println(Lakewood);
        System.out.println(Bridgewood);
        System.out.println(Ridgewood);
     }
}