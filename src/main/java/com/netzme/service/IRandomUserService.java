package com.netzme.service;

import java.util.Map;

import com.netzme.model.RandomUser;

public interface IRandomUserService {
	RandomUser fetchRandomUser() throws Exception;
	
	Map<String, String> getGeneralInfoRandomUser() throws Exception;
}
