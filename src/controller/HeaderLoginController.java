package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class HeaderLoginController implements Initializable{
	
	@FXML
	Button login;
	
	@FXML
	Button registration;
	
	private CommonController common = CommonController.getInstance();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Pane content = common.getContent();
		
		login.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					clear();
					content.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/LoginLayout.fxml")));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		registration.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					clear();
					content.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/RegisterLayout.fxml")));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	private void clear() throws IOException {
		CommonController ctl = CommonController.getInstance();
		Pane content = ctl.getContent();
		content.getChildren().clear();
	}
}
