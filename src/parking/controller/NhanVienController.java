
package parking.controller;

import parking.ui.NhanVienJDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NhanVienController implements ActionListener{
    private final NhanVienJDialog nhanVien;

    public NhanVienController(NhanVienJDialog nhanVien) {
        this.nhanVien = nhanVien;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String value = e.getActionCommand();
        switch (value) {
            case "Thêm" -> this.nhanVien.insert();
            case "Sửa" -> this.nhanVien.upDate();
            case "Xóa" -> this.nhanVien.delete();
            case "Mới" -> this.nhanVien.clearForm();
            case "|<" -> this.nhanVien.first();
            case "<<" -> this.nhanVien.previous();
            case ">>" -> this.nhanVien.next();
            case ">|" -> this.nhanVien.last();
            case "Khôi phục" -> this.nhanVien.restore();
            case "Đã xóa" -> this.nhanVien.fillToTableNhanVienDaXoa();
            case "Import Excel" -> this.nhanVien.ImportNhanVien();
            case "Nghỉ phép" -> this.nhanVien.fillToTableNhanVienNghiNghiPhep();
            case "Đang làm" -> this.nhanVien.fillToTable();
            case "Nghỉ việc" -> this.nhanVien.fillToTableNhanVienNghiViec();
            default -> {
            }
        }
    }
    
}
