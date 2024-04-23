
package parking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NhanVien {
    private int maNhanVien;
    private String ho;
    private String ten;
    private String tenDangNhap;
    private String matKhau;
    private String Email;
    private String soDienThoai;
    private int gioiTinh;
    private String diaChi;
    private boolean vaiTro;
    private int trangThai;
    private String hinhAnh; 
  
}
