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
    
    public void returnBook(ActionEvent event) throws IOException {
    	Library.selected.setLoaned(false);
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
				bookData[9] = "FALSE";
				String editedRow = bookData[0] + "," + bookData[1] + "," + bookData[2] + "," + bookData[3] + "," + bookData[4] +
						"," + bookData[5] + "," + bookData[6] + "," + bookData[7] + "," + bookData[8] + "," + bookData[9] +"," + "nodate" + "," + "noname";
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
    
    public void loan( ActionEvent event ) {
	    URL loanBookURL;
		try {
			loanBookURL = new File( "LoanBook.fxml" ).toURI( ).toURL( );
			Parent borderPane = FXMLLoader.load( loanBookURL );
			Scene scene = new Scene( borderPane );
			Stage stage = ( Stage ) ( ( Node ) event.getSource( ) ).getScene( ).getWindow( );
			stage.setScene( scene );
			stage.show( );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    


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
    		//String[ ] s = Library.selected.getDescription().split("\"");
    		//System.out.println(s[ 0 ]);
    		//BookDescription.setText( s[ 1 ] );
    		BookDescription.setText( Library.selected.getDescription() );
    	}
    	if( !Library.selected.getPicture( ).isEmpty() ) {
    		Image i = new Image(Library.selected.getPicture());
			BookImage.setImage(i);
    	}
		if( Library.selected.getFormat( ) != null )
			BookFormat.setText(Library.selected.getFormat());
		if( Library.selected.getISBN( ) != 0 )
			BookISBN.setText(String.valueOf(Library.selected.getISBN()));
		if( Library.selected.getLoaned()== true) {
			loanedDisplay.setText("LOANED");
			loanedBy.setText("Loaned by " + Library.selected.getNameLoaned() + " on " + Library.selected.getDateLoaned());		}
	}
    
    public void editBook( ActionEvent event ) {
    	//go to new book fxml
	    URL selectBookURL;
		try {
			selectBookURL = new File( "NewBook.fxml" ).toURI( ).toURL( );
			Parent borderPane = FXMLLoader.load( selectBookURL );
			Scene scene = new Scene( borderPane );
			Stage stage = ( Stage ) ( ( Node ) event.getSource( ) ).getScene( ).getWindow( );
			stage.setScene( scene );
			stage.show( );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
    	while ( ( row = csvReader.readLine( ) ) != "" && row != null ) {
			String[ ] bookData = row.split( ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)" );
			if( bookData.length < 8 || Long.valueOf( bookData[ 6 ] ) == Library.selected.getISBN( ) ) {
				continue;
			}
			//if not book, append to temp file
			csvWriter.append("\n");
			csvWriter.append(row);
		}
    	//remove old LibraryData.csv and rename temporary file to LibraryData.csv
		csvWriter.close();
		csvReader.close();
    	libData.delete();
    	tmp.renameTo(libData);
		Library.books.remove(Library.selected);
		Library.searchedBooks.remove(Library.selected);
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
    	loanedDisplay.setText("");
		URL addBookURL = new File( "MainMenu.fxml" ).toURI( ).toURL( );
		Parent root = FXMLLoader.load( addBookURL );
		Scene scene = new Scene( root );
		Stage stage = ( Stage ) ( ( Node ) event.getSource( ) ).getScene( ).getWindow( );
		stage.setScene( scene );
		stage.show( );
    }
}