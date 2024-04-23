
package parking.controller;

import parking.ui.KhuGuiXeJDialog;
import parking.ui.TheXeJDialog;
import parking.ui.BangGiaJDialog;
import parking.ui.GiaoDichJDialog;
import parking.ui.ParkFrame;
import parking.ui.DoiMatKhauJDialog;
import parking.ui.NhanVienJDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ParkController implements ActionListener, MouseListener{
    private final ParkFrame park;

    public ParkController(ParkFrame park) {
        this.park = park;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String value = e.getActionCommand();
        
        switch (value) {
            case "Kết thúc" -> this.park.ketThuc();
            case "Đăng xuất" -> this.park.dangXuat();
            case "Nhân viên", "Quản lý nhân viên" -> new NhanVienJDialog().NhanVienJDialog();
            case "Giao dịch", "Quản lý giao dịch" -> new GiaoDichJDialog().chuyenDeJDialog();
            case "Quản lý thẻ xe", "Thẻ xe" -> new TheXeJDialog().TheXeJDialog();
            case "Đổi mật khẩu" -> new DoiMatKhauJDialog(null, true).setVisible(true);
            case "Bảng giá", "Quản lý bảng giá" -> new BangGiaJDialog().BangGiaJDialog();
            case "Quản lý khu gửi xe", "Khu gửi xe" -> new KhuGuiXeJDialog().KhuGuiXeJDialog();
            case "Lượng xe đã gửi" -> this.park.openThongKe(0);
            case "Doanh thu theo thời gian" -> this.park.openThongKe(1);
//            case "Điểm chuyên đề" -> new KhuGuiXeJDialog();
//            case "Doanh thu từng chuyên đề" -> this.park.openThongKe(3);
            case "Giới thiệu sản phẩm" -> this.park.gioiThieu();
            case "Hướng dẫn", "Hướng dẫn sử dụng" -> this.park.huongDan();
            default -> {
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.isPopupTrigger()){
            this.park.popupMenu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.isPopupTrigger()){
            this.park.popupMenu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
