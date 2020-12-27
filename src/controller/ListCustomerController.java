package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import dao.KhachHangDao;
import daoimpl.KhachHangDaoImpl;
import generate.DOCX.GenerateDocx;
import generate.DOCX.KhachHangDocx;
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
import model.KhachHang;
import model.add.Customer;
import utils.Util;

public class ListCustomerController implements Initializable{
	
	@FXML
	TableView<Customer> table;
	
	@FXML 
	TableColumn<Customer, Number> stt;
	
	@FXML 
	TableColumn<Customer, Number> id;
	
	@FXML 
	TableColumn<Customer, String> ten;
	
	@FXML 
	TableColumn<Customer, String> cmnd;
	
	@FXML 
	TableColumn<Customer, String> gioiTinh;
	
	@FXML 
	TableColumn<Customer, String> diaChi;
	
	@FXML 
	TableColumn<Customer, String> dienThoai;
	
	@FXML 
	TableColumn<Customer, String> quocTich;
	
	@FXML 
	TableColumn<Customer, String> ngaySinh;
	
	@FXML 
	TableColumn<Customer, String> nghe;
	
	@FXML 
	TableColumn<Customer, String> phanLoai;
	
	@FXML 
	TableColumn<Customer, String> toChuc;
	
	@FXML
	Button scan;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		stt.setCellValueFactory(column -> new ReadOnlyObjectWrapper<Number>(table.getItems().indexOf(column.getValue()) + 1));
		id.setCellValueFactory(new PropertyValueFactory<>("maKH"));
		ten.setCellValueFactory(new PropertyValueFactory<>("tenKH"));
		cmnd.setCellValueFactory(new PropertyValueFactory<>("cmnd"));
		gioiTinh.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
		diaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
		dienThoai.setCellValueFactory(new PropertyValueFactory<>("dienThoai"));
		quocTich.setCellValueFactory(new PropertyValueFactory<>("quocTich"));
		ngaySinh.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
		nghe.setCellValueFactory(new PropertyValueFactory<>("ngheNghiep"));
		phanLoai.setCellValueFactory(new PropertyValueFactory<>("phanLoaiKH"));
		toChuc.setCellValueFactory(new PropertyValueFactory<>("tenToChuc"));
		
		KhachHangDao service = new KhachHangDaoImpl();
		List<KhachHang> listTT = new ArrayList<>();
		try {
			listTT.addAll(service.findAll());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<Customer> list = listTT.stream().map(tt -> new Customer(tt)).collect(Collectors.toCollection(ArrayList::new));
		if(!list.isEmpty()) {
			table.setItems(FXCollections.observableArrayList(list));
		}
		
		scan.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				List<Customer> list = (List<Customer>)table.getItems();
				GenerateDocx docx = new KhachHangDocx(new Date(), list);
				String file = docx.generateDocx();
				Util util = new Util();
				util.Toast("Done...");
				if(!file.equals("")) util.open(file);
			}
		});
	}
}
