package com.ikhokha.techcheck;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		// Added more here
		LineAnalyzer[] CommentPlugins = {
				new CommentHasMover(),
				new CommentHasShaker(),
				new CommentShorterThan15(),
				new CommentHasQuestion(),
				new CommentHasSpam()
		};
		
		Map<String, Integer> totalResults = new HashMap<String, Integer>();
		
		// Create ThreadPool Here (lets say about 32 threads)
		List<ThreadFileHandler> threads = new ArrayList<ThreadFileHandler>();  
		
		// get all file paths
		File docPath = new File("docs");
		List<File> commentFiles = new ArrayList<>();
		Collections.addAll(commentFiles, docPath.listFiles((d, n) -> n.endsWith(".txt")));

		System.out.println("TOTAL FILES " + commentFiles.size() + "\n=======");
		// assign files to threads
		int filesPerThread = Math.toIntExact(commentFiles.size()) + 1;
		int filesIndex = 0;
		
		for(int a = 0 ; a < 32 && filesIndex < commentFiles.size() - 1; a++) {
			System.out.println("CREATING THREAD " + a + "\n=======");
			ThreadFileHandler temp = new ThreadFileHandler();
			temp.setLineAnalyzer(CommentPlugins);
			if(filesIndex + filesPerThread < commentFiles.size()){
				temp.setFilePaths(commentFiles.subList(filesIndex, filesPerThread));
				filesIndex += filesPerThread;
			} else {
				int leftOvers = commentFiles.size() - filesIndex;
				temp.setFilePaths(commentFiles.subList(filesIndex, leftOvers));
				filesIndex += leftOvers;
			}
			filesIndex += filesPerThread;
			System.out.println("ADDING THREAD " + a + "\n=======");
			threads.add(temp);
		}
		
		// start all threads
		for (int i = 0; i < threads.size(); i++) 
		{
			System.out.println("STARTING THREAD " + i + "\n=======");
			threads.get(i).start(); 
		}
		
		// wait for all threads
		for (int i = 0; i < threads.size(); i++) 
		{
			try {
				System.out.println("JOINING THREAD " + i + "\n=======");
				threads.get(i).join();
			} catch (InterruptedException e) {
				System.out.println("ER:  " + e.toString() + "\n=======");
			} 
		}
		
		// get data from all threads
		for (int i = 0; i < threads.size(); i++) 
		{
			totalResults.putAll(threads.get(i).getResults()); 
		}
		
		System.out.println("RESULTS\n=======");
		totalResults.forEach((k,v) -> System.out.println(k + " : " + v));
	}	
}
