package com.claudiodimauro.it.ApiDataFetch.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.claudiodimauro.it.ApiDataFetch.dto.UserDto;
import com.claudiodimauro.it.ApiDataFetch.models.Address;
import com.claudiodimauro.it.ApiDataFetch.models.Company;
import com.claudiodimauro.it.ApiDataFetch.models.Geo;
import com.claudiodimauro.it.ApiDataFetch.models.User;

@Service
public class RestService {

	@Autowired
	private RestTemplate restTemplate;
	
	public void getAndMapData(String endpoint) {
		UserDto[] users = getDataFromApi(endpoint);
		
		for(UserDto userDto : users) {
			mapFromDto(userDto);
		}
	}
	
	private UserDto[] getDataFromApi(String endpoint) {
		return restTemplate.getForObject(endpoint, UserDto[].class);
	}
		
	private void mapFromDto(UserDto dto) {
		ModelMapper mapper = new ModelMapper();
		
		User user = mapper.map(dto, User.class);
		Address address = mapper.map(dto.getAddress(), Address.class);
		Geo geo = mapper.map(dto.getAddress().getGeo(), Geo.class);
		Company company = mapper.map(dto.getCompany(), Company.class);
		
		address.setGeo(geo);
		user.setAddress(address);
		user.setCompany(company);		
	}
}
