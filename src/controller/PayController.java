package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import generate.InfoCustomer;
import generate.InfoPay;
import generate.DOCX.GenerateDocx;
import generate.DOCX.HoaDonDocx;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import model.ChiTietThuePhong;
import model.Connect;
import model.KhachHang;
import model.NhanVien;
import model.Phong;
import model.ThuePhong;
import model.add.DeviceRoom;
import utils.Util;

public class PayController implements Initializable{

	@FXML
	TextField ID_P;
	
	@FXML
	TableView<StatusRoom> table;
	
	@FXML
	TableColumn<StatusRoom, Number> stt;
	
	@FXML
	TableColumn<StatusRoom, String> tenThietBi;
	
	@FXML
	TableColumn<StatusRoom, Number> soLuong;
	
	@FXML
	TableColumn<StatusRoom, String> soLuongHong;
	
	@FXML
	TableColumn<StatusRoom, String> tienPhat;
	
	@FXML
	Label ngayHenDi;
	
	@FXML
	Label tenPhong;
	
	@FXML
	Label ngayDi;
	
	@FXML
	Label tongTienPhat;
	
	@FXML
	Label tienCoc;
	
	@FXML
	Label ngayDangKi;
	
	@FXML
	Label ngayDen;
	
	@FXML
	Label trePhong;
	
	@FXML
	Label tienConLai;
	
	@FXML
	Label inID;
	
	@FXML
	Label inSLH;
	
	@FXML
	Button thanhToan;
	
	@FXML
	Button getInfo;
	
	@FXML
	Button set;
	
	private NhanVien nv;
	private Date date = new Date();
	private boolean flag = false;
	private List<StatusRoom> listData;
	private Long ID_root;
	private Phong phong;
	private ChiTietThuePhong cttp;
	private Long tien = 0L;
	private ThuePhong tp;
	
	private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	
	private PhongDAO pDAO = new PhongDAOimpl();
	private ChiTietThuePhongDAO cttpDAO = new ChiTietThuePhongDAOimpl();
	private ConnectDAO cnDAO = new ConnectDAOimpl();
	private ThuePhongDAO tpDAO = new ThuePhongDAOimpl();
	private ThietBiDAO tbDAO = new ThietBiDAOimpl();
	private KhachHangDao khDAO = new KhachHangDaoImpl();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listData = new ArrayList<>();
		setTable();
		
