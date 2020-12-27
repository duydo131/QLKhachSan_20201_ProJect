package dao;

import java.sql.SQLException;
import java.util.List;

import model.Phong;

public interface PhongDAO extends BaseDao<Phong>{
	List<Phong> findAllEmptyRoom() throws SQLException;
	boolean updateStatus(Phong t) throws SQLException;
}
