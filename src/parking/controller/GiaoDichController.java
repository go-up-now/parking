
package parking.controller;

import parking.ui.GiaoDichJDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GiaoDichController implements ActionListener{
    private GiaoDichJDialog giaoDich;

    public GiaoDichController(GiaoDichJDialog giaoDich) {
        this.giaoDich = giaoDich;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String value = e.getActionCommand();
        
        switch (value) {
            case "Thêm" -> this.giaoDich.insert();
            case "Sửa" -> this.giaoDich.update();
            case "Xóa" -> this.giaoDich.delete();
            case "Mới" -> {
                try {
                    this.giaoDich.clearForm();
                } catch (ParseException ex) {
                    Logger.getLogger(GiaoDichController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case "|<" -> this.giaoDich.first();
            case "<<" -> this.giaoDich.previous();
            case ">>" -> this.giaoDich.next();
            case ">|" -> this.giaoDich.last();
            case "Khôi phục" -> this.giaoDich.restore();
            case "Đã xóa" -> this.giaoDich.fillToTableGiaoDichDaXoa();
            case "Lấy xe" -> this.giaoDich.layXe();
            case "Đã lấy" -> this.giaoDich.fillTableLichSuGuiXe();
            case "Đang gửi" -> this.giaoDich.fillTable();
            case "Xuất excel" -> this.giaoDich.xuatDanhSachGiaoDich();
            default -> {
            }
        }
    }
  
}
