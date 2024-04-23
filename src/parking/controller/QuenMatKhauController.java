
package parking.controller;

import parking.ui.QuenMatKhauJDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuenMatKhauController implements ActionListener{
    private QuenMatKhauJDialog quenMatKhau;

    public QuenMatKhauController(QuenMatKhauJDialog quenMatKhau) {
        this.quenMatKhau = quenMatKhau;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String value = e.getActionCommand();
        switch (value) {
            case "Lấy mã" -> this.quenMatKhau.layMa();
            case "Hủy" -> this.quenMatKhau.huy();
            case "Gửi thông tin" -> this.quenMatKhau.guiThongTin();
            default -> {
            }
        }
    }
    
}