		getInfo.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {	
				setInfo();
			}
		});
		
		thanhToan.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(cttp != null && flag) {
					nv = CommonProcessController.getInstance().getNv();
					cttp.setActive(false);
					cttp.setID_NV_thanhToan(nv.getID());
					cttp.setNgayDi(date);
					cttp.setTienPhat(tien);
					try {
						cttpDAO.update(cttp);
					} catch (SQLException e) {
					}
					Phong p = null;
					try {
						p = pDAO.findById(ID_root);
						p.setTinhTrang(0);
						pDAO.update(p);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					try {
						List<InfoPay> list = new ArrayList<>();
						InfoPay in = new InfoPay();
						in.setTen(p.getTenPhong());
						in.setGia(p.getGiaPhong());
						in.setNgay(ngayDen.getText());
						in.setSo((int)(cttp.getNgayDi().getTime() - format.parse(ngayDen.getText()).getTime()) / (1000 * 60 * 60 * 24));
						in.setTien(in.getGia() * in.getSo());
						list.add(in);
						KhachHang kh = khDAO.findById(tp.getID_KH());
						InfoCustomer info = new InfoCustomer(kh.getTenKH(), kh.getDienThoai(), kh.getDiaChi(), in.getTien());
						GenerateDocx docx = new HoaDonDocx(new Date(), list, info);
						String file = docx.generateDocx();
						Util util = new Util();
						util.Toast("Thanh toán thành công...");
						if(!file.equals("")) util.open(file);
					} catch (SQLException | ParseException e) {
						e.printStackTrace();
					}
					Redirect();
				}
			}
		});
		
		set.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				for(StatusRoom sr : listData) {
					tien += sr.getTP();
				}
				
				Long tre = (date.getTime() - cttp.getNgayHenDi().getTime()) / (1000 * 60 * 60 * 24);
				Long tienTre = tre * phong.getGiaPhong();
				trePhong.setText(tienTre + "");
				
				tien += tienTre;
				tongTienPhat.setText(tien + "");
				
				Long ngay = (date.getTime() - cttp.getNgayHenDi().getTime()) / (1000 * 60 * 60 * 24);
				Long sum = ngay * phong.getGiaPhong() + tien - cttp.getTienCoc();
				tienConLai.setText(sum + "");
			}
		});
	}
	
	private void Redirect() {
		Pane content = CommonProcessController.getInstance().getContent();
		try {
			content.getChildren().clear();
			content.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/BookRoomLayout.fxml")));
		} catch (IOException e) {
		}
	}
	
	private void setInfo() {
		String ID = (String)ID_P.getText();
		checkRoom(ID);
		if(flag) {
			// get Info
			try {
				cttp = cttpDAO.findById_P(phong.getID());
				if(cttp == null) throw new Exception();
				Connect cn = cnDAO.findConnectByID_CT(cttp.getID());
				if(cn == null) throw new Exception();
				tp = tpDAO.findById(cn.getID_TP());
				if(tp == null) throw new Exception();
				List<DeviceRoom> listDevice = tbDAO.getDeviceByIdRoom(phong.getID());
				if(listDevice == null) throw new Exception();
				
				tenPhong.setText(phong.getTenPhong());
				ngayDangKi.setText(format.format(tp.getNgayDangKi()));
				ngayDen.setText(format.format(tp.getNgayDen()));
				ngayHenDi.setText(format.format(cttp.getNgayHenDi()));
				ngayDi.setText(format.format(date));
				tienCoc.setText(cttp.getTienCoc() + "");
				
				listData = listDevice.stream().map(dr -> new StatusRoom(dr)).collect(Collectors.toCollection(ArrayList::new));
				
				update();
			} catch (Exception e) {
				inID.setText("Phòng chưa được thuê");
				flag = false;
			}
			
		}
	}
	
	private void checkRoom(String ID) {
		if(ID.equals("")) {
			inID.setText("Không tồn tại phòng này");
			flag = false;
		}else {
			try {
				ID_root = Long.parseLong(ID);
				phong = pDAO.findById(ID_root);
				if(phong == null) throw new Exception();
				flag = true;
				inID.setText("");
			} catch (Exception e) {
				inID.setText("Không tồn tại phòng này");
				flag = false;
			}
		}
	}
	
	private void update() {
		table.setItems(FXCollections.observableArrayList(listData));
	}
	
	private void setTable() {
		table.setEditable(true);
		
		stt.setCellValueFactory(column -> new ReadOnlyObjectWrapper<Number>(table.getItems().indexOf(column.getValue()) + 1));
		
		tenThietBi.setCellValueFactory(new PropertyValueFactory<>("tenThietBi"));
		soLuong.setCellValueFactory(new PropertyValueFactory<>("soLuong"));

		tienPhat.setCellValueFactory(new PropertyValueFactory<>("tienPhat"));
		
		soLuongHong.setCellValueFactory(new PropertyValueFactory<>("soLuongHong"));
		soLuongHong.setCellFactory(TextFieldTableCell.forTableColumn());
		soLuongHong.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<StatusRoom,String>>() {
			
			@Override
			public void handle(CellEditEvent<StatusRoom, String> event) {
				StatusRoom sr = ((StatusRoom) event.getTableView().getItems().get(event.getTablePosition().getRow()));
				String sl_str = (String)event.getNewValue();
				try {
					Long sl = Long.parseLong(sl_str);
					if(sl > sr.getSoLuong()) throw new Exception("error");
					sr.setSoLuongHong(sl);
					sr.setTienPhat(sl * sr.getGia());
					table.getItems().clear();
					update();
					inSLH.setText("");
					flag = true;
				} catch (Exception e) {
					if(e.getMessage().equals("error")) {
						inSLH.setText("Nhập sai số lượng");
					}else {
						inSLH.setText("Invalid");
					}
					flag = false;
					soLuongHong.setCellFactory(column -> {
						return new TableCell<StatusRoom, String>(){
							@Override
					        protected void updateItem(String item, boolean empty) {
					            super.updateItem(item, empty);
					            if (item == null || empty) {
					                setText(null);
					            }else {
					            	setText(sr.getSoLuongHong());
					            }
					        }
						};
					});
					soLuongHong.setCellFactory(TextFieldTableCell.forTableColumn());
				}
			}
		});
		
		update();
		
	}
	
	public class StatusRoom{
		private String tenThietBi;
		private Long soLuong;
		private Long soLuongHong = 0L;
		private Long tienPhat = 0L;
		private Long gia;
		
		public StatusRoom() {
		}

		public StatusRoom(DeviceRoom dr) {
			this.tenThietBi = dr.getTenTB();
			this.soLuong = dr.getSoLuong();
			this.gia = dr.getGia();
		}

		public String getTenThietBi() {
			return tenThietBi;
		}

		public void setTenThietBi(String tenThietBi) {
			this.tenThietBi = tenThietBi;
		}

		public Long getSoLuong() {
			return soLuong;
		}

		public void setSoLuong(Long soLuong) {
			this.soLuong = soLuong;
		}

		public Long getSLH() {
			return soLuongHong;
		}

		public String getSoLuongHong() {
			return String.valueOf(soLuongHong);
		}

		public void setSoLuongHong(Long soLuongHong) {
			this.soLuongHong = soLuongHong;
		}
		
		public Long getTP() {
			return tienPhat;
		}

		public String getTienPhat() {
			return String.valueOf(tienPhat);
		}

		public void setTienPhat(Long tienPhat) {
			this.tienPhat = tienPhat;
		}

		public Long getGia() {
			return gia;
		}

		public void setGia(Long gia) {
			this.gia = gia;
		}
	}
}