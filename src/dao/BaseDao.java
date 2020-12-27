package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * mỗi đối tượng đều có hàm chung cơ bản sau:
 * findALl():  lấy tất  cả các bản ghi từ 1 bảng trong db
 * findById(): lấy ra một bản ghi có id tương ứng
 * insert(): thêm một bản ghi
 * update(): thay đổi dữ liệu của một bản ghi
 * delete(): xóa một bản ghi
 * getObject(), getList(): chuyển resultSet về 1 đối tượng  hoặc 1 list đối tượng
 */

public interface BaseDao<T> {

    T getObject(ResultSet resultSet) throws SQLException;

    List<T> getList(ResultSet resultSet) throws SQLException;

    List<T> findAll() throws SQLException;

    T findById(Long id) throws SQLException;

    T insert(T t) throws SQLException;

    boolean update(T t) throws SQLException;

    boolean delete(T t) throws SQLException;
}
