package application;

import java.io.*;

import exceptions.TreeException;
import referenceBasedTreeImplementation.BSTReferencedBased;
import utilities.Iterator;
import wordDomain.*;

/**
 * The program keeps track of each occurrence of a word in a file and 
 * the line on which it was found in that file. The program will also 
 * produce output, specified by the user at command line, to generate 
 * reports using a variety of iterators.
 *
 * @author Bushra Osman, Jihoon Oh, Jonghan Park, Eunji Lee
 * @version Nov 30, 2021
 */
public class WordTracker {
	private static final File BST_FILE = new File("repository.ser");
	private static File text;
	private static File report;
	private static String printingOption;
	private static BSTReferencedBased<Word> bst;
	
	public static void main(String[] args) {
		// Parse the arguments from a command line
		new WordTracker().parseArgs(args);
				
		// Read a text file and save words into a BST
		readData();
		
		// Display and write a report into a file
		if(report != null) {
			writeReport();
		} else {
			displayReport();
		}
		
		// Store a BST in a binary file
		storeBST();		
	}

	/** Process the command line arguments
 	* @param args the command line arguments
 	*/
	private void parseArgs(String[] args) {
		if(args.length < 2) {
			System.out.println(
					"Text file name and printing option must be provided via the command line");
			System.exit(1);
		}
		
		switch(args.length) {
		case 2:			
			if(args[0].toLowerCase().startsWith("-p")) {
				text = new File(args[1]);
				printingOption = args[0].toLowerCase();
			} else {
				text = new File(args[0]);
				printingOption = args[1].toLowerCase();
			}
			
			break;
		case 3:
			for(int i = 0; i < 3; i++) {
				if(args[i].toLowerCase().startsWith("-p")) {
					printingOption = args[i].toLowerCase();
				} else if(args[i].toLowerCase().startsWith("-f")) {
					report = new File(args[i].substring(2));
				} else {
					text = new File(args[i]);
				}
			}
			
			if(text == null || report == null || report.getName().equals("") ||
					printingOption == null) {
				System.out.println(
						"Please enter each text file, print option, and report file correctly");
				System.exit(2);
			}
			
			break;
		case 4:
			int reportPrefix = -1;
			
			for(int i = 0; i < 4; i++) {
				if(args[i].toLowerCase().startsWith("-p")) {
					printingOption = args[i].toLowerCase();
				}
				
				if(args[i].toLowerCase().startsWith("-f")) {
					reportPrefix = i;
				}
			}
			
			switch(reportPrefix) {
			case 0:
				report = new File(args[1]);
				
				if(!args[2].toLowerCase().startsWith("-p")) {
					text = new File(args[2]);
				} else {
					text = new File(args[3]);
				}
				
				break;
			case 1:
				report = new File(args[2]);
				
				if(!args[0].toLowerCase().startsWith("-p")) {
					text = new File(args[0]);
				} else {
					text = new File(args[3]);
				}
				
				break;
			case 2:
				report = new File(args[3]);
				
				if(!args[0].toLowerCase().startsWith("-p")) {
					text = new File(args[0]);
				} else {
					text = new File(args[1]);
				}
				
				break;
			}
			
			if(text == null || report == null || printingOption == null) {
				System.out.println(
						"Please enter each text file, print option, and report file correctly");
				System.exit(2);
			}
			
			break;			
		default:
			System.out.println(
					"Only text file, printing option, and report file must be entered");
			System.exit(3);
		}
		
		if(!printingOption.equals("-pf") && !printingOption.equals("-pl") &&
				!printingOption.equals("-po")) {
			System.out.println(
					"Print option are only -pf, -pl or -po");
			System.exit(4);
		}
	}
	
