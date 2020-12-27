package daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.InfoDAO;
import model.MyConnection;
import model.add.Info;

public class InfoDAOImpl implements InfoDAO{
	private MyConnection myConnection = new MyConnection();
	
	private Info getObject(ResultSet resultSet){
		Info info = new Info();
		try {
			info.setIdTP(resultSet.getLong("idTP"));
			info.setID_KH(resultSet.getLong("id_KH"));
			info.setCustomer(resultSet.getString("customer"));
			info.setID_NV1(resultSet.getLong("id_NV1"));
			info.setID_NV2(resultSet.getLong("id_NV2"));
			info.setIdRoom(resultSet.getLong("idRoom"));
			info.setRoom(resultSet.getString("room"));
			info.setNgayDen(resultSet.getDate("ngayDen"));
			info.setNgayDi(resultSet.getDate("ngayDi"));
			info.setTienCoc(resultSet.getLong("tienCoc"));
			info.setTienPhat(resultSet.getLong("tienPhat"));
			info.setGia(resultSet.getLong("gia"));
			info.setTotal();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return info;
	}
	
	private List<Info> getList(ResultSet resultSet){
		List<Info> list = new ArrayList<>();
        try {
			while (resultSet.next()) {
			    Info tb = getObject(resultSet);
			    if (tb != null) list.add(tb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return list;
	}

	@Override
	public List<Info> getInfo() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ct.ID as idTP, kh.id as ID_KH, kh.TenKH as customer, tp.ID_NV_choThue as ID_NV1, ");
		sql.append("ct.ID_NV_thanhToan as ID_NV2, p.ID as idRoom, p.TenPhong as room, tp.NgayDen as ngayDen, ");
		sql.append("ct.NgayDi as ngayDi, ct.TienCoc as tienCoc, ct.tienPhat as tienPhat, p.GiaPhong as gia ");
		sql.append("FROM  thuephong tp inner join connect cn on tp.ID = cn.ID_TP ");
		sql.append("inner join chiTietThuePhong ct on ct.ID = cn.ID_chiTiet ");
		sql.append("inner join khachHang as kh on tp.ID_KH = kh.ID ");
		sql.append("inner join phong as p on p.ID = ct.ID_P where ct.active = 0");
        PreparedStatement preparedStatement = myConnection.prepare(sql.toString());
		ResultSet resultSet = null;
		try {
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
        return getList(resultSet);
	}
}