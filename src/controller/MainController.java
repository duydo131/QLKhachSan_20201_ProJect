package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class MainController implements Initializable{
	
	@FXML
	Pane header;
	
	@FXML
	Pane content;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		CommonController commonController = CommonController.getInstance();
		commonController.setFooter(header);
		commonController.setContent(content);
		
		Parent root1 = null;
		Parent root2 = null;
		
		try {
			root1 = FXMLLoader.load(this.getClass().getResource("/view/HeaderLoginLayout.fxml"));
			header.getChildren().add(root1);
			root2 = FXMLLoader.load(this.getClass().getResource("/view/HomeLayout.fxml"));
			content.getChildren().add(root2);
		} catch (IOException e) {
		}
	}

}
