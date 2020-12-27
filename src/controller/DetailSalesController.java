package controller;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import dao.NhanVienDAO;
import daoimpl.NhanVienDAOimpl;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.NhanVien;
import model.add.DetailStatisticalModel;
import model.add.Info;

public class DetailSalesController implements Initializable{
	
	@FXML
	Label name;
	
	@FXML
	TableView<DetailStatisticalModel> table;
	
	@FXML
	TableColumn<DetailStatisticalModel, Number> numerical;
	
	@FXML
	TableColumn<DetailStatisticalModel, Number> idRoom;
	
	@FXML
	TableColumn<DetailStatisticalModel, String> customer;
	
	@FXML
	TableColumn<DetailStatisticalModel, String> staff;
	
	@FXML
	TableColumn<DetailStatisticalModel, String> room;
	
	@FXML
	TableColumn<DetailStatisticalModel, Number> quantity;
	
	@FXML
	TableColumn<DetailStatisticalModel, Number> total;
	
	private List<DetailStatisticalModel> listDetail;
	private List<NhanVien> listStaff;
	private Map<Long, NhanVien> map = new HashMap<>();
	
	private NhanVienDAO nvDAO = new NhanVienDAOimpl();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setTable();
		
		listDetail = new ArrayList<>();
		listStaff = new ArrayList<>();
		
		try {
			listStaff.addAll(nvDAO.findAll());
			for(NhanVien nv : listStaff) {
				map.put(nv.getID(), nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		StoreStatistical store = StoreStatistical.getInstance();
		List<Info> listInfo = store.getList();
		String time = store.getTime();
		int position = store.getPosition();
		
		String s = "Doanh thu chi tiết " + getName(position) + time;
		name.setText(s);
		
		for(Info info : listInfo) {
			if(predicate(info, time, position)) {
				DetailStatisticalModel dt = new DetailStatisticalModel();
				dt.setIdTP(info.getIdTP());
				dt.setCustomer(info.getCustomer());
				dt.setStaff(map.get(info.getID_NV1()).getTen());
				dt.setRoom(info.getRoom());
				dt.setQuantity((info.getNgayDi().getTime() - info.getNgayDen().getTime())/(24 * 60 * 60 * 1000));
				dt.setTotal(info.getTotal());
				
				listDetail.add(dt);
			}
		}
		
		updateTable();
	}
	
	private String getName(int position) {
		if(position <= 1) return "ngày ";
		else if(position == 2) return "tháng ";
		else return "năm ";
	}
	
	private boolean predicate(Info info, String time, int position) {
		if(position <= 1) {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			return format.format(info.getNgayDi()).equals(time);
		}else if(position == 2){
			Calendar c = Calendar.getInstance();
			c.setTime(info.getNgayDi());
			String str = c.get(Calendar.MONTH) + 1 + "";
			return str.equals(time);
		}else {
			Calendar c = Calendar.getInstance();
			c.setTime(info.getNgayDi());
			String str = c.get(Calendar.YEAR) + "";
			return str.equals(time);
		}
	}
	
	private void setTable() {
		numerical.setCellValueFactory(column -> new ReadOnlyObjectWrapper<Number>(table.getItems().indexOf(column.getValue()) + 1));
		idRoom.setCellValueFactory(new PropertyValueFactory<>("idTP"));
		customer.setCellValueFactory(new PropertyValueFactory<>("customer"));
		staff.setCellValueFactory(new PropertyValueFactory<>("staff"));
		room.setCellValueFactory(new PropertyValueFactory<>("room"));
		quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		total.setCellValueFactory(new PropertyValueFactory<>("total"));
	}

	private void updateTable() {
		table.getItems().clear();
		table.setItems(FXCollections.observableArrayList(listDetail));
	}
}