package sample;

import java.io.IOException;

import controller.CommonController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainTestLayout extends Application{

	@Override
	public void start(Stage arg0) throws Exception {
		Parent root = null;
		Scene scene = null;
		CommonController ctl = CommonController.getInstance();
		ctl.setStage(arg0);
		try {
			root = FXMLLoader.load(this.getClass().getResource("/view/DeviceLayout.fxml"));
			arg0.setTitle("Quản Lý Khách Sạn");
			scene = new Scene(root);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		arg0.setScene(scene);
		arg0.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
