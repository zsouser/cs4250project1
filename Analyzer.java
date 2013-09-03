import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Opens the GUI. 
 * 
 * @author andrew.canastar
 *
 */
public class Analyzer implements AnalyzerInterface {
	
		private Document document;
		private MetaCalculator metaCalculator;
		// private DBClass -- will include this once I get the DB code
		private String fileName, authorName;
		private ArrayList<String> words, sentences, pairs, letters;
		private int avgWordLength, numWords;
		private float avgSentLength, wrdSntLngthRatio, richness, ltrPairFreq;
		private Map<Character, Float> relLetterFreq;
		private ArrayList<String> knownAuthors;
		
	public Analyzer() {
		
	}
	
	@Override
	public String startUserInterface() {
		this.fileName = null;
		// Guessing that starting the GUI will result in the return of a fileName
		return fileName;
	}
	
	/*
	 * (non-Javadoc)
	 * @see AnalyzerInterface#getDocumentData(java.lang.String)
	 */
	@Override
	public void getDocumentData(String fileName) throws FileNotFoundException {
		this.document = new Document( fileName );
		document.parseSentences();
		document.parseWords();
		document.parseLetters();
		// document.parsePairs();
		this.sentences = document.sentences();
		this.words = document.words();
		this.letters = document.letters();
		
	}

	/*
	 * (non-Javadoc)
	 * @see AnalyzerInterface#calculateMetadata()
	 */
	@Override
	public void calculateMetadata() {
		this.metaCalculator = new MetaCalculator();
		this.avgWordLength = metaCalculator.calcAvgWordLength( words );
		this.numWords = metaCalculator.calcNumWords( words );
		this.avgSentLength = metaCalculator.calcWordPerSnt( sentences );
		//this.wrdSntLngthRatio = 
		//	metaCalculator.calcWrdLnSentLnRatio( avgSentLength, avgWordLength );
		this.richness = metaCalculator.calcRichness( words );
		//this.ltrPairFreq = metaCalculator.calcLtrPairFreq( words );
		this.relLetterFreq = metaCalculator.calcRelLtrFreq( letters );		
	}

	/*
	 * (non-Javadoc)
	 * @see AnalyzerInterface#addAuthorToDatabase()
	 */
	@Override
	public void addAuthorToDatabase() {
		// need to extract an authorname from the .txt file? or get from user? both?
		// this.authorName = ;
		// INSERT INTO TABLE (authorName, avgWordLength, numWords, avgSentLength,
		//						wrdSntLngthRatio, richness, ltrPairFreq, relLetterFreq)
		
	}

	@Override
	public void addUknownToDatabase() {
		// need to extract an authorname from the .txt file? or get from user? both?
		this.authorName = "unknown";
		// INSERT INTO TABLE (authorName, avgWordLength, numWords, avgSentLength,
		//						wrdSntLngthRatio, richness, ltrPairFreq, relLetterFreq)
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see AnalyzerInterface#getAuthorsFromDatabase()
	 */
	@Override
	public List<String> getAuthorsFromDatabase() {
		knownAuthors = new ArrayList<String>();
		int index = 0;
		// ResultSet = SELECT author FROM authorsDB WHERE author NOT LIKE '%unknown%'
	// the commented out bits are just the intention, but I think
	// there is a result set iterator
		// while resultSetIterator.hasNext()
			// dbAuthors.add( index, resultSetIterator.next() )
			// index++
		return knownAuthors;
	}
	
	/*
	 * (non-Javadoc)
	 * @see AnalyzerInterface#compareUnkownToAuthors(java.util.List)
	 */
	@Override
	public List<Author> compareUnkownToAuthors( List<String> knownAuthors ) {
		// for the unknown author, get the data from the db into its resultset
		// for each author in the List, get the author data from the db into another resultset
			// iterate through the resultsets and get the absolute value of their difference
				// for each metadata
			// put the author and total difference into a List<Author>
				// new Author (name, difference)
		// return the List
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see AnalyzerInterface#rankAuthorsForSimilarity(java.util.List)
	 */
	@Override
	public List<Author> rankAuthorsForSimilarity( List<Author> authDiffs ) {
		Collections.sort( authDiffs );
		return authDiffs;
	}
	
	/*
	 * (non-Javadoc)
	 * @see AnalyzerInterface#reportResultsToUser()
	 */
	@Override
	public void reportResultsToUser( List<Author> authDiffs ) {
		// for each author in the sorted list, send its value
		// to the message generating method of the ui		
	}
	
}