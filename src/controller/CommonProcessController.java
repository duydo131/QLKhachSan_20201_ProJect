package controller;

import java.sql.SQLException;

import dao.NhanVienDAO;
import daoimpl.NhanVienDAOimpl;
import javafx.scene.layout.Pane;
import model.NhanVien;

public class CommonProcessController {
	private static CommonProcessController instance;
	private Pane footer;
	private Pane content;
	private NhanVien nv;;

	private CommonProcessController() {}
	
	public static CommonProcessController getInstance() {
		if(instance == null) {
			instance = new CommonProcessController();
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
	
	public NhanVien getNv() {
		return nv;
	}

	public void setNv(Long id) {
		NhanVienDAO nvDAO = new NhanVienDAOimpl();
		try {
			this.nv = nvDAO.findById(id);
			
		} catch (SQLException e) {
		}
		
	}

	public static void clear() {
		instance = null;
	}
}
