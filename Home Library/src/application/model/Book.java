package application.model;

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
	
	public Book( String BookImage, String name, String description,
			String hashtags, Author author, String genre, long ISBN, String format, Boolean b) {
		this.setPicture( BookImage );
		this.setName( name );
		this.setDescription( description );
		this.setHashtags( hashtags );
		this.setAuthor( author );
		this.setGenre( genre );
		this.setISBN( ISBN );
		this.setFormat(format);
		this.setLoaned(b);
	}
	
	public Book() {
		this.setPicture(null);
		this.setName("");
		this.setDescription("");
		this.setHashtags("");
		this.setAuthor(null);
		this.setGenre(null);
		this.setISBN(0);
		this.setFormat("");
		this.setLoaned(false);
	}
	
	public Boolean getLoaned() {
		return loaned;
	}
	
	public void setLoaned(Boolean b) {
		this.loaned = b;
	}
	
	public void setFormat( String f ) {
		this.format = f;
	}
	
	public String getFormat( ) {
		return format;
	}

	public void setISBN(long i) {
		this.ISBN = i;
	}
	
	public long getISBN( ) {
		return ISBN;
	}
	
	public String getPicture( ) {
		return "application/view/" + BookImage;
	}
	
	public void setPicture( String BookImage ) {
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
		return hashtags;
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
	
	public String hashtagToString( ) {
		String s[] = hashtags.split("/");
		String h = "";
		for( int i = 0; i < s.length; i++ ) {
			h += "#" + s[ i ] + " ";
		}
		return h;
	}
	
	@Override
	public String toString( ) {
		if(this.getLoaned() == true )
			return this.name + " by " + this.author + " ( " + this.genre + " ) " + this.hashtagToString() + " LOANED";
		else
			return this.name + " by " + this.author + " ( " + this.genre + " ) " + this.hashtagToString();
	}
}
