package application.controller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * <h2>class SelectedBookController</h2>
 * This class displays information about Book objects and implements returnBook, goHome, loan, edit, and delete methods.
 * <br><br>
 * @author bagoferasers
 */
public class SelectedBookController implements Initializable {

    @FXML
    private Label loanedDisplay;
	
	@FXML
    private Label BookGenre;

    @FXML
    private Label BookHashtags;
    
    @FXML
    private Label BookISBN;

    @FXML
    private Label BookDescription;
    
    @FXML
    private Label BookFormat;

    @FXML
    private Text testText;

    @FXML
    private ImageView BookImage;

    @FXML
    private Label BookTitle;

    @FXML
    private Label BookAuthor;
    
    @FXML
    private Label loanedBy;
    
    @FXML
    private BorderPane SelectedPane;
    
    @FXML
    private Button goHome;
    
    @FXML
    private Button loanBook;

    @FXML
    public static Button editBook;
    
    @FXML
    private Button returnBook;
    
    /**
     * <h2>returnBook( ActionEvent event )</h2>
     * This method updates the csv to set loaned to False, updates the Library, and Book object.
     * <br><br>
     * @param event is the ActionEvent from returnBook Button.
     * @throws IOException if stream to file cannot be written to or closed.
     */
    public void returnBook( ActionEvent event ) throws IOException {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirmation Dialog");
    	alert.setContentText("Are you sure?");
    	Optional <ButtonType> action = alert.showAndWait();
    	if( action.get() == ButtonType.OK) {
	    	Library.selected.setLoaned( false );
	    	Library.searchedBooks.remove(Library.selected);
			//find Book object in csv and change loaned to TRUE
			//create temporary file to write to
	    	File tmp = new File( "data/tmp.csv" );
	    	tmp.getParentFile( ).mkdirs( ); 
	    	tmp.createNewFile( );
	    	//open LibraryData.csv
	    	File libData = new File( "data/LibraryData.csv" );
	    	try (
	    	BufferedReader csvReader = new BufferedReader( new FileReader( libData ) ) ) {
	    	BufferedWriter csvWriter = new BufferedWriter( new FileWriter( tmp ) );
	    	//remove header from libData and add to tmp
	    	String row = csvReader.readLine( );
	    	csvWriter.append( row );
			//find row that book is on and mark it as loaned and append to tmp
	    	while ( ( row = csvReader.readLine( ) ) != "" && row != null ) {
				String[ ] bookData = row.split( ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)" );
				if( Long.valueOf( bookData[ 6 ] ) == Library.selected.getISBN( ) ) {
					bookData[ 9 ] = "FALSE";
					String editedRow = bookData[ 0 ] + "," + bookData[ 1 ] + "," + bookData[ 2 ] + "," + bookData[ 3 ] + "," + bookData[ 4 ] +
							"," + bookData[ 5 ] + "," + bookData[ 6 ] + "," + bookData[ 7 ] + "," + bookData[ 8 ] + "," + bookData[ 9 ] + "," + "nodate" + "," + "noname";
					csvWriter.append( "\n" );
					csvWriter.append( editedRow );
				}
				else {
				//if not Book object to be edited, append to temp file
				csvWriter.append( "\n" );
				csvWriter.append( row );
				}
			}
	    	//remove old LibraryData.csv and rename temporary file to LibraryData.csv
			csvWriter.close( );
			csvReader.close( );
	    	libData.delete( );
	    	tmp.renameTo( libData );
	    	Library.selected = null;
			Library.loadLibrary( );
			//return to MainMenu scene
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
     * <h2>loan( ActionEvent event )</h2>
     * This method takes the event from Button and changes to LoanBook scene.
     * <br><br>
     * @param event is the ActionEvent from loan Button.
     */
    public void loan( ActionEvent event ) {
	    URL loanBookURL;
		try {
			loanBookURL = new File( "LoanBook.fxml" ).toURI( ).toURL( );
			Parent borderPane = FXMLLoader.load( loanBookURL );
			Scene scene = new Scene( borderPane );
			Stage stage = ( Stage ) ( ( Node ) event.getSource( ) ).getScene( ).getWindow( );
			stage.setScene( scene );
			stage.show( );
		} catch ( IOException e ) {
			e.printStackTrace( );
		}
    }

    /**
     * <h2>initialize( URL location, ResourceBundle resources )</h2>
     * Called to initialize a controller after its root element has been completely processed.
     * This sets the fields to the currently selected Book object. It also displays if book is loaned.
     * <br><br>
     * @param location is a location of something.
     * @param resources are nice to have.
     */
    @Override
	public void initialize( URL location, ResourceBundle resources ) {
    	if( Library.selected.getName( ) != null )
    		BookTitle.setText( Library.selected.getName( ) );
    	if( Library.selected.getGenre( ) != null )
    		BookGenre.setText( Library.selected.getGenre( ).toString( ) );
    	if( Library.selected.getHashtags( ) != null )
    		BookHashtags.setText( Library.selected.hashtagToString( ) );
    	if( Library.selected.getAuthor( ) != null )
    		BookAuthor.setText( Library.selected.getAuthor( ).toString( ) );
    	if( Library.selected.getDescription( ) != null ) {
    		BookDescription.setText( Library.selected.getDescription( ) );
    	}
    	if( !Library.selected.getPicture( ).isEmpty( ) ) {
    		java.nio.file.Path path = Paths.get(new File("").getAbsolutePath());
    		String s = "file:" + path.getParent() + "/BOOKIMAGES/" + Library.selected.getPicture();
    		/*
    		 * changed from windows:
    		 * String s = "file:" + path.getParent() + "\\BOOKIMAGES\\" + Library.selected.getPicture();
    		 * will this work on windows???
    		 */
    		System.out.println(s);
    		Image i = new Image(s);
    		BookImage.setImage(i);
    	}
		if( Library.selected.getFormat( ) != null )
			BookFormat.setText(Library.selected.getFormat( ) );
		if( Library.selected.getISBN( ) != 0 )
			BookISBN.setText( String.valueOf(Library.selected.getISBN( ) ) );
		if( Library.selected.getLoaned( )== true ) {
			loanedDisplay.setText( "LOANED" );
			loanedBy.setText( "Loaned by " + Library.selected.getNameLoaned( ) + " on " + Library.selected.getDateLoaned( ) );		
			}
	}
    
    /**
     * <h2>editBook( ActionEvent event )</h2>
     * This method  takes the event from Button and changes to NewBook scene.
     * <br><br>
     * @param event is the ActionEvent from editBook Button.
     */
    public void editBook( ActionEvent event ) {
	    URL selectBookURL;
		try {
			selectBookURL = new File( "NewBook.fxml" ).toURI( ).toURL( );
			Parent borderPane = FXMLLoader.load( selectBookURL );
			Scene scene = new Scene( borderPane );
			Stage stage = ( Stage ) ( ( Node ) event.getSource( ) ).getScene( ).getWindow( );
			stage.setScene( scene );
			stage.show( );
		} catch ( IOException e ) {
			e.printStackTrace( );
		}
    }
    
    /**
     * <h2>deleteBook( ActionEvent event )</h2>
     * This method takes the event from Button and deletes a Book object from csv.
     * <br><br>
     * @param event is the ActionEvent from deleteBook Button.
     * @throws IOException if stream to file cannot be written to or closed.
     */
    public void deleteBook( ActionEvent event ) throws IOException {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirmation Dialog");
    	alert.setContentText("Are you sure?");
    	Optional <ButtonType> action = alert.showAndWait();
    	if( action.get() == ButtonType.OK) {
	    	//create temporary file to write to
	    	File tmp = new File( "data/tmp.csv" );
	    	tmp.getParentFile( ).mkdirs( ); 
	    	tmp.createNewFile( );
	    	//open LibraryData.csv
	    	File libData = new File( "data/LibraryData.csv" );
	    	try (
	    	BufferedReader csvReader = new BufferedReader( new FileReader( libData ) ) ) {
	    	BufferedWriter csvWriter = new BufferedWriter( new FileWriter( tmp ) );
	    	//remove header from libData and add to tmp
	    	String row = csvReader.readLine( );
	    	csvWriter.append( row );
			//find row that book is on and don't add to tmp
	    	while ( ( row = csvReader.readLine( ) ) != "" && row != null ) {
				String[ ] bookData = row.split( ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)" );
				if( bookData.length < 8 || Long.valueOf( bookData[ 6 ] ) == Library.selected.getISBN( ) ) {
					continue;
				}
				//if not Book to be deleted, append to temp file
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
			Library.selected = null;
			Library.loadLibrary( );
			//return to MainMenu scene
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
	 * <h2>goHome( ActionEvent event )</h2>
	 * This method takes in the event from goHome Button and goes to the MainMenu scene. Before it does,
	 * it makes sure that the selected book is unselected.
	 * <br><br>
	 * @param event is the ActionEvent from goHome Button.
	 * @throws IOException if stream to file cannot be written to or closed.
	 */
    @FXML
    void goHome( ActionEvent event ) throws IOException {
    	Library.selected = null;
    	loanedDisplay.setText( "" );
		URL addBookURL = new File( "MainMenu.fxml" ).toURI( ).toURL( );
		Parent root = FXMLLoader.load( addBookURL );
		Scene scene = new Scene( root );
		Stage stage = ( Stage ) ( ( Node ) event.getSource( ) ).getScene( ).getWindow( );
		stage.setScene( scene );
		stage.show( );
    }
}