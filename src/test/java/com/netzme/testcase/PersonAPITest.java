package com.netzme.testcase;

import java.util.LinkedHashMap;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PersonAPITest {
	public static final String BASE_URL = "http://localhost:8080/";
	
	@GET("api/person")
	Call<LinkedHashMap<String, Object>> getPerson();
	
	@GET("api/wrongperson")
	Call<LinkedHashMap<String, Object>> getWrongPerson();
}
