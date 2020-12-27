package model.add;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeviceRoomMapper {
	public DeviceRoom getDeviceRoom(ResultSet rs){
		DeviceRoom dr = new DeviceRoom();
		try {
			dr.setTenTB(rs.getString("tenTB"));
			dr.setSoLuong(rs.getLong("soLuong"));
			dr.setGia(rs.getLong("Gia"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dr;
	}
	
	public List<DeviceRoom> getList(ResultSet rs){
		List<DeviceRoom> list = new ArrayList<>();
        try {
			while (rs.next()) {
				DeviceRoom tb = getDeviceRoom(rs);
			    if (tb != null) list.add(tb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return list;
	}
}
