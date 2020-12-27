package daoimpl;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.NhanVienDAO;
import model.MyConnection;
import model.NhanVien;

public class NhanVienDAOimpl implements NhanVienDAO{
	private MyConnection myConnection = new MyConnection();
	
	@Override
	public NhanVien getObject(ResultSet resultSet) throws SQLException {
		NhanVien tb = new NhanVien();
		tb.setID(resultSet.getLong("ID"));
		tb.setTen(resultSet.getString("Ten"));
		tb.setGioiTinh(resultSet.getBoolean("GioiTinh"));
		tb.setNgaySinh(resultSet.getDate("NgaySinh"));
		tb.setChuyenMon(resultSet.getString("ChuyenMon"));
		tb.setCMND(resultSet.getString("CMND"));
		tb.setDienThoai(resultSet.getString("DienThoai"));
		tb.setActive(resultSet.getBoolean("active"));
		return tb;
	}

	@Override
	public List<NhanVien> getList(ResultSet resultSet) throws SQLException {
		List<NhanVien> list = new ArrayList<>();
        while (resultSet.next()) {
            NhanVien tb = getObject(resultSet);
            if (tb != null) list.add(tb);
        }
        return list;
	}

	@Override
	public List<NhanVien> findAll() throws SQLException {
		String sql = "select * from nhanVien where active = true";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        return getList(resultSet);
	}

	@Override
	public NhanVien findById(Long id) throws SQLException {
		NhanVien tb = null;
        String sql = "select * from nhanVien where ID = ? and active = true";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            tb = getObject(resultSet);
        }
        return tb;
	}

	@Override
	public NhanVien insert(NhanVien t) throws SQLException {
		NhanVien tb = null;
        String sql = "insert into nhanVien (Ten, GioiTinh, NgaySinh, ChuyenMon, CMND, DienThoai) values (?,?,?,?,?,?);";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, t.getTen());
        preparedStatement.setBoolean(2, t.getGioiTinh());
        preparedStatement.setDate(3, new java.sql.Date(((Date)t.getNgaySinh()).getTime()));
        preparedStatement.setString(4, t.getChuyenMon());
        preparedStatement.setString(5, t.getCMND());
        preparedStatement.setString(6, t.getDienThoai());
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
	public boolean update(NhanVien t) throws SQLException {
		boolean result = false;
        String sql = "update nhanVien set Ten = ?, GioiTinh = ?, NgaySinh = ?, ChuyenMon = ?, CMND = ?, "
        		+ "DienThoai = ?, active = ? where ID = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, t.getTen());
        preparedStatement.setBoolean(2, t.getGioiTinh());
        preparedStatement.setDate(3, new java.sql.Date(((Date)t.getNgaySinh()).getTime()));
        preparedStatement.setString(4, t.getChuyenMon());
        preparedStatement.setString(5, t.getCMND());
        preparedStatement.setString(6, t.getDienThoai());
        preparedStatement.setBoolean(7, t.getActive());
        preparedStatement.setLong(8,  t.getID());
        int rs = preparedStatement.executeUpdate();
        if (rs > 0) result = true;
        return result;
	}

	@Override
	public boolean delete(NhanVien t) throws SQLException {
		t.setActive(false);
		return update(t);
	}
}
