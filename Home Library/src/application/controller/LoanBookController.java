package application.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
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
import javafx.stage.Stage;


public class LoanBookController {
	@FXML
	private Button goLoan;
	
	@FXML
	private Button goHome;
	
	public void goLoan(ActionEvent event) throws IOException {
		Library.selected.setLoaned(true);
		//find it in csv and change bookData[9] to TRUE
		//create temporary file to write to
    	File tmp = new File("data/tmp.csv");
    	tmp.getParentFile().mkdirs(); 
    	tmp.createNewFile();
    	//open LibraryData.csv
    	File libData = new File( "data/LibraryData.csv" );
    	try (
    	BufferedReader csvReader = new BufferedReader( new FileReader( libData ) )) {
    	BufferedWriter csvWriter = new BufferedWriter( new FileWriter( tmp ) );
    	// remove header and add header
    	String row = csvReader.readLine();
    	csvWriter.append(row);
		//find row that book is on and mark it as loaned
    	while ( ( row = csvReader.readLine( ) ) != "" && row != null ) {
			String[ ] bookData = row.split( ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)" );
			if( Long.valueOf( bookData[ 6 ] ) == Library.selected.getISBN( ) ) {
				bookData[9] = "TRUE";
				String editedRow = bookData[0] + "," + bookData[1] + "," + bookData[2] + "," + bookData[3] + "," + bookData[4] +
						"," + bookData[5] + "," + bookData[6] + "," + bookData[7] + "," + bookData[8] + "," + bookData[9];
				csvWriter.append("\n");
				csvWriter.append(editedRow);
			}
			else {
			//if not book, append to temp file
			csvWriter.append("\n");
			csvWriter.append(row);
			}
		}
    	//remove old LibraryData.csv and rename temporary file to LibraryData.csv
		csvWriter.close();
		csvReader.close();
    	libData.delete();
    	tmp.renameTo(libData);
    	Library.selected = null;
		Library.loadLibrary();
		//return to home
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