	// Read a text file and save words into BST
	@SuppressWarnings("unchecked")
	private static void readData() {
		try(BufferedReader textReader = new BufferedReader(new FileReader(text))) {
			if(BST_FILE.exists()) {
				ObjectInputStream BSTReader = new ObjectInputStream(
						new BufferedInputStream(new FileInputStream(BST_FILE)));
				bst = (BSTReferencedBased<Word>) BSTReader.readObject();
				BSTReader.close();				
			} else {
				bst = new BSTReferencedBased<>();
			}
			
			String line = "";
			int lineNumber = 0;
			
			while((line = textReader.readLine()) != null) {
				lineNumber++;
				String[] words = line.split("\\s+");
				
				for(int i = 0; i < words.length; i++) {
					String string = (words[i]).toLowerCase();
					
					if(isMark(string.charAt(0))) {
						if(isMark(string.charAt(string.length() - 2)) &&
								isMark(string.charAt(string.length() - 1))) {
							string = string.substring(1, string.length() - 2);
						} else if(isMark(string.charAt(string.length() - 1))) {
							string = string.substring(1, string.length() - 1);
						} else {
							string = string.substring(1);
						}
					} else if(isMark(string.charAt(string.length() - 1))) {
						if(isMark(string.charAt(string.length() - 2))) {
							string = string.substring(0, string.length() - 2);
						} else {
							string = string.substring(0, string.length() - 1);
						}
					}
										
					Word word = new Word(string);
					word.getInfo().add(new OccurrenceInfo(text.getName(), lineNumber));
					
					if(bst.isEmpty() || (!bst.isEmpty() && !bst.contains(word))) {
						bst.add(word);
					} else if(!bst.isEmpty() && bst.contains(word)) {
						bst.search(word).getElement()
							.getInfo().add(new OccurrenceInfo(text.getName(), lineNumber));
					}
				}				
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (TreeException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// Verify whether a character is a mark or not
	private static boolean isMark(char oneChar) {
		if(oneChar == '"' || oneChar == '\'' || oneChar == '(' || oneChar == ')' ||
				oneChar == '<' || oneChar == '>' ||	oneChar == '.' || oneChar == ',' ||
				oneChar == '!' || oneChar == '?' || oneChar == ';' || oneChar == ':') {
			return true;
		} else {
			return false;
		}
	}
	
	// Write a report file
	private static void writeReport() {
		try(BufferedWriter reportWriter = new BufferedWriter(new FileWriter(report))) {
			Iterator<Word> iterator = bst.inorderIterator();
			
			switch(printingOption) {
			case "-pf":
				while(iterator.hasNext()) {
					Word word = iterator.next();				
					String report = "Word: " + word.getWord();

					System.out.println(report);
					reportWriter.write(report);
					reportWriter.newLine();
					
					for(int i = 0; i < word.getInfo().size(); i++) {
						report = "File name: " + word.getInfo().get(i).getFileName();
						
						System.out.println(report);
						reportWriter.write(report);
						reportWriter.newLine();
					}
				}
				
				break;
			case "-pl":
				while(iterator.hasNext()) {
					Word word = iterator.next();				
					String report = "Word: " + word.getWord();

					System.out.println(report);
					reportWriter.write(report);
					reportWriter.newLine();
					
					for(int i = 0; i < word.getInfo().size(); i++) {
						report = "File name: " + word.getInfo().get(i).getFileName() +
								" - Line No: " + word.getInfo().get(i).getLineNumber();
						
						System.out.println(report);
						reportWriter.write(report);
						reportWriter.newLine();
					}
				}
				
				break;
			case "-po":
				while(iterator.hasNext()) {
					Word word = iterator.next();				
					String report = "Word: " + word.getWord() +
							" / Frequency: " + word.getInfo().size();

					System.out.println(report);
					reportWriter.write(report);
					reportWriter.newLine();
					
					for(int i = 0; i < word.getInfo().size(); i++) {
						report = "File name: " + word.getInfo().get(i).getFileName() +
								" - Line No: " + word.getInfo().get(i).getLineNumber();
						
						System.out.println(report);
						reportWriter.write(report);
						reportWriter.newLine();
					}
				}
				
				break;
			}
						
			reportWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	// Display a report
	private static void displayReport() {
		Iterator<Word> iterator = bst.inorderIterator();
		
		switch(printingOption) {
		case "-pf":
			while(iterator.hasNext()) {
				Word word = iterator.next();
				
				System.out.println("Word: " + word.getWord());
				
				for(int i = 0; i < word.getInfo().size(); i++) {
					System.out.println("File name: " + word.getInfo().get(i).getFileName());
				}
			}
			
			break;
		case "-pl":
			while(iterator.hasNext()) {
				Word word = iterator.next();
				
				System.out.println("Word: " + word.getWord());
				
				for(int i = 0; i < word.getInfo().size(); i++) {
					System.out.println("File name: " + word.getInfo().get(i).getFileName() +
							" - Line No: " + word.getInfo().get(i).getLineNumber());
				}
			}
			
			break;
		case "-po":
			while(iterator.hasNext()) {
				Word word = iterator.next();
				
				System.out.println("Word: " + word.getWord() +
						" / Frequency: " + word.getInfo().size());
				
				for(int i = 0; i < word.getInfo().size(); i++) {
					System.out.println("File name: " + word.getInfo().get(i).getFileName() +
							" - Line No: " + word.getInfo().get(i).getLineNumber());
				}
			}
			
			break;
		}
	}
	
	// Store a BST in a binary file
	private static void storeBST() {
		try(ObjectOutputStream BSTWriter = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(BST_FILE)))) {
			BSTWriter.writeObject(bst);
			BSTWriter.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}