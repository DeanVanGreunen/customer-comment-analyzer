package com.ikhokha.techcheck;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThreadFileHandler extends Thread {
	private List<File> file_paths;
	private LineAnalyzer[] analyzers;
	private Map<String, Integer> counts = new HashMap<String, Integer>();
	public void setLineAnalyzer(LineAnalyzer[] analyzers) {
		this.analyzers = analyzers;
	}
	public void setFilePaths(List<File> files) {
		this.file_paths = files;
	}	
	public void run() {
		for (File file : file_paths) {	
			System.out.println("OPEN FILE: " + file.getAbsoluteFile() + "\n=======");		
			CommentAnalyzer commentAnalyzer = new CommentAnalyzer(file.getAbsoluteFile());
			Map<String, Integer> fileResults = commentAnalyzer.analyze(this.analyzers);
			this.counts.putAll(fileResults);
		}
	}	
	public Map<String, Integer> getResults(){
		return counts;
	}	
}
