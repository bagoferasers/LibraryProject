package application.model;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.scene.image.Image;

/**
 * <h2>class Book</h2>
 * This class represents Book objects. It contains BookImage, name, description, hashtags, author, genre,
 * ISBN, format, loaned, dateLoaned, and nameLoaned variables.
 * <br><br>
 * @author bagoferasers
 */
public class Book {
    public String BookImage;
	private String name;
	public String description;
	public String hashtags;
	public Author author;
	public String genre;
	public long ISBN;
	public String format;
	public Boolean loaned;
	public String dateLoaned;
	public String nameLoaned;
	
	/**
	 * <h2>Book( String BookImage, String name, String description, String hashtags, Author author, String genre, long ISBN, 
	 * String format, Boolean b, String dateLoaned, String nameLoaned )</h2>
	 * This is a constructor for the Book object and sets variables according to parameters.
	 * <br><br>
	 * @param BookImage this is the Image path.
	 * @param name this is the name of the book.
	 * @param description this is the book description.
	 * @param hashtags these are the book's hashtags.
	 * @param author this is the book's author.
	 * @param genre this is the book's genre.
	 * @param ISBN this is the book's ISBN.
	 * @param format this is the book's format.
	 * @param b this is the boolean value to see if book is loaned out or not.
	 * @param dateLoaned this is the date the book was loaned.
	 * @param nameLoaned this is the name of person book is loaned to.
	 */
	public Book( String BookImage, String name, String description,
			String hashtags, Author author, String genre, long ISBN, 
			String format, Boolean b, String dateLoaned, String nameLoaned ) {
		this.setPicture( BookImage );
		this.setName( name );
		this.setDescription( description );
		this.setHashtags( hashtags );
		this.setAuthor( author );
		this.setGenre( genre );
		this.setISBN( ISBN );
		this.setFormat( format );
		this.setLoaned( b );
		this.setDateLoaned( dateLoaned );
		this.setNameLoaned( nameLoaned );
	}
	
	/**
	 * <h2>Book( )</h2>
	 * This is a default constructor for the Book object and sets variables to empty or null values.
	 */
	public Book( ) {
		this.setPicture( null );
		this.setName( "" );
		this.setDescription( "" );
		this.setHashtags( "" );
		this.setAuthor( null );
		this.setGenre( null );
		this.setISBN( 0 );
		this.setFormat( "" );
		this.setLoaned( false );
		this.setDateLoaned( "nodate" );
		this.setNameLoaned( "noname" );
	}
	
	/**
	 * <h2>getNameLoaned( )</h2>
	 * This getter method returns the name of person Book object is loaned to.
	 * <br><br>
	 * @return nameLoaned this is the name of person book is loaned to.
	 */
	public String getNameLoaned( ) {
		return nameLoaned;
	}
	
	/**
	 * <h2>setNameLoaned( String nameLoaned )</h2>
	 * This setter method sets the name of person Book object is going to be loaned to.
	 * <br><br>
	 * @param nameLoaned this is the name of person book is loaned to.
	 */
	public void setNameLoaned( String nameLoaned ) {
		this.nameLoaned = nameLoaned;
	}
	
	/**
	 * <h2>getDateLoaned( )</h2>
	 * This getter method returns the date Book object was loaned.
	 * <br><br>
	 * @return dateLoaned this is the date the book was loaned.
	 */
	public String getDateLoaned( ) {
		return dateLoaned;
	}
	
	/**
	 * <h2>setDateLoaned( String dateLoaned )</h2>
	 * This setter method sets the name of person Book object is going to be loaned to.
	 * <br><br>
	 * @param dateLoaned this is the date the book was loaned.
	 */
	public void setDateLoaned( String dateLoaned ) {
		this.dateLoaned = dateLoaned;
	}
	
	/**
	 * <h2>getLoaned( )</h2>
	 * This getter method returns the boolean value loaned.
	 * <br><br>
	 * @return loaned this is the boolean value to see if book is loaned out or not.
	 */
	public Boolean getLoaned( ) {
		return loaned;
	}
	
	/**
	 * <h2>setLoaned( Boolean b )</h2>
	 * This setter method sets the boolean value for Book object's variable loaned.
	 * <br><br>
	 * @param b this is the boolean value to see if book is loaned out or not.
	 */
	public void setLoaned( Boolean b ) {
		this.loaned = b;
	}
	
	/**
	 * <h2>setFormat( String f )</h2>
	 * This setter method sets the Book object's format to f.
	 * <br><br>
	 * @param f this is the book's format.
	 */
	public void setFormat( String f ) {
		this.format = f;
	}
	
