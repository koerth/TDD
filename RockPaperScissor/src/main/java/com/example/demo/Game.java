package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Game {
	@RequestMapping(value = "/game", method = { RequestMethod.POST, RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_VALUE)
	public String Logic(String playerInput) {
		String pattern = "{ \"Number of Wins\":\"%s\", \"Number of Losses\":\"%s\", \"Number of Ties\": \"%s\"}";
		 String CPUMove = CPULogic.CPURandomizer();
		
		//	Simple if statements that checks CPUMove and compares it to user inputs
		if (CPUMove.equals("Rock")) {
			if (playerInput.equals("Rock")) {
				ScoreBean.ties++;
				return "It's a tie!";
			} else if (playerInput.equals("Paper")) {
				ScoreBean.wins++;
				return "Player wins!";
			} else if (playerInput.equals("Scissors")){
				ScoreBean.losses++;
				return "Computer wins!";
			}
		}
		if (CPUMove.equals("Paper")) {
			if (playerInput.equals("Paper")) {
				ScoreBean.ties++;
				return "It's a tie!";
			} else if (playerInput.equals("Scissors")) {
				ScoreBean.wins++;
				return "Player wins!";
			} else if (playerInput.equals("Rock")){
				ScoreBean.losses++;
				return "Computer wins!";
			}
		}
		if (CPUMove.equals("Scissors")) {
			if (playerInput.equals("Scissors")) {
				ScoreBean.ties++;
				return "It's a tie!";
			} else if (playerInput.equals("Rock")) {
				ScoreBean.wins++;
				return "Player wins!";
			} else if (playerInput.equals("Paper")){
				ScoreBean.losses++;
				return "Computer wins!";
			}
		}
		return String.format(pattern, ScoreBean.wins, ScoreBean.losses, ScoreBean.ties);
	}
}
