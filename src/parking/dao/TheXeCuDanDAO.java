
package parking.dao;

import parking.entity.TheXeCuDan;
import parking.utils.JdbcHelper;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TheXeCuDanDAO implements ParkDAO<TheXeCuDan, Object>{
    String insert_sql = "INSERT INTO TheXeCuDan(MaThe, MaCuDan, MaLoaiXe) VALUES(?, ?, ?)";
    String update_sql = "UPDATE TheXeCuDan SET MaCuDan = ?, MaLoaiXe = ? WHERE MaThe = ?";
    String delete_sql = "DELETE TheXeCuDan WHERE MaThe = ?";
    String selectALL_sql = "SELECT MaThe, ThoiGianDangKy, MaCuDan, MaLoaiXe FROM TheXeCuDan";
    String selectByID = "SELECT MaThe, ThoiGianDangKy, MaCuDan, MaLoaiXe FROM TheXeCuDan WHERE MaThe = ?";

    @Override
    public void insert(TheXeCuDan e) {
        try {
            JdbcHelper.executeUpdate(insert_sql, e.getMaThe(), e.getMaCuDan(), e.getMaLoaiXe());
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public void update(TheXeCuDan e) {
        try {
            JdbcHelper.executeUpdate(update_sql, e.getMaCuDan(), e.getMaLoaiXe(), e.getMaThe());
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }

    @Override
    public void delete(Object id) {
        try {
            JdbcHelper.executeUpdate(delete_sql, id);
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<TheXeCuDan> selectALL() {
        return selectBySQL(selectALL_sql);
    }

    @Override
    public TheXeCuDan selectByID(Object id) {
        List<TheXeCuDan> list = selectBySQL(selectByID, id);
        return list.isEmpty()? null:list.get(0);
    }

    @Override
    public List<TheXeCuDan> selectBySQL(String sql, Object... args) {
        List<TheXeCuDan> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.ExecuteQuery(sql, args);
            while(rs.next()){
                TheXeCuDan theXeCuDan = new TheXeCuDan();
                theXeCuDan.setMaThe((String) rs.getObject("MaThe"));
                theXeCuDan.setThoiGianDangKy((Date) rs.getObject("ThoiGianDangKy"));
                theXeCuDan.setMaCuDan((String) rs.getObject("MaCuDan"));
                theXeCuDan.setMaLoaiXe((String) rs.getObject("MaLoaiXe"));
                list.add(theXeCuDan);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
