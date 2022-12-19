package application.controller;
import java.io.File;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * <h2>class AboutController</h2>
 * This controller is for the About scene and mainly implements the goHome button.
 * <br><br>
 * @author bagoferasers
 */
public class AboutController {

    @FXML
    private Label BookGenre;

    @FXML
    private BorderPane SelectedPane;

    @FXML
    private Label BookHashtags;

    @FXML
    private Button goHome;

    @FXML
    private Label BookDescription;

    @FXML
    private ImageView BookImage;

    @FXML
    private Label BookTitle;

    @FXML
    private Label BookAuthor;

    /**
     * <h2>goHome( ActionEvent event )</h2>
     * This method takes the input event from goHome Button and returns you to the MainMenu scene.
     * <br><br>
     * @param event is the ActionEvent from Button
     */
    @FXML
    void goHome( ActionEvent event ) {
    	try {
    		URL addBookURL = new File( "MainMenu.fxml" ).toURI( ).toURL( ) ;
			Parent root = FXMLLoader.load( addBookURL );
			Scene scene = new Scene( root );
			Stage stage = ( Stage ) ( ( Node ) event.getSource( ) ).getScene( ).getWindow( );
			stage.setScene( scene );
			stage.show( );
		} catch ( Exception e ) {
			e.printStackTrace( );
		}
    }

}
