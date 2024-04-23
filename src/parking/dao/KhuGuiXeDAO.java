
package parking.dao;

import parking.entity.KhuGuiXe;
import parking.utils.JdbcHelper;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class KhuGuiXeDAO implements ParkDAO<KhuGuiXe, Object>{
    String insert_sql = "INSERT INTO KhuGuiXe(MaKhu, TenKhu, DiaChi, SoLuongChoDau) VALUES(?, ?, ?, ?)";
    String update_sql = "UPDATE KhuGuiXe SET TenKhu = ?, DiaChi = ?, SoLuongChoDau = ? WHERE MaKhu = ?";
    String delete_sql = "DELETE KhuGuiXe WHERE MaKhu = ?";
    String selectByID_sql = "SELECT MaKhu, TenKhu, DiaChi, SoLuongChoDau FROM KhuGuiXe WHERE MaKhu = ?";
    String selectALL_sql = "SELECT MaKhu, TenKhu, DiaChi, SoLuongChoDau FROM KhuGuiXe";

    @Override
    public void insert(KhuGuiXe e) {
        try {
            JdbcHelper.executeUpdate(insert_sql, e.getMaKhu(), e.getTenKhu(), e.getDiaChi(), e.getSoLuongChoDau());
        } catch (SQLException x) {
            throw new RuntimeException();
        }
    }

    @Override
    public void update(KhuGuiXe e) {
        try {
            JdbcHelper.executeUpdate(update_sql, e.getTenKhu(), e.getDiaChi(), e.getSoLuongChoDau(), e.getMaKhu());
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
    public List<KhuGuiXe> selectALL() {
        return selectBySQL(selectALL_sql);
    }

    @Override
    public KhuGuiXe selectByID(Object id) {
        List<KhuGuiXe> list = selectBySQL(selectByID_sql, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<KhuGuiXe> selectBySQL(String sql, Object... args) {
        List<KhuGuiXe> list = new ArrayList<>();
        try {
            ResultSet resultSet = JdbcHelper.ExecuteQuery(sql, args);
            while(resultSet.next()){
                KhuGuiXe khuGuiXe = new KhuGuiXe();
                khuGuiXe.setMaKhu((String) resultSet.getObject("MaKhu"));
                khuGuiXe.setTenKhu((String) resultSet.getObject("TenKhu"));
                khuGuiXe.setDiaChi((String) resultSet.getObject("DiaChi"));
                khuGuiXe.setSoLuongChoDau((int) resultSet.getObject("SoLuongChoDau"));
                list.add(khuGuiXe);
            }
            resultSet.getStatement().getConnection().close();
            return list;
        } catch (SQLException x) {
            throw new RuntimeException();
        }
    }
    
}
