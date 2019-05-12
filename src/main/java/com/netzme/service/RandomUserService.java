package com.netzme.service;

import org.springframework.stereotype.Service;

import com.netzme.dao.RandomUserDAO;
import com.netzme.model.Location;
import com.netzme.model.Name;
import com.netzme.model.Picture;
import com.netzme.model.RandomUser;
import com.netzme.model.Result;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.netzme.dao.RandomUserDAO.BASE_URL;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class RandomUserService implements IRandomUserService {
	@Override
	public RandomUser fetchRandomUser() throws IOException {
		Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
				.build();

		RandomUserDAO randomUserDAO = retrofit.create(RandomUserDAO.class);
		Call<RandomUser> randomUser = randomUserDAO.getRandomUser();

		return randomUser.execute().body();
	}

	@Override
	public Map<String, String> getGeneralInfoRandomUser() throws Exception {
		RandomUser randomUser = fetchRandomUser();

		if (randomUser != null) {
			Map<String, String> data = new LinkedHashMap<>();

			Result resultUser = null;
			Name nameUser = null;
			Location locationUser = null;
			Picture pictureUser = null;

			resultUser = randomUser.getResults().get(0);
			nameUser = resultUser.getName();
			locationUser = resultUser.getLocation();
			pictureUser = resultUser.getPicture();

			data.put("gender", resultUser.getGender());
			data.put("fullname", String.format("%s, %s %s", nameUser.getTitle(), nameUser.getFirst(), nameUser.getLast()));
			data.put("address", String.format("%s %s", locationUser.getStreet(), locationUser.getCity()));
			data.put("picture", pictureUser.getLarge());

			// Test
			for (String s : data.keySet()) {
				System.out.println(String.format("%s\t : %s", s, data.get(s)));
			}
			return data;
		}
		return null;
	}
}
