package com.jtalics.ww.shared;

public class LatLon {
	public final Float lat;
	public final Float lon;

	public LatLon(String latlonName) {
		String s[] = latlonName.split(",");
		this.lat = new Float(s[0]);
		this.lon = new Float(s[1]);
	}
}