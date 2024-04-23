
package parking.controller;

import parking.ui.MatKhauMoiJDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewPassController implements ActionListener{
    private MatKhauMoiJDialog newPass;

    public NewPassController(MatKhauMoiJDialog newPass) {
        this.newPass = newPass;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String value = e.getActionCommand();
        
        if(value.equals("Hủy"))
            this.newPass.huy();
        else if(value.equals("Đổi mật khẩu"))
            this.newPass.doiPass();
    }
    
}
