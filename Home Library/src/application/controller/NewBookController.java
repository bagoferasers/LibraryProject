package application.controller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import application.model.Author;
import application.model.Book;
import application.model.Library;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * <h2>class NewBookController</h2>
 * This controller is for the NewBook scene and if selected Book is not empty, is initialized
 * to the values within the selected Book object. It implements goHome, which sets the prompt text back and
 * returns to MainMenu scene. It also implements goSubmit, which adds a new Book object to Library.
 * <br><br>
 * @author bagoferasers
 */
public class NewBookController implements Initializable {

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
    private Button goSubmit;

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

	/**
	 * <h2>goHome( ActionEvent event )</h2>
	 * This method takes in the event from goHome Button and goes to the MainMenu scene. Before it does,
	 * it makes sure that the selected book is unselected and the prompt text is returned.
	 * <br><br>
	 * @param event is the ActionEvent from goHome Button.
	 */
    @FXML
    void goHome( ActionEvent event ) {
    	try {
    		//set prompt text
    		enterTitle.setPromptText( "Enter book title." );
    		enterAuthor.setPromptText( "Enter book author." );
    		enterGenre.setPromptText( "Enter book genre." );
    		enterHashtags.setPromptText( "Enter book hashtags delimited by a forward slash. For example : adventure/love/wolves" );
    		enterDescription.setPromptText("Enter book description.");
    		enterImagePath.setPromptText( "Place image in application.view and enter Image path." );
    		enterISBN.setPromptText( "Enter book ISBN." );
    		enterFormat.setPromptText( "Enter book format." );
    		Library.selected = null;
    		Library.loadLibrary( );
    		//go to MainMenu scene
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
    
    /**
     * <h2>goSubmit( ActionEvent event )</h2>
     * This method creates and updates Book objects. It checks to see if all entries are filled,
     * deletes the previous Book in csv if necessary, adds the new Book object to csv,
     * and updates searched books if necessary with new Book object,
     * enters the prompt texts into entry fields, and returns to the MainMenu scene.
     * <br><br>
     * @param event is the ActionEvent from goSubmit Button.
     */
    @FXML
    void goSubmit( ActionEvent event ) {
    	String[ ] fullName = null;
    	Author a = null;
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirmation Dialog");
    	alert.setContentText("Are you sure?");
    	Optional <ButtonType> action = alert.showAndWait();
    	if( action.get() == ButtonType.OK) {
	    	try {
	    		//if anything is blank on goSubmit
	    		if ( enterTitle.getText( ).length( ) == 0 ||
	    			 enterAuthor.getText( ).length( ) == 0 ||
	    			 enterGenre.getText( ).length( ) == 0 ||
	    			 enterHashtags.getText( ).length( ) == 0 ||
	    			 enterDescription.getText( ).length( ) == 0 ||
	    			 enterISBN.getText( ).length( ) == 0 ||
	    			 enterFormat.getText( ).length( ) == 0 ) {
	    			System.out.println( "Not everything is filled. Please fill all text areas." );
	    		}
	    		else {
	    			/////////////////////////////////////////delete previous book entry////////////////////////////////////////////////////
	    	    	//create temporary file to write to
	    	    	File tmp = new File( "data/tmp.csv" );
	    	    	tmp.getParentFile( ).mkdirs( ); 
	    	    	try {
	    				tmp.createNewFile( );
	    			} catch ( IOException e1 ) {
	    				e1.printStackTrace( );
	    			}
	    	    	//open LibraryData.csv
	    	    	File libData = new File( "data/LibraryData.csv" );
	    	    	try (
	    	    	BufferedReader csvReader = new BufferedReader( new FileReader( libData ) ) ) {
	    	    	BufferedWriter csvWriter = new BufferedWriter( new FileWriter( tmp ) );
	    	    	//remove header from libData and append to tmp
	    	    	String row = csvReader.readLine( );
	    	    	csvWriter.append( row );
	    			//find row that book is on and continue instead of adding it to tmp
	    	    	while ( ( row = csvReader.readLine( ) ) != "" && row != null ) {
	    				String[ ] bookData = row.split( ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)" );
	    				if( Library.selected != null && bookData != null && ( bookData.length < 8 || Long.valueOf( bookData[ 6 ] ) == Library.selected.getISBN( ) ) ) {
	    					continue;
	    				}
	    				//if not Book object, append to temp file
	    				csvWriter.append( "\n" );
	    				csvWriter.append( row );
	    			}
	    	    	//remove old LibraryData.csv and rename temporary file to LibraryData.csv
	    			csvWriter.close( );
	    			csvReader.close( );
	    	    	libData.delete( );
	    	    	tmp.renameTo( libData );
	    			Library.books.remove( Library.selected );
	    			Library.searchedBooks.remove( Library.selected );
	    			Library.loadLibrary( );
	    	    	} catch ( IOException e ) {
	    				e.printStackTrace( );
	    			}
	    	    	//////////////////////////////replace old entry with new updated Book object/////////////////////////////////////////////////
	    			FileWriter csvWriter = new FileWriter( "data/LibraryData.csv", true );
	    			csvWriter.append( "\n" );
	    			csvWriter.append( enterTitle.getText( ) );
	    			csvWriter.append( "," );
	    			fullName = enterAuthor.getText( ).split( " ", 2 );
	    			if ( fullName.length != 2 ) {
	    				String firstName = fullName[ 0 ];
	    				csvWriter.append( firstName );
	    				csvWriter.append( "," );
	    				String lastName = "";
	    				csvWriter.append( lastName );
	    				csvWriter.append( "," );
	    			}
	    			else {
	    				String firstName = fullName[ 0 ];
	    				csvWriter.append( firstName );
	    				csvWriter.append( "," );
	    				String lastName = fullName[ 1 ];	
	    				csvWriter.append( lastName );
	    				csvWriter.append( "," );
	    			}
	    			csvWriter.append( enterGenre.getText( ) );
	    			csvWriter.append( "," );
	    			csvWriter.append( enterHashtags.getText( ) );
	    			csvWriter.append( "," );
	    			csvWriter.append( enterDescription.getText( ) );
	    			csvWriter.append( "," );
	    			csvWriter.append( enterISBN.getText( ) );
	    			csvWriter.append( "," );
	    			csvWriter.append( enterImagePath.getText( ) );
	    			csvWriter.append(",");
	    			csvWriter.append (enterFormat.getText( ) );
	    			csvWriter.append( "," );
	    			csvWriter.append( "FALSE" );
	    			csvWriter.append( "," );
	    			csvWriter.append( "nodate" );
	    			csvWriter.append( "," );
	    			csvWriter.append( "noname" );
	    			csvWriter.close( );
	    			Library.books.clear( );
	    			Library.loadLibrary() ;
	    		}
	    		//add Book object to Library's ObservableList searchedBooks
	    		if( fullName != null && fullName.length > 1 ) {
	    			a = new Author( fullName[ 0 ],fullName[ 1 ] );
	    			Book b = new Book( enterImagePath.getText( ), enterTitle.getText( ), enterDescription.getText( ), enterHashtags.getText( ), a,
	    				enterGenre.getText( ), Long.valueOf( enterISBN.getText( ) ), enterFormat.getText( ), false, "nodate", "noname" );
	    			Library.searchedBooks.add( b );
	    		}
	    		else if( fullName != null ) {
	    			a = new Author( fullName[ 0 ],"" );
	    			Book b = new Book( enterImagePath.getText( ), enterTitle.getText( ), enterDescription.getText( ), enterHashtags.getText( ), a,
	    				enterGenre.getText( ), Long.valueOf( enterISBN.getText( ) ), enterFormat.getText( ), false, "nodate", "noname" );
	    			Library.searchedBooks.add( b );
	    		}
	    		//clear Library selected book
	    		Library.selected = null;
	    		//set prompt text
	    		enterTitle.setPromptText( "Enter book title." );
	    		enterAuthor.setPromptText( "Enter book author." );
	    		enterGenre.setPromptText( "Enter book genre." );
	    		enterHashtags.setPromptText( "Enter book hashtags delimited by a forward slash. For example : adventure/love/wolves" );
	    		enterDescription.setPromptText( "Enter book description." );
	    		enterImagePath.setPromptText( "Place image in application.view and enter Image path." );
	    		enterISBN.setPromptText( "Enter book ISBN." );
	    		enterFormat.setPromptText( "Enter book format." );
	    		//go to MainMenu scene
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
    }

    /**
     * <h2>initialize( URL location, ResourceBundle resources )</h2>
     * Called to initialize a controller after its root element has been completely processed.
     * It sets the text of the fields to the values of the currently selected book.
     * <br><br>
     * @param location is a location of something.
     * @param resources are nice to have.
     */
	@Override
	public void initialize( URL location, ResourceBundle resources ) {
		if( Library.selected != null ) {
			enterTitle.setText( Library.selected.getName( ) );
			enterAuthor.setText( Library.selected.author.getFirstName( ) + " " + Library.selected.author.getLastName( ) );
			enterGenre.setText( Library.selected.getGenre( ) );
			enterHashtags.setText( Library.selected.hashtags );
			enterDescription.setText( Library.selected.getDescription( ) );
			enterISBN.setText( String.valueOf( Library.selected.getISBN( ) ) );
			enterFormat.setText( Library.selected.getFormat( ) );
			enterImagePath.setText( Library.selected.BookImage );
		}
		else
			System.out.println( "not null" );
	}
}