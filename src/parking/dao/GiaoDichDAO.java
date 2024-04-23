
package parking.dao;

import parking.entity.GiaoDich;
import parking.utils.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GiaoDichDAO implements ParkDAO<GiaoDich, Object>{
    String insert_CuDan_sql = "INSERT INTO GiaoDich(SoNgayGui, BienSoXe, PhiGuiXe, TrangThai,"
                        + "Hinh, MaNV, CuDanID) VALUES(?, ?, ?, ?, ?, ?, ?)";
    String insert_KhachVangLai_sql = "INSERT INTO GiaoDich(SoNgayGui, BienSoXe, PhiGuiXe, TrangThai,"
                        + "Hinh, MaNV, KhachVangLaiID) VALUES(?, ?, ?, ?, ?, ?, ?)";
    String update_sql = "UPDATE GiaoDich SET SoNgayGui = ?, ThoiGianRa = ?, BienSoXe = ?, Hinh = ?, PhiGuiXe = ? WHERE MaGD = ?";
    String delete_sql = "UPDATE GiaoDich SET is_Delete = 1 WHERE MaGD = ?";
    String restore_sql = "UPDATE GiaoDich SET is_Delete = 0 WHERE MaGD = ?";
    String selectByDelete_sql = "SELECT MaGD, ThoiGianVao, ThoiGianRa, SoNgayGui, BienSoXe, PhiGuiXe, TrangThai, Hinh, MaNV, CuDanID, KhachVangLaiID FROM GiaoDich WHERE is_Delete = 1 AND MaGD = ?";
    String selectByID_sql = "SELECT MaGD, ThoiGianVao, ThoiGianRa, SoNgayGui, BienSoXe, PhiGuiXe, TrangThai, Hinh, MaNV, CuDanID, KhachVangLaiID FROM GiaoDich WHERE MaGD = ?";
    String selectALL_sql = "SELECT MaGD, ThoiGianVao, ThoiGianRa, SoNgayGui, BienSoXe, PhiGuiXe, TrangThai, Hinh, MaNV, CuDanID, KhachVangLaiID FROM GiaoDich ";
    
    @Override
    public void insert(GiaoDich e) {
        try {
            JdbcHelper.executeUpdate(insert_CuDan_sql, e.getSoNgayGui(), e.getBienSoXe(), 
                        e.getPhiGuiXe(), e.isTrangThai(), e.getHinh(), e.getMaNhanVien(), e.getTheCuDanID());
        } catch (SQLException x) {
            throw new RuntimeException();
        }
    }

    @Override
    public void update(GiaoDich e) {
        try {
            JdbcHelper.executeUpdate(update_sql,e.getSoNgayGui(), e.getThoiGianRa(), e.getBienSoXe(), e.getHinh(), e.getPhiGuiXe(), e.getMaGiaoDich());    
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
    public List<GiaoDich> selectALL() {
        return selectBySQL(selectALL_sql);
    }

    @Override
    public GiaoDich selectByID(Object id) {
        List<GiaoDich> list = selectBySQL(selectByID_sql, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<GiaoDich> selectBySQL(String sql, Object... args) {
        List<GiaoDich> list = new ArrayList<>();
        try {
            ResultSet resultSet = JdbcHelper.ExecuteQuery(sql, args);
            while(resultSet.next()){
                GiaoDich giaoDich = new GiaoDich();
                giaoDich.setMaGiaoDich((int) resultSet.getObject("MaGD"));
                giaoDich.setThoiGianVao((Date) resultSet.getObject("ThoiGianVao"));
                giaoDich.setThoiGianRa((Date) resultSet.getObject("ThoiGianRa"));
                giaoDich.setSoNgayGui((int) resultSet.getObject("SoNgayGui"));
                giaoDich.setBienSoXe((String) resultSet.getObject("BienSoXe"));
                giaoDich.setPhiGuiXe((double) resultSet.getObject("PhiGuiXe"));
                giaoDich.setTrangThai((boolean) resultSet.getObject("TrangThai"));
                giaoDich.setHinh((String) resultSet.getObject("Hinh"));
                giaoDich.setMaNhanVien((int) resultSet.getObject("MaNV"));
                giaoDich.setTheKhachVangLaiID((String) resultSet.getObject("KhachVangLaiID"));
                giaoDich.setTheCuDanID((String) resultSet.getObject("CuDanID"));
                list.add(giaoDich);
            }
            resultSet.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    
    public void insert_KhachVangLai(GiaoDich e) {
        try {
            JdbcHelper.executeUpdate(insert_KhachVangLai_sql, e.getSoNgayGui(), e.getBienSoXe(), 
                        e.getPhiGuiXe(), e.isTrangThai(), e.getHinh(), e.getMaNhanVien(), e.getTheKhachVangLaiID());
        } catch (SQLException x) {
            throw new RuntimeException();
        }
    }

    public List<GiaoDich> selectBbyDeleted(Object id) {
        return selectBySQL(selectByDelete_sql, id);
    }
    
    public List<GiaoDich> selectBySearch(Object key){
        String selectBySearch = "SELECT MaGD, ThoiGianVao, ThoiGianRa, SoNgayGui, BienSoXe, PhiGuiXe, TrangThai, Hinh, MaNV, "
                + "CuDanID, KhachVangLaiID FROM GiaoDich WHERE is_Delete = 0 AND TrangThai = 0 AND MaGD LIKE ?";
        return selectBySQL(selectBySearch, "%"+key+"%");
    }
    
    public List<GiaoDich> selectBySearchGiaoDichDaXoa(Object key){
        String selectBySearch = "SELECT MaGD, ThoiGianVao, ThoiGianRa, SoNgayGui, BienSoXe, PhiGuiXe, TrangThai, Hinh, MaNV, "
                + "CuDanID, KhachVangLaiID FROM GiaoDich WHERE is_Delete = 1 AND MaGD LIKE ?";
        return selectBySQL(selectBySearch, "%"+key+"%");
    }
    
    public List<GiaoDich> selectBySearchLichSuGuiXe(Object key){
        String selectBySearch = "SELECT MaGD, ThoiGianVao, ThoiGianRa, SoNgayGui, BienSoXe, PhiGuiXe, TrangThai, Hinh, MaNV, "
                + "CuDanID, KhachVangLaiID FROM GiaoDich WHERE is_Delete = 0 AND TrangThai = 1 AND MaGD LIKE ?";
        return selectBySQL(selectBySearch, "%"+key+"%");
    }
    
    public void layXe(GiaoDich e){
        String layXe_sql = "UPDATE GiaoDich SET ThoiGianRa = GETDATE(), SoNgayGui = ?, PhiGuiXe = ?, TrangThai = 1 WHERE MaGD = ?";
        try {
            JdbcHelper.executeUpdate(layXe_sql, e.getSoNgayGuiXe(), e. getPhiGuiXe(), e.getMaGiaoDich());
        } catch (SQLException x) {
            throw new RuntimeException();
        }  
    }
    
    public List<Integer> selectYearGiaoDich(){
        String sql = "SELECT DISTINCT YEAR(ThoiGianVao) FROM GiaoDich";
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet resultSet = JdbcHelper.ExecuteQuery(sql);
            while(resultSet.next()){
                list.add(resultSet.getInt(1));
            }
            resultSet.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

}
