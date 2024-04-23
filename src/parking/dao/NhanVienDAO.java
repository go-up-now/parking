
package parking.dao;

import parking.entity.NhanVien;
import parking.utils.Authenticated;
import parking.utils.JdbcHelper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

public class NhanVienDAO implements ParkDAO<NhanVien, Object>{
    String insert_sql = "INSERT INTO NhanVien(Ho, Ten, TenDangNhap, MatKhau, Email, SDT, GioiTinh, DiaChi, VaiTro, TrangThai, HinhAnh) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String update_sql = "UPDATE NhanVien SET Ho = ?, Ten = ?, MatKhau = ?, Email = ?, SDT = ?, GioiTinh = ?, DiaChi = ?, HinhAnh = ? WHERE MaNV = ?";
    String update_sql_admin = "UPDATE NhanVien SET Ho = ?, Ten = ?, MatKhau = ?, Email = ?, SDT = ?, GioiTinh = ?, DiaChi = ?, VaiTro = ?, TrangThai = ?, HinhAnh = ? WHERE MaNV = ?";
    String delete_sql = "UPDATE NhanVien SET is_Delete = 1 WHERE MaNV = ?";
    String restore_sql = "UPDATE NhanVien SET is_Delete = 0 WHERE MaNV = ?";
    String restoreAll_sql = "UPDATE NhanVien SET is_Delete = 0";
    String selectALL_sql = "SELECT MaNV, Ho, Ten, TenDangNhap, MatKhau, Email, SDT, GioiTinh, DiaChi, VaiTro, TrangThai, HinhAnh FROM NhanVien WHERE is_Delete = 0 AND TrangThai = 0";
    String selectByID = "SELECT MaNV, Ho, Ten, TenDangNhap, MatKhau, Email, SDT, GioiTinh, DiaChi, VaiTro, TrangThai, HinhAnh FROM NhanVien WHERE MaNV = ?"; 
    String selectByUserName = "SELECT MaNV, Ho, Ten, TenDangNhap, MatKhau, Email, SDT, GioiTinh, DiaChi, VaiTro, TrangThai, HinhAnh FROM NhanVien WHERE is_Delete = 0 AND TenDangNhap = ?"; 

    

    @Override
    public void insert(NhanVien e) {
        try {
            JdbcHelper.executeUpdate(insert_sql, e.getHo(), e.getTen(),e.getTenDangNhap(),e.getMatKhau(),
                    e.getEmail(), e.getSoDienThoai(), e.getGioiTinh(), e.getDiaChi(), e.isVaiTro(), e.getTrangThai(), e.getHinhAnh());
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(NhanVien e) {
        try {
            if(!Authenticated.isManage())
                JdbcHelper.executeUpdate(update_sql, e.getHo(), e.getTen(),e.getMatKhau(),
                                    e.getEmail(), e.getSoDienThoai(), e.getGioiTinh(), e.getDiaChi(), e.getHinhAnh(), e.getMaNhanVien());
            else
                JdbcHelper.executeUpdate(update_sql_admin, e.getHo(), e.getTen(), e.getMatKhau(),e.getEmail(), 
                    e.getSoDienThoai(), e.getGioiTinh(), e.getDiaChi(), e.isVaiTro(), e.getTrangThai(), e.getHinhAnh(), e.getMaNhanVien());
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Object id) {
        try {
            JdbcHelper.executeUpdate(delete_sql, id);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<NhanVien> selectALL() {
        return selectBySQL(selectALL_sql);
    }

    @Override
    public NhanVien selectByID(Object id) {
        List<NhanVien> list = selectBySQL(selectByID, id);
        return list.isEmpty() ? null:list.get(0);
    }

    @Override
    public List<NhanVien> selectBySQL(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet resultSet = JdbcHelper.ExecuteQuery(sql, args);
            while(resultSet.next()){
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNhanVien((int) resultSet.getObject("MaNV"));
                nhanVien.setHo((String) resultSet.getObject("Ho"));
                nhanVien.setTen((String) resultSet.getObject("Ten"));
                nhanVien.setTenDangNhap((String) resultSet.getObject("TenDangNhap"));
                nhanVien.setMatKhau((String) resultSet.getObject("MatKhau"));
                nhanVien.setEmail((String) resultSet.getObject("Email"));
                nhanVien.setSoDienThoai((String) resultSet.getObject("SDT"));
                nhanVien.setGioiTinh((int) resultSet.getObject("GioiTinh"));
                nhanVien.setDiaChi((String) resultSet.getObject("DiaChi"));
                nhanVien.setVaiTro((boolean) resultSet.getObject("VaiTro"));
                nhanVien.setTrangThai((int) resultSet.getObject("TrangThai"));
                nhanVien.setHinhAnh((String) resultSet.getObject("HinhAnh"));
                list.add(nhanVien);
            }
            resultSet.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void restore(Object id) {
        try {
            JdbcHelper.executeUpdate(restore_sql, id);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    
    public void restoreAll() {
        try {
            JdbcHelper.executeUpdate(restoreAll_sql);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    
    public NhanVien selectByUserName(Object id) {
        List<NhanVien> list = selectBySQL(selectByUserName, id);
        return list.isEmpty() ? null:list.get(0);
    }
    
//    public NhanVien selectByMaNhanVien(Object maNhanVien) {
//        String selectByMaNhanVien_sql = "SELECT MaNV, Ho, Ten, TenDangNhap, MatKhau, Email, SDT, GioiTinh, DiaChi, VaiTro,"
//                + " TrangThai, HinhAnh FROM NhanVien WHERE is_Delete = 0 AND MaNV = ?";   
//        List<NhanVien> list = selectBySQL(selectByMaNhanVien_sql, maNhanVien);
//        return list.isEmpty() ? null : list.get(0);
//    }
    
    public List<NhanVien> selectNhanVienDaXoa(){
        String selectNhanVienDaXoa_sql = "SELECT MaNV, Ho, Ten, TenDangNhap, MatKhau, Email, SDT, GioiTinh, DiaChi, VaiTro, TrangThai, "
                + "HinhAnh FROM NhanVien WHERE is_Delete = 1"; 
        return selectBySQL(selectNhanVienDaXoa_sql);
    }
    
    public List<NhanVien> selectNhanVienNghiViec(){
        String selectNhanVienNghiViec_sql = "SELECT MaNV, Ho, Ten, TenDangNhap, MatKhau, Email, SDT, GioiTinh, DiaChi, VaiTro, TrangThai, "
                + "HinhAnh FROM NhanVien WHERE is_Delete = 0 AND TrangThai = 2"; 
        return selectBySQL(selectNhanVienNghiViec_sql);
    }
    
    public List<NhanVien> selectNhanVienNghiPhep(){
        String selectNhanVienNghiPhep_sql = "SELECT MaNV, Ho, Ten, TenDangNhap, MatKhau, Email, SDT, GioiTinh, DiaChi, VaiTro, TrangThai, "
                + "HinhAnh FROM NhanVien WHERE is_Delete = 0 AND TrangThai = 1"; 
        return selectBySQL(selectNhanVienNghiPhep_sql);
    }
    
    public List<NhanVien> selectBbyDeleted(Object id) {
        String selectNhanVienDaXoa_sql = "SELECT MaNV, Ho, Ten, TenDangNhap, MatKhau, Email, SDT, GioiTinh, DiaChi, VaiTro, TrangThai, "
                + "HinhAnh FROM NhanVien WHERE is_Delete = 1 AND MaNV = ?"; 
        return selectBySQL(selectNhanVienDaXoa_sql, id);
    }
}
