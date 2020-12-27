package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import dao.DangNhapDao;
import dao.NhanVienDAO;
import daoimpl.DangNhapDaoImpl;
import daoimpl.NhanVienDAOimpl;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.DangNhap;
import model.NhanVien;

public class RegisterController implements Initializable{
	
	@FXML
	TextField hoTen;
	
	@FXML
	DatePicker ngaySinh;
	
	@FXML
	TextField CMND;
	
	@FXML
	TextField dienThoai;
	
	@FXML
	TextField chuyenMon;
	
	@FXML
	PasswordField matKhau1;
	
	@FXML
	PasswordField matKhau2;
	
	@FXML
	TextField tenDangNhap;
	
	@FXML
	ComboBox<String> gioiTinh;
	
	@FXML
	Label inTen;
	
	@FXML
	Label inTenDangNhap;
	
	@FXML
	Label inMK1;
	
	@FXML
	Label inMK2;
	
	@FXML
	Label inCMND;
	
	@FXML
	Button regis;
	
	private NhanVienDAO nvDAO = new NhanVienDAOimpl();
	private DangNhapDao loginDAO = new DangNhapDaoImpl();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gioiTinh.getItems().addAll("Nam", "Nữ");
		gioiTinh.getSelectionModel().select(0);
		
		regis.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				inTen.setText("");
				inCMND.setText("");
				inMK1.setText("");
				inMK2.setText("");
				inTenDangNhap.setText("");
				
				String tenDangNhap_NV = (String)tenDangNhap.getText();
				String matKhau1_NV = (String)matKhau1.getText();
				String matKhau2_NV = (String)matKhau2.getText();
				String ten_NV = (String)hoTen.getText();
				String ngaySinh_NV = (String)ngaySinh.getValue().toString();
				String CMND_NV = (String)CMND.getText();
				String chuyenMon_NV = (String)chuyenMon.getText();
				String dienThoai_NV = (String)dienThoai.getText();
				String gioiTinh_NV = (String)gioiTinh.getValue();
				
				ngaySinh.getValue();
				
				Date date = new Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				boolean flag = true;
				
				
				if(matKhau1_NV.equals("")) {
					inMK1.setText("Invalid");
					flag = false;
				}
				if(matKhau2_NV.equals("")) {
					inMK2.setText("Invalid");
					flag = false;
				}
				if(tenDangNhap_NV.equals("")) {
					inTenDangNhap.setText("Invalid");
					flag = false;
				}
				if(ten_NV.equals("")) {
					inTen.setText("Invalid");
					flag = false;
				}
				if(CMND_NV.equals("")) {
					inCMND.setText("Invalid");
					flag = false;
				}
				try {
					date = format.parse(ngaySinh_NV);
				} catch (ParseException e) {
					flag = false;
				}
				
				if(flag) {
					try {
						if(loginDAO.contains(tenDangNhap_NV) == null) {
							if(matKhau1_NV.equals(matKhau2_NV)) {
								NhanVien nv = new NhanVien();
								nv.setTen(ten_NV);
								nv.setNgaySinh(date);
								nv.setCMND(CMND_NV);
								nv.setChuyenMon(chuyenMon_NV);
								nv.setDienThoai(dienThoai_NV);
								nv.setGioiTinh(gioiTinh_NV.equals("Nam"));
								
								Long id = nvDAO.insert(nv).getID();
								
								DangNhap dn = new DangNhap();
								dn.setUsername(tenDangNhap_NV);
								dn.setPassword(matKhau1_NV);
								dn.setMaNV(id);
								
								loginDAO.insert(dn);
								CommonProcessController common = CommonProcessController.getInstance();
								common.setNv(dn.getMaNV());
								Redirect();
							}else {
								inMK2.setText("Mật khẩu không trùng nhau");
							}
						}else {
							inTenDangNhap.setText("Tên đăng nhập đã tồn tại");
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	
	private void Redirect() {
		CommonController commonController = CommonController.getInstance();
		Pane header = commonController.getFooter();
		Pane content = commonController.getContent();
		
		Parent root1 = null;
		Parent root2 = null;
		
		try {
			clear();
			root1 = FXMLLoader.load(this.getClass().getResource("/view/HeaderLogoutLayout.fxml"));
			header.getChildren().add(root1);
			root2 = FXMLLoader.load(this.getClass().getResource("/view/ProcessLayout.fxml"));
			content.getChildren().add(root2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void clear() throws IOException {
		CommonController commonController = CommonController.getInstance();
		Pane content = commonController.getContent();
		content.getChildren().clear();
		Pane header = commonController.getFooter();
		header.getChildren().clear();
	}
}
