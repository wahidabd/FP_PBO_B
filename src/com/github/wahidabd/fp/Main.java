package com.github.wahidabd.fp;

import com.github.wahidabd.fp.start.Start;
import com.github.wahidabd.fp.start.Welcome;
import com.github.wahidabd.fp.utils.Functions;

public class Main {

	public static void main(String[] args) {
		Welcome welcome = new Welcome(5);
		welcome.showAndWelcome();

		Start start = new Start();
		Functions.frame(start);
	}

}
