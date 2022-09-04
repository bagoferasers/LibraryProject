/**
 * 
 */
package application.model;

import javafx.scene.image.Image;
/**
 * @author colbybailey
 *
 */
public class Book {
	private Image picture;
	private String name;
	private String description;
	private String[ ] hashtags;
	private String[ ] authors;
	private String genre;
	
	public Book( Image picture, String name, String description,
		         String[ ] hashtags, String[ ] authors, String genre ) {
		
	}
	
	public Image getPicture( ) {
		return this.picture;
	}
	
	public void setPicture( Image picture ) {
		this.picture = picture;
	}
	
	public String getName( ) {
		return this.name;
	}
	
	public void setName( String name ) {
		this.name = name;
	}
	
	public String getDescription( ) {
		return this.description;
	}
	
	public void setDescription( String description ) {
		this.description = description;
	}
	
	public String[ ] getHashtags( ) {
		return this.hashtags;
	}
	
	public void setHashtags( String[ ] hashtags ) {
		this.hashtags = hashtags;
	}
	
	public String[ ] getAuthors( ) {
		return this.authors;
	}
	
	public void setAuthors( String[ ] authors ) {
		this.authors = authors;
	}
	
	public String getGenre( ) {
		return this.genre;
	}
	
	public void setGenre( String genre ) {
		this.genre = genre;
	}
}
