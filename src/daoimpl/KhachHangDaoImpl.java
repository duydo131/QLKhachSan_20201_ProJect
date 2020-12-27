package daoimpl;

import dao.KhachHangDao;
import model.KhachHang;
import model.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KhachHangDaoImpl implements KhachHangDao {
    MyConnection myConnection = new MyConnection();
    public KhachHang getObject(ResultSet resultSet) throws SQLException {
        KhachHang khachHang  = null;
        khachHang = new KhachHang(resultSet.getLong("ID"),
                resultSet.getString("TenKH"),
                resultSet.getString("CMND"),
                resultSet.getBoolean("GioiTinh"),
                resultSet.getString("DiaChi"),
                resultSet.getString("DienThoai"),
                resultSet.getString("QuocTich"),
                resultSet.getDate("NgaySinh"),
                resultSet.getString("Nghe"),
                resultSet.getString("PhanLoaiKhachHang"),
                resultSet.getString("ToChuc"));
        
		khachHang.setActive(resultSet.getBoolean("active"));
        return khachHang;
    }

    public List<KhachHang> getList(ResultSet resultSet) throws SQLException {
        List<KhachHang> list = new ArrayList<KhachHang>();
        while (resultSet.next()){
            KhachHang khachHang = getObject(resultSet);
            if (khachHang!=null) list.add(khachHang);
        }
        return list;
    }

    public List<KhachHang> findAll() throws SQLException {
        String sql = "select * from khachHang where active = true";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        return  getList(preparedStatement.executeQuery());
    }

    public KhachHang findById(Long id) throws SQLException {
    	KhachHang tb = null;
        String sql = "select * from khachHang where ID = ? and active = true";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            tb = getObject(resultSet);
        }
        return tb;
    }

    public KhachHang insert(KhachHang t) throws SQLException {
    	KhachHang tb = null;
        String sql = "insert into khachHang(TenKH, CMND, GioiTinh, DiaChi, DienThoai, QuocTich, NgaySinh, "
        		+ "Nghe, PhanLoaiKhachHang, ToChuc) values (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, t.getTenKH());
        preparedStatement.setString(2, t.getCmnd());
        preparedStatement.setBoolean(3, t.isGioiTinh());
        preparedStatement.setString(4, t.getDiaChi());
        preparedStatement.setString(5, t.getDienThoai());
        preparedStatement.setString(6, t.getQuocTich());
        preparedStatement.setDate(7, new java.sql.Date(((Date) t.getNgaySinh()).getTime()));
        preparedStatement.setString(8, t.getNgheNghiep());
        preparedStatement.setString(9, t.getPhanLoaiKH());
        preparedStatement.setString(10, t.getTenToChuc());
        int rs = preparedStatement.executeUpdate();

        if (rs > 0){
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                tb = findById((Long) resultSet.getLong(1));
            }
        }
        return tb;
    }

    public boolean update(KhachHang t) throws SQLException {
    	boolean result = false;
        String sql = "update khachHang TenKH = ?, CMND = ?, GioiTinh = ?, DiaChi = ?, DienThoai = ?, QuocTich = ?, NgaySinh = ?, "
        		+ "Nghe = ?, PhanLoaiKhachHang = ?, ToChuc = ?, active = ? where ID = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, t.getTenKH());
        preparedStatement.setString(2, t.getCmnd());
        preparedStatement.setBoolean(3, t.isGioiTinh());
        preparedStatement.setString(4, t.getDiaChi());
        preparedStatement.setString(5, t.getDienThoai());
        preparedStatement.setString(6, t.getQuocTich());
        preparedStatement.setDate(7, new java.sql.Date(((Date) t.getNgaySinh()).getTime()));
        preparedStatement.setString(8, t.getNgheNghiep());
        preparedStatement.setString(9, t.getPhanLoaiKH());
        preparedStatement.setString(10, t.getTenToChuc());
        preparedStatement.setBoolean(11, t.getActive());
        preparedStatement.setLong(12, t.getMaKH());
        int rs = preparedStatement.executeUpdate();
        if (rs > 0) result = true;
        return result;
    }

	@Override
	public boolean delete(KhachHang t) throws SQLException {
		t.setActive(false);
		return update(t);
	}
}
