package application;
import java.io.File;
import java.net.URL;
import application.model.Library;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * <h1>Bailey Library</h1>
 * This is a side project that uses applications programming class knowledge to create a desktop app that manages my personal library. 
 * It will search books, hashtags, genres, and authors. It will add, edit, and delete books in library. 
 * It will loan books out and return books to library after loan.
 * <h2>class Main</h2>
 * This class implements the main method.
 * <br><br>
 * @author bagoferasers
 */
public class Main extends Application {
	
	@Override
	public void start( Stage primaryStage ) {
		try {
			URL url = new File( "MainMenu.fxml" ).toURI( ).toURL( );
			BorderPane root = ( BorderPane )FXMLLoader.load( url );
			Scene scene = new Scene( root,1000,800 );
			primaryStage.setTitle( "Bailey Library" );
			primaryStage.setScene( scene );
			primaryStage.setMaxHeight( 800 );
			primaryStage.setMaxWidth( 1000 );
			Image logo = new Image("application/view/icon.png");
			primaryStage.getIcons().add(logo);
			primaryStage.show( );
		} catch( Exception e ) {
			e.printStackTrace( );
		}
	}
	
	/**
	 * <h2>main( String[ ] args )</h2>
	 * This is the main method which loads the library from the csv and starts the MainMenu scene.
	 * <br><br>
	 * @param args takes in the command line arguments.
	 */
	public static void main( String[ ] args ) {
		Library.loadLibrary();
		launch( args );
	}
}