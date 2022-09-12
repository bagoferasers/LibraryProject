package application.controller;

import java.io.File;
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
	public void initialize(URL location, ResourceBundle resources) {
		BookTitle.setText(Library.selected.getName());
		BookGenre.setText(Library.selected.getGenre().toString());
		BookHashtags.setText(Library.selected.getHashtags().toString());
		BookAuthor.setText(Library.selected.getAuthor().toString());
		BookDescription.setText(Library.selected.getDescription());
		BookImage.setImage(Library.selected.getPicture());
	}
    
    @FXML
    void goHome() {
    	Stage stage = (Stage) goHome.getScene().getWindow();
    	stage.close();
    }
}
