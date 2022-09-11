/**
 * 
 */
package application.model;

//import java.io.PrintStream;

import javafx.scene.image.Image;
/**
 * @author colbybailey
 *
 */
public class Book {
    private Image BookImage;
	private String name;
	private String description;
	private Hashtag hashtags;
	private Author author;
	private Genre genre;
	
	public Book( Image BookImage, String name, String description,
		         Hashtag hashtags, Author author, Genre genre ) {
		this.setPicture(BookImage);
		this.setName(name);
		this.setDescription(description);
		this.setHashtags(hashtags);
		this.setAuthor(author);
		this.setGenre(genre);
	}
	
	public Image getPicture( ) {
		return BookImage;
	}
	
	public void setPicture( Image BookImage ) {
		this.BookImage = BookImage;
	}
	
	public String getName( ) {
		return name;
	}
	
	public void setName( String name ) {
		this.name = name;
	}
	
	public String getDescription( ) {
		return description;
	}
	
	public void setDescription( String description ) {
		this.description = description;
	}
	
	public Hashtag getHashtags( ) {
		return hashtags;
	}
	
	public void setHashtags( Hashtag hashtags ) {
		this.hashtags = hashtags;
	}
	
	public Genre getGenre( ) {
		return genre;
	}
	
	public void setGenre( Genre genre ) {
		this.genre = genre;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
	
	@Override
	public String toString() {
		return this.name + " by " + this.author + " ( " + this.genre + " ) " + this.hashtags;
	}
}
