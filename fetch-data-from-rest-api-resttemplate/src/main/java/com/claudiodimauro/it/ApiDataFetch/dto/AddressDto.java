package com.claudiodimauro.it.ApiDataFetch.dto;

public class AddressDto {
	private String street;
	private String suite;
	private String city;
	private String zipcode;
	private GeoDto geo;

	public AddressDto(String street, String suite, String city, String zipcode, GeoDto geo) {
		super();
		this.street = street;
		this.suite = suite;
		this.city = city;
		this.zipcode = zipcode;
		this.geo = geo;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getSuite() {
		return suite;
	}

	public void setSuite(String suite) {
		this.suite = suite;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public GeoDto getGeo() {
		return geo;
	}

	public void setGeo(GeoDto geo) {
		this.geo = geo;
	}

	//Sovrascrivo il toString per definire il modo in cui voglio che venga stampato
	//l'oggetto Address
	@Override
	public String toString() {
		return street + ", " + suite + " - " + zipcode + " - " + city + " | geo=["+ geo + "]"; //sfrutta il toString della classe Geo
	}


}
