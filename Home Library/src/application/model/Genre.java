/**
 * 
 */
package application.model;

/**
 * @author colbybailey
 *
 */
public class Genre {
	private String genreName;
	
	public Genre( String genreName ) {
		this.setGenreName(genreName);
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	
	@Override
	public String toString() {
		return this.genreName;
	}
}
