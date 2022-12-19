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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * <h2>class LoanBookController</h2>
 * This controller is for the LoanBook scene. It implements goLoan( ) and goHome( ) methods.
 * <br><br>
 * @author bagoferasers
 */
public class LoanBookController {
	
	@FXML
	private Button goLoan;
	
	@FXML
	private Button goHome;
	
	@FXML
	public TextField dateLoaned;
	
	@FXML
	private TextField nameLoaned;
	
	/**
	 * <h2>goLoan( ActionEvent event )</h2>
	 * This method takes in the event from goLoan button and marks the selected book as being on loan. It 
	 * also attaches the name and date of who loaned it and when. It updates the csv and then returns to MainMenu scene.
	 * <br><br>
	 * @param event is the ActionEvent from goLoan Button.
	 * @throws IOException if stream to file cannot be written to or closed.
	 */
	public void goLoan( ActionEvent event ) throws IOException {
		Library.selected.setLoaned( true );
		Library.selected.setDateLoaned( dateLoaned.getText( ) );
		Library.selected.setNameLoaned( nameLoaned.getText( ) );
		//find Book object in csv and change bookData[9] to TRUE
    	File tmp = new File( "data/tmp.csv" );
    	tmp.getParentFile( ).mkdirs( ); 
    	tmp.createNewFile( );
    	//open LibraryData.csv
    	File libData = new File( "data/LibraryData.csv" );
    	try (
    	BufferedReader csvReader = new BufferedReader( new FileReader( libData ) ) ) {
    	BufferedWriter csvWriter = new BufferedWriter( new FileWriter( tmp ) );
    	//remove header from libData and append to tmp
    	String row = csvReader.readLine( );
    	csvWriter.append( row );
		//find row that book is on
    	while ( ( row = csvReader.readLine( ) ) != "" && row != null ) {
			String[ ] bookData = row.split( ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)" );
			//if Book object is to be loaned, mark it as loaned and add to tmp
			if( Long.valueOf( bookData[ 6 ] ) == Library.selected.getISBN( ) ) {
				bookData[ 9 ] = "TRUE";
				String editedRow = bookData[ 0 ] + "," + bookData[ 1 ] + "," + bookData[ 2 ] + "," + bookData[ 3 ] + "," + bookData[ 4 ] +
						"," + bookData[ 5 ] + "," + bookData[ 6 ] + "," + bookData[ 7 ] + "," + bookData[ 8 ] + "," + bookData[ 9 ];
				String s = editedRow + "," + dateLoaned.getText( ) + "," + nameLoaned.getText( );
				csvWriter.append( "\n" );
				csvWriter.append( s );
			}
			else {
			//if not Book object to be loaned, append to temp file
			csvWriter.append( "\n" );
			csvWriter.append( row );
			}
		}
    	//remove old LibraryData.csv and rename tmp file to "LibraryData.csv"
		csvWriter.close( );
		csvReader.close( );
    	libData.delete( );
    	tmp.renameTo( libData );
    	Library.selected = null;
		Library.loadLibrary( );
		//return to home
		URL addBookURL = new File( "MainMenu.fxml" ).toURI( ).toURL( );
		Parent root = FXMLLoader.load( addBookURL );
		Scene scene = new Scene( root );
		Stage stage = ( Stage ) ( ( Node ) event.getSource( ) ).getScene( ).getWindow( );
		stage.setScene( scene );
		stage.show( );
    	} catch ( IOException e ) {
			e.printStackTrace( );
		}
	}
	
	/**
	 * <h2>goHome( ActionEvent event )</h2>
	 * This method takes in the event from goHome Button and goes to the MainMenu scene. Before it goes,
	 * it makes sure that the selected book is unselected.
	 * <br><br>
	 * @param event is the ActionEvent from goHome Button.
	 * @throws IOException if stream to file cannot be written to or closed.
	 */
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
