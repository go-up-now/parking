
package parking.dao;

import parking.entity.CuDan;
import parking.utils.JdbcHelper;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CuDanDAO implements ParkDAO<CuDan, Object>{
    String insert_sql = "INSERT INTO CuDan(MaCuDan, Ho, Ten, GioiTinh, SDT, Email) VALUES(?, ?, ?, ?, ?, ?)";
    String update_sql = "UPDATE CuDan SET Ho = ?, Ten = ?, GioiTinh = ?, SDT = ?, Email = ? WHERE MaCuDan = ?";
    String delete_sql = "UPDATE CuDan SET is_Delete = 1 WHERE MaCuDan = ?";
    String restore_sql = "UPDATE CuDan SET is_Delete = 0 WHERE MaCuDan = ?";
    String selectByID_sql = "SELECT MaCuDan, Ho, Ten, GioiTinh, SDT, Email FROM CuDan WHERE MaCuDan = ?";
    String selectALL_sql = "SELECT MaCuDan, Ho, Ten, GioiTinh, SDT, Email FROM CuDan";

    @Override
    public void insert(CuDan e) {
        try {
            JdbcHelper.executeUpdate(insert_sql, e.getMaCuDan(), e.getHo(), e.getTen(), e.getGioiTinh(),
                         e.getSoDienThoai(), e.getEmail());
        } catch (SQLException x) {
            throw new RuntimeException();
        }
    }

    @Override
    public void update(CuDan e) {
        try {
            JdbcHelper.executeUpdate(update_sql, e.getHo(), e.getTen(), e.getGioiTinh(),
                         e.getSoDienThoai(), e.getEmail(), e.getMaCuDan());
        } catch (SQLException x) {
            throw new RuntimeException();
        }
    }

    @Override
    public void delete(Object id) {
        try {
            JdbcHelper.executeUpdate(delete_sql, id);
        } catch (SQLException x) {
            throw new RuntimeException();
        }
    }

    public void restore(Object id) {
        try {
            JdbcHelper.executeUpdate(restore_sql, id);
        } catch (SQLException x) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<CuDan> selectALL() {
        return selectBySQL(selectALL_sql);
    }

    @Override
    public CuDan selectByID(Object id) {
        List<CuDan> list = selectBySQL(selectByID_sql, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<CuDan> selectBySQL(String sql, Object... args) {
        List<CuDan> list = new ArrayList<>();
        try {
            ResultSet resultSet = JdbcHelper.ExecuteQuery(sql, args);
            while(resultSet.next()){
                CuDan cuDan = new CuDan();
                cuDan.setMaCuDan((String) resultSet.getObject("MaCuDan"));
                cuDan.setHo((String) resultSet.getObject("Ho"));
                cuDan.setHo((String) resultSet.getObject("Ten"));
                cuDan.setGioiTinh((int) resultSet.getObject("GioiTinh"));
                cuDan.setSoDienThoai((String) resultSet.getObject("SDT"));
                cuDan.setEmail((String) resultSet.getObject("Email"));
                list.add(cuDan);
            }
            resultSet.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    
}
