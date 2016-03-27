/*
 * Name: Kyomin Ku
 * UPI: 5521999
 * 29.04.2014
 * Stage 2: Matching one element
 */

import java.io.*;
import java.util.ArrayList;

public class ReadWriteTextFromToFileStage2 {

	public void execute(String inputFileName, String outputFileName) throws IOException {
		BufferedReader inputFile = null;
		PrintWriter outputFile = null;
		
		try {
			inputFile = new BufferedReader(new FileReader(inputFileName));
			outputFile = new PrintWriter(new File(outputFileName));
			
			String pattern = inputFile.readLine();
			ArrayList<String> line = readLineFromFile(inputFile);
			ArrayList<String> matchedPattern = findMatchedPattern(inputFile, line, pattern);
			ArrayList<String> unmatchedPattern = findUnmatchedPattern(inputFile, line, pattern);
			writeLineToFile(outputFile, matchedPattern, unmatchedPattern);
			
		} catch (NumberFormatException e) {
			System.out.println("NumberFormatException!\nFirst line of input.txt is NOT a number.");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("ArrayIndexOutOfBoundsException!\nNot enough parameter.");
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException!\nCould not open the file: " + inputFileName + ".");
		} catch (Exception e) {
			System.out.println("ERROR copying the file.");
		} finally {
			if (inputFile != null){
				try {
					inputFile.close();
				} catch (IOException e){
					System.out.println("Error closing input.txt.");
				}
			}
			if (outputFile != null){
				outputFile.close();
			}
		}
	}
	
	private ArrayList<String> readLineFromFile(BufferedReader inputFile) throws IOException {
		String value = inputFile.readLine();
		String[] values = value.split(" ");
		ArrayList<String> result = new ArrayList<String>();
		
		for (int i = 0; i < values.length; i++){ 
			result.add(values[i]);
		}
		return result;
	}
	
	private ArrayList<String> findMatchedPattern(BufferedReader inputFile, ArrayList<String> line, String pattern) throws IOException {
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < line.size(); i++){ 
			if (line.get(i).equals(pattern)){
				result.add(line.get(i));
			}
		}
		return result;
	}

	private ArrayList<String> findUnmatchedPattern(BufferedReader inputFile, ArrayList<String> line, String pattern) throws IOException {
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < line.size(); i++){ 
			if (!line.get(i).equals(pattern)){
				result.add(line.get(i));
			}
		}
		return result;
	}

	private void writeLineToFile(PrintWriter outputFile, ArrayList<String> matchedPattern, ArrayList<String> unmatchedPattern) {
		try {
			for (int i = 0; i < matchedPattern.size(); i++) {
				String value = matchedPattern.get(i);
				outputFile.print(value);
				if (i < matchedPattern.size() - 1) {
					outputFile.write(" ");
				}
			}
			outputFile.println();
			
			for (int i = 0; i < unmatchedPattern.size(); i++) {
				String value = unmatchedPattern.get(i);
				outputFile.print(value);
				if (i < unmatchedPattern.size() - 1) { 
					outputFile.write(" ");
				}
			}
			outputFile.println();
			
			int countMatchedPattern, countUnmatchedPattern;
			countMatchedPattern = matchedPattern.size();
			countUnmatchedPattern = unmatchedPattern.size();
			
			outputFile.println(countMatchedPattern + " " + countUnmatchedPattern);
			
		} catch (Exception e) {
			System.out.println("ERROR writing lines to a file.");
		}
	}
	 
	public static void main(String[] arguments) throws IOException {
		if (arguments.length != 2) {
			throw new RuntimeException("Usage: java ReadWriteTextFromToFileStage2 <inputFile> <outputFile>");
		}
		new ReadWriteTextFromToFileStage2().execute(arguments[0], arguments[1]);
	}
}