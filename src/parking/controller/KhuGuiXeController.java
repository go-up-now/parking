
package parking.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import parking.ui.KhuGuiXeJDialog;

public class KhuGuiXeController implements ActionListener{
    private KhuGuiXeJDialog khuGuiXe;

    public KhuGuiXeController(KhuGuiXeJDialog khuGuiXe) {
        this.khuGuiXe = khuGuiXe;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String value = e.getActionCommand();
        
        switch (value) {
            case "Thêm" -> this.khuGuiXe.insert();
            case "Sửa" -> this.khuGuiXe.upDate();
            case "Xóa" -> this.khuGuiXe.delete();
            case "Mới" -> this.khuGuiXe.clearForm();
            case "|<" -> this.khuGuiXe.first();
            case "<<" -> this.khuGuiXe.previous();
            case ">>" -> this.khuGuiXe.next();
            case ">|" -> this.khuGuiXe.last();
        }
    }
    
}
