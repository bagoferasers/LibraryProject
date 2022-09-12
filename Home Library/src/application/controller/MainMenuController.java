package application.controller;

import application.model.Library;
import java.io.File;
import java.net.URL;
import application.model.Author;
import application.model.Book;
import application.model.Genre;
import application.model.Hashtag;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
    	Library.books.clear( );
		Author jane = new Author( "jane", "bailey" );
		Author kathy = new Author( "kathy", "bailey" );
		Author bart = new Author( "bart", "bailey" );
		Genre genre = new Genre( "Sci-Fi" );
		String[ ] h = { "h1", "h2", "h3" };
		Hashtag hashtags = new Hashtag( h );
		Image i = new Image( "application/view/TheHobbit_COVER.PNG" );
		Book b1 = new Book( i, "The Hobbit", "A book about words.", hashtags, jane, genre );
		Book b2 = new Book( i, "AssHat", "A book about ass.", hashtags, kathy, genre );
		Book b3 = new Book( i, "Poo", "A book about poo.", hashtags, bart, genre );
		Book b4 = new Book( i, "Rodents", "A book about rodents.", hashtags, kathy, genre );
		Book b5 = new Book( i, "Cosmos", "A book about space.", hashtags, kathy, genre );
		Library.books.add( b1 );
		Library.books.add( b2 );
		Library.books.add( b3 );
		Library.books.add( b4 );
		Library.books.add( b5 );
    	searchList.setItems( Library.books );
    }
    
    @FXML
    void goSelectBook( MouseEvent event ) {
    	try {
    		Book b = searchList.getSelectionModel( ).getSelectedItem( );
    		Library.selected = b;
    		URL selectBookURL = new File( "D:\\godot\\ActionRPGTutorial\\LibraryProject\\Home Library\\SelectedBook.fxml" ).toURI( ).toURL( );
    		borderPane = FXMLLoader.load( selectBookURL );
    		Scene scene = new Scene( borderPane );
    		Stage stage = new Stage( );
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

