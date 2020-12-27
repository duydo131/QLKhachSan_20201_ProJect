package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class HeaderLogoutController implements Initializable{
	
	@FXML
	Button logout;
	
	@FXML
	Label tenNV;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		setName();
		
		logout.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Redirect();
			}
		});
		
	}
	
	private void setName() {
		CommonProcessController common = CommonProcessController.getInstance();
		String ten = common.getNv().getTen();
		tenNV.setText(ten);
	}
	
	private void Redirect() {
		CommonController commonController = CommonController.getInstance();
		Pane header = commonController.getFooter();
		Pane content = commonController.getContent();
		
		Parent root1 = null;
		Parent root2 = null;
		
		try {
			clear();
			root1 = FXMLLoader.load(this.getClass().getResource("/view/HeaderLoginLayout.fxml"));
			header.getChildren().add(root1);
			root2 = FXMLLoader.load(this.getClass().getResource("/view/HomeLayout.fxml"));
			content.getChildren().add(root2);
		} catch (IOException e) {
		}
	}
	
	private void clear() throws IOException {
		CommonController commonController = CommonController.getInstance();
		Pane content = commonController.getContent();
		content.getChildren().clear();
		Pane header = commonController.getFooter();
		header.getChildren().clear();
		
		CommonProcessController.clear();
	}
}
