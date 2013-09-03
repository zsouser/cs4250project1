
public class Author implements AuthorInterface {

	private String name;
	private float value;
	
	public Author() { 	}
	
	public Author( String name, float value ) {
		this.name = name;
		this.value = value;
	}
	
	@Override
	public int compareTo(Author anAuthor) {
		float diff = this.value - anAuthor.getValue();
		if( diff > 0 ) {
			return 1;
		} else if( diff < 0 ) {
			return -1;
		} else {
			return 0;
		} 
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setValue(float value) {
		this.value = value;
	}

	@Override
	public float getValue() {
		return value;
	}

}
