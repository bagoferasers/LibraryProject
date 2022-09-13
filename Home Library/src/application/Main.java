package application;

import java.io.File;
import java.net.URL;
import application.model.Library;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

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
			primaryStage.show( );
		} catch( Exception e ) {
			e.printStackTrace( );
		}
	}
	
	public static void main( String[ ] args ) {
		Library.loadLibrary();
		launch( args );
	}
}