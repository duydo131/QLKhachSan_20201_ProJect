package controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

import dao.InfoDAO;
import daoimpl.InfoDAOImpl;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.add.Info;
import model.add.SalesStatistical;

public class SalesStatisticalController implements Initializable{
	
	private String[] c1 = {"Tuần", "Tháng", "Năm", "Tất cả"};
	private Map<String, Integer> map = new HashMap<>();
	
	@FXML
	ComboBox<String> choose;
	
	@FXML
	TableView<SalesStatistical> table;
	
	@FXML
	TableColumn<SalesStatistical, Number> numerical;
	
	@FXML
	TableColumn<SalesStatistical, String> day;
	
	@FXML
	TableColumn<SalesStatistical, Number> amountCustomer;
	
	@FXML
	TableColumn<SalesStatistical, Number> amountRoom;
	
	@FXML
	TableColumn<SalesStatistical, Number> totalMoney;
	
	@FXML
	Button detailStatistical;
	
	@FXML
	Label inDate;
	
	@FXML
	Label labelSearch;
	
	@FXML
	TextField text;
	
	@FXML
	Button search;
	
	private Date date = new Date();
	private List<SalesStatistical> listSale = new ArrayList<>();
	List<Info> listInfo = new ArrayList<>();
	
	private InfoDAO infoDAO = new InfoDAOImpl();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		labelSearch.setVisible(false);
		text.setVisible(false);
		search.setVisible(false);
		
		for(int i = 0; i < c1.length; i++) {
			map.put(c1[i], i);
		}
		
		choose.getItems().addAll(c1);
		
		setTable();
		
		choose.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				String time = (String)choose.getValue();
				int t = map.get(time) - 1;
				day.setText(t > 0 ? c1[t] : "Ngày");
				
				listInfo = new ArrayList<>();
				listSale = new ArrayList<>();
				
				listInfo.addAll(infoDAO.getInfo());
				
				listInfo = listInfo.parallelStream().filter(in -> predicate(in, map.get(time))).collect(Collectors.toCollection(ArrayList::new));
				
				setData(map.get(time));
				
				updateTable();
				
				labelSearch.setVisible(true);
				text.setVisible(true);
				text.setText("");
				search.setVisible(true);
				
