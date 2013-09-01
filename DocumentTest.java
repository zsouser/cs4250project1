import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class DocumentTest {

	@Test
	public void testSentences() throws Exception {
		Document d = new Document("test.txt");
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("This is a setnence");
		// Note the leading spaces
		expected.add(" This is a sentence that is actually spelled correctly");
		expected.add(" This is another, question");
		expected.add(" Answer");
		assertEquals(expected,d.sentences());
		
	}
	
	@Test
	public void testWords() throws Exception {
		Document d = new Document("test.txt");
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("This");
		expected.add("is");
		expected.add("a");
		expected.add("setnence");
		expected.add("This");
		expected.add("is");
		expected.add("a");
		expected.add("sentence");
		expected.add("that");
		expected.add("is");
		expected.add("actually");
		expected.add("spelled");
		expected.add("correctly");
		expected.add("This");
		expected.add("is");
		expected.add("another");
		expected.add("question");
		expected.add("Answer");
		assertEquals(expected,d.words());
		
	}
	
	@Test 
	public void testLetters() throws Exception {
		Document d = new Document("test.txt");
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("T");
		expected.add("h");
		expected.add("i");
		expected.add("s");
		expected.add(" ");
		expected.add("i");
		expected.add("s");
		expected.add(" ");
		expected.add("a");
		expected.add(" ");
		expected.add("s");
		expected.add("e");
		expected.add("t");
		expected.add("n");
		expected.add("e");
		expected.add("n");
		expected.add("c");
		expected.add("e");
		expected.add(".");
		expected.add(" ");
		expected.add("T");
		expected.add("h");
		expected.add("i");
		expected.add("s");
		expected.add(" ");
		expected.add("i");
		expected.add("s");
		expected.add(" ");
		expected.add("a");
		expected.add(" ");
		expected.add("s");
		expected.add("e");
		expected.add("n");
		expected.add("t");
		expected.add("e");
		expected.add("n");
		expected.add("c");
		expected.add("e");
		expected.add(" ");
		expected.add("t");
		expected.add("h");
		expected.add("a");
		expected.add("t");
		expected.add(" ");
		expected.add("i");
		expected.add("s");
		expected.add(" ");
		expected.add("a");
		expected.add("c");
		expected.add("t");
		expected.add("u");
		expected.add("a");
		expected.add("l");
		expected.add("l");
		expected.add("y");
		expected.add(" ");
		expected.add("s");
		expected.add("p");
		expected.add("e");
		expected.add("l");
		expected.add("l");
		expected.add("e");
		expected.add("d");
		expected.add(" ");
		expected.add("c");
		expected.add("o");
		expected.add("r");
		expected.add("r");
		expected.add("e");
		expected.add("c");
		expected.add("t");
		expected.add("l");
		expected.add("y");
		expected.add(".");
		expected.add(" ");
		expected.add("T");
		expected.add("h");
		expected.add("i");
		expected.add("s");
		expected.add(" ");
		expected.add("i");
		expected.add("s");
		expected.add(" ");
		expected.add("a");
		expected.add("n");
		expected.add("o");
		expected.add("t");
		expected.add("h");
		expected.add("e");
		expected.add("r");
		expected.add(",");
		expected.add(" ");
		expected.add("q");
		expected.add("u");
		expected.add("e");
		expected.add("s");
		expected.add("t");
		expected.add("i");
		expected.add("o");
		expected.add("n");
		expected.add("?");
		expected.add(" ");
		expected.add("A");
		expected.add("n");
		expected.add("s");
		expected.add("w");
		expected.add("e");
		expected.add("r");
		expected.add("!");
		
		assertEquals(expected,d.letters());
	}
	
	@Test
	public void testPairs() throws Exception {
		Document d = new Document("test.txt");
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("Th");
		expected.add("is");
		expected.add(" i");
		expected.add("s ");
		expected.add("a ");
		expected.add("se");
		expected.add("tn");
		expected.add("en");
		expected.add("ce");
		expected.add(". ");
		expected.add("Th");
		expected.add("is");
		expected.add(" i");
		expected.add("s ");
		expected.add("a ");
		expected.add("se");
		expected.add("nt");
		expected.add("en");
		expected.add("ce");
		expected.add(" t");
		expected.add("ha");
		expected.add("t ");
		expected.add("is");
		expected.add(" a");
		expected.add("ct");
		expected.add("ua");
		expected.add("ll");
		expected.add("y ");
		expected.add("sp");
		expected.add("el");
		expected.add("le");
		expected.add("d ");
		expected.add("co");
		expected.add("rr");
		expected.add("ec");
		expected.add("tl");
		expected.add("y.");
		expected.add(" T");
		expected.add("hi");
		expected.add("s ");
		expected.add("is");
		expected.add(" a");
		expected.add("no");
		expected.add("th");
		expected.add("er");
		expected.add(", ");
		expected.add("qu");
		expected.add("es");
		expected.add("ti");
		expected.add("on");
		expected.add("? ");
		expected.add("An");
		expected.add("sw");
		expected.add("er");
		expected.add("!");
		
		assertEquals(expected,d.pairs());
	}
}
