package application.model;

/**
 * <h2>class Author</h2>
 * This class represents Author objects. It contains firstName and lastName variables.
 * <br><br>
 * @author bagoferasers
 */
public class Author {
	private String firstName;
	private String lastName;
	
	/**
	 * <h2>Author( String firstName, String lastName )</h2>
	 * This is a constructor for the Author object and passes two Strings for first and last name.
	 * <br><br>
	 * @param firstName the first name of the Author.
	 * @param lastName the last name of the Author.
	 */
	public Author( String firstName, String lastName ) {
		this.setFirstName( firstName );
		this.setLastName( lastName );
	}
	
	/**
	 * <h2>Author( )</h2>
	 * This is a default constructor for the Author object and sets variables to empty Strings.
	 */
	public Author( ) {
		this.setFirstName( "" );
		this.setLastName( "" );
	}

	/**
	 * <h2>getFirstName( )</h2>
	 * This getter method returns the first name of the Author object.
	 * <br><br>
	 * @return firstName the first name of the Author.
	 */
	public String getFirstName( ) {
		return firstName;
	}
	
	/**
	 * <h2>setFirstName( String firstName )</h2>
	 * This setter method sets the first name of the Author object.
	 * <br><br>
	 * @param firstName the first name of the Author.
	 */
	public void setFirstName( String firstName ) {
		this.firstName = firstName;
	}
	
	/**
	 * <h2>getLastName( )</h2>
	 * This getter method returns the last name of the Author object.
	 * <br><br>
	 * @return lastName the last name of the Author.
	 */
	public String getLastName( ) {
		return lastName;
	}
	
	/**
	 * <h2>setLastName( String lastName )</h2>
	 * This setter method sets the last name of the Author object.
	 * <br><br>
	 * @param lastName the last name of the Author.
	 */
	public void setLastName( String lastName ) {
		this.lastName = lastName;
	}
	
	/**
	 * <h2>toString( )</h2>
	 * This method overrides the default toString( ) method to display first and last name of Author object. 
	 */
	@Override
	public String toString( ) {
		return this.getFirstName( ) + " " + this.getLastName( );
	}
}
