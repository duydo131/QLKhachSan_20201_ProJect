package utils;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javafx.stage.Stage;

public class Util {

    private Desktop desktop = Desktop.getDesktop();
    
	public void Toast(String toastMsg) {
		Stage primaryStage = new Stage();
		int toastMsgTime = 500; 
		int fadeInTime = 500; 
		int fadeOutTime= 500; 
		Toast.makeText(primaryStage, toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
	}
	
	public void open(String s) {
		File file = new File(s);
		openFile(file);
	}
	
	private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
        	ex.printStackTrace();
        }
    }
}
