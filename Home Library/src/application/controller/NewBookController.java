package application.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import application.model.Library;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class NewBookController {

    @FXML
    private Label BookGenre;

    @FXML
    private BorderPane SelectedPane;

    @FXML
    private Label BookHashtags;

    @FXML
    private Label BookDescription;

    @FXML
    private ImageView BookImage;

    @FXML
    private Label BookTitle;

    @FXML
    private Label BookAuthor;
    
    @FXML
    private Button goHome;

    @FXML
    private TextField enterTitle;

    @FXML
    private TextField enterAuthor;

    @FXML
    private TextField enterGenre;

    @FXML
    private TextField enterHashtags;

    @FXML
    private TextArea enterDescription;

    @FXML
    private TextField enterImagePath;
    
    @FXML
    private TextField enterISBN;
    
    @FXML
    private TextField enterFormat;
    
    @FXML
    private Label BookFormat;

    @FXML
    void goHome( ActionEvent event ) {
    	try {
    		URL addBookURL = new File( "MainMenu.fxml" ).toURI( ).toURL( );
			Parent root = FXMLLoader.load( addBookURL );
			Scene scene = new Scene( root );
			Stage stage = ( Stage ) ( ( Node ) event.getSource( ) ).getScene( ).getWindow( );
			stage.setScene( scene );
			stage.show( );
		} catch ( Exception e ) {
			e.printStackTrace( );
		}
    }
    
    @FXML
    void goSubmit( ActionEvent event ) {
    	try {
    		//if anything is blank on goSubmit, continue to main
    		if ( enterTitle.getText().length() == 0 ||
    			 enterAuthor.getText().length() == 0 ||
    			 enterGenre.getText().length() == 0 ||
    			 enterHashtags.getText().length() == 0 ||
    			 enterDescription.getText().length() == 0 ||
    			 enterISBN.getText().length() == 0 ||
    			 enterFormat.getText().length() == 0 ) {
    			System.out.println("Not everything is filled. Please fill all text areas.");
    		}
    		else {
    			FileWriter csvWriter = new FileWriter( "data/LibraryData.csv", true );
    			csvWriter.append("\n");
    			csvWriter.append(enterTitle.getText());
    			csvWriter.append(",");
    			String fullName [ ] = enterAuthor.getText().split(" ", 2);
    			if ( fullName.length != 2 ) {
    				String firstName = fullName[ 0 ];
    				csvWriter.append(firstName);
    				csvWriter.append(",");
    				String lastName = "";
    				csvWriter.append(lastName);
    				csvWriter.append(",");
    			}
    			else {
    				String firstName = fullName[ 0 ];
    				csvWriter.append(firstName);
    				csvWriter.append(",");
    				String lastName = fullName[ 1 ];	
    				csvWriter.append(lastName);
    				csvWriter.append(",");
    			}
    			csvWriter.append(enterGenre.getText());
    			csvWriter.append(",");
    			csvWriter.append(enterHashtags.getText());
    			csvWriter.append(",");
    			csvWriter.append(enterDescription.getText());
    			csvWriter.append(",");
    			csvWriter.append(enterISBN.getText());
    			csvWriter.append(",");
    			csvWriter.append(enterImagePath.getText());
    			csvWriter.append(",");
    			csvWriter.append(enterFormat.getText());
    			csvWriter.close();	
    			//return Library search?
    			Library.books.clear();
    			Library.loadLibrary();
    		}
    		//else add book then continue to main

	    	//return to home scene 
    		URL addBookURL = new File( "MainMenu.fxml" ).toURI( ).toURL( );
			Parent root = FXMLLoader.load( addBookURL );
			Scene scene = new Scene( root );
			Stage stage = ( Stage ) ( ( Node ) event.getSource( ) ).getScene( ).getWindow( );
			stage.setScene( scene );
			stage.show( );
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}