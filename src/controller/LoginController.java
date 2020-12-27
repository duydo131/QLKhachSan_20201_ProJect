package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao.DangNhapDao;
import daoimpl.DangNhapDaoImpl;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.DangNhap;

public class LoginController implements Initializable{
	
	@FXML
	TextField user;
	
	@FXML
	TextField password;
	
	@FXML
	Label inUser;
	
	@FXML
	Label inMK;
	
	@FXML
	Button login;
	
	private DangNhapDao loginDAO = new DangNhapDaoImpl();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		login.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				String username_login = (String)user.getText();
				String password_login = (String)password.getText();
				
				inUser.setText("");
				inMK.setText("");
				
				boolean flag = true;
				
				if(username_login.equals("")) {
					inUser.setText("Invalid");
					flag = false;
				}
				if(password_login.equals("")) {
					inMK.setText("Invalid");
					flag = false;
				}
				if(flag) {
					try {
						DangNhap dn = loginDAO.contains(username_login);
						if(dn != null) {
							if(dn.getPassword().equals(password_login)) {
								CommonProcessController common = CommonProcessController.getInstance();
								common.setNv(dn.getMaNV());
								Redirect();
							}else {
								inMK.setText("Mật khẩu không chính xác");
							}
						}else {
							inUser.setText("Tài khoản chưa tồn tại");
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
