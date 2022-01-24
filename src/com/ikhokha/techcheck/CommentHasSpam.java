package com.ikhokha.techcheck;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommentHasSpam extends LineAnalyzer {
	Pattern pattern = Pattern.compile("^(https?:\\/\\/)?((([a-z\\d]([a-z\\d-]*[a-z\\d])*)\\.)+[a-z]{2,}|((\\d{1,3}\\.){3}\\d{1,3}))(\\:\\d+)?(\\/[-a-z\\d%_.~+]*)*(\\?[;&a-z\\d%_.~+=-]*)?(\\#[-a-z\\d_]*)?$", Pattern.CASE_INSENSITIVE);
		
	public CommentHasSpam() {
		this.type = "SPAM";
	}
	
	public boolean getLine(String line) {
		return (pattern.matcher(line).find());
	}
}