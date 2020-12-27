package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import dao.InfoDAO;
import dao.NhanVienDAO;
import daoimpl.InfoDAOImpl;
import daoimpl.NhanVienDAOimpl;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.NhanVien;
import model.add.Info;
import model.add.StaffStatisticalModel;

public class StaffStatisticalController implements Initializable{
	
	private String[] c1 = {"Tuần", "Tháng", "Năm", "Tất cả"};
	private Map<String, Integer> ch = new HashMap<>();
	
	@FXML
	TableView<StaffStatisticalModel> table;
	
	@FXML
	TableColumn<StaffStatisticalModel, Number> numerical;
	
	@FXML
	TableColumn<StaffStatisticalModel, Number> id;
	
	@FXML
	TableColumn<StaffStatisticalModel, String> name;
	
	@FXML
	TableColumn<StaffStatisticalModel, Number> quantityLease;
	
	@FXML
	TableColumn<StaffStatisticalModel, Number> quantityPay;
	
	@FXML
	ComboBox<String> choose;
	
	@FXML
	Button search;
	
	@FXML
	TextField idS;
	
	@FXML
	TextField nameS;
	
	private List<StaffStatisticalModel> list = new ArrayList<>();
	private Map<Long, NhanVien> dataStaff = new HashMap<>();
	
	private NhanVienDAO nvDAO = new NhanVienDAOimpl();
	private InfoDAO infoDAO = new InfoDAOImpl();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setTable();
		
		List<NhanVien> listStaff = new ArrayList<>();
		try {
			listStaff.addAll(nvDAO.findAll());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(NhanVien nv : listStaff) {
			dataStaff.put(nv.getID(), nv);
		}

		for(int i = 0; i < c1.length; i++) {
			ch.put(c1[i], i);
		}
		
		choose.getItems().addAll(c1);
		
		choose.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				String time = (String)choose.getValue();
				
				list = new ArrayList<>();
				
				List<Info> listInfo = new ArrayList<>();
				listInfo.addAll(infoDAO.getInfo());
				
				listInfo = listInfo.parallelStream().filter(in -> predicate(in, ch.get(time))).collect(Collectors.toCollection(ArrayList::new));

				Map<Long, StaffStatisticalModel> map = new HashMap<>();
				
				for(Info info : listInfo) {
					Long id1 = info.getID_NV1();
					Long id2 = info.getID_NV2();
					if(map.containsKey(id1)) {
						StaffStatisticalModel staff = map.get(id1);
						staff.setQuantityLease(staff.getQuantityLease() + 1);
					}else {
						StaffStatisticalModel staff = new StaffStatisticalModel(id1, dataStaff.get(id1).getTen(), 1, 0);
						map.put(id1, staff);
					}
					if(map.containsKey(id2)) {
						StaffStatisticalModel staff = map.get(id2);
						staff.setQuantityPay(staff.getQuantityPay() + 1);
					}else {
						StaffStatisticalModel staff = new StaffStatisticalModel(id2, dataStaff.get(id2).getTen(), 0, 1);
						map.put(id2, staff);
					}
				}
				
				for(Map.Entry<Long, StaffStatisticalModel> iter : map.entrySet()) {
					list.add(iter.getValue());
				}
				
				updateTable();
			}
		});
		
		search.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				String idSearch = (String)idS.getText();
				String ten = (String)nameS.getText();
				List<StaffStatisticalModel> listT = new ArrayList<>(list);
				if(!idSearch.equals("")) listT = listT.parallelStream().filter(st -> (st.getId() + "").equals(idSearch)).collect(Collectors.toCollection(ArrayList::new));
				if(!ten.equals("")) listT = listT.parallelStream().filter(st -> st.getName().equals(ten)).collect(Collectors.toCollection(ArrayList::new));
				table.setItems(FXCollections.observableArrayList(listT));
			}
		});
	}
	
	private boolean predicate(Info info, int position) {
		Date date = new Date();
		if(position == 0) {
			long d = 7 * 60 * 24 * 60 * 1000;
			return date.getTime() - info.getNgayDi().getTime() <= d;
		}else{
			Calendar c1 = Calendar.getInstance();
			c1.setTime(date);
			Calendar c2 = Calendar.getInstance();
			c2.setTime(date);
			if(position == 1) return c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH);
			else if(position == 2) return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR);
			else return true;
		}
	}

	private void setTable() {
		numerical.setCellValueFactory(column -> new ReadOnlyObjectWrapper<Number>(table.getItems().indexOf(column.getValue()) + 1));
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		quantityLease.setCellValueFactory(new PropertyValueFactory<>("quantityLease"));
		quantityPay.setCellValueFactory(new PropertyValueFactory<>("quantityPay"));
	}
	
	private void updateTable() {
		table.getItems().clear();
		table.setItems(FXCollections.observableArrayList(list));
	}
}
