package controller;

import java.util.List;

import javafx.scene.layout.Pane;
import model.add.Info;

public class StoreStatistical {
	private static StoreStatistical instance;
	private Pane footer;
	private Pane content;
	private List<Info> list;
	private Integer position;
	private String time;

	private StoreStatistical() {}
	
	public static StoreStatistical getInstance() {
		if(instance == null) {
			instance = new StoreStatistical();
		}
		return instance;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public List<Info> getList() {
		return list;
	}

	public void setList(List<Info> list) {
		this.list = list;
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

	public static void clear() {
		instance = null;
	}
}
