
package parking.controller;

import parking.ui.BangGiaJDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BangGiaController implements ActionListener{
    private BangGiaJDialog bangGia;

    public BangGiaController(BangGiaJDialog bangGia) {
        this.bangGia = bangGia;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String value = e.getActionCommand();
        
        switch (value) {
            case "Thêm" -> this.bangGia.insert();
            case "Sửa" -> this.bangGia.upDate();
            case "Xóa" -> this.bangGia.delete();
            case "Mới" -> this.bangGia.clearForm();
            case "|<" -> this.bangGia.first();
            case "<<" -> this.bangGia.previous();
            case ">>" -> this.bangGia.next();
            case ">|" -> this.bangGia.last();
            default -> {}
        }
    }
    
}
