package com.ikhokha.techcheck;

public class CommentHasQuestion extends LineAnalyzer {
	public CommentHasQuestion() {
		this.type = "QUESTION";
	}
	
	public boolean getLine(String line) {
		return (line.contains("?"));
	}
}