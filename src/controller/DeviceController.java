package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import dao.ThietBiDAO;
import daoimpl.ThietBiDAOimpl;
import generate.DOCX.GenerateDocx;
import generate.DOCX.ThietBiDocx;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ThietBi;
import utils.Util;

public class DeviceController implements Initializable{
	
	@FXML
	TableView<ThietBi> table;
	
	@FXML
	TableColumn<ThietBi, Number> stt;
	
	@FXML
	TableColumn<ThietBi, Long> id;
	
	@FXML
	TableColumn<ThietBi, String> ten;
	
	@FXML
	TableColumn<ThietBi, Number> gia;
	
	@FXML
	Button scan;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		stt.setCellValueFactory(column -> new ReadOnlyObjectWrapper<Number>(table.getItems().indexOf(column.getValue()) + 1));
		id.setCellValueFactory(new PropertyValueFactory<>("ID"));
		ten.setCellValueFactory(new PropertyValueFactory<>("TenTB"));
		gia.setCellValueFactory(new PropertyValueFactory<>("Gia"));
		
		ThietBiDAO tbDAO = new ThietBiDAOimpl();
		
		List<ThietBi> list = new ArrayList<ThietBi>();
		try {
			list.addAll(tbDAO.findAll());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(!list.isEmpty()) {
			table.setItems(FXCollections.observableArrayList(list));
		}
		
		scan.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				List<ThietBi> list = (List<ThietBi>)table.getItems();
				GenerateDocx docx = new ThietBiDocx(new Date(), list);
				String file = docx.generateDocx();
				Util util = new Util();
				util.Toast("Done...");
				if(!file.equals("")) util.open(file);
			}
		});
	}

}
