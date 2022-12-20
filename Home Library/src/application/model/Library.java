package application.model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.RadioButton;

/**
 * <h2>class Library</h2>
 * This class represents the Library. It contains an Observable list with all Book objects in csv,
 * an Observable list containing Book objects that were searched, and the Book object that is
 * currently selected. It's primary method is to load the library.
 * <br><br>
 * @author bagoferasers
 */
public class Library {
	public static ObservableList < Book > books = FXCollections.observableArrayList( );
	public static ObservableList < Book > searchedBooks = FXCollections.observableArrayList( );
	public static Book selected;
	
	/**
	 * <h2>sortLibrary( ObservableList < Book > searchedBooks )</h2>
	 * This method sorts the searchedBooks by name of book.
	 * @param searchedBooks the ObservableList to be sorted.
	 * @param sortByISBN 
	 */
	public static void sortLibrary( ObservableList < Book > searchedBooks, RadioButton sortByISBN, RadioButton sortByName ) {
		if( sortByISBN.isSelected( ) ) {
			Comparator< Book> c = Comparator.comparing( Book::getISBN );
			Collections.sort( searchedBooks, c );
		}
		else if(sortByName.isSelected( ) ) {
			Comparator< Book > c = Comparator.comparing( Book::getName );
			Collections.sort( searchedBooks, c );
		}
	}
	
	/**
	 * <h2>loadLibrary( )</h2>
	 * This method loads the csv into Book objects and places each Book object created into the Library.
	 */
	public static void loadLibrary( ) {
		try {
			Library.books.clear( );
			//create objects
			BufferedReader csvReader = new BufferedReader( new FileReader( "data/LibraryData.csv" ) );
			Book b = new Book( );
			//get rid of first row containing column IDs
			String row = csvReader.readLine( );
			//while csv isn't null, read a new line into a Book object
			while ( ( row = csvReader.readLine( ) ) != null ) {
				String[ ] bookData = row.split( ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)" );
				//if book is loaned
				if( bookData.length > 10 ) {
					b.setName( bookData[ 0 ] );
					Author a = new Author( );
					a.setFirstName( bookData[ 1 ] );
					a.setLastName( bookData[ 2 ] );
					b.setAuthor( a );
					b.setGenre( bookData[ 3 ] );
					b.setHashtags( bookData[ 4 ] );
					b.setDescription( bookData[ 5 ] );
					b.setISBN( Long.valueOf( bookData[ 6 ] ) );
					b.setPicture( bookData[ 7 ] );
					b.setFormat( bookData[ 8]  );
					if( bookData[ 9 ].contains( "TRUE" ) )
						b.setLoaned( true );
					b.setDateLoaned( bookData[ 10 ] );
					b.setNameLoaned( bookData[ 11 ] );
					//once book object is complete, add to library
					Library.books.add( b );
					b = new Book( );
				}
				//if book is not loaned
				else if( bookData.length > 9 ) {
					b.setName( bookData[ 0 ] );
					Author a = new Author( );
					a.setFirstName( bookData[ 1 ] );
					a.setLastName( bookData[ 2 ] );
					b.setAuthor( a );
					b.setGenre( bookData[ 3 ] );
					b.setHashtags( bookData[ 4 ] );
					b.setDescription( bookData[ 5 ] );
					b.setISBN( Long.valueOf( bookData[ 6 ] ) );
					b.setPicture( bookData[ 7 ] );
					b.setFormat( bookData[ 8 ] );
					if( bookData[ 9 ].contains( "TRUE" ) )
						b.setLoaned( true );
					b.setDateLoaned( "nodate" );
					b.setNameLoaned( "noname" );
					//once book object is complete, add to library
					Library.books.add( b );
					b = new Book( );
				}
			}
			csvReader.close( );
		} catch ( Exception e ) {
			e.printStackTrace( );
		}
	}
}