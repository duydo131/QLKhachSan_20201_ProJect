package dao;

import java.sql.SQLException;
import java.util.List;

import model.DanhSachThietBi;

public interface DanhSachThietBiDAO extends BaseDao<DanhSachThietBi>{
	List<DanhSachThietBi> findRoomByID_TB(Long id) throws SQLException;
	List<DanhSachThietBi> findTBByID_P(Long id) throws SQLException;
	public boolean updateByID_P(DanhSachThietBi t) throws SQLException;
	public boolean updateByID_TB(DanhSachThietBi t) throws SQLException;
}
