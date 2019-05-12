package com.netzme.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
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
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@RunWith(MockitoJUnitRunner.class)
public class RandomUserTest {
	@Mock
	private RestTemplate restTemplate = new RestTemplate();
	
	private MockRestServiceServer mockServer;
	private ObjectMapper mapper;
	
	@Before
	public void init() {
		mockServer = MockRestServiceServer.createServer(restTemplate);
		mapper = new ObjectMapper();
	}
	
	//Positive Test
	@Test
	public void getResponseWhenHitEndpointPerson() throws IOException {
		String dataKey[] = {"gender", "fullname", "address", "picture"};
		
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(PersonAPITest.BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		PersonAPITest callRequest = retrofit.create(PersonAPITest.class);
		LinkedHashMap<String, String> response = callRequest.getPerson().execute().body();
		
		assertNotNull(response);
		assertThat(response.isEmpty(), equalTo(false));
		assertThat(response.size(), equalTo(dataKey.length));
		
		int i = 0;
		for (String key : response.keySet()) {
			assertThat(key, equalTo(dataKey[i++]));
		}
	}
	
	
	//====================================
	@Test
	public void getResponseWhenHitEndpointPerson1() throws JsonProcessingException, URISyntaxException {
		
		
		/*Object object = null;
		mockServer
		.expect(ExpectedCount.once(), requestTo(new URI("http://localhost:8080/api/person")))
		.andExpect(method(HttpMethod.GET))
		.andRespond(
				withStatus(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_JSON)
				.body(mapper.writeValueAsString(object)));
		mockServer.verify();
		
		System.out.println(object == null);
		
		assertNotNull(object);*/
	}
	
}
