package com.ikhokha.techcheck;

public class CommentShorterThan15 extends LineAnalyzer {
	public CommentShorterThan15() {
		this.type = "SHORTER_THAN_15";
	}
	
	public boolean getLine(String line) {
		return (line.length() < 15);
	}
}
