package application.controller;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import application.model.Author;
import application.model.Book;
import application.model.Genre;
import application.model.Hashtag;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class MainMenuController {
	
    @FXML
    private BorderPane borderPane;

    @FXML
    private ListView<Book> searchList;

    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton;

    @FXML
    private RadioButton bookButton;

    @FXML
    private RadioButton hashtagsButton;

    @FXML
    private RadioButton authorsButton;

    @FXML
    private RadioButton genresButton;
    
    @FXML
	public void onSearch(ActionEvent event) {
		
		// load up books in inventory
		Author jane = new Author("jane", "bailey");
		Author kathy = new Author("kathy", "bailey");
		Author bart = new Author("bart", "bailey");
		Genre genre = new Genre("Sci-Fi" );
		
		String[] h = {"h1", "h2", "h3"};
		Hashtag hashtags = new Hashtag(h);
		
		Book b1 = new Book( null, "Dictionary", "A book about words.",
		         hashtags, jane, genre );
		Book b2 = new Book( null, "AssHat", "A book about ass.",
		         hashtags, kathy, genre );
		Book b3 = new Book( null, "Poo", "A book about poo.",
		         hashtags, bart, genre );
		Book b4 = new Book( null, "Rodents", "A book about rodents.",
		         hashtags, kathy, genre );
		Book b5 = new Book( null, "Cosmos", "A book about space.",
		         hashtags, kathy, genre );
		ObservableList <Book> b= FXCollections.observableArrayList();
		b.add(b1);
		b.add(b2);
		b.add(b3);
		b.add(b4);
		b.add(b5);
		
    	searchList.setItems(b);
    }
	
    @FXML
	public void onSelectBook() {
		Book b = searchList.getSelectionModel().getSelectedItem();
		//send selected item to pop out view of book
		System.out.println(b.getName());
    	System.out.println("selected Book");
	}
    
    @FXML
    public void newBook(ActionEvent event) {
    	//open new Scene
    	URL newBookURL;
		try {
			newBookURL = new File("NewBook.fxml").toURI().toURL();
			Parent root = FXMLLoader.load(newBookURL);
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			stage.setHeight(800);
			stage.setWidth(1000);
			stage.setTitle("Add Book");
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	//add book attributes to book object via pop out view
    	
    	//print book to csv
    	
    }
}

