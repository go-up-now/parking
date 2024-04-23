
package parking.controller;

import parking.ui.DangNhapJDiaLog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DangNhapController implements ActionListener{
    private DangNhapJDiaLog dangNhap;

    public DangNhapController(DangNhapJDiaLog dangNhap) {
        this.dangNhap = dangNhap;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String value = e.getActionCommand();
        if(value.equals("Đăng nhập")){
            this.dangNhap.dangNhap();
        } 
        else
            this.dangNhap.Huy();
    }
    
}
