package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class ProcessController implements Initializable{
	
	@FXML
	AnchorPane headerProcess;
	
	@FXML
	AnchorPane mainProcess;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		CommonProcessController common = CommonProcessController.getInstance();
		common.setFooter(headerProcess);
		common.setContent(mainProcess);
		
		Parent root1 = null;
		Parent root2 = null;
		
		try {
			root1 = FXMLLoader.load(this.getClass().getResource("/view/HeaderProcessLayout.fxml"));
			headerProcess.getChildren().add(root1);
			root2 = FXMLLoader.load(this.getClass().getResource("/view/BookRoomLayout.fxml"));
			mainProcess.getChildren().add(root2);
		} catch (IOException e) {
		}
		
	}

}
