import java.util.List;

/**
 * Opens the GUI. 
 * 
 * @author andrew.canastar
 *
 */
public class Analyzer implements AnalyzerInterface {

	public Analyzer() {
		
	}
	
	@Override
	public String startUserInterface() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getDocumentData(String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void calculateMetadata() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAuthorToDatabase() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addUknownToDatabase() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reportResultsToUser() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getAuthorsFromDatabase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> compareUnkownToAuthors(List<String> knownAuthors) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> rankAuthorsForSimilarity(
			List<Integer> authorshipDifferences) {
		// TODO Auto-generated method stub
		return null;
	}
	
}