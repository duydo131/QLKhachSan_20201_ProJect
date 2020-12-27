package dao;

import java.sql.SQLException;
import java.util.List;

import model.ThietBi;
import model.add.DeviceRoom;

public interface ThietBiDAO extends BaseDao<ThietBi>{
	List<DeviceRoom> getDeviceByIdRoom(Long id) throws SQLException;
}
