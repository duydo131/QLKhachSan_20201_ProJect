package daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.ChiTietThuePhongDAO;
import model.MyConnection;
import model.ChiTietThuePhong;

public class ChiTietThuePhongDAOimpl implements ChiTietThuePhongDAO{
	private MyConnection myConnection = new MyConnection();
	
	@Override
	public ChiTietThuePhong getObject(ResultSet resultSet) throws SQLException {
		ChiTietThuePhong tb = new ChiTietThuePhong();
		tb.setID(resultSet.getLong("ID"));
		tb.setID_P(resultSet.getLong("ID_P"));
		tb.setGhiChu(resultSet.getString("GhiChu"));
		tb.setTienPhat(resultSet.getLong("TienPhat"));
		tb.setTrangThai(resultSet.getString("TrangThai"));
		tb.setNgayHenDi(resultSet.getDate("NgayHenDi"));
		tb.setNgayDi(resultSet.getDate("NgayDi"));
		tb.setTienCoc(resultSet.getLong("TienCoc"));
		tb.setID_NV_thanhToan(resultSet.getLong("ID_NV_thanhToan"));
		tb.setActive(resultSet.getBoolean("active"));
		return tb;
	}

	@Override
	public List<ChiTietThuePhong> getList(ResultSet resultSet) throws SQLException {
		List<ChiTietThuePhong> list = new ArrayList<>();
        while (resultSet.next()) {
            ChiTietThuePhong tb = getObject(resultSet);
            if (tb != null) list.add(tb);
        }
        return list;
	}

	@Override
	public List<ChiTietThuePhong> findAll() throws SQLException {
		String sql = "select * from chiTietThuePhong where and active = true";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        return getList(resultSet);
	}

	@Override
	public ChiTietThuePhong findById(Long id) throws SQLException {
		ChiTietThuePhong tb = null;
        String sql = "select * from chiTietThuePhong where ID = ? and active = true";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            tb = getObject(resultSet);
        }
        return tb;
	}

	@Override
	public ChiTietThuePhong insert(ChiTietThuePhong t) throws SQLException {
		ChiTietThuePhong tb = null;
        String sql = "insert into chiTietThuePhong (ID_P, GhiChu, NgayHenDi, NgayDi, TienCoc, TienPhat, TrangThai, ID_NV_thanhToan) values (?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setLong(1, t.getID_P());
        preparedStatement.setString(2, t.getGhiChu());
        preparedStatement.setDate(3, new java.sql.Date(((Date) t.getNgayHenDi()).getTime()));
        preparedStatement.setDate(4, new java.sql.Date(((Date) t.getNgayDi()).getTime()));
        preparedStatement.setLong(5, t.getTienCoc());
        preparedStatement.setLong(6, t.getTienPhat());
        preparedStatement.setString(7, t.getTrangThai());
        preparedStatement.setLong(8, t.getID_NV_thanhToan());
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
	public boolean update(ChiTietThuePhong t) throws SQLException {
		boolean result = false;
        StringBuilder sql = new StringBuilder("update chiTietThuePhong set ID_P = ?, GhiChu = ?,  NgayHenDi = ?, NgayDi = ?, ");
        sql.append("TienCoc = ?, TienPhat = ?, TrangThai = ?, ID_NV_thanhToan = ?, active = ? where ID = ?;");
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql.toString());
        preparedStatement.setLong(1, t.getID_P());
        preparedStatement.setString(2, t.getGhiChu());
        preparedStatement.setDate(3, new java.sql.Date(((Date) t.getNgayHenDi()).getTime()));
        preparedStatement.setDate(4, new java.sql.Date(((Date) t.getNgayDi()).getTime()));
        preparedStatement.setLong(5, t.getTienCoc());
        preparedStatement.setLong(6, t.getTienPhat());
        preparedStatement.setString(7, t.getTrangThai());
        preparedStatement.setLong(8, t.getID_NV_thanhToan());
        preparedStatement.setBoolean(9, t.getActive());
        preparedStatement.setLong(10, t.getID());
        int rs = preparedStatement.executeUpdate();
        if (rs > 0) result = true;
        return result;
	}

	@Override
	public boolean delete(ChiTietThuePhong t) throws SQLException {
		t.setActive(false);
		return update(t);
	}

	@Override
	public ChiTietThuePhong insertBook(ChiTietThuePhong t) throws SQLException {
		ChiTietThuePhong tb = null;
        String sql = "insert into chiTietThuePhong (ID_P, NgayHenDi, NgayDi, TienCoc) values (?, ?, ?, ?);";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setLong(1, t.getID_P());
        preparedStatement.setDate(2, new java.sql.Date(((Date) t.getNgayHenDi()).getTime()));
        preparedStatement.setDate(3, new java.sql.Date(((Date) t.getNgayDi()).getTime()));
        preparedStatement.setLong(4, t.getTienCoc());
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
	public ChiTietThuePhong findById_P(Long id) throws SQLException {
		ChiTietThuePhong tb = null;
        String sql = "select * from chiTietThuePhong where ID_P = ? and active = true";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            tb = getObject(resultSet);
        }
        return tb;
	}
}
