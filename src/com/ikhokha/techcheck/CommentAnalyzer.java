package com.ikhokha.techcheck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommentAnalyzer {
	
	private File file;
	
	public CommentAnalyzer(File file) {
		this.file = file;
	}
	
	public Map<String, Integer> analyze(LineAnalyzer[] CommentPlugins) {
		
		Map<String, Integer> resultsMap = new HashMap<>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {			
			String line = null;
			while ((line = reader.readLine()) != null) {
				for(LineAnalyzer plugin : CommentPlugins) {				
					if(plugin.getLine(line)) {
						incOccurrence(resultsMap, plugin.type);
					}
				}
			}			
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + file.getAbsolutePath());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO Error processing file: " + file.getAbsolutePath());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Unknown Error processing file: " + file.getAbsolutePath());
		}		
		return resultsMap;		
	}
	
	/**
	 * This method increments a counter by 1 for a match type on the countMap. Uninitialized keys will be set to 1
	 * @param countMap the map that keeps track of counts
	 * @param key the key for the value to increment
	 */
	private void incOccurrence(Map<String, Integer> countMap, String key) {		
		countMap.putIfAbsent(key, 0);
		countMap.put(key, countMap.get(key) + 1);		
	}
}
