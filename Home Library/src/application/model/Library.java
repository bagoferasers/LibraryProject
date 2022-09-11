/**
 * 
 */
package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author colbybailey
 *
 */
public class Library {
	public static ObservableList <Book> books= FXCollections.observableArrayList();
	public static Book selected;
}
