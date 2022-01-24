package com.ikhokha.techcheck;

public class CommentHasShaker extends LineAnalyzer {
	public CommentHasShaker() {
		this.type = "SHAKER_MENTIONS";
	}
	
	public boolean getLine(String line) {
		return (line.contains("Shaker") || line.contains("shaker"));
	}
}