package daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DanhSachThietBiDAO;
import model.DanhSachThietBi;
import model.MyConnection;

public class DanhSachThietBiDAOimpl implements DanhSachThietBiDAO{
	private MyConnection myConnection = new MyConnection();

	@Override
	public DanhSachThietBi getObject(ResultSet resultSet) throws SQLException {
		DanhSachThietBi ds = new DanhSachThietBi();
		ds.setID_P(resultSet.getLong("ID_P"));
		ds.setID_TB(resultSet.getLong("ID_TB"));
		ds.setSoLuong(resultSet.getLong("SoLuong"));
		return ds;
	}

	@Override
	public List<DanhSachThietBi> getList(ResultSet resultSet) throws SQLException {
		List<DanhSachThietBi> list = new ArrayList<>();
        while (resultSet.next()) {
            DanhSachThietBi tb = getObject(resultSet);
            if (tb != null) list.add(tb);
        }
        return list;
	}

	@Override
	public List<DanhSachThietBi> findAll() throws SQLException {
		String sql = "select * from danhSachThietBi";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        return getList(resultSet);
	}

	@Override
	public DanhSachThietBi findById(Long id) throws SQLException {
		return null;
	}

	@Override
	public List<DanhSachThietBi> findRoomByID_TB(Long id) throws SQLException{
        String sql = "select * from danhSachThietBi where ID_TB = ?";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<DanhSachThietBi> list = new ArrayList<>();
        while (resultSet.next()) {
            DanhSachThietBi tb = getObject(resultSet);
            if (tb != null) list.add(tb);
        }
        return list;
	}

	@Override
	public List<DanhSachThietBi> findTBByID_P(Long id) throws SQLException{
		String sql = "select * from danhSachThietBi where ID_P = ?";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<DanhSachThietBi> list = new ArrayList<>();
        while (resultSet.next()) {
            DanhSachThietBi tb = getObject(resultSet);
            if (tb != null) list.add(tb);
        }
        return list;
	}

	@Override
	public DanhSachThietBi insert(DanhSachThietBi t) throws SQLException {
		DanhSachThietBi tb = null;
        String sql = "insert into danhSachThietBi (ID_P, ID_TB, SoLuong) values (?,?,?);";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setLong(1, t.getID_P());
        preparedStatement.setLong(2, t.getID_TB());
        preparedStatement.setLong(3, t.getSoLuong());
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
	public boolean update(DanhSachThietBi t) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateByID_P(DanhSachThietBi t) throws SQLException {
		boolean result = false;
        String sql = "update danhSachThietBi set ID_TB = ?, SoLuong = ? where ID_P = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setLong(1, t.getID_TB());
        preparedStatement.setLong(2, t.getSoLuong());
        preparedStatement.setLong(3, t.getID_P());
        int rs = preparedStatement.executeUpdate();
        if (rs > 0) result = true;
        return result;
	}

	@Override
	public boolean updateByID_TB(DanhSachThietBi t) throws SQLException {
		boolean result = false;
        String sql = "update danhSachThietBi set ID_P = ?, SoLuong = ? where ID_TB = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setLong(1, t.getID_P());
        preparedStatement.setLong(2, t.getSoLuong());
        preparedStatement.setLong(3, t.getID_TB());
        int rs = preparedStatement.executeUpdate();
        if (rs > 0) result = true;
        return result;
	}

	@Override
	public boolean delete(DanhSachThietBi t) throws SQLException {
		return false;
	}

}
