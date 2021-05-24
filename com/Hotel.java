package com;

public class Hotel {
	 private String name;
	    private int regularRate;

	    public Hotel(String name,int regularCxRate) {
	        this.name = name;
	        this.regularRate = regularRate;
	    }

	    @Override
	    public String toString() {
	        return "Hotel:"+name+" /tRegular Customer Rates: "+regularRate;
	    }

}
	


