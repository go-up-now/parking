
package parking.controller;

import parking.ui.DoiMatKhauJDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoiMatKhauController implements ActionListener{
    private DoiMatKhauJDialog doiMatKhau;

    public DoiMatKhauController(DoiMatKhauJDialog dmk) {
        this.doiMatKhau = dmk;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String value = e.getActionCommand();
        
        if(value.equals("Đồng ý"))
            this.doiMatKhau.doiPass();
        else if(value.equals("Hủy"))
            this.doiMatKhau.huy();
    }
    
}
