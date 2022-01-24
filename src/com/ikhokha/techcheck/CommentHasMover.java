package com.ikhokha.techcheck;

public class CommentHasMover extends LineAnalyzer {
	public CommentHasMover() {
		this.type = "MOVER_MENTIONS";
	}
	
	public boolean getLine(String line) {
		return (line.contains("Mover") || line.contains("mover"));
	}
}