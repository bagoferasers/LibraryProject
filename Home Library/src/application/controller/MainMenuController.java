package application.controller;
import application.model.Library;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import application.model.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * <h2>class MainMenuController</h2>
 * This controller is for the MainMenu scene and the ListView is initialized to the Book Objects currently 
 * inside of searched books. It implements the about, add, and search buttons. Its main function is to search the library
 * for different titles, authors, hashtags, and genres.
 * <br><br>
 * @author bagoferasers
 */
public class MainMenuController implements Initializable {

    @FXML
    private ListView< Book > searchList;

    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton;

    @FXML
    private RadioButton bookButton;

    @FXML
    private RadioButton hashtagsButton;

    @FXML
    private RadioButton authorsButton;

    @FXML
    private RadioButton genresButton;
    
    @FXML
    private BorderPane borderPane;
    
    /**
     * <h2>onSearch( ActionEvent event )</h2>
     * This method takes the event from the onSearch Button and updates the searched books accordingly
     * and filling up the results into the ListView.
     * <br><br>
     * @param event is the ActionEvent from onSearch Button.
     */
    @FXML
	public void onSearch( ActionEvent event ) {
    	//initialize by clearing previous searchedBooks
    	Library.searchedBooks.clear( );
    	//if library has books
    	if( Library.books != null ) {
    		//if bookButton is selected, search library for books and add to searchedBooks
    		if( bookButton.isSelected( ) ) {
    			//if nothing is in the searchField, display all books
    			if( searchField.getText( ).isEmpty( ) && Library.searchedBooks.isEmpty( ) ) {
    				Library.searchedBooks.addAll( Library.books );
    			}
    			else {
    				//search books for keyword
    				for( int i = 0; i < Library.books.size( ); i++ ) {
    					//if book at position i contains keyword, add to searchedBooks
    					if( Library.books.get( i ).getName( ).toLowerCase( ).contains(searchField.getText( ).toLowerCase( ) ) 
    					   && !Library.searchedBooks.contains( Library.books.get( i ) ) ) {
    						Library.searchedBooks.add( Library.books.get( i ) );
    					}
    				}
    			}
    		}
    		//if hashtagsButton is selected, search library for hashtags and add to searchedBooks
    		if( hashtagsButton.isSelected( ) ) {
    			//if nothing is in the searchField, display all books
    			if( searchField.getText( ).isEmpty( ) && Library.searchedBooks.isEmpty( ) ) {
    				Library.searchedBooks.addAll( Library.books );
    			}
    			else {
    				//search books for keyword
    				for( int i = 0; i < Library.books.size( ); i++ ) {
    					//if book at position i contains keyword, add to searchedBooks
    					String s[ ] = Library.books.get( i ).getHashtags( ).split( "/" );
    					for( int j = 0; j < s.length; j++ ) {
    						if( s[ j ].toLowerCase( ).contains( searchField.getText( ).toLowerCase( ) )
    					       && !Library.searchedBooks.contains( Library.books.get( i ) ) ) {
    						   Library.searchedBooks.add(Library.books.get( i ) );
    						}
    					}

    				}
    			}
    		}
    		//if authorsButton is selected, search library for authors and add to searchedBooks
    		if( authorsButton.isSelected( ) ) {
    			//if nothing is in the searchField, display all books
    			if(searchField.getText( ).isEmpty( ) && Library.searchedBooks.isEmpty( ) ) {
    				Library.searchedBooks.addAll( Library.books );
    			}
    			else {
    				//search books for keyword
    				for( int i = 0; i < Library.books.size( ); i++ ) {
    					//if book at position i contains keyword, add to searchedBooks
    					if( Library.books.get( i ).getAuthor( ).getFirstName( ).toLowerCase( ).contains( searchField.getText( ).toLowerCase( ) )
    					   || Library.books.get( i ).getAuthor( ).getLastName( ).toLowerCase( ).contains( searchField.getText( ).toLowerCase( ) )
    					   && !Library.searchedBooks.contains( Library.books.get( i ) ) ) {
    						Library.searchedBooks.add( Library.books.get( i ) );
    					}
    				}
    			}
    		}
    		//if genresButton is selected, search library for authors and add to searchedBooks
    		if( genresButton.isSelected( ) ) {
    			//if nothing is in the searchField, display all books
    			if(searchField.getText( ).isEmpty( ) && Library.searchedBooks.isEmpty( ) ) {
    				Library.searchedBooks.addAll( Library.books );
    			}
    			else {
    				//search books for keyword
    				for( int i = 0; i < Library.books.size( ); i++ ) {
    					//if book at position i contains keyword, add to searchedBooks
    					if( Library.books.get( i ).getGenre( ).toLowerCase( ).contains( searchField.getText( ).toLowerCase( ) )
    					   && !Library.searchedBooks.contains( Library.books.get( i ) ) ) {
    						Library.searchedBooks.add( Library.books.get( i ) );
    					}
    				}
    			}
    		}
    		//if nothing is selected, add entire library to searchedBooks
    		if( !bookButton.isSelected( ) && !hashtagsButton.isSelected( ) && !authorsButton.isSelected( ) && !genresButton.isSelected( ) 
    			&& Library.searchedBooks.isEmpty( ) ) {
    			Library.searchedBooks.addAll( Library.books );
    		}
    		//if nothing is found
    		if( Library.searchedBooks.isEmpty( ) ) {
    			searchField.clear( );
    			searchField.setPromptText( "Nothing found." );
    			
    		}
    		searchList.setItems( Library.searchedBooks );
    	}
    }
    
