package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Collectors;

import dao.PhongDAO;
import dao.ThietBiDAO;
import daoimpl.PhongDAOimpl;
import daoimpl.ThietBiDAOimpl;
import generate.DOCX.GenerateDocx;
import generate.DOCX.PhongDocx;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Phong;
import model.add.DeviceRoom;
import model.add.Room;
import utils.Util;

public class RoomController implements Initializable{
	
	@FXML
	TableView<Room> table;
	
	@FXML
	TableColumn<Room, Number> stt;
	
	@FXML
	TableColumn<Room, Number> id;

	@FXML
	TableColumn<Room, String> loaiPhong;

	@FXML
	TableColumn<Room, Number> giaPhong;

	@FXML
	TableColumn<Room, String> tinhTrang;

	@FXML
	TableColumn<Room, String> moTa;
	
	@FXML
	ComboBox<String> choose;
	
	@FXML
	Button scan;
	
	private Function<Phong, Room> mapper;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		stt.setCellValueFactory(column -> new ReadOnlyObjectWrapper<Number>(table.getItems().indexOf(column.getValue()) + 1));
		id.setCellValueFactory(new PropertyValueFactory<>("ID"));
		loaiPhong.setCellValueFactory(new PropertyValueFactory<>("LoaiPhong"));
		giaPhong.setCellValueFactory(new PropertyValueFactory<>("GiaPhong"));
		tinhTrang.setCellValueFactory(new PropertyValueFactory<>("TinhTrang"));
		moTa.setCellValueFactory(new PropertyValueFactory<>("MoTa"));
		
		choose.getItems().addAll("Tất cả", "Trống", "Đã thuê", "Sửa chữa");
		
		mapper = new Function<Phong, Room>() {
			@Override
			public Room apply(Phong t) {
				ThietBiDAO tbDAO = new ThietBiDAOimpl();
				Room room = new Room(t.getID(), t.getLoaiPhong(), t.getGiaPhong(), t.getTinhTrang());
				List<DeviceRoom> listDevice = new ArrayList<>();
				try {
					listDevice.addAll(tbDAO.getDeviceByIdRoom(t.getID()));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				StringBuilder moTa = new StringBuilder();
				for(DeviceRoom tb : listDevice) {
					moTa.append(tb.getTenTB() + " : " + tb.getSoLuong() + "\n");
				}
				room.setMoTa(moTa.toString());
				return room;
			}
		};
		
		choose.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				List<Room> list = getList();
				String c = (String)choose.getValue();
				if(!c.equals("Tất cả")) {
					list = list.stream().filter(room -> room.getTinhTrang().equals(c)).collect(Collectors.toCollection(ArrayList::new));
				}
				table.getItems().clear();
				if(!list.isEmpty()) {
					table.setItems(FXCollections.observableArrayList(list));
				}
			}
		});
		
		choose.getSelectionModel().select("Tất cả");
		List<Room> listRoom = getList();
		if(!listRoom.isEmpty()) {
			table.setItems(FXCollections.observableArrayList(listRoom));
		}
		
		scan.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				List<Room> list = (List<Room>)table.getItems();
				GenerateDocx docx = new PhongDocx(new Date(), list);
				String file = docx.generateDocx();
				Util util = new Util();
				util.Toast("Done...");
				if(!file.equals("")) util.open(file);
			}
		});
	}
	
	private List<Room> getList(){
		PhongDAO pDAO = new PhongDAOimpl();
		List<Phong> listRoom = new ArrayList<>();
		try {
			listRoom.addAll(pDAO.findAll());
		} catch (SQLException e) {
		}
		
		List<Room> list = listRoom.stream().map(mapper).collect(Collectors.toCollection(ArrayList::new));
		return list;
	}
	
	
}
