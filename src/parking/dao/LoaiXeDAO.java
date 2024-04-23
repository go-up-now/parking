
package parking.dao;

import parking.entity.LoaiXe;
import parking.utils.JdbcHelper;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LoaiXeDAO implements ParkDAO<LoaiXe, Object>{
    String insert_sql = "INSERT INTO LoaiXe(MaLoaiXe, TenLoaiXe, MaKhu, MaLoaiGia) VALUES(?, ?, ?, ?)";
    String update_sql = "UPDATE LoaiXe SET TenLoaiXe = ?, MaKhu = ?, MaLoaiGia = ? WHERE MaLoaiXe = ?";
    String delete_sql = "DELETE GiaoDich WHERE MaLoaiXe = ?";
    String selectByID_sql = "SELECT MaLoaiXe, TenLoaiXe, MaKhu, MaLoaiGia FROM LoaiXe WHERE MaLoaiXe = ?";
    String selectALL_sql = "SELECT MaLoaiXe, TenLoaiXe, MaKhu, MaLoaiGia FROM LoaiXe";

    @Override
    public void insert(LoaiXe e) {
        try {
            JdbcHelper.executeUpdate(insert_sql, e.getMaLoaiXe(), e.getTenLoaiXe(), e.getMaKhu(), e.getMaLoaiGia());
        } catch (SQLException x) {
            throw new RuntimeException();
        }
    }

    @Override
    public void update(LoaiXe e) {
        try {
            JdbcHelper.executeUpdate(update_sql, e.getTenLoaiXe(), e.getMaKhu(), e.getMaLoaiGia(), e.getMaLoaiXe());
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

    @Override
    public List<LoaiXe> selectALL() {
        return selectBySQL(selectALL_sql);
    }

    @Override
    public LoaiXe selectByID(Object id) {
        List<LoaiXe> list = selectBySQL(selectByID_sql, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<LoaiXe> selectBySQL(String sql, Object... args) {
        List<LoaiXe> list = new ArrayList<>();
        try {
            ResultSet resultSet = JdbcHelper.ExecuteQuery(sql, args);
            while(resultSet.next()){
                LoaiXe loaiXe = new LoaiXe();
                loaiXe.setMaLoaiXe((String) resultSet.getObject("MaLoaiXe"));
                loaiXe.setTenLoaiXe((String) resultSet.getObject("TenLoaiXe"));
                loaiXe.setMaKhu((String) resultSet.getObject("MaKhu"));
                loaiXe.setMaLoaiGia((String) resultSet.getObject("MaLoaiGia"));
                list.add(loaiXe);
            }
            resultSet.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
