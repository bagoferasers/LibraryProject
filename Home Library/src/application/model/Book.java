package application.model;

import javafx.scene.image.Image;

public class Book {
    private Image BookImage;
	private String name;
	private String description;
	private String hashtags;
	private Author author;
	private String genre;
	
	public Book( Image BookImage, String name, String description,
			String hashtags, Author author, String genre ) {
		this.setPicture( BookImage );
		this.setName( name );
		this.setDescription( description );
		this.setHashtags( hashtags );
		this.setAuthor( author );
		this.setGenre( genre );
	}
	
	public Book() {
		this.setPicture(null);
		this.setName("");
		this.setDescription("");
		this.setHashtags("");
		this.setAuthor(null);
		this.setGenre(null);
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
	
	public String getHashtags( ) {
		String s[] = hashtags.split("/");
		String h = "";
		 for( int i = 0; i < s.length; i++ ) {
			 h += "#" + s[ i ] + " ";
		 }
		return h;
	}
	
	public void setHashtags( String hashtags ) {
		this.hashtags = hashtags;
	}
	
	public String getGenre( ) {
		return genre;
	}
	
	public void setGenre( String genre ) {
		this.genre = genre;
	}

	public Author getAuthor( ) {
		return author;
	}

	public void setAuthor( Author author ) {
		this.author = author;
	}
	
	@Override
	public String toString( ) {
		return this.name + " by " + this.author + " ( " + this.genre + " ) " + this.getHashtags( );
	}
}
