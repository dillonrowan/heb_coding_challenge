/**
	* This class counts the number of occurences of each word in a textfile. The words
	* are counted in a HashMap, placed in an ArrayList, and then sorted using
	* the Collections.sort class. The ArrayList is then printed in a particular format.
	* @author Dillon Rowan.
	* @version 05/20/2018
 */

import java.io.*;
import java.util.*;


public class WordCounter {

		private String largestKey;

		//constructor initializes longestKey (longest word) empty
		private WordCounter() {
			largestKey = "";
		}

		//sets value of largestKey to be parameter key
		private void setLargestKey(String key) {
			this.largestKey = key;
		}

		//returns largestKey (longest word) to what invoked it
		private String getLargestKey() {
			return largestKey;
		}

		/**
		* A method that adds the words from the input file into a HashMap. The keys
		* are the words and the values are the number of occurences. This method
		* also keeps track of the longest word found to be used in formatting.
		* @param map The HashMap to contain the words from input file.
		*/
		public void addToHashMap(Scanner file, HashMap<String, Integer> map) {
			while (file.hasNext()) {
				 String word = file.next().toLowerCase();
				 if (map.containsKey(word)) {
						 map.put(word, map.get(word) + 1);
						 if(word.length() > this.getLargestKey().length()) {
							 this.setLargestKey(word);
						 }
				 } else {
						 map.put(word, 1);
						 if(word.length() > this.getLargestKey().length()) {
							 this.setLargestKey(word);
						 }
				 }
			}
		}

		/**
		* A method that iterates over each object in wordList and prints its key and
		* value in a particular format.
 		* @param wordList This parameter is the ArrayList whose values are compared.
		*/
		public  void showListFormatted(ArrayList<Map.Entry<String, Integer>> wordList) {
			String keyName = "";
			int valueQuantity = 0;

			for(int i = 0; i < (wordList.size()); i++) {
					keyName = wordList.get(wordList.size() - i - 1).getKey();
					valueQuantity = wordList.get(wordList.size() - i - 1).getValue();

					System.out.format("%" + getLargestKey().length() + "s | %" + valueQuantity
					 + "s\n", keyName, new String(new char[valueQuantity]).replace("\0", "=")
					 + " (" + valueQuantity + ")");
				 }
		}

		/**
		 * A method that sorts an ArrayList containing Map Entry objects.
		 * The sort is based on the Map Entry's values. Collections.sort uses a custom
		 * comparator to compare the contents of the Map Entry. The compare method
		 * in the comparator nterface is overwritten to compare the values inside the Map Entry.
		 * @param wordList This parameter is the ArrayList whose Map Entry values are compared.
		 */
		public void sortList(ArrayList<Map.Entry<String, Integer>> wordList) {
			Collections.sort(wordList, new Comparator<Map.Entry<String, Integer>>() {

					 @Override
					 public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
							 return a.getValue().compareTo(b.getValue());
					 }
			 });
		}

		/**
		 * Main method of program. Creates instance of class then checks to see if
		 * the input file exists. Calls various methods to perform operations using
		 * input if input file exists.
		 * @throws FileNotFoundException if input file is not found
		 */
		public static void main(String[] args) {
			WordCounter run = new WordCounter();
				try
				{
						Scanner file = new Scanner(new File("input.txt")).useDelimiter("\\W+");
						HashMap<String, Integer> map = new HashMap<>();
						run.addToHashMap(file, map);

					 	ArrayList<Map.Entry<String, Integer>> wordList = new ArrayList<>(map.entrySet());
					 	run.sortList(wordList);
						run.showListFormatted(wordList);
				}
				catch (FileNotFoundException e)
				{
						System.out.println("File is not found.");
						return;
				}
		}
}
