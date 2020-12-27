package controller;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import dao.ChiTietThuePhongDAO;
import dao.ConnectDAO;
import dao.KhachHangDao;
import dao.PhongDAO;
import dao.ThietBiDAO;
import dao.ThuePhongDAO;
import daoimpl.ChiTietThuePhongDAOimpl;
import daoimpl.ConnectDAOimpl;
import daoimpl.KhachHangDaoImpl;
import daoimpl.PhongDAOimpl;
import daoimpl.ThietBiDAOimpl;
import daoimpl.ThuePhongDAOimpl;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import model.ChiTietThuePhong;
import model.Connect;
import model.KhachHang;
import model.NhanVien;
import model.Phong;
import model.ThuePhong;
import model.add.DeviceRoom;
import utils.Util;

public class BookRoomController implements Initializable{
	
	@FXML
	AnchorPane thongtinkhachhang;
	
	@FXML
	TextField tenKH;
	
	@FXML
	TextField diaChiKH;
	
	@FXML
	TextField ngaySinhKH;
	
	@FXML
	TextField cmndKH;
	
	@FXML
	TextField quocTichKH;
	
	@FXML
	TextField tenToChuc;
	
	@FXML
	TextField ngheKH;
	
	@FXML
	TextField dienThoaiKH;
	
	@FXML
	ComboBox<String> gioiTinhKH;
	
	@FXML
	ComboBox<String> phanloaiKH;
	
	@FXML
	Label inTenKH;
	
	@FXML
	Label inQuocTich;
	
	@FXML
	Label inCMNDKH;
	
	@FXML
	Label inDiaChiKH;
	
	@FXML
	Label inDienThoaiKH;
	
	@FXML
	Label inNgaySinhKH;
	
	@FXML 
	TextField soLuongPhong;
	
	@FXML
	TextField ngayDangKi;
	
	@FXML
	TextField ngayDen;
	
	@FXML
	Label inNgayDen;
	
	@FXML
	Label inNgayDangKi;
	
	@FXML
	Label inNgayDi;
	
	@FXML
	ComboBox<String> phong;
	
	@FXML
	TextArea moTa;
	
	@FXML
	TableView<Form> table;
	
	@FXML
	TableColumn<Form, Number> stt;
	
	@FXML
	TableColumn<Form, String> tenPhong;
	
	@FXML
	TableColumn<Form, String> ngayDi;
	
	@FXML
	TableColumn<Form, String> moTaPhong;
	
	@FXML
	Button remove;
	
	@FXML
	Button add;
	
	@FXML
	Button book;
	
	@FXML
	Label deposit;
	
	private List<Form> listData;
	private Form form = null;
	private PhongDAO roomDAO = new PhongDAOimpl();
	private ThietBiDAO tbDAO = new ThietBiDAOimpl();
	private KhachHangDao khDAO = new KhachHangDaoImpl();
	private ThuePhongDAO tpDAO = new ThuePhongDAOimpl();
	private ChiTietThuePhongDAO cttpDAO = new ChiTietThuePhongDAOimpl();
	private ConnectDAO cnDAO = new ConnectDAOimpl();
	private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	private boolean flag = true;
	private Date date3 = new Date();
	private Long money = 0L;
	private Map<String, Phong> map = new HashMap<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listData = new ArrayList<>();
		
		gioiTinhKH.getItems().addAll("Nam", "Nữ");
		gioiTinhKH.getSelectionModel().select(0);
		
		phanloaiKH.getItems().addAll("Cá nhân", "Tổ chức");
		phanloaiKH.getSelectionModel().select(0);
		
