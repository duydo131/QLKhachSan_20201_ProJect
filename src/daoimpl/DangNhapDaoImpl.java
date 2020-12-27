package daoimpl;

import dao.DangNhapDao;
import model.DangNhap;
import model.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DangNhapDaoImpl implements DangNhapDao {
    private MyConnection myConnection = new MyConnection();

    //nhận vào một đối tượng resultSet đại diện cho một dòng (bản ghi) chứa các thông
    //tin của đối tượng
    //Nhiệm vụ hàm: đọc các thông tin của đối tượng bằng các hàm get kiểu dữ liệu tương ứng
    //để chuyển về một đối tượng trong java

    public DangNhap getObject(ResultSet resultSet) throws SQLException {
        DangNhap dangNhap = null;
        //sử dụng contructor full tham số để tạo ra đối tượng và lần lượt
        //truyền vào các giái trị thuộc tính tương ứng từ resultSet
        dangNhap = new DangNhap(resultSet.getString("User"),
                resultSet.getString("Password"), resultSet.getLong("ID_NV"));
        //đối với các đối tượng có nhiều thuộc tính đê tránh nhầm trường
        //có thể tạo đối tượng bằng contructor mặc định và sử dụng các hàm set để
        //truyền giá trị cho đối tượng

        return dangNhap;
    }


    public List<DangNhap> getList(ResultSet resultSet) throws SQLException {
        List<DangNhap> list = new ArrayList<DangNhap>();
        //cho con trỏ resulteSet chạy lần lượt qua các bản ghi bằng hàm .next() lần đầu tiên khi
        //.next() con trỏ resultSet mới được trỏ vào bản ghi đầu tiền
        while (resultSet.next()) {
            DangNhap dangNhap = getObject(resultSet);
            if (dangNhap != null) list.add(dangNhap);
        }
        return list;
    }


    public List<DangNhap> findAll() throws SQLException {
        //select *(lấy tất cả các trường) from table(table là tên bảng muốn lấy dữ liệu)
        //where (điều kiện khi tìm kiếm cá bản ghi) deleted = false (tức chỉ lấy các bản ghi
        // chưa bị xóa)
        String sql = "select * from login";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        return getList(preparedStatement.executeQuery());
    }


    public DangNhap findById(Long id) throws SQLException {
        DangNhap dangNhap = null;
        //kiểm tra deleted trước id để tối ưu thời gian câu lệnh
        String sql = "select * from login where ID_NV = ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        //kiểm tra xem có bản ghi hay không nếu có sử dụng hàm getObject để lấy ra đối tượng.
        if (resultSet.next()) {
            dangNhap = getObject(resultSet);
        }
        return dangNhap;
    }

    public DangNhap insert(DangNhap dangNhap) throws SQLException {
        DangNhap newDangNhap = null;
        String sql = "insert into login (User, Password, ID_NV) values (?,?,?);";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, dangNhap.getUsername());
        preparedStatement.setString(2, dangNhap.getPassword());
        preparedStatement.setLong(3, dangNhap.getMaNV());
//        int rs = 
        		preparedStatement.executeUpdate();

        //nếu insert thành công rs là số bản ghi mình vừa thay đổi dữ liệu
//        if (rs > 0){
//            //sử dụng hầm getGenerateKeys để trả về cho mình resultSet
//            // chứa id tương ứng với bản ghi vừa thêm vào;
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
//            //kiểm tra xem có kết quả result trả về hay không
//            if (resultSet.next()) {
//                //resultSet.getLong(số thứ tự cột) ở đây chỉ trả về 1 ô nên stt sẽ là 1
//                //getGeneratedKeys chứa kiểu trả về là long
//                newDangNhap = findById((int) resultSet.getLong(1));
//            }
//        }
        return newDangNhap;
    }


    public boolean update(DangNhap dangNhap) throws SQLException {
        boolean result = false;
        //đổi mật khẩu
        String sql = "update login set Password =? where User = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, dangNhap.getPassword());
        preparedStatement.setString(2, dangNhap.getUsername());
        int rs = preparedStatement.executeUpdate();
        if (rs > 0) result = true;
        return result;
    }


    public boolean delete(DangNhap dn) throws SQLException {
        boolean result = false;
        String sql = "delete from login where User = ? ;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, dn.getUsername());
        int rs = preparedStatement.executeUpdate();
        if (rs > 0) result = true;
        return result;
    }


	@Override
	public DangNhap contains(String tenDangNhap) throws SQLException {
		String sql = "Select * from login where User = ?";
		PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
		preparedStatement.setString(1, tenDangNhap);
		List<DangNhap> list = getList(preparedStatement.executeQuery());
		return list.isEmpty() ? null : list.get(0);
	}
}
