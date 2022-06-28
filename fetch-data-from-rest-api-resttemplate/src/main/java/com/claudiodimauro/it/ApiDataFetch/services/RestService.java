package com.claudiodimauro.it.ApiDataFetch.services;

import java.util.ArrayList;
import java.util.List;

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
	
	
	
	
	public List<User> getAndMapData(String endpoint) {
		List<User> userList = new ArrayList<>();		
		
		UserDto[] users = getDataFromApi(endpoint);
		
		for(UserDto userDto : users) {
			
			userList.add(mapFromDto(userDto));
		}
		
		return userList;
		
	}
	
	private UserDto[] getDataFromApi(String endpoint) {
		return restTemplate.getForObject(endpoint, UserDto[].class);
	}
		
	private User mapFromDto(UserDto dto) {
		ModelMapper mapper = new ModelMapper();
		
		User user = mapper.map(dto, User.class);
		Address address = mapper.map(dto.getAddress(), Address.class);
		Geo geo = mapper.map(dto.getAddress().getGeo(), Geo.class);
		Company company = mapper.map(dto.getCompany(), Company.class);
		
		address.setGeo(geo);
		user.setAddress(address);
		user.setCompany(company);	
		
		return user;
	}
}
