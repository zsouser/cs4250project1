import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Document Class
 * 
 * Parses a text file into a 
 * @author zsouser
 *
 */
public class Document {
	private String text;
	private ArrayList<String> words, sentences, pairs, letters;
	private File f;
	public Document(String filename) throws FileNotFoundException {
		this.words = new ArrayList<String>();
		this.sentences = new ArrayList<String>();
		this.pairs = new ArrayList<String>();
		this.letters = new ArrayList<String>();
		this.f = new File(filename);
		parseSentences();
		parseWords();
		parseLetters();
		
	}
	
	public void parseSentences() throws FileNotFoundException {
		Scanner sc = new Scanner(this.f);
		sc.useDelimiter("[\\.|\\?|!]+");
		while (sc.hasNext()) {
			this.sentences.add(sc.next());
		}
	}
	
	public void parseWords() throws FileNotFoundException {
		Scanner sc = new Scanner(this.f);
		sc.useDelimiter("[\\.|,|\\?|!|\\s]+");
		while (sc.hasNext()) {
			this.words.add(sc.next());
		}
	}
	
	public void parseLetters() throws FileNotFoundException {
		Scanner sc = new Scanner(this.f);
		sc.useDelimiter("");
		String single = "";
		String pair = "";
		while (sc.hasNext()) {
			String letter = sc.next();
			single = "" + letter;
			pair = pair + letter;
			this.letters.add(single);
			if (pair.length() == 2 || !sc.hasNext()) {
				this.pairs.add(pair);
				pair = "";
			}
		}
	}
	
	
	public String text() {
		return text;
	}
	
	public ArrayList<String> words() {
		return this.words;
	}
	
	public ArrayList<String> sentences() {
		return this.sentences;
	}
	
	public ArrayList<String> pairs() {
		return this.pairs;
	}
	
	public ArrayList<String> letters() {
		return this.letters;
	}	
}
