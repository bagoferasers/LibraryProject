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
				String[] bookData = row.split(",");
				b.setName( bookData[ 0 ] );
				Author a = new Author();
				a.setLastName( bookData[ 1 ] );
				a.setFirstName( bookData[ 2 ] );
				b.setAuthor(a);
				b.setGenre( bookData[ 3 ] );
				b.setHashtags( bookData[ 4 ] );
				b.setDescription( bookData[ 5 ] );
				b.setISBN( Integer.valueOf(bookData[ 6 ] ) );
				b.setPicture( bookData[ 7 ] );
				//once book object is complete, add to library
				Library.books.add(b);
				//clear out local b object
				b = new Book();
			}
			csvReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
