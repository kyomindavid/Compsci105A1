/*
 * Kyomin Ku
 * UPI: 5521999
 * 29.04.2014
 * Stage 4: Matching N elements
 */

import java.io.*;
import java.util.ArrayList;

public class ReadWriteTextFromToFileStage4 {
	 
	public void execute(String inputFileName, String outputFileName) throws IOException {
		BufferedReader inputFile = null;
		PrintWriter outputFile = null;
		
		try {
			inputFile = new BufferedReader(new FileReader(inputFileName));
			outputFile = new PrintWriter(new File(outputFileName));
			
			int n = Integer.parseInt(inputFile.readLine());
			ArrayList<String> patterns = readLineFromFile(inputFile);
			ArrayList<String> line = readLineFromFile(inputFile);
			
			ArrayList<String> patternsForMatching = new ArrayList<String>();
			patternsForMatching = findpatternsToUse(patterns, n);
			
			writeLineToFile(outputFile, patternsForMatching, line);
			
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
	
	private ArrayList<String> findpatternsToUse(ArrayList<String> patterns, int n) throws IOException {
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < n; i++) {
			if (i < patterns.size()) {
				result.add(patterns.get(i));
			}
			result.add("");
		}
		return result;
	}

	private void writeLineToFile(PrintWriter outputFile, ArrayList<String> patternsForMatching, ArrayList<String> line) {
		try {
			ArrayList<String> unmatchedPatterns = line;
			ArrayList<Integer> countPatterns = new ArrayList<Integer>();
			
			for (int i = 0; i < patternsForMatching.size(); i++) {
				int count = 0;
				if (!patternsForMatching.get(i).equals("")) {
					if (line.contains(patternsForMatching.get(i))) {
						for (int j = 0; j < line.size(); j++) {
							if (line.get(j).equals(patternsForMatching.get(i))) {
								outputFile.print(line.get(j));
								count++;
								if (j < line.size() - 1) {
									outputFile.write(" ");
								}
								unmatchedPatterns.remove(line.get(j));
								j--;
							}
						}
						outputFile.println();
					}
					countPatterns.add(count);
				}
			}
			
			int count = 0;
			for (int i = 0; i < unmatchedPatterns.size(); i++) {
				outputFile.print(unmatchedPatterns.get(i));
				count++;
				if (i < unmatchedPatterns.size() - 1) {
					outputFile.write(" ");
				}
			}
			outputFile.println();
			countPatterns.add(count);
			
			for (int i = 0; i < countPatterns.size(); i++) {
				outputFile.print(countPatterns.get(i));
				if (i < countPatterns.size() - 1) {
					outputFile.write(" ");
				}
			}
			
		} catch (Exception e) {
			System.out.println("ERROR writing lines to a file.");
		}
	}
	 
	public static void main(String[] arguments) throws IOException {
		if (arguments.length != 2) {
			throw new RuntimeException("Usage: java ReadWriteTextFromToFileStage4 <inputFile> <outputFile>");
		}
		new ReadWriteTextFromToFileStage4().execute(arguments[0], arguments[1]);
	}
}