				labelSearch.setText("Tìm kiếm theo " + getName(t + 1));
			}
		});
		
		search.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				inDate.setText("");
				
				String time = (String)choose.getValue();
				int t = map.get(time);
				
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				
				boolean flag = true;
				String d = (String)text.getText();
				if(!d.equals("")) {
					List<SalesStatistical> listS = new ArrayList<>();
					try {
						if(t <= 1) {
							format.parse(d);
						}else if(t == 2) {
							int month = Integer.parseInt(d);
							if(month > 12 || month < 1) throw new Exception();
							
						}else {
							int year = Integer.parseInt(d);
							if(year > Calendar.getInstance().get(Calendar.YEAR) || year < 1970) throw new Exception();
						}
					} catch (Exception e) {
						flag = false;
						inDate.setText("nhập sai");
					}
					
					if(flag) {
						for(SalesStatistical sale : listSale) if(sale.getTime().equals(d)) listS.add(sale);
						table.setItems(FXCollections.observableArrayList(listS));
					}
				}else {
					updateTable();
				}
			}
		});
		
		detailStatistical.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				ObservableList<SalesStatistical> selectedRows = table.getSelectionModel().getSelectedItems();
				List<SalesStatistical> rows = new ArrayList<>(selectedRows);
				if(!rows.isEmpty()) {
					StoreStatistical store = StoreStatistical.getInstance();
					store.setList(listInfo);
					store.setPosition(map.get((String)choose.getValue()));
					store.setTime(rows.get(0).getTime());
					Redirect();
				}
			}
		});
	}
	
	private String getName(int position) {
		if(position <= 1) return "ngày ";
		else if(position == 2) return "tháng ";
		else return "năm ";
	}
	
	private void setData(int k) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		if(k == 0 || k == 1) {
			Map<Date, SalesStatistical> map = new HashMap<>();
			Map<Date, Set<Long>> cus = new HashMap<>();
			
			for(Info info : listInfo) {
				Date d = info.getNgayDi();
				if(map.containsKey(d)) {
					SalesStatistical sale = map.get(d);
					cus.get(d).add(info.getID_KH());
					sale.setAmountRoom(sale.getAmountRoom() + 1);
					sale.setTotalMoney(sale.getTotalMoney() + info.getTotal());
				}else {
					SalesStatistical sale = new SalesStatistical();
					sale.setTime(format.format(d));
					cus.put(d, new HashSet<>());
					cus.get(d).add(info.getID_KH());
					sale.setAmountRoom(1);
					sale.setTotalMoney(info.getTotal());
					map.put(d, sale);
				}
			}
			
			for(Map.Entry<Date, SalesStatistical> iter : map.entrySet()) {
				SalesStatistical sale = iter.getValue();
				sale.setAmountCustomer(cus.get(iter.getKey()).size());
				listSale.add(sale);
			}
		}else if(k == 2){
			Map<Integer, SalesStatistical> map = new HashMap<>();
			Map<Integer, Set<Long>> cus = new HashMap<>();
			
			for(Info info : listInfo) {
				Calendar c = Calendar.getInstance();
				c.setTime(info.getNgayDi());
				Integer d = c.get(Calendar.MONTH) + 1;
				if(map.containsKey(d)) {
					SalesStatistical sale = map.get(d);
					cus.get(d).add(info.getID_KH());
					sale.setAmountRoom(sale.getAmountRoom() + 1);
					sale.setTotalMoney(sale.getTotalMoney() + info.getTotal());
				}else {
					SalesStatistical sale = new SalesStatistical();
					sale.setTime(d + "");
					cus.put(d, new HashSet<>());
					cus.get(d).add(info.getID_KH());
					sale.setAmountRoom(1);
					sale.setTotalMoney(info.getTotal());
					map.put(d, sale);
				}
			}
			
			for(Map.Entry<Integer, SalesStatistical> iter : map.entrySet()) {
				SalesStatistical sale = iter.getValue();
				sale.setAmountCustomer(cus.get(iter.getKey()).size());
				listSale.add(sale);
			}
		}else {
			Map<Integer, SalesStatistical> map = new HashMap<>();
			Map<Integer, Set<Long>> cus = new HashMap<>();
			
			for(Info info : listInfo) {
				Calendar c = Calendar.getInstance();
				c.setTime(info.getNgayDi());
				Integer d = c.get(Calendar.YEAR);
				if(map.containsKey(d)) {
					SalesStatistical sale = map.get(d);
					cus.get(d).add(info.getID_KH());
					sale.setAmountRoom(sale.getAmountRoom() + 1);
					sale.setTotalMoney(sale.getTotalMoney() + info.getTotal());
				}else {
					SalesStatistical sale = new SalesStatistical();
					sale.setTime(d + "");
					cus.put(d, new HashSet<>());
					cus.get(d).add(info.getID_KH());
					sale.setAmountRoom(1);
					sale.setTotalMoney(info.getTotal());
					map.put(d, sale);
				}
			}
			
			for(Map.Entry<Integer, SalesStatistical> iter : map.entrySet()) {
				SalesStatistical sale = iter.getValue();
				sale.setAmountCustomer(cus.get(iter.getKey()).size());
				listSale.add(sale);
			}
		}
		
	}
	
	private boolean predicate(Info info, int position) {
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
	
	private void Redirect() {
		Stage stage = new Stage();
		Parent root = null;
		Scene scene = null;
		try {
			root = FXMLLoader.load(this.getClass().getResource("/view/DetailStatisticalLayout.fxml"));
			stage.setTitle("Chi tiết");
			scene = new Scene(root);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		stage.setScene(scene);
		stage.show();
	}
	
	private void setTable() {
		table.setEditable(true);
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		numerical.setCellValueFactory(column -> new ReadOnlyObjectWrapper<Number>(table.getItems().indexOf(column.getValue()) + 1));
		day.setCellValueFactory(new PropertyValueFactory<>("time"));
		amountCustomer.setCellValueFactory(new PropertyValueFactory<>("amountCustomer"));
		amountRoom.setCellValueFactory(new PropertyValueFactory<>("amountRoom"));
		totalMoney.setCellValueFactory(new PropertyValueFactory<>("totalMoney"));
	}

	private void updateTable() {
		table.getItems().clear();
		table.setItems(FXCollections.observableArrayList(listSale));
	}
}
