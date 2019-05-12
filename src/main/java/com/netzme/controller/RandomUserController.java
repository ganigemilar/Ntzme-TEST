package com.netzme.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.netzme.model.Location;
import com.netzme.model.Name;
import com.netzme.model.Picture;
import com.netzme.model.RandomUser;
import com.netzme.model.Result;
import com.netzme.service.RandomUserService;

@RestController
@RequestMapping("/api")
public class RandomUserController {
	@Autowired
	private RandomUserService randomUserService;

	@RequestMapping("/person")
	@ResponseBody
	public Map<String, String> getPerson() throws Exception {
		return randomUserService.getGeneralInfoRandomUser();
	}

	@RequestMapping("/person1")
	@ResponseBody
	public Map<String, String> getPerson1() {
		Map<String, String> data = new LinkedHashMap<>();

		RandomUser randomUser = null;
		Result resultUser = null;
		Name nameUser = null;
		Location locationUser = null;
		Picture pictureUser = null;

		try {
			randomUser = randomUserService.fetchRandomUser();
			resultUser = randomUser.getResults().get(0);
			nameUser = resultUser.getName();
			locationUser = resultUser.getLocation();
			pictureUser = resultUser.getPicture();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

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

	@RequestMapping("/test")
	@ResponseBody
	public String test() {
		return "RUNNING BOSQUE...";
	}
}
