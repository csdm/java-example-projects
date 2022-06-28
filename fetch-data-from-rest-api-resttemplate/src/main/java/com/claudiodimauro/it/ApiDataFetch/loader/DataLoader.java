package com.claudiodimauro.it.ApiDataFetch.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.claudiodimauro.it.ApiDataFetch.models.User;
import com.claudiodimauro.it.ApiDataFetch.services.RestService;
import com.claudiodimauro.it.ApiDataFetch.utils.Constants;

@Component
public class DataLoader implements ApplicationRunner {
	
	@Autowired
	RestService service;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		for(User u : service.getAndMapData(Constants.URLs.USERS)) {
			System.out.println(u.toString());
		}
	}
}