    /**
     * <h2>goSelectBook( MouseEvent event )</h2>
     * This method takes in an event from clicking on a book with the mouse and changes
     * scenes to the SelectedBook scene. It also sets the library's selected book to the one
     * from the MouseEvent event.
     * <br><br>
     * @param event is the ActionEvent from goSelectBook Button.
     */
    @FXML
    void goSelectBook( MouseEvent event ) {
    	try {
    		Book b = searchList.getSelectionModel( ).getSelectedItem( );
    		if( b != null ) {
	    		Library.selected = b;
	    		URL selectBookURL = new File( "SelectedBook.fxml" ).toURI( ).toURL( );
	    		borderPane = FXMLLoader.load( selectBookURL );
	    		Scene scene = new Scene( borderPane );
	    		Stage stage = ( Stage ) ( ( Node ) event.getSource( ) ).getScene( ).getWindow( );
	    		stage.setScene( scene );
	    		stage.show( );
    		}
    	} catch ( Exception e ) {
    		e.printStackTrace( );
    	}
    }
    
    /**
     * <h2>onAddBook( ActionEvent event )</h2>
     * This method takes in the event from onAddBook Button and changes scenes to 
     * the NewBook scene.
     * <br><br>
     * @param event is the ActionEvent from onAddBook Button.
     */
    @FXML
    public void onAddBook( ActionEvent event ) {
    	try {
    		URL addBookURL = new File( "NewBook.fxml" ).toURI( ).toURL( );
			borderPane = FXMLLoader.load( addBookURL );
			Scene scene = new Scene( borderPane );
			Stage stage = ( Stage ) ( ( Node ) event.getSource( ) ).getScene( ).getWindow( );
			stage.setScene( scene );
			stage.show( );
		} catch ( Exception e ) {
			e.printStackTrace( );
		}
    }
    
    /**
     * <h2>goAbout( ActionEvent event )</h2>
     * This method takes in the event from goAbout Button and changes scenes to 
     * the About scene.
     * <br><br>
     * @param event is the ActionEvent from goAbout Button.
     */
    @FXML
    public void goAbout( ActionEvent event ) {
    	try {
    		URL aboutURL = new File( "About.fxml" ).toURI( ).toURL( );
    		borderPane = FXMLLoader.load( aboutURL );
    		Scene scene = new Scene( borderPane );
    		Stage stage = ( Stage ) ( ( Node ) event.getSource( ) ).getScene( ).getWindow( );
    		stage.setScene( scene );
    		stage.show( );
    	} catch ( Exception e ) {
    		e.printStackTrace( );
    	}
    }

    /**
     * <h2>initialize( URL location, ResourceBundle resources )</h2>
     * Called to initialize a controller after its root element has been completely processed.
     * <br><br>
     * @param location is a location of something.
     * @param resources are nice to have.
     */
	@Override
	public void initialize( URL location, ResourceBundle resources ) {
		if( !Library.searchedBooks.isEmpty( ) ) {
			searchList.setItems( Library.searchedBooks );
		}
	}
}

