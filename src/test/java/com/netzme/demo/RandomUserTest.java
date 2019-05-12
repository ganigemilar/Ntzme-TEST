package com.netzme.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.catalina.mapper.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.ResponseBody;
import retrofit2.Call;
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
	
	@Test
	public void getResponseWhenHitEndpointPerson() {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://localhost:8080/api/person")
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		Call<Map<String, String>> callResponse = (Call<Map<String, String>>) retrofit.create(Map.class); 
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
