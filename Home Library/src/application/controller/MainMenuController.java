package application.controller;

import application.model.Author;
import application.model.Book;
import application.model.Genre;
import application.model.Hashtag;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class MainMenuController {

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
    
}

