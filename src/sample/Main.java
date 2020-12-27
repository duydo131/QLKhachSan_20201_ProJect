package sample;

import java.sql.SQLException;
import java.util.Date;

import dao.KhachHangDao;
import dao.NhanVienDAO;
import dao.ThietBiDAO;
import daoimpl.KhachHangDaoImpl;
import daoimpl.NhanVienDAOimpl;
import daoimpl.ThietBiDAOimpl;
import generate.DOCX.GenerateDocx;
import generate.DOCX.KhachHangDocx;
import generate.DOCX.NhanVienDocx;
import generate.DOCX.ThietBiDocx;

public class Main{
	public static void main(String[] args) throws SQLException {
		ThietBiDAO dao = new ThietBiDAOimpl();
		GenerateDocx docx = new ThietBiDocx(new Date(), dao.findAll());
		String file = docx.generateDocx();
		System.out.println(file);
	}
}
