package com.claudiodimauro.it.ApiDataFetch.dto;

public class GeoDto {
	private String lat;
	private String lng;

	public GeoDto(String lat, String lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	//Sovrascrivo il toString per definire il modo in cui voglio che venga stampato
	//l'oggetto Geo
	@Override
	public String toString() {
		return "lat: " + lat + ", lng:" + lng;
	}


}
