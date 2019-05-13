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

	@RequestMapping("/test")
	@ResponseBody
	public String test() {
		return "RUNNING BOSQUE...";
	}
}
