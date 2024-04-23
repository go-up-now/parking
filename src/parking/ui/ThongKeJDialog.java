
package parking.ui;

import parking.controller.ThongKeController;
import parking.dao.GiaoDichDAO;
import parking.dao.KhuGuiXeDAO;
import parking.dao.ThongKeDAO;
import parking.entity.KhuGuiXe;
import parking.utils.Authenticated;
import parking.utils.DateHelper;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import parking.entity.ThongKe;

public class ThongKeJDialog {
    ThongKeController ac = new ThongKeController(this);
    ThongKeDAO thongKeDAO = new ThongKeDAO();
    KhuGuiXeDAO khuGuiXeDAO = new KhuGuiXeDAO();
    
    private  JTable tblDoanhThu;
    private JTable tblLuongXeGui;
    private JTabbedPane tabsThongKe;
    private JComboBox cboKhuGuiXe;
    private JComboBox cboLuongXeKhachHang;
    private JComboBox cboLuongXeNam;
    private JComboBox cboLuongXeThang;
    private JComboBox cboDoanhThuKhachHang;
    private JDateChooser dcsDateStart;
    private JDateChooser dcsDateEnd;
    
    public void thongKeJDialog() {
        Components();
        init();
    }

    public void Components() {
        // Tạo tabbed panel
        tabsThongKe = new JTabbedPane();
        
        // Tạo panel
        JPanel panelLuongXeGui = new JPanel(new BorderLayout());     // Nếu ko có "new borderlayout()" thì size sẽ thu nhỏ lại, ngược lại thì size full cửa sổ
        JPanel panelLuongXeGuiHead = new JPanel(new GridLayout(1, 2, 200, 200));
        JPanel panelLuongXeComBoBoxLeft = new JPanel(new GridLayout(2,2));
        JPanel panelLuongXeComBoBoxRight = new JPanel(new GridLayout(2,2));
        
        JPanel panelDoanhThu = new JPanel(new BorderLayout());
        JPanel panelDoanhThuHead = new JPanel(new GridLayout(2,1));
        JPanel panelDoanhThuKhachHangHead = new JPanel(new GridLayout(1, 4));
        JPanel panelDoanhThuThoiGianHead = new JPanel(new GridLayout(1, 5, 100, 100));
        
        // Font
        Font font = new Font("Arial", Font.BOLD, 18);
        
        // Cập nhật - Label
        JLabel lblThongKe = new JLabel("THỐNG KÊ");
        JLabel lblKH = new JLabel("KHÓA HỌC");
        JLabel lblDoanhThuKhachHang = new JLabel("Khách hàng");
        JLabel lblKhuGuiXe = new JLabel("KHU GỬI XE");
        JLabel lblKhachHang = new JLabel("Khách hàng");
        JLabel lblLuongXeNam = new JLabel("Năm");
        JLabel lblLuongXeThang = new JLabel("Tháng");
        JLabel lblDateStart = new JLabel("Từ ngày (dd/MM/yyyy)");
        JLabel lblDateEnd = new JLabel("Đến ngày (dd/MM/yyyy)");
        
        // Combobox
         cboDoanhThuKhachHang = new JComboBox();
         cboDoanhThuKhachHang.addItem("Cư dân");
         cboDoanhThuKhachHang.addItem("Khách vãng lai");
         cboKhuGuiXe = new JComboBox();
         cboLuongXeKhachHang = new JComboBox();
         cboLuongXeKhachHang.addItem("Cư dân");
         cboLuongXeKhachHang.addItem("Khách vãng lai");
         cboLuongXeNam = new JComboBox();
         cboLuongXeThang = new JComboBox();
         
         cboLuongXeKhachHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillComBoBoxLuongXeNam();
                fillTableLuongXeGuiNam();
            }
         });
         
        cboKhuGuiXe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillComBoBoxLuongXeNam();
            }
         });
        
        cboLuongXeNam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                fillComBoBoxLuongXeThang();
                if(cboLuongXeNam.getSelectedIndex() != 0){
                    fillComBoBoxLuongXeThang();
                    fillTableLuongXeGuiThang();
                }
                else 
                    fillTableLuongXeGuiNam();
            }
         });

        cboLuongXeThang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cboLuongXeThang.getSelectedIndex() != 0)
                    fillTableLuongXeGuiNgay();
                else if(cboLuongXeNam.getSelectedIndex() != 0){
                    fillTableLuongXeGuiThang();
                } else
                    fillTableLuongXeGuiNam();
            }
         });
        
        // JCalendar
        dcsDateStart = new JDateChooser();
        dcsDateStart.setDateFormatString("dd/MM/yyyy");
        try {
            dcsDateStart.setDate(DateHelper.StringtoDate("01/01/2019", "dd/MM/yyyy"));
        } catch (ParseException ex) {
            Logger.getLogger(ThongKeJDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        dcsDateEnd = new JDateChooser();
        dcsDateEnd.setDateFormatString("dd/MM/yyyy");
        dcsDateEnd.setDate(new Date());
        JButton btnLoc = new JButton("Lọc");

        // Scroll
        JScrollPane scrollKhuGuiXe = new JScrollPane(cboKhuGuiXe);
        JScrollPane scrollLuongXeGui = new JScrollPane();
        JScrollPane scrollDoanhThu = new JScrollPane();
        
        // Setting 
        lblThongKe.setFont(font);
        lblThongKe.setForeground(Color.blue);
        lblThongKe.setHorizontalAlignment(JLabel.CENTER);
        
        // đặt giá trị thuốc tính cho jcombobox để xử lý sự kiện
        cboKhuGuiXe.setActionCommand("cboKhuGuiXe");
        cboDoanhThuKhachHang.setActionCommand("cboDoanhThuKhachHang");
        
        // DANH SÁCH LƯỢNG XE GỬI
        tblLuongXeGui = new JTable();
        tblLuongXeGui.setRowHeight(22);
        tblLuongXeGui.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "THỜI GIAN", "SỐ XE GỬI", "THỜI GIAN VÀO ĐẦU TIÊN", "THỜI GIAN VÀO CUỐI CÙNG"
            }
        ){
            Boolean[] canEdit = new Boolean[]{
                false,false,false,false
            };

            @Override
            public boolean isCellEditable(int row, int column) {
                return canEdit[column];
            }
        });
        scrollLuongXeGui.setViewportView(tblLuongXeGui);
        
        // DANH SÁCH DOANH THU
        tblDoanhThu = new JTable();
        tblDoanhThu.setRowHeight(22);
        tblDoanhThu.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "KHU GỬI XE", "SỐ LOẠI XE GỬI", "SỐ XE GỬI", "DOANH THU", "DOANH THU THẤP NHẤT", "DOANH THU CAO NHẤT", "DOANH THU TRUNG BÌNH"
            }
        ){
            Boolean[] canEdit = new Boolean[]{
                false,false,false,false,false,false,false
            };

            @Override
            public boolean isCellEditable(int row, int column) {
                return canEdit[column];
            }
        });
        scrollDoanhThu.setViewportView(tblDoanhThu);
        
        // Actionlistener
        cboKhuGuiXe.addActionListener(ac);
        cboDoanhThuKhachHang.addActionListener(ac);
        btnLoc.addActionListener(ac);
        
        // Thêm vào tabbed panel
        tabsThongKe.add("LƯỢNG XE GỬI", panelLuongXeGui);
        tabsThongKe.add("DOANH THU", panelDoanhThu);

        if(!Authenticated.isManage()){
            tabsThongKe.remove(1);
        }
        
        // Thêm components vào panel
        panelLuongXeComBoBoxLeft.add(lblKhuGuiXe);
        panelLuongXeComBoBoxLeft.add(scrollKhuGuiXe);
        panelLuongXeComBoBoxLeft.add(lblLuongXeNam);
        panelLuongXeComBoBoxLeft.add(cboLuongXeNam);
        
        panelLuongXeComBoBoxRight.add(lblKhachHang);
        panelLuongXeComBoBoxRight.add(cboLuongXeKhachHang);
        panelLuongXeComBoBoxRight.add(lblLuongXeThang);
        panelLuongXeComBoBoxRight.add(cboLuongXeThang);
        
        panelLuongXeGuiHead.add(panelLuongXeComBoBoxLeft);
        panelLuongXeGuiHead.add(panelLuongXeComBoBoxRight);
        panelLuongXeGui.add(panelLuongXeGuiHead, BorderLayout.NORTH);
        panelLuongXeGui.add(scrollLuongXeGui);
        
        panelDoanhThuKhachHangHead.add(lblDoanhThuKhachHang);
        panelDoanhThuKhachHangHead.add(cboDoanhThuKhachHang);
        panelDoanhThuKhachHangHead.add(new JLabel());
        panelDoanhThuKhachHangHead.add(new JLabel());
        panelDoanhThuThoiGianHead.add(lblDateStart);
        panelDoanhThuThoiGianHead.add(dcsDateStart);
        panelDoanhThuThoiGianHead.add(lblDateEnd);
        panelDoanhThuThoiGianHead.add(dcsDateEnd);
        panelDoanhThuThoiGianHead.add(btnLoc);
        
        panelDoanhThuHead.add(panelDoanhThuKhachHangHead);
        panelDoanhThuHead.add(panelDoanhThuThoiGianHead);
        panelDoanhThu.add(panelDoanhThuHead, BorderLayout.NORTH);
        panelDoanhThu.add(scrollDoanhThu, BorderLayout.CENTER);

        ParkFrame.CenterPanel.removeAll();
        ParkFrame.CenterPanel.add(lblThongKe, BorderLayout.NORTH);
        ParkFrame.CenterPanel.add(tabsThongKe, BorderLayout.CENTER);
        ParkFrame.CenterPanel.setVisible(false);
        ParkFrame.CenterPanel.setVisible(true);
    }
    

    public void init(){
        fillComBoBoxKhuGuiXe();
        fillTableDoanhThu(); 
    }
    
    public void tabIndex(int index){
        tabsThongKe.setSelectedIndex(index);
    }
    
    private void fillComBoBoxKhuGuiXe() {
        DefaultComboBoxModel KhuGuiXemodel = (DefaultComboBoxModel) cboKhuGuiXe.getModel();
        KhuGuiXemodel.removeAllElements();
        List<KhuGuiXe> list = khuGuiXeDAO.selectALL();
        for (KhuGuiXe khuGuiXe : list) {
            KhuGuiXemodel.addElement(khuGuiXe);
        }
        fillComBoBoxLuongXeNam();
        fillTableLuongXeGuiNam();
    }
    
    private void fillComBoBoxLuongXeNam() {
        DefaultComboBoxModel luongXeNamModel = (DefaultComboBoxModel) cboLuongXeNam.getModel();
        luongXeNamModel.removeAllElements();
        luongXeNamModel.addElement("Chọn năm!");
        KhuGuiXe khuGuiXe = (KhuGuiXe) cboKhuGuiXe.getSelectedItem();
        
        if(cboLuongXeKhachHang.getSelectedIndex() == 0) {
            List<ThongKe> list = thongKeDAO.selectCuDanNamByMaKhu(khuGuiXe.getMaKhu());
            for (ThongKe thongKe : list) {
                luongXeNamModel.addElement(thongKe);
            }
        } else {
            List<ThongKe> list = thongKeDAO.selectVangLaiNamByMaKhu(khuGuiXe.getMaKhu());
            for (ThongKe thongKe : list) {
                luongXeNamModel.addElement(thongKe);
            }
        }
         fillComBoBoxLuongXeThang();
    }
    
    private void fillComBoBoxLuongXeThang() {
        DefaultComboBoxModel luongXeThangModel = (DefaultComboBoxModel) cboLuongXeThang.getModel();
        luongXeThangModel.removeAllElements();
        luongXeThangModel.addElement("Chọn tháng!");

        List<ThongKe> list;
        KhuGuiXe khuGuiXe = (KhuGuiXe) cboKhuGuiXe.getSelectedItem();
            if(cboLuongXeKhachHang.getSelectedIndex() == 0){
                if(cboLuongXeNam.getSelectedIndex() != 0){
                ThongKe thongKe = (ThongKe) cboLuongXeNam.getSelectedItem();
                    if(thongKe != null){
                        list = thongKeDAO.selectCuDanThangByMaKhu(khuGuiXe.getMaKhu(), thongKe.getNam());
                        for (ThongKe luongXeGui : list) {
                            luongXeThangModel.addElement(luongXeGui.toStringThang());
                        }
                    }
                }
            } else{
                if(cboLuongXeNam.getSelectedIndex() != 0){
                ThongKe thongKe = (ThongKe) cboLuongXeNam.getSelectedItem();
                    if(thongKe != null){
                        list = thongKeDAO.selectVangLaiThangByMaKhu(khuGuiXe.getMaKhu(), thongKe.getNam());
                        for (ThongKe luongXeGui : list) {
                            luongXeThangModel.addElement(luongXeGui.toStringThang());
                        }
                    }
                }
            }
    }
    
    public void fillTableLuongXeGuiNam() {
        DefaultTableModel LuongXeGuimodel = (DefaultTableModel) tblLuongXeGui.getModel();
        LuongXeGuimodel.setRowCount(0);
        List<Object[]> list;
        KhuGuiXe khuGuiXe = (KhuGuiXe) cboKhuGuiXe.getSelectedItem();
            if(cboLuongXeKhachHang.getSelectedIndex() == 0)
                list = thongKeDAO.getLuongXeCuDanGuiNam(khuGuiXe.getMaKhu());
            else
                list = thongKeDAO.getLuongXeKhachVangLaiGuiNam(khuGuiXe.getMaKhu());

            for (Object[] luongXeGui : list) {
                LuongXeGuimodel.addRow(luongXeGui);
            }
    }
    
    public void fillTableLuongXeGuiThang() {
        DefaultTableModel LuongXeGuimodel = (DefaultTableModel) tblLuongXeGui.getModel();
        LuongXeGuimodel.setRowCount(0);
        List<Object[]> list;
        KhuGuiXe khuGuiXe = (KhuGuiXe) cboKhuGuiXe.getSelectedItem();
            if(cboLuongXeKhachHang.getSelectedIndex() == 0){
                ThongKe thongKe = (ThongKe) cboLuongXeNam.getSelectedItem();
                if(thongKe != null){
                    list = thongKeDAO.getLuongXeCuDanGuiThang(khuGuiXe.getMaKhu(), thongKe.getNam());
                    for (Object[] luongXeGui : list) {
                        LuongXeGuimodel.addRow(luongXeGui);
                    }
                }
            }else{
                ThongKe thongKe = (ThongKe) cboLuongXeNam.getSelectedItem();
                if(thongKe != null){
                    list = thongKeDAO.getLuongXeKhachVangLaiGuiThang(khuGuiXe.getMaKhu(), thongKe.getNam());
                    for (Object[] luongXeGui : list) {
                        LuongXeGuimodel.addRow(luongXeGui);
                    }
                }
            }
    }
    
    public void fillTableLuongXeGuiNgay() {
        DefaultTableModel LuongXeGuimodel = (DefaultTableModel) tblLuongXeGui.getModel();
        LuongXeGuimodel.setRowCount(0);
        List<Object[]> list;
        KhuGuiXe khuGuiXe = (KhuGuiXe) cboKhuGuiXe.getSelectedItem();

            if(cboLuongXeKhachHang.getSelectedIndex() == 0){
                if(cboLuongXeNam.getSelectedIndex() != 0){
                ThongKe thongKeNam = (ThongKe) cboLuongXeNam.getSelectedItem();
                    if(thongKeNam != null) {
                        if(cboLuongXeThang.getSelectedIndex() != 0){
                        String thang = (String)cboLuongXeThang.getSelectedItem();
                            if(thang != null && !thang.isEmpty()){
                            list = thongKeDAO.getLuongXeCuDanGuiNgay(khuGuiXe.getMaKhu(), thongKeNam.getNam(), Integer.parseInt(thang));
                                for (Object[] luongXeGui : list) {
                                LuongXeGuimodel.addRow(luongXeGui);
                                }
                            }
                        }
                    }
                }
            }else{
                if(cboLuongXeNam.getSelectedIndex() != 0){
                ThongKe thongKeNam = (ThongKe) cboLuongXeNam.getSelectedItem();
                    if(thongKeNam != null) {
                        if(cboLuongXeThang.getSelectedIndex() != 0){
                        String thang = (String)cboLuongXeThang.getSelectedItem();
                            if(thang != null && !thang.isEmpty()){
                            list = thongKeDAO.getLuongXeKhachVangLaiGuiNgay(khuGuiXe.getMaKhu(), thongKeNam.getNam(), Integer.parseInt(thang));
                                for (Object[] luongXeGui : list) {
                                LuongXeGuimodel.addRow(luongXeGui);
                                }
                            }
                        }
                    }
                }
            }
    }

//    private void fillComBoBoxDoanhThuNam() {
//        DefaultComboBoxModel namModel = (DefaultComboBoxModel) cboDoanhThuNam.getModel();
//        namModel.removeAllElements();
//        List<Integer> list = giaoDichDAO.selectYearGiaoDich();
//        for (Integer nam : list) {
//            namModel.addElement(nam);
//        }
//    }

    public void fillTableDoanhThu() {
        DefaultTableModel doanhThuModel = (DefaultTableModel) tblDoanhThu.getModel();
        doanhThuModel.setRowCount(0);
        int khachHang =  (int) cboDoanhThuKhachHang.getSelectedIndex();
        if(khachHang == 0){
            List<Object[]> list = thongKeDAO.getDoanhThuCuDan(dcsDateStart.getDate(), dcsDateEnd.getDate());
            for (Object[] doanhThu : list) {
                doanhThuModel.addRow(doanhThu);
            }
        }
        else {
            List<Object[]> list = thongKeDAO.getDoanhThuKhachVangLai(dcsDateStart.getDate(), dcsDateEnd.getDate());
            for (Object[] doanhThu : list) {
                doanhThuModel.addRow(doanhThu);
            }
        }
    }
}
