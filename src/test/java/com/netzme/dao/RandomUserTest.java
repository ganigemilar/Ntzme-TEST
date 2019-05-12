package com.netzme.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static com.netzme.dao.RandomUserDAO.BASE_URL;

import static org.mockito.BDDMockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.netzme.model.RandomUser;
import com.netzme.service.IRandomUserService;
import com.netzme.service.RandomUserService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RandomUserTest {
	private IRandomUserService randomUserService = new RandomUserService();
	
	@Mock
	private RandomUserDAO randomUserDAO;
	
	
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void statusOkIfHitCorrectEndPointGetPerson() {
		
	}
	
	@Test
	public void statusNotOkIfHitWrongEndPointGetPerson() {
		
	}
	
	@Test
	public void getResponseIfSuccessFetchRandomUser() {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://localhost:8080/api/person")
				.build();
		Call<ResponseBody> callResponse = (Call<ResponseBody>) retrofit.create(ResponseBody.class);
		
		
	}
	
	@Test
	public void getErrorIfFailedFetchRandomUser() {
		
	}
	
	
	@Test
	public void positiveTestCase() throws Exception {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		Call<RandomUser> callRandomUser = retrofit.create(RandomUserDAO.class).getRandomUser();
		RandomUser expectedRandomUser = callRandomUser.execute().body();
		
		given(randomUserDAO.getRandomUser()).willReturn(callRandomUser);
		
		RandomUser test = randomUserService.fetchRandomUser();
		
		System.out.println(randomUserService == null);
		
		assertThat(test, notNullValue());
		assertThat(test, equalTo(expectedRandomUser));
	}
	
	@Test
	public void randomUserIsAvailable() {
		
	}
}
