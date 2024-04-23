
package parking.dao;

import parking.entity.BangGia;
import parking.utils.JdbcHelper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

public class BangGiaDAO implements ParkDAO<BangGia, Object>{
    String insert_sql = "INSERT INTO BangGia(MaLoaiGia, ThoiGian, Gia) VALUES(?, ?, ?)";
    String update_sql = "UPDATE BangGia SET ThoiGian = ?, Gia = ? WHERE MaLoaiGia = ?";
    String delete_sql = "DELETE BangGia WHERE MaLoaiGia = ?";
    String selectALL_sql = "SELECT MaLoaiGia, ThoiGian, Gia FROM BangGia";
    String selectByID = "SELECT MaLoaiGia, ThoiGian, Gia FROM BangGia WHERE MaLoaiGia = ?";
    

    @Override
    public void insert(BangGia e) {
        try {
            JdbcHelper.executeUpdate(insert_sql, e.getMaLoaiGia(), e.getThoiGian(), e.getGia());
        } catch (SQLException ex) {
            Logger.getLogger(BangGiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(BangGia e) {
        try {
            JdbcHelper.executeUpdate(update_sql, e.getThoiGian(), e.getGia(), e.getMaLoaiGia());
        } catch (SQLException ex) {
            Logger.getLogger(BangGiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Object id) {
        try {
            JdbcHelper.executeUpdate(delete_sql, id);
        } catch (SQLException ex) {
            Logger.getLogger(BangGiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<BangGia> selectALL() {
        return selectBySQL(selectALL_sql);
    }

    @Override
    public BangGia selectByID(Object id) {
        List<BangGia> list = selectBySQL(selectByID, id);
        return list.isEmpty()? null:list.get(0);
    }

    @Override
    public List<BangGia> selectBySQL(String sql, Object... args) {
        List<BangGia> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.ExecuteQuery(sql, args);
            while(rs.next()){
                BangGia bangGia = new BangGia();
                bangGia.setMaLoaiGia((String) rs.getObject("MaLoaiGia"));
                bangGia.setThoiGian((String) rs.getObject("ThoiGian"));
                bangGia.setGia((double) rs.getObject("Gia"));
                list.add(bangGia);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<BangGia> selectListByID(Object MaLoaiXe) {
        String selectByLoaiXe = "SELECT MaLoaiGia, ThoiGian, Gia FROM BangGia WHERE MaLoaiGia = ?";
        List<BangGia> list = selectBySQL(selectByLoaiXe, MaLoaiXe);
        return list;
    }
    
//    public BangGia selectByOneLoaiXe(String MaLoaiXe) {
//        String selectByOneLoaiXe = "SELECT MaLoaiGia, ThoiGian, Gia, MaLoaiXe FROM BangGia WHERE MaLoaiXe = ?";
//        List<BangGia> list = selectBySQL(selectByOneLoaiXe, MaLoaiXe);
//        return list.isEmpty() ? null: list.get(0);
//    }

}
