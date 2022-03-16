package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RpsController {

	//	Controller
	@RequestMapping(value = "/score", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getScoreBean() {
		String pattern = "{ \"Wins\":\"%s\", \"Losses\":\"%s\", \"Ties\": \"%s\"}";
		return String.format(pattern, ScoreBean.wins, ScoreBean.losses, ScoreBean.ties);

	}
}
