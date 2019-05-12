package com.netzme.dao;

import com.netzme.model.RandomUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

public interface RandomUserDAO {
	public static final String BASE_URL = "https://randomuser.me/";

	@GET("api")
	Call<RandomUser> getRandomUser();
}
