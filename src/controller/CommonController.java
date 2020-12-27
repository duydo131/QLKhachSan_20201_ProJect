package controller;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CommonController {
	private static CommonController instance;
	private Pane footer;
	private Pane content;
	private Stage stage;

	private CommonController() {}
	
	public static CommonController getInstance() {
		if(instance == null) {
			instance = new CommonController();
		}
		return instance;
	}

	public Pane getFooter() {
		return footer;
	}

	public void setFooter(Pane footer) {
		this.footer = footer;
	}

	public Pane getContent() {
		return content;
	}

	public void setContent(Pane content) {
		this.content = content;
	}
	
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
