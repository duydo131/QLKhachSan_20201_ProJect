package daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.HoaDonDAO;
import model.HoaDon;
import model.MyConnection;

public class HoaDonDAOimpl implements HoaDonDAO{
	private MyConnection myConnection = new MyConnection();

	@Override
	public HoaDon getObject(ResultSet resultSet) throws SQLException {
		HoaDon hd = new HoaDon();
		hd.setID(resultSet.getLong("ID"));
		hd.setID_TP(resultSet.getLong("ID_TP"));
		hd.setNgayLap(resultSet.getDate("NgayLap"));
		hd.setPhiPhatSinh(resultSet.getLong("PhiPhatSinh"));
		hd.setThue(resultSet.getLong("Thue"));
		hd.setActive(resultSet.getBoolean("active"));
		return hd;
	}

	@Override
	public List<HoaDon> getList(ResultSet resultSet) throws SQLException {
		List<HoaDon> list = new ArrayList<>();
        while (resultSet.next()) {
            HoaDon tb = getObject(resultSet);
            if (tb != null) list.add(tb);
        }
        return list;
	}

	@Override
	public List<HoaDon> findAll() throws SQLException {
		String sql = "select * from hoaDon where active = true";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        return getList(resultSet);
	}

	@Override
	public HoaDon findById(Long id) throws SQLException {
		HoaDon tb = null;
        String sql = "select * from hoaDon where ID = ? and active = true";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            tb = getObject(resultSet);
        }
        return tb;
	}

	@Override
	public HoaDon insert(HoaDon t) throws SQLException {
		HoaDon tb = null;
        String sql = "insert into hoaDon (ID_TP, NgayLap, Thue, PhiPhatSinh) values (?,?, ?, ?);";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setLong(1, t.getID_TP());
        preparedStatement.setDate(2, new java.sql.Date(((Date) t.getNgayLap()).getTime()));
        preparedStatement.setLong(3, t.getThue());
        preparedStatement.setLong(4, t.getPhiPhatSinh());
        int rs = preparedStatement.executeUpdate();

        if (rs > 0){
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                tb = findById((Long) resultSet.getLong(1));
            }
        }
        return tb;
	}

	@Override
	public boolean update(HoaDon t) throws SQLException {
		boolean result = false;
        String sql = "update hoaDon set ID_TP = ?, NgayLap = ?, Thue = ?, PhiPhatSinh = ?, active = ? where ID = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setLong(1, t.getID_TP());
        preparedStatement.setDate(2, new java.sql.Date(((Date) t.getNgayLap()).getTime()));
        preparedStatement.setLong(3, t.getThue());
        preparedStatement.setLong(4, t.getPhiPhatSinh());
        preparedStatement.setBoolean(5, t.getActive());
        preparedStatement.setLong(6, t.getID());
        int rs = preparedStatement.executeUpdate();
        if (rs > 0) result = true;
        return result;
	}

	@Override
	public boolean delete(HoaDon t) throws SQLException {
		t.setActive(false);
		return update(t);
	}

}
