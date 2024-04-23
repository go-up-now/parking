
package parking.ui;

import parking.utils.Authenticated;
import parking.utils.MessageBox;
import parking.utils.ImagesHelper;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import parking.controller.ParkController;


public class ParkFrame extends JFrame{
    public ParkController ac = new ParkController(this);
    
    public static JPanel CenterPanel;
    public static String user = "";
    private JLabel lblhinh;
    private JLabel lblTime;
    public JPopupMenu popupMenu;
    private JPanel CenterPanelTong;
    private JPanel CenterPanelUser;
    public static JLabel lblUser;
    public static JLabel lblVaiTro;
    private JPanel CenterPanelUser1;
    
    public ParkFrame(){
        initComponents();
        init();
    }

    private void initComponents() {
        // Panel
        CenterPanelTong = new JPanel(new BorderLayout());
        CenterPanelUser = new JPanel(new BorderLayout());
        CenterPanelUser1 = new JPanel(new GridLayout(1, 2, 10, 10));
        CenterPanel = new JPanel(new BorderLayout());
        JPanel bottomPanel = new JPanel(new BorderLayout());
        
        // Menubar
        JMenuBar menubar = new JMenuBar();
        JMenu mnuHeThong = new JMenu("Hệ thống");
        JMenu mnuQuanLy = new JMenu("Quản lý");
        JMenu mnuThongKe = new JMenu("Thống kê");
        JMenu mnuTroGiup = new JMenu("Trợ giúp");
        
        // Menu Item
        JMenuItem mniDangXuat = new JMenuItem("Đăng xuất");
        JMenuItem mniDoiMatKhau = new JMenuItem("Đổi mật khẩu");
        JMenuItem mniKetThuc = new JMenuItem("Kết thúc");
        
        JMenuItem mniNhanVien = new JMenuItem("Quản lý nhân viên");
        JMenuItem mniGiaoDich = new JMenuItem("Quản lý giao dịch");
        JMenuItem mniTheXe = new JMenuItem("Quản lý thẻ xe");
        JMenuItem mniBangGia = new JMenuItem("Quản lý bảng giá");
        JMenuItem mniKhuGuiXe = new JMenuItem("Quản lý khu gửi xe");
        JMenuItem mniLoaiXe = new JMenuItem("Quản lý loại xe");
        
        JMenuItem mniLuongXeDaGui = new JMenuItem("Lượng xe đã gửi");
        JMenuItem mniDoanhThu = new JMenuItem("Doanh thu theo thời gian");
        JMenuItem mniBangDiem = new JMenuItem("Bảng điểm");
        JMenuItem mniDiemChuyenDe = new JMenuItem("Điểm chuyên đề");
        
        JMenuItem mniHDSD = new JMenuItem("Hướng dẫn sử dụng");
        JMenuItem mniLienHe= new JMenuItem("Thông tin liên hệ");
        
        // Thêm icon vào jmenuItem
        mniDangXuat.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/Log out.png")));
        mniDangXuat.setAccelerator(KeyStroke.getKeyStroke("ctrl 0"));
        mniDoiMatKhau.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/Refresh.png")));
        mniKetThuc.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/Stop.png")));
        mniKetThuc.setAccelerator(KeyStroke.getKeyStroke("alt F4"));
        
        mniGiaoDich.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/Conference.png")));
        mniTheXe.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/Certificate.png")));
        mniBangGia.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/Numbered list.png")));
        mniNhanVien.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/User group.png")));
        mniKhuGuiXe.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/location.png")));
        mniLoaiXe.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/Sedan.png")));
        
        mniLuongXeDaGui.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/spaces.png")));
        mniBangDiem.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/Card file.png")));
        mniDiemChuyenDe.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/Bar chart.png")));
        mniDoanhThu.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/Dollar.png")));
        
        mniHDSD.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/Online.png")));
        mniHDSD.setAccelerator(KeyStroke.getKeyStroke("F1"));
        mniLienHe.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/Brick house.png")));
        
        // Thêm vào menubar
        menubar.add(mnuHeThong);
        menubar.add(mnuQuanLy);
        menubar.add(mnuThongKe);
        menubar.add(mnuTroGiup);
        
        // Thêm vào menu
        mnuHeThong.add(mniDangXuat);
        mnuHeThong.addSeparator();
        mnuHeThong.add(mniDoiMatKhau);
        mnuHeThong.addSeparator();
        mnuHeThong.add(mniKetThuc);
        
        mnuQuanLy.add(mniNhanVien);
        mnuQuanLy.add(mniGiaoDich);
        mnuQuanLy.add(mniTheXe);
        mnuQuanLy.add(mniBangGia);
        mnuQuanLy.add(mniKhuGuiXe);
//        mnuQuanLy.add(mniLoaiXe);
        
        mnuThongKe.add(mniLuongXeDaGui);
        mnuThongKe.addSeparator();
//        mnuThongKe.add(mniBangDiem);
//        mnuThongKe.add(mniDiemChuyenDe);
//        mnuThongKe.addSeparator();
        mnuThongKe.add(mniDoanhThu);
        
        mnuTroGiup.add(mniHDSD);
        mnuTroGiup.addSeparator();
        mnuTroGiup.add(mniLienHe);
        
        // user
        lblUser = new JLabel("User");
        lblUser.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/user1.png")));
        lblVaiTro = new JLabel("VaiTro");
        lblVaiTro.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/setting.png")));
        
        // JpopupMenu
        popupMenu = new JPopupMenu();
        JMenu menuHeThong = new JMenu("Hệ thống");
        menuHeThong.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/system.png")));
        JMenuItem mnitemDangXuat = new JMenuItem("Đăng xuất");
        mnitemDangXuat.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/Log out.png")));
        JMenuItem mnitemDoiPass= new JMenuItem("Đổi mật khẩu");
        mnitemDoiPass.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/Refresh.png")));
        JMenuItem mnitemKetThuc = new JMenuItem("Kết thúc");
        mnitemKetThuc.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/Stop.png")));
        
        JMenu menuQuanLy = new JMenu("Quản lý");
        menuQuanLy.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/manager.png")));
        JMenuItem mnitemNhanVien = new JMenuItem("Quản lý nhân viên");
        mnitemNhanVien.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/User group.png")));
        JMenuItem mnitemGiaoDich= new JMenuItem("Quản lý giao dịch");
        mnitemGiaoDich.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/Conference.png")));
        JMenuItem mnitemTheXe = new JMenuItem("Quản lý thẻ xe");
        mnitemTheXe.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/Certificate.png")));
        JMenuItem mnitemBangGia = new JMenuItem("Quản lý bảng giá");
        mnitemBangGia.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/Numbered list.png")));
        JMenuItem mnitemKhuGuiXe = new JMenuItem("Quản lý khu gửi xe");
        mnitemKhuGuiXe.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/location.png")));
        JMenuItem mnitemLoaiXe = new JMenuItem("Quản lý loại xe");
        mnitemLoaiXe.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/Sedan.png")));
        
        JMenu menuThongKe = new JMenu("Thống kê");
        menuThongKe.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/statistical.png")));
        JMenuItem mnitemLuongXeDaGui = new JMenuItem("Lượng xe đã gửi");
        mnitemLuongXeDaGui.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/spaces.png")));
        JMenuItem mnitemBangDiem= new JMenuItem("Bảng điểm");
        mnitemBangDiem.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/Card file.png")));
        JMenuItem mnitemDiemCD = new JMenuItem("Điểm chuyên đề");
        mnitemDiemCD.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/Bar chart.png")));
        JMenuItem mnitemDoanhThu = new JMenuItem("Doanh thu theo thời gian");
        mnitemDoanhThu.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/Dollar.png")));
        
        JMenu menuTroGiup = new JMenu("Trợ giúp");
        menuTroGiup.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/Help.png")));
        JMenuItem mnitemHDSD = new JMenuItem("Hướng dẫn sử dụng");
        mnitemHDSD.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/Online.png")));
        JMenuItem mnitemLienHe= new JMenuItem("Thông tin liên hệ");
        mnitemLienHe.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/Brick house.png")));
        
        // Thêm popup menu vào
        menuHeThong.add(mnitemDangXuat);
        menuHeThong.addSeparator();
        menuHeThong.add(mnitemDoiPass);
        menuHeThong.addSeparator();
        menuHeThong.add(mnitemKetThuc);
        
        menuQuanLy.add(mnitemNhanVien);
        menuQuanLy.add(mnitemGiaoDich);
        menuQuanLy.add(mnitemTheXe);
        menuQuanLy.add(mnitemBangGia);
        menuQuanLy.add(mnitemKhuGuiXe);
//        menuQuanLy.add(mnitemLoaiXe);
        
        menuThongKe.add(mnitemLuongXeDaGui);
        menuThongKe.addSeparator();
//        menuThongKe.add(mnitemBangDiem);
//        menuThongKe.add(mnitemDiemCD);
//        menuThongKe.addSeparator();
        menuThongKe.add(mnitemDoanhThu);
        
        menuTroGiup.add(mnitemHDSD);
        menuTroGiup.addSeparator();
        menuTroGiup.add(mnitemLienHe);
        
        popupMenu.add(menuHeThong);
        popupMenu.addSeparator();
        popupMenu.add(menuQuanLy);
        popupMenu.add(menuThongKe);
        popupMenu.addSeparator();
        popupMenu.add(menuTroGiup);
        
        // Tool bar
        JToolBar toolbar = new JToolBar();
        
        // Button
        JButton btnDangXuat = new JButton("Đăng xuất");
        btnDangXuat.setFocusable(false);
        btnDangXuat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDangXuat.setMargin(new java.awt.Insets(2, 22, 2, 22));
        btnDangXuat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        
        JButton btnKetThuc = new JButton("Kết thúc");
        btnKetThuc.setFocusable(false);
        btnKetThuc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnKetThuc.setMargin(new java.awt.Insets(2, 22, 2, 22));
        btnKetThuc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        
        JButton btnNhanVien = new JButton("Nhân viên");
        btnNhanVien.setFocusable(false);
        btnNhanVien.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNhanVien.setMargin(new java.awt.Insets(2, 22, 2, 22));
        btnNhanVien.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        
        JButton btnGiaoDich = new JButton("Giao dịch");
        btnGiaoDich.setFocusable(false);
        btnGiaoDich.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGiaoDich.setMargin(new java.awt.Insets(2, 22, 2, 22));
        btnGiaoDich.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        
        JButton btnTheXe = new JButton("Thẻ xe");
        btnTheXe.setFocusable(false);
        btnTheXe.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTheXe.setMargin(new java.awt.Insets(2, 22, 2, 22));
        btnTheXe.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        
        JButton btnBangGia= new JButton("Bảng giá");
        btnBangGia.setFocusable(false);
        btnBangGia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBangGia.setMargin(new java.awt.Insets(2, 22, 2, 22));
        btnBangGia.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        
        JButton btnKhuGuiXe = new JButton("Khu gửi xe");
        btnKhuGuiXe.setFocusable(false);
        btnKhuGuiXe.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnKhuGuiXe.setMargin(new java.awt.Insets(2, 22, 2, 22));
        btnKhuGuiXe.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        
        JButton btnLoaiXe = new JButton("Loại xe");
        btnLoaiXe.setFocusable(false);
        btnLoaiXe.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLoaiXe.setMargin(new java.awt.Insets(2, 22, 2, 22));
        btnLoaiXe.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        
        JButton btnHuongDan = new JButton("Hướng dẫn");
        btnHuongDan.setFocusable(false);
        btnHuongDan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnHuongDan.setMargin(new java.awt.Insets(2, 22, 2, 22));
        btnHuongDan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        
        // Thêm icon vào button tool bar
        btnDangXuat.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/Log out.png")));
        btnKetThuc.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/Stop.png")));
        btnNhanVien.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/User group.png")));
        btnGiaoDich.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/Conference.png")));
        btnTheXe.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/Certificate.png")));
        btnBangGia.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/Numbered list.png")));
        btnKhuGuiXe.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/location.png")));
        btnLoaiXe.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/Sedan.png")));
        btnHuongDan.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/Online.png")));
        
        // Thêm vào tool bar
        toolbar.add(btnDangXuat);
        toolbar.add(btnKetThuc);
        toolbar.addSeparator();
        toolbar.add(btnNhanVien);
        toolbar.add(btnGiaoDich);
        toolbar.add(btnTheXe);
        toolbar.add(btnBangGia);
        toolbar.add(btnKhuGuiXe);
//        toolbar.add(btnLoaiXe);
        toolbar.addSeparator();
        toolbar.add(btnHuongDan);
        
        // center
        lblhinh = new JLabel();
        lblhinh.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/car-parking.png")));
        lblhinh.setHorizontalAlignment(JLabel.CENTER);   
        
        // Footer
        JLabel lblQuanLyGuiXe = new JLabel("Hệ thống quản lý gửi xe");
        lblQuanLyGuiXe.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/Home.png")));
        lblTime = new JLabel("Clock");
        lblTime.setIcon(new ImageIcon(ImagesHelper.getImage("/parking/icon/Alarm.png")));

        // Gán listener vào button
        btnDangXuat.addActionListener(ac);
        mniDangXuat.addActionListener(ac);
        btnKetThuc.addActionListener(ac);
        mniKetThuc.addActionListener(ac);
        btnGiaoDich.addActionListener(ac);
        mniGiaoDich.addActionListener(ac);
        btnTheXe.addActionListener(ac);
        mniTheXe.addActionListener(ac);
        mniDoiMatKhau.addActionListener(ac);
        btnBangGia.addActionListener(ac);
        mniBangGia.addActionListener(ac);
        btnKhuGuiXe.addActionListener(ac);
        mniKhuGuiXe.addActionListener(ac);
        btnNhanVien.addActionListener(ac);
        mniNhanVien.addActionListener(ac);
        btnLoaiXe.addActionListener(ac);
        mniLoaiXe.addActionListener(ac);
        mniLuongXeDaGui.addActionListener(ac);
        mniBangDiem.addActionListener(ac);
        mniDiemChuyenDe.addActionListener(ac);
        mniDoanhThu.addActionListener(ac);
        mniLienHe.addActionListener(ac);
        btnHuongDan.addActionListener(ac);
        mniHDSD.addActionListener(ac);
        this.addMouseListener(ac);
        
        // Gán sự kiện popup menu
        mnitemDangXuat.addActionListener(ac);
        mnitemDoiPass.addActionListener(ac);
        mnitemKetThuc.addActionListener(ac);
        mnitemNhanVien.addActionListener(ac);
        mnitemGiaoDich.addActionListener(ac);
        mnitemTheXe.addActionListener(ac);
        mnitemBangGia.addActionListener(ac);
        mnitemKhuGuiXe.addActionListener(ac);
        mnitemLoaiXe.addActionListener(ac);
        mnitemLuongXeDaGui.addActionListener(ac);
        mnitemBangDiem.addActionListener(ac);
        mnitemDiemCD.addActionListener(ac);
        mnitemDoanhThu.addActionListener(ac);
        mnitemHDSD.addActionListener(ac);
        mnitemLienHe.addActionListener(ac);
        
        // set background
        menubar.setBackground(Color.getHSBColor(250, 206, 155));
        toolbar.setBackground(Color.getHSBColor(250, 206, 155));
        CenterPanelUser.setBackground(Color.getHSBColor(250, 206, 156));
        bottomPanel.setBackground(Color.getHSBColor(250, 206, 155));
        
        // Thêm vào panel
        bottomPanel.add(lblQuanLyGuiXe, BorderLayout.WEST);
        bottomPanel.add(lblTime, BorderLayout.EAST);
        
        this.setJMenuBar(menubar);
        this.add(popupMenu);
        this.setLayout(new BorderLayout());
        this.add(toolbar, BorderLayout.NORTH);
        this.add(bottomPanel, BorderLayout.SOUTH);
        CenterPanelUser1.add(lblVaiTro);
        CenterPanelUser1.add(lblUser);
        CenterPanel.add(lblhinh);
        CenterPanelUser.add(CenterPanelUser1, BorderLayout.EAST);
        CenterPanelTong.add(CenterPanelUser, BorderLayout.NORTH);
        CenterPanelTong.add(CenterPanel, BorderLayout.CENTER);
        this.add(CenterPanelTong, BorderLayout.CENTER);
        
    }

