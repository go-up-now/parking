
package parking.dao;

import parking.entity.TheXeKhachVangLai;
import parking.utils.JdbcHelper;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TheXeKhachVangLaiDAO implements ParkDAO<TheXeKhachVangLai, Object>{
    String insert_sql = "INSERT INTO TheXeKhachVangLai(MaThe, MaLoaiXe) VALUES(?, ?)";
    String update_sql = "UPDATE TheXeKhachVangLai SET MaLoaiXe = ? WHERE MaThe = ?";
    String delete_sql = "DELETE TheXeKhachVangLai WHERE MaThe = ?";
    String selectALL_sql = "SELECT MaThe, MaLoaiXe FROM TheXeKhachVangLai";
    String selectByID = "SELECT MaThe, MaLoaiXe FROM TheXeKhachVangLai WHERE MaThe = ?";

    @Override
    public void insert(TheXeKhachVangLai e) {
        try {
            JdbcHelper.executeUpdate(insert_sql, e.getMaThe(), e.getMaLoaiXe());
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }

    @Override
    public void update(TheXeKhachVangLai e) {
        try {
            JdbcHelper.executeUpdate(update_sql, e.getMaLoaiXe(), e.getMaThe());
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }

    @Override
    public void delete(Object id) {
        try {
            JdbcHelper.executeUpdate(delete_sql, id);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<TheXeKhachVangLai> selectALL() {
        return selectBySQL(selectALL_sql);
    }

    @Override
    public TheXeKhachVangLai selectByID(Object id) {
        List<TheXeKhachVangLai> list = selectBySQL(selectByID, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<TheXeKhachVangLai> selectBySQL(String sql, Object... args) {
        List<TheXeKhachVangLai> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.ExecuteQuery(sql, args);
            while(rs.next()){
                TheXeKhachVangLai theXeVangLai = new TheXeKhachVangLai();
                theXeVangLai.setMaThe((String) rs.getObject("MaThe"));
                theXeVangLai.setMaLoaiXe((String) rs.getObject("MaLoaiXe"));
                list.add(theXeVangLai);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
