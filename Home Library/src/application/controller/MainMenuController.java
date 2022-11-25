package application.controller;

import application.model.Library;
import java.io.File;
import java.net.URL;
import application.model.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainMenuController {

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
    
    @FXML
	public void onSearch( ActionEvent event ) {
    	//initialize by clearing previous searchedBooks
    	Library.searchedBooks.clear();
    	//if library has books
    	if( Library.books != null ) {
    		//if bookButton is selected, search library for books and add to searchedBooks
    		
    		//if hashtagsButton is selected, search library for hashtags and add to searchedBooks
    		
    		//if authorsButton is selected, search library for authors and add to searchedBooks
    		
    		//if genresButton is selected, search library for authors and add to searchedBooks
    		
    		//if nothing is selected, add entire library to searchedBooks
    		if( !bookButton.isSelected() && !hashtagsButton.isSelected() && !authorsButton.isSelected() && !genresButton.isSelected() ) {
    			System.out.println("No radio button selected, displaying all books in library.");
    			Library.searchedBooks.addAll(Library.books);
    			searchList.setItems( Library.searchedBooks );
    		}
    	}
    }
    
    @FXML
    void goSelectBook( MouseEvent event ) {
    	try {
    		Book b = searchList.getSelectionModel( ).getSelectedItem( );
    		Library.selected = b;
    		URL selectBookURL = new File( "SelectedBook.fxml" ).toURI( ).toURL( );
    		borderPane = FXMLLoader.load( selectBookURL );
    		Scene scene = new Scene( borderPane );
    		Stage stage = ( Stage ) ( ( Node ) event.getSource( ) ).getScene( ).getWindow( );
    		stage.setScene( scene );
    		stage.show( );
    	} catch ( Exception e ) {
    		e.printStackTrace( );
    	}
    }
    
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
}

