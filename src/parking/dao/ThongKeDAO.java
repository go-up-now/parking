
package parking.dao;

import parking.utils.JdbcHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import parking.entity.ThongKe;

public class ThongKeDAO{
    public List<Object[]> getListOfArray(String sql, String[] cols, Object...args){
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = JdbcHelper.ExecuteQuery(sql, args);
            while(rs.next()){
                Object[] value = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    value[i] = rs.getObject(cols[i]);
                }
                list.add(value);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }
    
    public List<Object[]> getBangDiem(int makh){
        String sql  = "{call sp_BangDiem(?)}";
        String[] cols = {"MaNH","HoTen","Diem"};
        return getListOfArray(sql, cols, makh);
    }
    
    public List<Object[]> getLuongXeCuDanGuiNam(Object khuGuiXe){
        String sql  = "{call Sp_LuongXeCuDanGuiTheoNam(?)}";
        String[] cols = {"Nam", "SoLuong", "DauTien", "CuoiCung"};
        return getListOfArray(sql, cols, khuGuiXe);
    }
    
    public List<Object[]> getLuongXeCuDanGuiThang(Object khuGuiXe, int nam){
        String sql  = "{call Sp_LuongXeCuDanGuiTheoThang(?, ?)}";
        String[] cols = {"ThoiGian", "SoLuong", "DauTien", "CuoiCung"};
        return getListOfArray(sql, cols, khuGuiXe, nam);
    }
    
    public List<Object[]> getLuongXeCuDanGuiNgay(Object khuGuiXe, int nam, int thang){
        String sql  = "{call Sp_LuongXeCuDanGuiTheoNgay(?, ?, ?)}";
        String[] cols = {"ThoiGian", "SoLuong", "DauTien", "CuoiCung"};
        return getListOfArray(sql, cols, khuGuiXe, nam, thang);
    }
    
    public List<Object[]> getLuongXeKhachVangLaiGuiNam(Object khuGuiXe){
        String sql  = "{call Sp_LuongXeKhachVangLaiGuiTheoNam(?)}";
        String[] cols = {"Nam", "SoLuong", "DauTien", "CuoiCung"};
        return getListOfArray(sql, cols, khuGuiXe);
    }
    
    public List<Object[]> getLuongXeKhachVangLaiGuiThang(Object khuGuiXe, int nam){
        String sql  = "{call Sp_LuongXeKhachVangLaiGuiTheoThang(?, ?)}";
        String[] cols = {"ThoiGian", "SoLuong", "DauTien", "CuoiCung"};
        return getListOfArray(sql, cols, khuGuiXe, nam);
    }
    
    public List<Object[]> getLuongXeKhachVangLaiGuiNgay(Object khuGuiXe, int nam, int thang){
        String sql  = "{call Sp_LuongXeKhachVangLaiGuiTheoNgay(?, ?, ?)}";
        String[] cols = {"ThoiGian", "SoLuong", "DauTien", "CuoiCung"};
        return getListOfArray(sql, cols, khuGuiXe, nam, thang);
    }
    
    public List<Object[]> getDiemChuyenDe(){
        String sql  = "{call sp_DiemChuyenDe}";
        String[] cols = {"ChuyenDe","SoHV","ThapNhat","CaoNhat","TrungBinh"};
        return getListOfArray(sql, cols);
    }
    
    public List<Object[]> getDoanhThuCuDan(Date dateStart, Date dateEnd){
        String sql  = "{call Sp_DoanhThu(?, ?)}";
        String[] cols = {"KhuGuiXe", "SoLoaiXeGui", "SoXeGui", "DoanhThu", "DoanhThuMin", "DoanhThuMax", "DoanhThuAVG"};
        return getListOfArray(sql, cols, dateStart, dateEnd);
    }
    
    public List<Object[]> getDoanhThuKhachVangLai(Date dateStart, Date dateEnd){
        String sql  = "{call Sp_DoanhThuKhachVangLai(?, ?)}";
        String[] cols = {"KhuGuiXe", "SoLoaiXeGui", "SoXeGui", "DoanhThu", "DoanhThuMin", "DoanhThuMax", "DoanhThuAVG"};
        return getListOfArray(sql, cols, dateStart, dateEnd);
    }
    
    public List<ThongKe> selectCuDanNamByMaKhu(String maKhu){
        String selectNamByMaKhu_SQL = "SELECT DISTINCT Nam FROM View_ThoiGianCuDanGui WHERE MaKhu = ?";
        List<ThongKe> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.ExecuteQuery(selectNamByMaKhu_SQL, maKhu);
            while(rs.next()){
                ThongKe thongKe = new ThongKe();
                thongKe.setNam(rs.getInt("Nam"));
                list.add(thongKe);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<ThongKe> selectCuDanThangByMaKhu(String maKhu, Object nam){
        String selectThangByMaKhu_SQL = "SELECT DISTINCT Thang FROM View_ThoiGianCuDanGui WHERE MaKhu = ? AND Nam = ?";
        List<ThongKe> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.ExecuteQuery(selectThangByMaKhu_SQL, maKhu, nam);
            while(rs.next()){
                ThongKe thongKe = new ThongKe();
                thongKe.setThang(rs.getInt("Thang"));
                list.add(thongKe);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<ThongKe> selectVangLaiNamByMaKhu(String maKhu){
        String selectNamByMaKhu_SQL = "SELECT DISTINCT Nam FROM View_ThoiGianKhachVangLaiGui WHERE MaKhu = ?";
        List<ThongKe> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.ExecuteQuery(selectNamByMaKhu_SQL, maKhu);
            while(rs.next()){
                ThongKe thongKe = new ThongKe();
                thongKe.setNam(rs.getInt("Nam"));
                list.add(thongKe);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<ThongKe> selectVangLaiThangByMaKhu(String maKhu, Object nam){
        String selectThangByMaKhu_SQL = "SELECT DISTINCT Thang FROM View_ThoiGianKhachVangLaiGui WHERE MaKhu = ? AND Nam = ?";
        List<ThongKe> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.ExecuteQuery(selectThangByMaKhu_SQL, maKhu, nam);
            while(rs.next()){
                ThongKe thongKe = new ThongKe();
                thongKe.setThang(rs.getInt("Thang"));
                list.add(thongKe);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
