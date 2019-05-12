package com.netzme.demo;

import java.util.LinkedHashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PersonAPITest {
	public static final String BASE_URL = "http://localhost:8080/";
	
	@GET("api/person")
	Call<LinkedHashMap<String, String>> getPerson();
}
