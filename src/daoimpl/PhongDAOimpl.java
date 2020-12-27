package daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.PhongDAO;
import model.MyConnection;
import model.Phong;

public class PhongDAOimpl implements PhongDAO{
	
	private MyConnection myConnection = new MyConnection();
	
	@Override
	public Phong getObject(ResultSet resultSet) throws SQLException {
		Phong tb = new Phong();
		tb.setID(resultSet.getLong("ID"));
		tb.setLoaiPhong(resultSet.getString("LoaiPhong"));
		tb.setGiaPhong(resultSet.getLong("GiaPhong"));
		tb.setTinhTrang(resultSet.getInt("TinhTrang"));
		tb.setTenPhong(resultSet.getString("TenPhong"));
		return tb;
	}

	@Override
	public List<Phong> getList(ResultSet resultSet) throws SQLException {
		List<Phong> list = new ArrayList<>();
        while (resultSet.next()) {
            Phong tb = getObject(resultSet);
            if (tb != null) list.add(tb);
        }
        return list;
	}

	@Override
	public List<Phong> findAll() throws SQLException {
		String sql = "select * from phong";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        return getList(resultSet);
	}

	@Override
	public Phong findById(Long id) throws SQLException {
		Phong tb = null;
        String sql = "select * from phong where ID = ? ";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            tb = getObject(resultSet);
        }
        return tb;
	}

	@Override
	public Phong insert(Phong t) throws SQLException {
		Phong tb = null;
        String sql = "insert into phong (LoaiPhong, GiaPhong, TinhTrang, TenPhong) values (?,?,?,?);";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, t.getLoaiPhong());
        preparedStatement.setLong(2, t.getGiaPhong());
        preparedStatement.setInt(3, t.getTinhTrang());
        preparedStatement.setString(4, t.getTenPhong());
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
	public boolean update(Phong t) throws SQLException {
		boolean result = false;
        String sql = "update phong set LoaiPhong = ?, GiaPhong = ?, TinhTrang = ?, TenPhong = ? where ID = ?";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, t.getLoaiPhong());
        preparedStatement.setLong(2, t.getGiaPhong());
        preparedStatement.setInt(3, t.getTinhTrang());
        preparedStatement.setString(4, t.getTenPhong());
        preparedStatement.setLong(5, t.getID());
        int rs = preparedStatement.executeUpdate();
        if (rs > 0) result = true;
        return result;
	}

	@Override
	public boolean delete(Phong t) throws SQLException {
		return false;
	}

	@Override
	public List<Phong> findAllEmptyRoom() throws SQLException {
		String sql = "select * from phong where TinhTrang = 0";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        return getList(resultSet);
	}

	@Override
	public boolean updateStatus(Phong t) throws SQLException {
		boolean result = false;
        String sql = "update phong set TinhTrang = ? where ID = ?";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setInt(1, t.getTinhTrang());
        preparedStatement.setLong(2, t.getID());
        int rs = preparedStatement.executeUpdate();
        if (rs > 0) result = true;
        return result;
	}

}
