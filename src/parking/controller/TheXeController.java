
package parking.controller;

import parking.ui.TheXeJDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class TheXeController implements ActionListener{
    private TheXeJDialog theXe;

    public TheXeController(TheXeJDialog theXe) {
        this.theXe = theXe;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String value = e.getActionCommand();
        
        switch (value) {
            case "Thêm" -> this.theXe.insert();
            case "Sửa" -> this.theXe.upDate();
            case "Xóa" -> this.theXe.delete();
            case "Mới" -> this.theXe.clearForm();
            case "|<" -> this.theXe.first();
            case "<<" -> this.theXe.previous();
            case ">>" -> this.theXe.next();
            case ">|" -> this.theXe.last();
            case "Thẻ xe cư dân" -> this.theXe.fillTableCuDan();
            case "Thẻ xe khách vãng lai" -> this.theXe.fillTableKhachVangLai();
            default -> {
            }
        }
    }
    
}
