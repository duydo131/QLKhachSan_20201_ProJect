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

public class HeaderProcessController implements Initializable{
	
	@FXML
	Button book;
	
	@FXML
	Button customer;
	
	@FXML
	Button staff;
	
	@FXML
	Button room;
	
	@FXML
	Button device;
	
	@FXML
	Button pay;
	
	@FXML
	Button statistical;

	private Pane content = CommonProcessController.getInstance().getContent();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		book.setOnAction(getEventHandler("BookRoomLayout"));
		
		customer.setOnAction(getEventHandler("ListCustomerLayout"));

		staff.setOnAction(getEventHandler("ListStaffLayout"));
		
		room.setOnAction(getEventHandler("RoomLayout"));
		
		device.setOnAction(getEventHandler("DeviceLayout"));
		
		pay.setOnAction(getEventHandler("PayLayout"));
		
		statistical.setOnAction(getEventHandler("Statistical"));
		
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
