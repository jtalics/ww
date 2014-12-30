package com.jtalics.ww.shared;

import java.io.Serializable;

public class City implements Serializable {
	private String city;
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	private String state;

	public City() {
		// as required by GWT Serialization
	}
	
	public City(String cityState) {
		String s[] = cityState.split(",");
		this.city = s[0];
		this.state = s[1];
	}
	
	@Override
	public String toString() {
		return city+","+state;
	}
}