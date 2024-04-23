
package parking.ui;

import parking.controller.NhanVienController;
import parking.dao.NhanVienDAO;
import parking.entity.NhanVien;
import parking.utils.Authenticated;
import parking.utils.ImagesHelper;
import parking.utils.MessageBox;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import parking.utils.En_DecodeHelper;

public class NhanVienJDialog {
    NhanVienDAO nhanVienDAO = new NhanVienDAO();
    private DefaultTableModel tableModel;
    NhanVienController ac = new NhanVienController(this);
    int row = -1;
    JFileChooser fileChooser = new JFileChooser();
    String maNhanVien = null;
    
    private static JTable tblDanhSach;
    private static JButton btnThem;
    private static JButton btnSua;
    private static JButton btnXoa;
    private static JButton btnMoi;
    private static JButton btnFirst;
    private static JButton btnPre;
    private static JButton btnNext;
    private static JButton btnLast;
    private  JTextField txtEmail;
    private  JPasswordField txtMatKhau;
    private  JPasswordField txtMatKhau2;
    private  JTextField txtHoDem;
    private  JTextField txtTen;
    private  JTextField txtTenDangNhap;
    private  JTextField txtSoDienThoai;
    private  JTextField txtDiaChi;
    private  JTextField txtMaNhanVien;
    private JRadioButton rdoAdmin;
    private JRadioButton rdoNhanVien;
    private JComboBox cboGioiTinh;
    private JComboBox cboTrangThai;
    private JButton btnRestore;
    private JLabel lblHoDem_ThongBao;
    private JLabel lblTen_ThongBao;
    private JLabel lblTenDangNhap_ThongBao;
    private JLabel lblMatKhau_ThongBao;
    private JLabel lblEmail_ThongBao;
    private JLabel lblSoDienThoai_ThongBao;
    private JLabel lblMatKhau2_ThongBao;
    private JButton btnImportNhanVienFromExcel;
    private JLabel lblHinh;
    private int clear = 0;

    public void NhanVienJDialog() {
        Componensts();
        init();
    }
    private JButton btnNghiPhep;
    private JButton btnDangLam;
    private JButton btnNghiViec;
    private JButton btnDaXoa;

