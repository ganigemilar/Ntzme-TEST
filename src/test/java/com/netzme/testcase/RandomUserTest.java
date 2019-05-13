package com.netzme.testcase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@RunWith(MockitoJUnitRunner.class)
public class RandomUserTest {
	
	/*
	 * Positive Test
	 */
	@Test
	public void getResponseWhenHitEndpointPerson() throws IOException {
		String dataKey[] = {"gender", "fullname", "address", "picture"};
		
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(PersonAPITest.BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		PersonAPITest callApi = retrofit.create(PersonAPITest.class);
		LinkedHashMap<String, Object> response = callApi.getPerson().execute().body();
		
		assertNotNull(response);
		assertEquals(false,  response.isEmpty());
		assertEquals(dataKey.length, response.size());
		
		for (String key : dataKey) {
			assertEquals(true, response.containsKey(key));
		}
	}
	
	/*
	 * Negative Test
	 */
	@Test
	public void get404IfHitWrongEndpointPerson() throws IOException {
		String errorKey[] = {"timestamp", "status", "error", "message", "path"};
		
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(PersonAPITest.BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		PersonAPITest callApi = retrofit.create(PersonAPITest.class);
		LinkedHashMap<String, Object> response = new LinkedHashMap<>();
		Response<LinkedHashMap<String, Object>> callResponse = callApi.getWrongPerson().execute();
		
		if (callResponse.code() == HttpStatus.NOT_FOUND.value()) {
			Gson gson = new Gson();
			
			String resJson = callResponse.errorBody().string();
			response = gson.fromJson(resJson, LinkedHashMap.class);
			response.put(errorKey[1], HttpStatus.NOT_FOUND.value());
		}
		
		assertNotNull(response);
		assertEquals(HttpStatus.NOT_FOUND.value(), response.get(errorKey[1]));
		
		for (String key : errorKey) {
			assertEquals(true, response.containsKey(key));
		}
	}
	
}
