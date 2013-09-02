import java.util.List;

/**
 * 
 * @author andrew.canastar
 *
 */
public interface AnalyzerInterface {
	
	/**
	 * Starts the user interface and returns a fileName.
	 * @return String fileName
	 */
	String startUserInterface();
	
	/**
	 * Sends the fileName to be parsed in Document.
	 * @param fileName
	 */
	void getDocumentData( String fileName );
	
	/**
	 * Calculates the metadata for (call Document functions).
	 */
	void calculateMetadata();
	
	/**
	 * Sends the collection of information for storage
	 * in the database.
	 */
	void addAuthorToDatabase();	
	
	/**
	 * After getting all the difference data for an unknown author,
	 * add the unknown's results to the database. 
	 */
	void addUknownToDatabase();
	
	/**
	 * Send message of results to the user.
	 */
	void reportResultsToUser();
	
	
	/****************************************************************
	 * SHOULD PROBABLY BE ANOTHER CLASS
	 */
	List<String> getAuthorsFromDatabase();
	
	/**
	 * Compare an unknown author's statistics to
	 * known authors. It's unclear how this should
	 * work.
	 * @return List<Integer> authorshipDifferences - list of total 
	 * differences between the known and unknown author. 
	 */
	List<Integer> compareUnkownToAuthors( List<String> knownAuthors );
	
	/**
	 * Sorts the known authors by their overall difference with the unknown
	 * author.
	 * @param authorshipDifferences
	 * @return List<Integer> authorshipDifferences  - a sorted list of authorship
	 * differences.
	 */
	List<Integer> rankAuthorsForSimilarity( List<Integer> authorshipDifferences );
	
	
	
	
	
}