    private void Componensts() {
        // Tạo panel
        JPanel panelNhanVien = new JPanel(new GridLayout(1, 2, 10, 10));
        JPanel LeftPanel = new JPanel(new BorderLayout());
        JPanel LeftPanelChung = new JPanel(new GridLayout(1, 2));
        JPanel LeftPanelComponents1 = new JPanel(new GridLayout(12, 1));
        JPanel LeftPanelComponents2 = new JPanel(new GridLayout(2, 1));
        JPanel LeftPanelComponents2_1 = new JPanel(new GridLayout(3, 1));
        JPanel LeftPanerole = new JPanel(new GridLayout(1, 2));
        JPanel LeftPanelButton1 = new JPanel(new GridLayout(1,4,10,10));
        JPanel LeftPanelButton2 = new JPanel(new GridLayout(1,4,10,10));
        JPanel LeftPanelButton = new JPanel(new BorderLayout());
        JPanel LeftRightPanelHinh = new JPanel(new BorderLayout(10, 10));
        JPanel LeftPanelRight1= new JPanel(new GridLayout(1, 2, 10, 10));
        JPanel LeftPanelRight2= new JPanel(new GridLayout(1, 2, 10, 10));
        JPanel LeftPanelRight3= new JPanel(new GridLayout(1, 2, 10, 10));
        
        JPanel LeftPanelEmail = new JPanel(new GridLayout(2, 1));
        JPanel LeftPanelSoDienThoai = new JPanel(new GridLayout(2, 1));
        JPanel LeftPanelDiaChi = new JPanel(new GridLayout(2, 1));
        JPanel LeftPanelGioiTinh = new JPanel(new GridLayout(2, 1));
        JPanel LeftPanelVaiTro = new JPanel(new GridLayout(2, 1));
        JPanel LeftPanelTrangThai = new JPanel(new GridLayout(2, 1));
        
        JPanel RightPanel = new JPanel(new BorderLayout());
        JPanel RightPanelRestore = new JPanel(new BorderLayout());
        JPanel RightPanelRestore1 = new JPanel(new GridLayout(1, 6, 10, 10));
        
        JPanel panelHoDem = new JPanel(new BorderLayout(10, 10));
        JPanel panelTen = new JPanel(new BorderLayout(10, 10));
        JPanel panelTenDangNhap = new JPanel(new BorderLayout(10, 10));
        JPanel panelMatKhau = new JPanel(new BorderLayout(10, 10));
        JPanel panelMatKhau2 = new JPanel(new BorderLayout(10, 10));
        JPanel panelEmail = new JPanel(new BorderLayout(10, 10));
        JPanel panelSoDienThoai = new JPanel(new BorderLayout(10, 10));
        

        // Font
        Font font = new Font("Arial", Font.BOLD, 18);
        Font font_ThongBao = new Font("Arial", font.PLAIN, 10);
        
        // Cập nhật - Label
        JLabel lblNhanVien = new JLabel("QUẢN LÝ NHÂN VIÊN QUẢN TRỊ");
        JLabel lblCapNhat = new JLabel("CẬP NHẬT");
        JLabel lblDanhSach = new JLabel("DANH SÁCH");
        
        JLabel lblMaNhanVien = new JLabel("Mã nhân viên");
        JLabel lblHoDem = new JLabel("Họ đệm");
        JLabel lblTen = new JLabel("Tên");
        JLabel lblTenDangNhap = new JLabel("Tên đăng nhập");
        JLabel lblMatKhau = new JLabel("Mật khẩu");
        JLabel lblMatKhau2 = new JLabel("Xác nhận mật khẩu");
        JLabel lblEmail = new JLabel("Email");
        JLabel lblSoDienThoai = new JLabel("Số điện thoại");
        JLabel lblGioiTinh = new JLabel("Giới tính");
        JLabel lblDiaChi = new JLabel("Địa chỉ");
        JLabel lblVaiTro = new JLabel("Vai Trò");
        JLabel lblTrangThai = new JLabel("Trạng thái");
        
        lblHinh = new JLabel();
        lblHinh.setSize(50, 50);
        lblHinh.setBorder(new LineBorder(Color.black));
        lblHinh.setHorizontalAlignment(JLabel.CENTER);
        lblHinh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                chonHinh();
            }
        });
        
        // thông báo lỗi
        lblHoDem_ThongBao = new JLabel();
        lblHoDem_ThongBao.setForeground(Color.red);
        lblHoDem_ThongBao.setFont(font_ThongBao);
        
        lblTen_ThongBao = new JLabel();
        lblTen_ThongBao.setForeground(Color.red);
        lblTen_ThongBao.setFont(font_ThongBao);
        
        lblTenDangNhap_ThongBao = new JLabel();
        lblTenDangNhap_ThongBao.setForeground(Color.red);
        lblTenDangNhap_ThongBao.setFont(font_ThongBao);
        
        lblMatKhau_ThongBao = new JLabel();
        lblMatKhau_ThongBao.setForeground(Color.red);
        lblMatKhau_ThongBao.setFont(font_ThongBao);
        
        lblMatKhau2_ThongBao = new JLabel();
        lblMatKhau2_ThongBao.setForeground(Color.red);
        lblMatKhau2_ThongBao.setFont(font_ThongBao);
        
        lblEmail_ThongBao = new JLabel();
        lblEmail_ThongBao.setForeground(Color.red);
        lblEmail_ThongBao.setFont(font_ThongBao);
        
        lblSoDienThoai_ThongBao = new JLabel();
        lblSoDienThoai_ThongBao.setForeground(Color.red);
        lblSoDienThoai_ThongBao.setFont(font_ThongBao);
        
        
        
        // Text field
        txtMaNhanVien = new JTextField();
        txtHoDem = new JTextField();
        txtHoDem.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                checkHoDem();
            }
        });
        txtTen = new JTextField();
        txtTen.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                checkTen();
            }
        });
        txtTenDangNhap = new JTextField();
        txtTenDangNhap.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                checkTenDangNhap1();
            }
        });
        txtMatKhau = new JPasswordField();
        txtMatKhau.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                checkMatKhau();
            }
        });
        txtMatKhau2 = new JPasswordField();
        txtMatKhau2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                checkXacNhanMatKhau();
            }
        });
        txtEmail = new JTextField();
        txtEmail.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                checkEmail();
            }
        });
        txtSoDienThoai = new JTextField();
        txtSoDienThoai.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                checkSoDienThoai();
            }
        });
        txtDiaChi = new JTextField();
        
        txtSoDienThoai.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!Character.isDigit(c) && !Character.isISOControl(c))
                    e.consume();
            }
        });
        
        // Radio button
        rdoAdmin = new JRadioButton("Admin");
        rdoAdmin.setSelected(true);
        rdoAdmin.setFocusable(false);
        rdoAdmin.setFont(new Font("Arial", Font.BOLD, 10));
        rdoNhanVien = new JRadioButton("Nhân viên");
        rdoNhanVien.setFocusable(false);
        rdoNhanVien.setFont(new Font("Arial", Font.BOLD, 10));
        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(rdoAdmin);
        btnGroup.add(rdoNhanVien);
        
        // Gioi tính
        cboGioiTinh = new JComboBox();
        cboGioiTinh.addItem("Nữ");
        cboGioiTinh.addItem("Nam");
        cboGioiTinh.addItem("Khác");
        
        // Trạng thái
        cboTrangThai = new JComboBox();
        cboTrangThai.addItem("Đang làm");
        cboTrangThai.addItem("Nghỉ phép");
        cboTrangThai.addItem("Nghỉ việc");
        
        // Button
        btnThem = new JButton("Thêm");
        btnThem.setFocusable(false);
        btnSua = new JButton("Sửa");
        btnSua.setFocusable(false);
        btnXoa = new JButton("Xóa");
        btnXoa.setFocusable(false);
        btnMoi = new JButton("Mới");
        btnMoi.setFocusable(false);
        btnFirst = new JButton("|<");
        btnFirst.setFocusable(false);
        btnPre = new JButton("<<");
        btnPre.setFocusable(false);
        btnNext = new JButton(">>");
        btnNext.setFocusable(false);
        btnLast = new JButton(">|");
        btnLast.setFocusable(false);
        btnRestore = new JButton("Khôi phục");
        btnRestore.setFocusable(false);
        btnDaXoa = new JButton("Đã xóa");
        btnDaXoa.setFocusable(false);
        btnImportNhanVienFromExcel = new JButton("Import Excel");
        btnImportNhanVienFromExcel.setFocusable(false);
        btnNghiPhep = new JButton("Nghỉ phép");
        btnNghiPhep.setFocusable(false);
        btnDangLam = new JButton("Đang làm");
        btnDangLam.setFocusable(false);
        btnNghiViec = new JButton("Nghỉ việc");
        btnNghiViec.setFocusable(false);

        // Actionlistener
        btnThem.addActionListener(ac);
        btnSua.addActionListener(ac);
        btnXoa.addActionListener(ac);
        btnMoi.addActionListener(ac);
        btnFirst.addActionListener(ac);
        btnPre.addActionListener(ac);
        btnNext.addActionListener(ac);
        btnLast.addActionListener(ac);
        btnRestore.addActionListener(ac);
        btnDaXoa.addActionListener(ac);
        btnImportNhanVienFromExcel.addActionListener(ac);
        btnNghiPhep.addActionListener(ac);
        btnDangLam.addActionListener(ac);
        btnNghiViec.addActionListener(ac);
        
        // Thanh cuộn
        JScrollPane scroll1DanhSach = new JScrollPane();
        
        // Setting 
