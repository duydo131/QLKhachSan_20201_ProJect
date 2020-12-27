package daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ConnectDAO;
import model.Connect;
import model.MyConnection;

public class ConnectDAOimpl implements ConnectDAO{
	private MyConnection myConnection = new MyConnection();

	@Override
	public Connect getObject(ResultSet resultSet) throws SQLException {
		Connect tb = new Connect();
		tb.setID_chiTiet(resultSet.getLong("ID_chitiet"));
		tb.setID_TP(resultSet.getLong("ID_TP"));
		return tb;
	}

	@Override
	public List<Connect> getList(ResultSet resultSet) throws SQLException {
		List<Connect> list = new ArrayList<>();
        while (resultSet.next()) {
            Connect tb = getObject(resultSet);
            if (tb != null) list.add(tb);
        }
        return list;
	}

	@Override
	public List<Connect> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connect findById(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connect insert(Connect t) throws SQLException {
        String sql = "insert into connect (ID_TP, ID_chiTiet) values (?,?);";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setLong(1, t.getID_TP());
        preparedStatement.setLong(2, t.getID_chiTiet());
        preparedStatement.executeUpdate();
		return null;
	}

	@Override
	public boolean update(Connect t) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Connect t) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Connect findConnectByID_TP(Long id) throws SQLException {
		Connect tb = null;
        String sql = "select * from connect where ID_TP = ?";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            tb = getObject(resultSet);
        }
        return tb;
	}

	@Override
	public Connect findConnectByID_CT(Long id) throws SQLException {
		Connect tb = null;
        String sql = "select * from connect where ID_chiTiet = ?";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            tb = getObject(resultSet);
        }
        return tb;
	}

}