	/**
	 * <h2>getFormat( )</h2>
	 * This getter method returns the format of Book object.
	 * <br><br>
	 * @return format this is the book's format.
	 */
	public String getFormat( ) {
		return format;
	}

	/**
	 * <h2>setISBN( long i )</h2>
	 * This setter method sets the Book object's ISBN to i.
	 * <br><br>
	 * @param i this is the book's ISBN.
	 */
	public void setISBN( long i ) {
		this.ISBN = i;
	}
	
	/**
	 * <h2>getISBN( )</h2>
	 * This getter method returns the ISBN of Book object.
	 * <br><br>
	 * @return ISBN this is the book's ISBN.
	 */
	public long getISBN( ) {
		return ISBN;
	}
	
	/**
	 * <h2>getPicture( )</h2>
	 * This getter method returns the Picture path of Book object.
	 * <br><br>
	 * @return s this is the Image path.
	 */
	public String getPicture( ) {
		return BookImage;
	}
	
	/**
	 * <h2>setPicture( String BookImage )</h2>
	 * This setter method sets the Book object's Image path to BookImage.
	 * <br><br>
	 * @param BookImage this is the Image path.
	 */
	public void setPicture( String BookImage ) {
		this.BookImage = BookImage;
	}
	
	/**
	 * <h2>getName( )</h2>
	 * This getter method returns the name of Book object.
	 * <br><br>
	 * @return name this is the name of the book.
	 */
	public String getName( ) {
		return name;
	}
	
	/**
	 * <h2>setName( String name )</h2>
	 * This setter method sets the Book object's name.
	 * <br><br>
	 * @param name this is the name of the book.
	 */
	public void setName( String name ) {
		this.name = name;
	}
	
	/**
	 * <h2>getDescription( )</h2>
	 * This getter method returns the description of Book object.
	 * <br><br>
	 * @return description this is the book description.
	 */
	public String getDescription( ) {
		return description;
	}
	
	/**
	 * <h2>setDescription( String description )</h2>
	 * This setter method sets the Book object's description.
	 * <br><br>
	 * @param description this is the book description.
	 */
	public void setDescription( String description ) {
		this.description = description;
	}
	
	/**
	 * <h2>getHashtags( )</h2>
	 * This getter method returns the hashtags of Book object.
	 * <br><br>
	 * @return hashtags these are the book's hashtags.
	 */
	public String getHashtags( ) {
		return hashtags;
	}
	
	/**
	 * <h2>setHashtags( String hashtags )</h2>
	 * This setter method sets the Book object's hashtags.
	 * <br><br>
	 * @param hashtags these are the book's hashtags.
	 */
	public void setHashtags( String hashtags ) {
		this.hashtags = hashtags;
	}
	
	/**
	 * <h2>getGenre( )</h2>
	 * This getter method returns the genre of Book object.
	 * <br><br>
	 * @return genre this is the book's genre.
	 */
	public String getGenre( ) {
		return genre;
	}
	
	/**
	 * <h2>setGenre( String genre )</h2>
	 * This setter method sets the Book object's genre.
	 * <br><br>
	 * @param genre this is the book's genre.
	 */
	public void setGenre( String genre ) {
		this.genre = genre;
	}

	/**
	 * <h2>getAuthor( )</h2>
	 * This getter method returns the author of Book object.
	 * <br><br>
	 * @return author this is the book's author.
	 */
	public Author getAuthor( ) {
		return author;
	}

	/**
	 * <h2>setAuthor( String author )</h2>
	 * This setter method sets the Book object's author.
	 * <br><br>
	 * @param author this is the book's author.
	 */
	public void setAuthor( Author author ) {
		this.author = author;
	}
	
	/**
	 * <h2>hashtagToString( )</h2>
	 * This method returns all Book object hashtags in a string.
	 * <br><br>
	 * @return h this is the book's hashtags combined in a single String.
	 */
	public String hashtagToString( ) {
		String s[ ] = hashtags.split( "/" );
		String h = "";
		for( int i = 0; i < s.length; i++ ) {
			h += "#" + s[ i ] + " ";
		}
		return h;
	}
	
	/**
	 * <h2>toString( )</h2>
	 * This method overrides the default toString( ) method to display the name of book, author, genre, hashtags, and loaned status. 
	 */
	@Override
	public String toString( ) {
		if( this.getLoaned( ) == true )
			return this.name.toUpperCase() + " by " + this.author + " ( " + this.genre + " ) LOANED\n\t" + this.hashtagToString( );
		else
			return this.name.toUpperCase() + " by " + this.author + " ( " + this.genre + " )\n\t" + this.hashtagToString( );
	}
}