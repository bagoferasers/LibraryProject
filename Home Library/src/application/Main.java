/**
 * 
 */
package application;
import java.io.File;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author colbybailey
 *
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//URL url = new File("D:\\godot\\ActionRPGTutorial\\LibraryProject\\Home Library\\MainMenu.fxml").toURI().toURL();
			URL url = new File("MainMenu.fxml").toURI().toURL();
			BorderPane root = (BorderPane)FXMLLoader.load(url);
			Scene scene = new Scene(root,1000,800);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Bailey Library");
			//Image logo = new Image("application/view/developers-hand-logo.png");
			//primaryStage.getIcons().add(logo);
			primaryStage.setScene(scene);
			primaryStage.setMaxHeight(800);
			primaryStage.setMaxWidth(1000);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}