    private void init() {
        this.setTitle("HỆ THỐNG QUẢN LÝ GỬI XE");
        this.setSize(1280, 685);
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setIconImage(ImagesHelper.getImage("/parking/icon/parking (1).png"));
        
        // đồng hồ
        StartDongHo();
        
        new ChaoJDiaLog(this, true).setVisible(true);
        new DangNhapJDiaLog(this, true).setVisible(true);
    }
    
    public void dangXuat() {
        if(MessageBox.confirm(this, "Bạn thực sự muốn đăng xuất?")){
            ParkFrame.user = Authenticated.user.getTenDangNhap();
            Authenticated.clear();
            this.dispose();
            new DangNhapJDiaLog(this, true).setVisible(true);
            CenterPanel.removeAll();
            CenterPanel.add(lblhinh);
            this.setVisible(true);
        }
    }
    
    public void ketThuc() {
        if(MessageBox.confirm(this, "Bạn thực sự muốn thoát?"))
            System.exit(0);
    }
    
    private void StartDongHo() {
        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss a");
                lblTime.setText(sdf.format(new Date()));
            }
        }).start();
    }
    
    public void gioiThieu(){
        new GioiThieuSP(this, true).setVisible(true);
    }
    
    public void huongDan(){
        try {
//            Desktop.getDesktop().browse(new URI("https://caodang.fpt.edu.vn/"));
            Desktop.getDesktop().browse(new File("help/home.html").toURI());

        } catch (IOException ex) {
           MessageBox.alert(this, "Không tìm thấy Web hướng dẫn!");
        }
    }
    
    public void openThongKe( int index) {
        if(Authenticated.isLogin()){
            if(index == 1 && !Authenticated.isManage())
                MessageBox.alert(this, "Bạn không có quyền xem thông tin doanh thu!");
            else{
               ThongKeJDialog thongKe = new ThongKeJDialog();
               thongKe.thongKeJDialog();
               thongKe.tabIndex(index);
            }
        }
        else
            MessageBox.alert(this, "Vui lòng đăng nhập");
    }

    public static void main(String[] args) {
        new ParkFrame().setVisible(true);
    }
}