		List<Phong> listR = new ArrayList<>();
		try {
			listR.addAll(roomDAO.findAll());
			for(Phong p : listR) {
				map.put(p.getTenPhong(), p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		setRoom();
		setTable();
		setProcess();
	}
	
	private void setProcess() {
		book.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				String ten_KH = (String)tenKH.getText();
				String qt_KH = (String)quocTichKH.getText();
				String cmnd_KH = (String)cmndKH.getText();
				String diaCHi_KH = (String)diaChiKH.getText();
				String dienThoai_KH = (String)dienThoaiKH.getText();
				String ngaySinh_KH = (String)ngaySinhKH.getText();
				String ngayDangKi_KH = (String)ngayDangKi.getText();
				String ngayDen_KH = (String)ngayDen.getText();
				
				inCMNDKH.setText("");
				inDiaChiKH.setText("");
				inDienThoaiKH.setText("");
				inNgayDangKi.setText("");
				inNgayDen.setText("");
				inNgaySinhKH.setText("");
				inQuocTich.setText("");
				inTenKH.setText("");
				
				Date date1 = new Date();
				Date date2 = new Date();
				
				if(ten_KH.equals("")) {
					inTenKH.setText("Invalid");
					flag = false;
				}
				if(qt_KH.equals("")) {
					inQuocTich.setText("Invalid");
					flag = false;
				}
				if(cmnd_KH.equals("")) {
					inCMNDKH.setText("Invalid");
					flag = false;
				}
				if(diaCHi_KH.equals("")) {
					inDiaChiKH.setText("Invalid");
					flag = false;
				}
				if(dienThoai_KH.equals("")) {
					inDienThoaiKH.setText("Invalid");
					flag = false;
				}
				if(ngaySinh_KH.equals("")) {
					inNgaySinhKH.setText("Invalid");
					flag = false;
				}else {
					try {
						date1 = format.parse(ngaySinh_KH);
					} catch (ParseException e) {
						inNgaySinhKH.setText("Invalid");
						flag = false;
					}
				}
				if(ngayDangKi_KH.equals("")) {
					inNgayDangKi.setText("Invalid");
					flag = false;
				}else {
					try {
						date2 = format.parse(ngayDangKi_KH);
					} catch (ParseException e) {
						inNgayDangKi.setText("Invalid");
						flag = false;
					}
				}
				if(ngayDen_KH.equals("")) {
					inNgayDen.setText("Invalid");
					flag = false;
				}else {
					try {
						date3 = format.parse(ngayDen_KH);
					} catch (ParseException e) {
						inNgayDen.setText("Invalid");
						flag = false;
					}
				}
				
				if(flag) {
					CommonProcessController common = CommonProcessController.getInstance();
					NhanVien nv = common.getNv();
					
					// update Phong
					listData.stream().forEach(r -> {
						try {
							Phong p = new Phong();
							p.setID(r.getID());
							p.setTinhTrang(1);
							roomDAO.updateStatus(p);
						} catch (SQLException e) {
						}
					});
					
					// insert Khach hang
					
					String gioiTinh_KH = gioiTinhKH.getValue();
					String phanLoai_KH = phanloaiKH.getValue();
					String toChuc_KH = (String)tenToChuc.getText();
					String nghe_KH = (String)ngheKH.getText();
					
					KhachHang kh = new KhachHang();
					kh.setTenKH(ten_KH);
					kh.setQuocTich(qt_KH);
					kh.setCmnd(cmnd_KH);
					kh.setDiaChi(diaCHi_KH);
					kh.setDienThoai(dienThoai_KH);
					kh.setNgaySinh(date1);
					kh.setGioiTinh(gioiTinh_KH.equals("Nam"));
					kh.setPhanLoaiKH(phanLoai_KH);
					kh.setTenToChuc(toChuc_KH);
					kh.setNgheNghiep(nghe_KH);
					
					try {
						kh = khDAO.insert(kh);
					} catch (SQLException e) {
					}
					
					// insert thue Phong
					ThuePhong tp = new ThuePhong();
					tp.setID_KH(kh.getMaKH());
					tp.setNgayDangKi(date2);
					tp.setNgayDen(date3);
					tp.setID_NV_choThue(nv.getID());
					
					try {
						tp = tpDAO.insert(tp);
					} catch (SQLException e) {
					}
					
					Long ID_TP = tp.getID();
					
					//insert chi tiet
					
					listData.stream().forEach(t -> {
						ChiTietThuePhong cttp = new ChiTietThuePhong();
						cttp.setID_P(t.getID());
						try {
							cttp.setNgayHenDi(format.parse(t.getNgayDi()));
							cttp.setNgayDi(new Date(0L));
						} catch (ParseException e) {
						}
						Long money = (cttp.getNgayHenDi().getTime() - date3.getTime())/(24 * 60 * 60 * 1000) * map.get(t.getTenPhong()).getGiaPhong();
						
						cttp.setTienCoc(money);
						
						Connect connect = new Connect();
						connect.setID_TP(ID_TP);
						
						try {
							cttp = cttpDAO.insertBook(cttp);
							connect.setID_chiTiet(cttp.getID());
							cnDAO.insert(connect);
						} catch (SQLException e) {
						}
					});
					
					Util util = new Util();
					util.Toast("Đăng kí thành công");
					clearForm();
					//Redirect
				}
			}
		});
	}
	
	private void clearForm() {
		cmndKH.setText("");
		diaChiKH.setText("");
		dienThoaiKH.setText("");
		ngayDangKi.setText("");
		ngayDen.setText("");
		ngaySinhKH.setText("");
		quocTichKH.setText("");
		tenKH.setText("");
		tenToChuc.setText("");
		ngheKH.setText("");
		deposit.setText("");
		
		listData = null;
		gioiTinhKH.getSelectionModel().select(0);
		phanloaiKH.getSelectionModel().select(0);
		set();
		
		table.setItems(FXCollections.observableArrayList());
		
		soLuongPhong.setText("");
	}
	
	private void set() {
		List<Phong> listRoom = new ArrayList<>();
		try {
			listRoom.addAll(roomDAO.findAllEmptyRoom());
		} catch (SQLException e) {
		}
		List<String> list = listRoom.stream().map(r -> r.getTenPhong()).collect(Collectors.toCollection(ArrayList::new));
		phong.getItems().clear();
		phong.getItems().addAll(list);
	}
	
	private void setRoom() {
		List<Phong> listRoom = new ArrayList<>();
		try {
			listRoom.addAll(roomDAO.findAllEmptyRoom());
		} catch (SQLException e) {
		}
		List<String> list = listRoom.stream().map(r -> r.getTenPhong()).collect(Collectors.toCollection(ArrayList::new));
		phong.getItems().addAll(list);
		
		phong.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				String name = phong.getValue();
				Phong room = null;
				try {
					room = listRoom.stream().filter(r -> r.getTenPhong().equals(name)).findFirst().get();
				} catch (Exception e) {
				}
				
				List<DeviceRoom> listDevice = new ArrayList<>();
				if(room != null) {
					try {
						listDevice.addAll(tbDAO.getDeviceByIdRoom(room.getID()));
					} catch (SQLException e) {
					}
					StringBuilder moTaPhong = new StringBuilder();
					for(DeviceRoom tb : listDevice) {
						moTaPhong.append(tb.getTenTB() + " : " + tb.getSoLuong() + "\n");
					}
					
					moTa.setText(moTaPhong.toString());
					
					form = new Form();
					form.setMoTaPhong(moTaPhong.toString());
					form.setTenPhong(room.getTenPhong());
					form.setID(room.getID());
					form.setNgayDi("");
				}
			}
		});
		
		add.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				listData.add(form);
				table.setItems(FXCollections.observableArrayList(listData));
				moTa.clear();
			}
		});
	}
	
	private void setTable() {
		table.setEditable(true);
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		stt.setCellValueFactory(column -> new ReadOnlyObjectWrapper<Number>(table.getItems().indexOf(column.getValue()) + 1));
		
		tenPhong.setCellValueFactory(new PropertyValueFactory<>("tenPhong"));
		
		ngayDi.setCellValueFactory(new PropertyValueFactory<>("ngayDi"));
		ngayDi.setCellFactory(TextFieldTableCell.forTableColumn());
		ngayDi.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Form,String>>() {
			
			@Override
			public void handle(CellEditEvent<Form, String> event) {
				Form form = ((Form) event.getTableView().getItems().get(event.getTablePosition().getRow()));
				String date_str = (String)event.getNewValue();
				try {
					Date d = format.parse(date_str);
					form.setNgayDi(date_str);
					inNgayDi.setText("");
					flag = true;
					Date date = format.parse(ngayDen.getText());
					money += (d.getTime() - date.getTime())/(24 * 60 * 60 * 1000) * map.get(form.getTenPhong()).getGiaPhong();
					deposit.setText(money + " đồng");
				} catch (ParseException e) {
					inNgayDi.setText("Invalid");
					flag = false;
					ngayDi.setCellFactory(column -> {
						return new TableCell<Form, String>(){
							@Override
					        protected void updateItem(String item, boolean empty) {
					            super.updateItem(item, empty);
					            if (item == null || empty) {
					                setText(null);
					            }else {
					            	setText(form.getNgayDi());
					            }
					        }
						};
					});
					ngayDi.setCellFactory(TextFieldTableCell.forTableColumn());
				}
			}
		});
		
		moTaPhong.setCellValueFactory(new PropertyValueFactory<>("moTaPhong"));
		
		table.setItems(FXCollections.observableArrayList(listData));
		
		remove.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				ObservableList<Form> selectedRows = table.getSelectionModel().getSelectedItems();
				List<Form> rows = new ArrayList<>(selectedRows);
				rows.forEach(row -> table.getItems().remove(row));
			}
		});
	}
	
	public class Form{
		private Long ID;
		private String tenPhong;
		private String ngayDi;
		private String moTaPhong;
		
		public Form() {
		}

		public Form(Long id, String tenPhong, String ngayDi, String moTaPhong) {
			this.tenPhong = tenPhong;
			this.ngayDi = ngayDi;
			this.moTaPhong = moTaPhong;
			this.ID = id;
		}

		public String getTenPhong() {
			return tenPhong;
		}

		public void setTenPhong(String tenPhong) {
			this.tenPhong = tenPhong;
		}

		public String getNgayDi() {
			return ngayDi;
		}

		public void setNgayDi(String ngayDi) {
			this.ngayDi = ngayDi;
		}

		public String getMoTaPhong() {
			return moTaPhong;
		}

		public void setMoTaPhong(String moTaPhong) {
			this.moTaPhong = moTaPhong;
		}

		public Long getID() {
			return ID;
		}

		public void setID(Long iD) {
			ID = iD;
		}
	}
}
