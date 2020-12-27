package dao;

import java.sql.SQLException;

import model.Connect;

public interface ConnectDAO extends BaseDao<Connect>{
	Connect findConnectByID_TP(Long id) throws SQLException;
	Connect findConnectByID_CT(Long id) throws SQLException;
}
