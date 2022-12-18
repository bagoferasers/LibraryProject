package application.controller;

import java.io.File;
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
import javafx.stage.Stage;


public class LoanBookController {
	@FXML
	private Button goLoan;
	
	@FXML
	private Button goHome;
	
	public void goLoan(ActionEvent event) {
		//
	}
	
    @FXML
    void goHome( ActionEvent event ) throws IOException {
    	Library.selected = null;
		URL addBookURL = new File( "MainMenu.fxml" ).toURI( ).toURL( );
		Parent root = FXMLLoader.load( addBookURL );
		Scene scene = new Scene( root );
		Stage stage = ( Stage ) ( ( Node ) event.getSource( ) ).getScene( ).getWindow( );
		stage.setScene( scene );
		stage.show( );
    }
}
