package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class MainStatisticalController implements Initializable{
	
	@FXML
	Pane header;
	
	@FXML
	Pane content;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		StoreStatistical storeStatistical = StoreStatistical.getInstance();
		storeStatistical.setFooter(header);
		storeStatistical.setContent(content);
		
		Parent root1 = null;
		
		try {
			root1 = FXMLLoader.load(this.getClass().getResource("/view/HeaderStatisticalLayout.fxml"));
			header.getChildren().add(root1);
		} catch (IOException e) {
		}
	}

}
