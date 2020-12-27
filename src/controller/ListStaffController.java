package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import dao.NhanVienDAO;
import daoimpl.NhanVienDAOimpl;
import generate.DOCX.GenerateDocx;
import generate.DOCX.NhanVienDocx;
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
import model.NhanVien;
import model.add.Staff;
import utils.Util;

public class ListStaffController implements Initializable{
	@FXML
	TableView<Staff> table;
	
	@FXML 
	TableColumn<Staff, Number> stt;
	
	@FXML 
	TableColumn<Staff, Number> id;
	
	@FXML 
	TableColumn<Staff, String> ten;
	
	@FXML 
	TableColumn<Staff, String> cmnd;
	
	@FXML 
	TableColumn<Staff, String> gioiTinh;
	
	@FXML 
	TableColumn<Staff, String> chuyenMon;
	
	@FXML 
	TableColumn<Staff, String> dienThoai;

	@FXML 
	TableColumn<Staff, String> ngaySinh;
	
	@FXML
	Button scan;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		stt.setCellValueFactory(column -> new ReadOnlyObjectWrapper<Number>(table.getItems().indexOf(column.getValue()) + 1));
		id.setCellValueFactory(new PropertyValueFactory<>("ID"));
		ten.setCellValueFactory(new PropertyValueFactory<>("Ten"));
		cmnd.setCellValueFactory(new PropertyValueFactory<>("CMND"));
		gioiTinh.setCellValueFactory(new PropertyValueFactory<>("GioiTinh"));
		chuyenMon.setCellValueFactory(new PropertyValueFactory<>("ChuyenMon"));
		dienThoai.setCellValueFactory(new PropertyValueFactory<>("DienThoai"));
		ngaySinh.setCellValueFactory(new PropertyValueFactory<>("NgaySinh"));
		
		NhanVienDAO service = new NhanVienDAOimpl();
		List<NhanVien> listTT = new ArrayList<>();
		try {
			listTT.addAll(service.findAll());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<Staff> list = listTT.stream().map(tt -> new Staff(tt)).collect(Collectors.toCollection(ArrayList::new));
		if(!list.isEmpty()) {
			table.setItems(FXCollections.observableArrayList(list));
		}
		
		scan.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				List<Staff> list = (List<Staff>)table.getItems();
				GenerateDocx docx = new NhanVienDocx(new Date(), list);
				String file = docx.generateDocx();
				Util util = new Util();
				util.Toast("Done");
				if(!file.equals("")) util.open(file);
			}
		});
	}
}
