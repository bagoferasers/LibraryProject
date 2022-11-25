package application.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import application.model.Library;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SelectedBookController implements Initializable {

    @FXML
    private Label BookGenre;

    @FXML
    private Label BookHashtags;

    @FXML
    private Label BookDescription;

    @FXML
    private Text testText;

    @FXML
    private ImageView BookImage;

    @FXML
    private Label BookTitle;

    @FXML
    private Label BookAuthor;
    
    @FXML
    private BorderPane SelectedPane;
    
    @FXML
    private Button goHome;

    @Override
	public void initialize( URL location, ResourceBundle resources ) {
		BookTitle.setText( Library.selected.getName( ) );
		BookGenre.setText( Library.selected.getGenre( ).toString( ) );
		BookHashtags.setText( Library.selected.getHashtags( ).toString( ) );
		BookAuthor.setText( Library.selected.getAuthor( ).toString( ) );
		BookDescription.setText( Library.selected.getDescription( ) );
		Image i = new Image(Library.selected.getPicture());
		BookImage.setImage(i);
	}
    
    public void deleteBook( ActionEvent event ) throws IOException {
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
		//find row that book is on and continue
    	while ( ( row = csvReader.readLine()) != null ) {
			String [] bookData = row.split(",");
			if(Integer.valueOf(bookData[ 6 ]) == Library.selected.getISBN()) {
				System.out.println("Found Book");
				continue;
			}
			//if not book, append to temp file
			csvWriter.append("\n");
			csvWriter.append(row);
		}
    	//remove old LibraryData.csv and rename temporary file to LibraryData.csv
		libData.delete();
		tmp.renameTo(libData);
		csvWriter.close();
		csvReader.close();
		Library.books.remove(Library.selected);
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
		URL addBookURL = new File( "MainMenu.fxml" ).toURI( ).toURL( );
		Parent root = FXMLLoader.load( addBookURL );
		Scene scene = new Scene( root );
		Stage stage = ( Stage ) ( ( Node ) event.getSource( ) ).getScene( ).getWindow( );
		stage.setScene( scene );
		stage.show( );
    }
}
