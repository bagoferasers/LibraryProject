package application.model;

import java.io.BufferedReader;
import java.io.FileReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Library {
	public static ObservableList < Book > books = FXCollections.observableArrayList( );
	public static ObservableList < Book > searchedBooks = FXCollections.observableArrayList();
	public static Book selected;
	
	public static void loadLibrary() {
		try {
			Library.books.clear();
			//create objects
			BufferedReader csvReader = new BufferedReader( new FileReader( "data/LibraryData.csv" ) );
			Book b = new Book();
			//get rid of first row containing column IDs
			String row = csvReader.readLine( );
			//while csv isn't null, read a new line into a Book object
			while ( ( row = csvReader.readLine( ) ) != null ) {
				String[] bookData = row.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
				if( bookData.length > 10 ) {
					b.setName( bookData[ 0 ] );
					Author a = new Author( );
					a.setFirstName( bookData[ 1 ] );
					a.setLastName( bookData[ 2 ] );
					b.setAuthor( a );
					b.setGenre( bookData[ 3 ] );
					b.setHashtags( bookData[ 4 ] );
					b.setDescription( bookData[ 5 ] );
					b.setISBN( Long.valueOf(bookData[ 6 ] ) );
					b.setPicture( bookData[ 7 ] );
					b.setFormat(bookData[8]);
					if(bookData[9].contains("TRUE"))
						b.setLoaned(true);
					b.setDateLoaned(bookData[10]);
					b.setNameLoaned(bookData[11]);
					//once book object is complete, add to library
					Library.books.add(b);
					b = new Book();
				}
				else if( bookData.length > 9 ) {
					b.setName( bookData[ 0 ] );
					Author a = new Author( );
					a.setFirstName( bookData[ 1 ] );
					a.setLastName( bookData[ 2 ] );
					b.setAuthor( a );
					b.setGenre( bookData[ 3 ] );
					b.setHashtags( bookData[ 4 ] );
					b.setDescription( bookData[ 5 ] );
					b.setISBN( Long.valueOf(bookData[ 6 ] ) );
					b.setPicture( bookData[ 7 ] );
					b.setFormat(bookData[8]);
					if(bookData[9].contains("TRUE"))
						b.setLoaned(true);
					b.setDateLoaned("nodate");
					b.setNameLoaned("noname");
					//once book object is complete, add to library
					Library.books.add(b);
					b = new Book();
				}
			}
			csvReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}