//        lblHinh.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        lblNhanVien.setFont(font);
        lblNhanVien.setForeground(Color.blue);
        lblNhanVien.setHorizontalAlignment(JLabel.CENTER);
        
        lblCapNhat.setFont(font);
        lblCapNhat.setForeground(Color.blue);
        lblCapNhat.setHorizontalAlignment(JLabel.CENTER);
        
        lblDanhSach.setFont(font);
        lblDanhSach.setForeground(Color.blue);
        lblDanhSach.setHorizontalAlignment(JLabel.CENTER);
        
        // DANH SÁCH
        tblDanhSach = new JTable();
        tblDanhSach.setRowHeight(22);
        tblDanhSach.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "MÃ", "HỌ VÀ TÊN", "EMAIL", "SỐ ĐIỆN THOẠI", "GIỚI TÍNH", "ĐỊA CHỈ", "VAI TRÒ", "TRẠNG THÁI"
            }
        ){
            Boolean[] canEdit = new Boolean[]{
                false,false,false,false, false,false,false,false, false
            };

            @Override
            public boolean isCellEditable(int row, int column) {
                return canEdit[column];
            }
        });
        scroll1DanhSach.setViewportView(tblDanhSach);
        
        // set độ rộng column
        TableColumn columnSTT = tblDanhSach.getColumn("STT");
        columnSTT.setPreferredWidth(50);
        TableColumn columnMa = tblDanhSach.getColumn("MÃ");
        columnMa.setPreferredWidth(50);
        TableColumn columnName = tblDanhSach.getColumn("HỌ VÀ TÊN");
        columnName.setPreferredWidth(170);
        
        tblDanhSach.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2){
                    row = tblDanhSach.getSelectedRow();
                    edit();
                }
            }
            
        });

        // Thêm thông báo lỗi
        panelHoDem.add(lblHoDem, BorderLayout.WEST);
        panelHoDem.add(lblHoDem_ThongBao, BorderLayout.CENTER);
        panelTen.add(lblTen, BorderLayout.WEST);
        panelTen.add(lblTen_ThongBao, BorderLayout.CENTER);
        panelTenDangNhap.add(lblTenDangNhap, BorderLayout.WEST);
        panelTenDangNhap.add(lblTenDangNhap_ThongBao, BorderLayout.CENTER);
        panelMatKhau.add(lblMatKhau, BorderLayout.WEST);
        panelMatKhau.add(lblMatKhau_ThongBao, BorderLayout.CENTER);
        panelMatKhau2.add(lblMatKhau2, BorderLayout.WEST);
        panelMatKhau2.add(lblMatKhau2_ThongBao, BorderLayout.CENTER);
        panelEmail.add(lblEmail, BorderLayout.WEST);
        panelEmail.add(lblEmail_ThongBao, BorderLayout.CENTER);
        panelSoDienThoai.add(lblSoDienThoai, BorderLayout.WEST);
        panelSoDienThoai.add(lblSoDienThoai_ThongBao, BorderLayout.CENTER);
        
        // Thêm vào panel
        LeftPanerole.add(rdoAdmin);
        LeftPanerole.add(rdoNhanVien);
        
        LeftPanelComponents1.add(lblMaNhanVien);
        LeftPanelComponents1.add(txtMaNhanVien);
        LeftPanelComponents1.add(panelHoDem);
        LeftPanelComponents1.add(txtHoDem);
        LeftPanelComponents1.add(panelTen);
        LeftPanelComponents1.add(txtTen);
        LeftPanelComponents1.add(panelTenDangNhap);
        LeftPanelComponents1.add(txtTenDangNhap);
        LeftPanelComponents1.add(panelMatKhau);
        LeftPanelComponents1.add(txtMatKhau);
        LeftPanelComponents1.add(panelMatKhau2);
        LeftPanelComponents1.add(txtMatKhau2);
        
        LeftPanelComponents2.add(LeftRightPanelHinh);
        LeftPanelComponents2.add(LeftPanelComponents2_1);
        
        LeftPanelComponents2_1.add(LeftPanelRight1);
        LeftPanelComponents2_1.add(LeftPanelRight2);
        LeftPanelComponents2_1.add(LeftPanelRight3);
        
        LeftPanelButton1.add(btnThem);
        LeftPanelButton1.add(btnXoa);
        LeftPanelButton1.add(btnSua);
        LeftPanelButton1.add(btnMoi);
        LeftPanelButton2.add(btnFirst);
        LeftPanelButton2.add(btnPre);
        LeftPanelButton2.add(btnNext);
        LeftPanelButton2.add(btnLast);
        LeftPanelButton.add(LeftPanelButton1,BorderLayout.WEST);
        LeftPanelButton.add(LeftPanelButton2,BorderLayout.EAST);
        
        // Thêm components vào panel
        LeftPanelChung.add(LeftPanelComponents1);
        LeftPanelChung.add(LeftPanelComponents2);
        
        LeftPanel.add(lblCapNhat, BorderLayout.NORTH);
        LeftPanel.add(LeftPanelChung, BorderLayout.CENTER);
        LeftPanel.add(LeftPanelButton, BorderLayout.SOUTH);
        
        LeftRightPanelHinh.add(lblHinh);
        
        LeftPanelEmail.add(panelEmail);
        LeftPanelEmail.add(txtEmail);
        LeftPanelSoDienThoai.add(panelSoDienThoai);
        LeftPanelSoDienThoai.add(txtSoDienThoai);
        LeftPanelRight1.add(LeftPanelEmail);
        LeftPanelRight1.add(LeftPanelSoDienThoai);
        
        LeftPanelDiaChi.add(lblDiaChi);
        LeftPanelDiaChi.add(txtDiaChi);
        LeftPanelGioiTinh.add(lblGioiTinh);
        LeftPanelGioiTinh.add(cboGioiTinh);
        LeftPanelRight2.add(LeftPanelDiaChi);
        LeftPanelRight2.add(LeftPanelGioiTinh);
        
        LeftPanelVaiTro.add(lblVaiTro);
        LeftPanelVaiTro.add(LeftPanerole);
        LeftPanelTrangThai.add(lblTrangThai);
        LeftPanelTrangThai.add(cboTrangThai);
        LeftPanelRight3.add(LeftPanelVaiTro);
        LeftPanelRight3.add(LeftPanelTrangThai);
        
        // Right
        RightPanelRestore1.add(btnNghiPhep);
        RightPanelRestore1.add(btnNghiViec);
        RightPanelRestore1.add(btnDangLam);
        RightPanelRestore1.add(btnImportNhanVienFromExcel);
        RightPanelRestore1.add(btnDaXoa);
        RightPanelRestore1.add(btnRestore);
        RightPanelRestore.add(RightPanelRestore1, BorderLayout.CENTER);
        RightPanel.add(lblDanhSach, BorderLayout.NORTH);
        RightPanel.add(scroll1DanhSach, BorderLayout.CENTER);
        RightPanel.add(RightPanelRestore, BorderLayout.SOUTH);
        
        panelNhanVien.add(LeftPanel);
        panelNhanVien.add(RightPanel);
        
        ParkFrame.CenterPanel.removeAll();
        ParkFrame.CenterPanel.add(lblNhanVien, BorderLayout.NORTH);
        ParkFrame.CenterPanel.add(panelNhanVien, BorderLayout.CENTER);
        ParkFrame.CenterPanel.setVisible(false);
        ParkFrame.CenterPanel.setVisible(true);
    }
    
    private void init(){
        
        fillToTable();
        row = -1;
        upDateStatus();
    }
    
    public void fillToTable(){
        tableModel = (DefaultTableModel) tblDanhSach.getModel();
        tableModel.setRowCount(0);
        try {
            List<NhanVien> list = nhanVienDAO.selectALL();
            for (int i = 0; i < list.size(); i++) {
                NhanVien nhanVien = list.get(i);
                Object row[] = {i + 1, nhanVien.getMaNhanVien(), nhanVien.getHo() +" " + nhanVien.getTen(), nhanVien.getEmail(), nhanVien.getSoDienThoai(),
                        getGioiTinh(nhanVien), nhanVien.getDiaChi(), nhanVien.isVaiTro() ? "Admin" : "Nhân viên", getTrangThai(nhanVien)};
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            MessageBox.alert(null, "Lỗi truy vấn dữ liệu");
        }
    }
    
    public void fillToTableNhanVienDaXoa(){
        tableModel = (DefaultTableModel) tblDanhSach.getModel();
        tableModel.setRowCount(0);
        try {
            List<NhanVien> list = nhanVienDAO.selectNhanVienDaXoa();
            for (int i = 0; i < list.size(); i++) {
                NhanVien nhanVien = list.get(i);
                Object row[] = {i + 1, nhanVien.getMaNhanVien(), nhanVien.getHo() +" " + nhanVien.getTen(), nhanVien.getEmail(), nhanVien.getSoDienThoai(),
                        getGioiTinh(nhanVien), nhanVien.getDiaChi(), nhanVien.isVaiTro() ? "Admin" : "Nhân viên", getTrangThai(nhanVien)};
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            MessageBox.alert(null, "Lỗi truy vấn dữ liệu");
        }
    }
    
    public void fillToTableNhanVienNghiViec(){
        tableModel = (DefaultTableModel) tblDanhSach.getModel();
        tableModel.setRowCount(0);
        try {
            List<NhanVien> list = nhanVienDAO.selectNhanVienNghiViec();
            for (int i = 0; i < list.size(); i++) {
                NhanVien nhanVien = list.get(i);
                Object row[] = {i + 1, nhanVien.getMaNhanVien(), nhanVien.getHo() +" " + nhanVien.getTen(), nhanVien.getEmail(), nhanVien.getSoDienThoai(),
                        getGioiTinh(nhanVien), nhanVien.getDiaChi(), nhanVien.isVaiTro() ? "Admin" : "Nhân viên", getTrangThai(nhanVien)};
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            MessageBox.alert(null, "Lỗi truy vấn dữ liệu");
        }
    }
    
    public void fillToTableNhanVienNghiNghiPhep(){
        tableModel = (DefaultTableModel) tblDanhSach.getModel();
        tableModel.setRowCount(0);
        try {
            List<NhanVien> list = nhanVienDAO.selectNhanVienNghiPhep();
            for (int i = 0; i < list.size(); i++) {
                NhanVien nhanVien = list.get(i);
                Object row[] = {i + 1, nhanVien.getMaNhanVien(), nhanVien.getHo() +" " + nhanVien.getTen(), nhanVien.getEmail(), nhanVien.getSoDienThoai(),
                        getGioiTinh(nhanVien), nhanVien.getDiaChi(), nhanVien.isVaiTro() ? "Admin" : "Nhân viên", getTrangThai(nhanVien)};
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            MessageBox.alert(null, "Lỗi truy vấn dữ liệu");
        }
    }
    
    public String getGioiTinh(NhanVien nhanVien){
        return switch (nhanVien.getGioiTinh()) {
            case 0 -> "Nữ";
            case 1 -> "Nam";
            default -> "Khác";
        };
    }
    
    public String getTrangThai(NhanVien nhanVien){
        return switch(nhanVien.getTrangThai()){
            case 0 -> "Đang làm";
            case 1 -> "Nghỉ phép";
            default -> "Nghỉ việc";
        };
    }
    
    public void upDateStatus(){
        Boolean edit = row >= 0;
        Boolean first = row == 0;
        Boolean last = row == (tblDanhSach.getRowCount()-1);
        
        // status
        txtMaNhanVien.setEnabled(false);
        txtTenDangNhap.setEnabled(false);
        // Trạng thái form
        if(!Authenticated.isManage()){
            btnThem.setEnabled(false);
            btnXoa.setEnabled(false);
            btnMoi.setEnabled(false);
            btnSua.setEnabled(false);
            rdoAdmin.setEnabled(false);
            rdoNhanVien.setEnabled(false);
            cboTrangThai.setEnabled(false);
            btnRestore.setEnabled(false);
            btnDaXoa.setEnabled(false);
            btnImportNhanVienFromExcel.setEnabled(false);
        }
        else{
            btnThem.setEnabled(!edit);
            btnSua.setEnabled(edit);
            btnXoa.setEnabled(edit);
        }

        // Trạng thái điều hướng
        btnFirst.setEnabled(edit && !first);
        btnPre.setEnabled(edit && !first);
        btnNext.setEnabled(edit && !last);
        btnLast.setEnabled(edit && !last);
    }
    
    public NhanVien getForm(){
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNhanVien(Integer.parseInt(txtMaNhanVien.getText()));
        nhanVien.setHo(txtHoDem.getText());
        nhanVien.setTen(txtTen.getText());
        nhanVien.setTenDangNhap(txtTenDangNhap.getText());
        nhanVien.setMatKhau(new String(En_DecodeHelper.encrypt(txtMatKhau.getText())));
        nhanVien.setEmail(txtEmail.getText());
        nhanVien.setSoDienThoai(txtSoDienThoai.getText());
        nhanVien.setGioiTinh(cboGioiTinh.getSelectedIndex());
        nhanVien.setDiaChi(txtDiaChi.getText());
        nhanVien.setVaiTro(rdoAdmin.isSelected());
        nhanVien.setTrangThai(cboTrangThai.getSelectedIndex());
        
        if(lblHinh.getToolTipText() == null)
            nhanVien.setHinhAnh(null);
        else
            nhanVien.setHinhAnh(lblHinh.getToolTipText());
        return nhanVien;
    }
    
    public void setForm(NhanVien nhanVien){
        txtMaNhanVien.setText(String.valueOf(nhanVien.getMaNhanVien()));
        txtHoDem.setText(nhanVien.getHo());
        txtTen.setText(nhanVien.getTen());
        txtTenDangNhap.setText(nhanVien.getTenDangNhap());
        if(clear != 1) {
            txtMatKhau.setText(En_DecodeHelper.decrypt(nhanVien.getMatKhau()));
            txtMatKhau2.setText(En_DecodeHelper.decrypt(nhanVien.getMatKhau()));
        }
        else {
            txtMatKhau.setText(nhanVien.getMatKhau());
            txtMatKhau2.setText(nhanVien.getMatKhau());
        }
        clear = 0;
        txtEmail.setText(nhanVien.getEmail());
        txtSoDienThoai.setText(nhanVien.getSoDienThoai());
        cboGioiTinh.setSelectedIndex(nhanVien.getGioiTinh());
        txtDiaChi.setText(nhanVien.getDiaChi());
        rdoAdmin.setSelected(nhanVien.isVaiTro());
        rdoNhanVien.setSelected(!nhanVien.isVaiTro());
        cboTrangThai.setSelectedIndex(nhanVien.getTrangThai());
        
        lblHinh.setToolTipText(nhanVien.getHinhAnh());
        if(nhanVien.getHinhAnh() != null) {
            lblHinh.setIcon(ImagesHelper.readImageFromDirectory1(nhanVien.getHinhAnh(),lblHinh.getWidth(), lblHinh.getHeight()));
            lblHinh.setText("");
        } else {
            lblHinh.setIcon(null);
            lblHinh.setText("NO AVATAR");
        }
    }
    
    public void clearForm() {
        clear = 1;
        NhanVien nhanVien = new NhanVien();
        setForm(nhanVien);
        row = -1;
//        txtHoDem.requestFocus();
        txtMaNhanVien.setText(String.valueOf(getMaNhanVienMax()+1));
        upDateStatus();
        txtTenDangNhap.setEnabled(true);
    }
    
    private void edit() {
        int maNhanVien = (int) tblDanhSach.getValueAt(row, 1);
        NhanVien nhanVien = nhanVienDAO.selectByID(maNhanVien);
        if(nhanVien != null) {
            setForm(nhanVien);
            tblDanhSach.setRowSelectionInterval(row, row);
            txtTenDangNhap.setEnabled(false);
            upDateStatus();
            if(!Authenticated.isManage() && !Authenticated.user.getTenDangNhap().equals(nhanVien.getTenDangNhap()))
                btnSua.setEnabled(false);
            else
                btnSua.setEnabled(true);
        }
    }

    public void insert() {
        NhanVien nhanVien = getForm();
        if(check() == 7 && checkTenDangNhap()){
                try {
                        nhanVienDAO.insert(nhanVien);
                        fillToTable();
                        clearForm();
                        MessageBox.alert(null, "Thêm mới thành công!");
                } catch (Exception e) {
                        MessageBox.alert(null, "Thêm mới thất bại!");
                }
        }
    }
    
    public void upDate() {
        NhanVien nhanVien = getForm();
//            if(!Authenticated.isManage() && !Authenticated.user.getTenDangNhap().equals(nhanVien.getTenDangNhap()))
//                MessageBox.alert(null, "Bạn không có quyền sửa thông tin người khác!");
            {
                if(check() == 7){
                    try {
                        nhanVienDAO.update(nhanVien);
                        fillToTable();
                        MessageBox.alert(null, "Cập nhật thành công!");
                    } catch (Exception e) {
                        MessageBox.alert(null, "Cập nhật thất bại!");
                    }
                }
            }
    }
    
    public void delete() {
        if(!Authenticated.isManage())
            MessageBox.alert(null, "Bạn không có quyền xóa nhân viên!");
        else{
            String maNhanVien = txtMaNhanVien.getText();
            if(maNhanVien.equals(Authenticated.user.getMaNhanVien()))
                MessageBox.alert(null, "Bạn không thể xóa chính bạn!");
            else if(MessageBox.confirm(null, "Bạn thực sự muốn xóa nhân viên này?")){
                try {
                    nhanVienDAO.delete(maNhanVien);
                    fillToTable();
                    clearForm();
                    MessageBox.alert(null, "Xóa nhân viên thành công!");
                } catch (Exception e) {
                    MessageBox.alert(null, "Xóa nhân viên thất bại!");
                }
                
            }
        }
    }
    
//    public void restore(){
//        if(this.maNhanVien == null)
//            MessageBox.alert(null, "Không có nhân viên nào vừa xóa!");
//        else if(MessageBox.confirm(null, "Bạn muốn khôi phục nhân viên vừa xóa?")) {
//            try {
//                nhanVienDAO.restore(this.maNhanVien);
//                fillToTable();
//                clearForm();
//                this.maNhanVien = null;
//                MessageBox.alert(null, "Khôi phục nhân viên thành công!");
//            } catch (Exception e) {
//                MessageBox.alert(null, "Khôi phục nhân viên thất bại!");
//            }
//        }
//    }
    
    public void restore(){
        this.maNhanVien = txtMaNhanVien.getText();
        if(this.maNhanVien.isEmpty())
            MessageBox.alert(null, "Vui lòng chọn giao dịch muốn khôi phục!");
        else if (nhanVienDAO.selectBbyDeleted(maNhanVien).isEmpty()){
            MessageBox.alert(null, "Giao dịch này chưa xóa, không thể khôi phục!");
        }
        else if(MessageBox.confirm(null, "Bạn muốn khôi phục giao dịch này?")) {
            try {
                nhanVienDAO.restore(maNhanVien);
                fillToTableNhanVienDaXoa();
                clearForm();
                this.maNhanVien = null;
                MessageBox.alert(null, "Khôi phục nhân viên thành công!");
            } catch (Exception e) {
                MessageBox.alert(null, "Khôi phục nhân vi thất bại!");
            }
        }
    }
    
    public void chonHinh() {
        fileChooser.setDialogTitle("Hình nhân viên");
        if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            ImagesHelper.saveImageToDirectory(file); //lưu hình vào thư mục logos
            ImageIcon iconImage = ImagesHelper.readImageFromDirectory1(file.getName(), lblHinh.getWidth(), lblHinh.getHeight());
            lblHinh.setIcon(iconImage);
            lblHinh.setToolTipText(file.getName()); // giữ tên hình trong tooltip
            lblHinh.setText("");
        }
    }
    
//    public void restoreAll(){
//        if(MessageBox.confirm(null, "Bạn muốn khôi phục tất cả nhân viên từng xóa?")) {
//            try {
//                nhanVienDAO.restoreAll();
//                fillToTable();
//                clearForm();
//                MessageBox.alert(null, "Khôi phục nhân viên thành công!");
//            } catch (Exception e) {
//                MessageBox.alert(null, "Khôi phục nhân viên thất bại!");
//            }
//        }
//    }
    
    public int getMaNhanVienMax(){
        List<NhanVien> list = nhanVienDAO.selectALL();
        return list.get(list.size()-1).getMaNhanVien();
    }
    
    public boolean checkHoDem(){
        if(txtHoDem.getText().isEmpty()){
            lblHoDem_ThongBao.setText("Vui lòng nhập họ đệm nhân viên!");
            txtHoDem.setBorder(BorderFactory.createLineBorder(Color.red));
            return false;
        } else if(txtHoDem.getText().length() > 30){
            lblHoDem_ThongBao.setText("Độ dài trong khoản 0 đến 30 ký tự!");
            txtHoDem.setBorder(BorderFactory.createLineBorder(Color.red));
            return false;
        }
        else {
            lblHoDem_ThongBao.setText("");
            txtHoDem.setBorder(BorderFactory.createLineBorder(Color.black));
            return true;
        }
    }
    
    public boolean checkTen(){
        if(txtTen.getText().isEmpty()){
            lblTen_ThongBao.setText("Vui lòng nhập tên nhân viên!");
            txtTen.setBorder(BorderFactory.createLineBorder(Color.red));
            return false;
        } else if(txtTen.getText().length() > 30){
            lblTen_ThongBao.setText("Độ dài trong khoản 0 đến 30 ký tự!");
            txtTen.setBorder(BorderFactory.createLineBorder(Color.red));
            return false;
        }
        else {
            lblTen_ThongBao.setText("");
            txtTen.setBorder(BorderFactory.createLineBorder(Color.black));
            return true;
        }
    }
    
    public boolean checkTenDangNhap1(){
        String mauTenDangNhap = "[a-z0-9_-]{6,50}";
            if(txtTenDangNhap.getText().isEmpty()){
                lblTenDangNhap_ThongBao.setText("Vui lòng nhập tên đăng nhập!");
                txtTenDangNhap.setBorder(BorderFactory.createLineBorder(Color.red));
                return false;
            } else if(!txtTenDangNhap.getText().matches(mauTenDangNhap)){
                lblTenDangNhap_ThongBao.setText("Độ dài từ 6 đến 50 ký tự, viết thường, không dấu!");
                txtTenDangNhap.setBorder(BorderFactory.createLineBorder(Color.red));
                return false;
            } 
            else {
                lblTenDangNhap_ThongBao.setText("");
                txtTenDangNhap.setBorder(BorderFactory.createLineBorder(Color.black));
                return true;
            }
    }
    
    public boolean checkMatKhau(){
        String mauMatKhau = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,30}$";
        String matKhau = new String(txtMatKhau.getPassword());
        if(matKhau.isEmpty()){
            lblMatKhau_ThongBao.setText("Vui lòng nhập mật khẩu!");
            txtMatKhau.setBorder(BorderFactory.createLineBorder(Color.red));
            return false;
        } else if(!matKhau.matches(mauMatKhau)){
            lblMatKhau_ThongBao.setText("8-30 ký tự, ít nhất một chữ, một số và không dấu!");
            txtMatKhau.setBorder(BorderFactory.createLineBorder(Color.red));
            return false;
        }
        else {
            lblMatKhau_ThongBao.setText("");
            txtMatKhau.setBorder(BorderFactory.createLineBorder(Color.black));
            return true;
        }
    }
    
    public boolean checkXacNhanMatKhau(){
        String matKhau = new String(txtMatKhau.getPassword());
        String matKhau2 = new String(txtMatKhau2.getPassword());
        if(matKhau2.isEmpty()){
            lblMatKhau2_ThongBao.setText("Vui lòng xác nhận mật khẩu!");
            txtMatKhau2.setBorder(BorderFactory.createLineBorder(Color.red));
            return false;
        } else if(!matKhau2.equals(matKhau)){
            lblMatKhau2_ThongBao.setText("Mật khẩu không khớp!");
            txtMatKhau2.setBorder(BorderFactory.createLineBorder(Color.red));
            return false;
        }
        else {
            lblMatKhau2_ThongBao.setText("");
            txtMatKhau2.setBorder(BorderFactory.createLineBorder(Color.black));
            return true;
        }
    }
    
    public boolean checkEmail(){
        String mauEmail = "\\w+@\\w+(\\.\\w+){1,2}";
        if(txtEmail.getText().isEmpty()){
            lblEmail_ThongBao.setText("Vui lòng nhập email không dấu!");
            txtEmail.setBorder(BorderFactory.createLineBorder(Color.red));
            return false;
        } else if(txtEmail.getText().length() > 50){
            lblEmail_ThongBao.setText("Độ dài trong khoản 0 đến 50 ký tự!");
            txtEmail.setBorder(BorderFactory.createLineBorder(Color.red));
            return false;
        } else if(!txtEmail.getText().matches(mauEmail)){
            lblEmail_ThongBao.setText("Email không hợp lệ!");
            txtEmail.setBorder(BorderFactory.createLineBorder(Color.red));
            return false;
        }
        else {
            lblEmail_ThongBao.setText("");
            txtEmail.setBorder(BorderFactory.createLineBorder(Color.black));
            return true;
        }
    }
    
    public boolean checkSoDienThoai(){
        String mauSDT = "0\\d{9}";
        if(txtSoDienThoai.getText().isEmpty()){
            lblSoDienThoai_ThongBao.setText("Vui lòng nhập số điện thoại!");
            txtSoDienThoai.setBorder(BorderFactory.createLineBorder(Color.red));
            return false;
        } else if(!txtSoDienThoai.getText().matches(mauSDT)){
            lblSoDienThoai_ThongBao.setText("Không hợp lệ!");
            txtSoDienThoai.setBorder(BorderFactory.createLineBorder(Color.red));
            return false;
        }
        else {
            lblSoDienThoai_ThongBao.setText("");
            txtSoDienThoai.setBorder(BorderFactory.createLineBorder(Color.black));
            return true;
        }
    }
    
    public int check(){
        int dem = 0;
        // Check họ đệm
        if(checkHoDem())
            dem++;
        
        if(checkTen())
            dem++;
        
        if(checkTenDangNhap1())
            dem++;
        
        if(checkMatKhau())
            dem++;
        
        if(checkXacNhanMatKhau())
            dem++;
        
        if(checkEmail())
            dem++;
        
        if(checkSoDienThoai())
            dem++;
        
        return dem;
    }
    
    public boolean checkTenDangNhap(){
            NhanVien nhanVien = nhanVienDAO.selectByUserName(txtTenDangNhap.getText());
            if(nhanVien != null){
                lblTenDangNhap_ThongBao.setText("Tên đăng nhập đã tồn tại!");
                txtTenDangNhap.setBorder(BorderFactory.createLineBorder(Color.red));
                return false;
            }
            else {
                lblTenDangNhap_ThongBao.setText("");
                txtTenDangNhap.setBorder(BorderFactory.createLineBorder(Color.black));
                return true;
            }
    }

    public void first() {
        row = 0;
        edit();
    }

    public void previous() {
        if(row>0){
            row --;
            edit();
        }
    }

    public void next() {
        if(row < tblDanhSach.getRowCount()-1){
            row++;
            edit();
        }
    }

    public void last() {
        row = tblDanhSach.getRowCount()-1;
        edit();
    }
    
    private File chonFileExcelImportNhanVien() {
        File excelFile = null;
        fileChooser = new JFileChooser();
                
        if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            excelFile = ImagesHelper.saveExel(file); // lưu hình vào thư mục logos
            
        }
        return excelFile;
    }
    
            
    private void importNhanVienFromExcel(File excelFile){
        NhanVien nhanVien = new NhanVien();
        DataFormatter formatter = new DataFormatter();
        try{
			FileInputStream file = new FileInputStream(excelFile);

			//Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			//Get first/desired sheet from the workbook
                        XSSFSheet sheet = workbook.getSheetAt(0);

			//Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
                        rowIterator.next();//Skip the header row
			while (rowIterator.hasNext()) 
			{
                            Row row = rowIterator.next();

//                            nhanVien.setMaNhanVien(Integer.parseInt(row.getCell(0).getStringCellValue()));
                            nhanVien.setHo(row.getCell(1).getStringCellValue());
                            nhanVien.setTen(row.getCell(2).getStringCellValue());
                            nhanVien.setTenDangNhap(row.getCell(3).getStringCellValue());
                            nhanVien.setMatKhau(formatter.formatCellValue(row.getCell(4)));
                            nhanVien.setEmail(formatter.formatCellValue(row.getCell(5)));
                            nhanVien.setSoDienThoai(formatter.formatCellValue(row.getCell(6)));
                            if(row.getCell(7).getStringCellValue().equals("Nữ"))
                                nhanVien.setGioiTinh(0);
                            else if(row.getCell(7).getStringCellValue().equals("Nam"))
                                nhanVien.setGioiTinh(1);
                            else
                                nhanVien.setGioiTinh(2);
                            nhanVien.setDiaChi(row.getCell(8).getStringCellValue());
                            if(row.getCell(9).getNumericCellValue() == 1)
                                nhanVien.setVaiTro(true);
                            else 
                                nhanVien.setVaiTro(false);
                            if(row.getCell(10).getNumericCellValue() == 0)
                                nhanVien.setTrangThai(0);
                            else if(row.getCell(10).getNumericCellValue() == 1)
                                nhanVien.setTrangThai(1);
                            else
                                nhanVien.setTrangThai(2);
                            nhanVienDAO.insert(nhanVien);
			}
			file.close();
                        this.fillToTable();
		} 
		catch (IOException e) 
		{
			throw new RuntimeException();
		}
    }
    
    public void ImportNhanVien() {
        File file = this.chonFileExcelImportNhanVien();
        if(file == null){
            MessageBox.alert(null, "Lỗi đọc tập tin Excel!");
        }else
        {
            this.importNhanVienFromExcel(file);
            MessageBox.alert(null, "Import danh sách người học thành công!");
        }
            }

}
