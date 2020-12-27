package dao;

import java.sql.SQLException;

import model.DangNhap;

public interface DangNhapDao extends BaseDao<DangNhap> {
	DangNhap contains(String tenDangNhap) throws SQLException;
}
