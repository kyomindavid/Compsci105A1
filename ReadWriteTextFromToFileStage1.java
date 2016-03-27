/*
 * Name: Kyomin Ku
 * UPI: 5521999
 * 29.04.2014
 * Stage 1 : Exceptions
 */

import java.io.*;
import java.util.ArrayList;

public class ReadWriteTextFromToFileStage1 {
	 
	public void execute(String inputFileName, String outputFileName) throws IOException {
		BufferedReader inputFile = null;
		PrintWriter outputFile = null;
		
		try {
			inputFile = new BufferedReader(new FileReader(inputFileName));
			outputFile = new PrintWriter(new File(outputFileName));
			
			int n = Integer.parseInt(inputFile.readLine());
			ArrayList<String> values = readLineFromFile(inputFile, n);
			writeLineToFile(outputFile, values, n);
			
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
	 
	private ArrayList<String> readLineFromFile(BufferedReader inputFile, int n) throws IOException {
		String value = inputFile.readLine();
		String[] values = value.split(" ");
		ArrayList<String> result = new ArrayList<String>();
		
		for (int i = 0; i < n; i++){ 
			result.add(values[i]);
		}
		return result;
	}
	 
	private void writeLineToFile(PrintWriter outputFile, ArrayList<String> values, int n) {
		try {
			for (int i = 0; i < n; i++) {
				String value = values.get(i);
				outputFile.print(value);
				if (i < n - 1) {
					outputFile.write(" ");
				}
			}
			outputFile.println();
		} catch (Exception e) {
			System.out.println("ERROR writing to a file.");
		}
	}
	 
	public static void main(String[] arguments) throws IOException {
		if (arguments.length != 2) {
			throw new RuntimeException("Usage: java ReadWriteTextFromToFileStage1 <inputFile> <outputFile>");
		}
		new ReadWriteTextFromToFileStage1().execute(arguments[0], arguments[1]);
	}
}