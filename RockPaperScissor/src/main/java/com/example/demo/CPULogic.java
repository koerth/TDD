package com.example.demo;

import java.util.Random;

//	Logiken för CPU, baserat på random
public class CPULogic {
	public static String CPURandomizer() {
		String CPUMoves = "";
		Random random = new Random();
		int randomNum = random.nextInt(3) + 1;
		
		switch (randomNum) {
		case 1:
			CPUMoves = "Rock";
			break;
		case 2:
			CPUMoves = "Paper";
			break;
		case 3: 
			CPUMoves = "Scissors";
			break;
		}
		return CPUMoves;
	}
}
