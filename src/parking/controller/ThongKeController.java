
package parking.controller;

import parking.ui.ThongKeJDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThongKeController implements ActionListener{
    private ThongKeJDialog tk;

    public ThongKeController(ThongKeJDialog tk) {
        this.tk = tk;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String value = e.getActionCommand();
        
        if(value.equals("cboKhuGuiXe"))
            this.tk.fillTableLuongXeGuiNam();
        else if(value.equals("cboDoanhThuKhachHang") || value.equals("Lọc")){
            this.tk.fillTableDoanhThu();
        } 
        
    }
    
}
