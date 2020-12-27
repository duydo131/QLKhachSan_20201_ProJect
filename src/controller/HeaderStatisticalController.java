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

public class HeaderStatisticalController implements Initializable{
	
	@FXML
	Button sales;
	
	@FXML
	Button nv1;
	
	@FXML
	Button nv2;

	private Pane content = StoreStatistical.getInstance().getContent();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sales.setOnAction(getEventHandler("SalesStatisticalLayout"));
		nv1.setOnAction(getEventHandler("StaffStatisticalLayout"));
		nv2.setOnAction(getEventHandler("CustomerStatisticalLayout"));
	}
	
	private EventHandler<ActionEvent> getEventHandler(String filename){
		return (new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					clear();
					content.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/" + filename + ".fxml")));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void clear() throws IOException {
		content.getChildren().clear();
	}
	
}
