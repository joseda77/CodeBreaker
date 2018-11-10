package co.edu.udea.dappe.codebreaker.dao;

import java.util.Random;

public class Codebreaker {

	private String secret = "";

	public Codebreaker() {
		
	}
	
	public void setSecret(String secret) {
		if(secret.isEmpty()) {
			this.secret = this.generateSecret();
			return;
		}
		this.secret = secret;
	}

	public String decode(String input) {
		if(this.secret.isEmpty()) {
			return "No se ha asignado un secret";
		}
		String[] secretVector = secret.split("");
		String[] inputVector = input.split("");
		String answerNumber = "";
		String answerPosition = "";
		try {
			for (int i = 0; i < inputVector.length; i++) {
				if (secretVector[i].equals(inputVector[i])) {
					answerPosition += "X";
				} else if (this.secret.indexOf(inputVector[i]) >= 0) {
					answerNumber += "_";
				}
			}
		} catch (NumberFormatException e) {
			return "Ha ocurrido un error " + e.getCause().getMessage();
		}
		return answerPosition + answerNumber;
	}

	public String generateSecret() {
		String number = "";
		while (number.length() < 4) {
			Random random = new Random();
			int num = random.nextInt(9);
			String character = Integer.toString(num);
			if (!number.contains(character)) {
				number = number + character;
			}
		}
		return number;
	}
	
	public Boolean isNumber(String number) {
		try {
			Integer.parseInt(number);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
