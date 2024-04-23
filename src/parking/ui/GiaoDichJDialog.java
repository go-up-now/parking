
package parking.ui;

import parking.controller.GiaoDichController;
import parking.dao.BangGiaDAO;
import parking.dao.GiaoDichDAO;
import parking.dao.LoaiXeDAO;
import parking.dao.NhanVienDAO;
import parking.dao.TheXeCuDanDAO;
import parking.dao.TheXeKhachVangLaiDAO;
import parking.entity.BangGia;
import parking.entity.GiaoDich;
import parking.entity.LoaiXe;
import parking.entity.NhanVien;
import parking.entity.TheXeCuDan;
import parking.entity.TheXeKhachVangLai;
import parking.utils.Authenticated;
import parking.utils.DateHelper;
import parking.utils.MessageBox;
import parking.utils.ImagesHelper;
import parking.computervision.ComputerVision;
import parking.carplaterecognize.CarPlateRecognize;
import com.google.zxing.WriterException;
import parking.computervision.RunOpenCV;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.SecureRandom;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import parking.ui.QRCodeJDialog;
import parking.utils.QRCodeUtil;

public class GiaoDichJDialog {
    GiaoDichDAO giaoDichdao = new GiaoDichDAO();
    LoaiXeDAO loaiXeDAO = new LoaiXeDAO();
    BangGiaDAO bangGiaDAO = new BangGiaDAO();
    TheXeCuDanDAO theXeCuDanDAO = new TheXeCuDanDAO();
    TheXeKhachVangLaiDAO theXeKhachVangLaiDAO = new TheXeKhachVangLaiDAO();
    NhanVienDAO nhanVienDAO = new NhanVienDAO();
    
    JFileChooser fileChooser = new JFileChooser();
    GiaoDichController ac = new GiaoDichController(this);
    NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
    int row = -1;
    int index1 = 0;
    String maGiaoDich = null;
    public static String qrCodeImage = "";
    
    private  JTable tblDanhSach;
    private  JButton btnNext;
    private  JButton btnThem;
    private  JButton btnSua;
    private  JButton btnXoa;
    private  JButton btnMoi;
    private  JButton btnFirst;
    private  JButton btnPre;
    private  JButton btnLast;
    private  JLabel lblHinh;
    private JTextField txtMaGiaoDich;
    private JTextField txtThoiGianVao;
    private JTextField txtThoiGianRa;
    private JTextField txtSoNgayGui;
    public JTextField txtBienSoXe;
    private JTextField txtPhiGuiXe;
    private JTextField txtNhanVien;
    private JComboBox<Object> cboTrangThai;
    private JComboBox<Object> cboMaLoaiXe;
    private JComboBox<Object> cboBangGia;
    private JComboBox<Object> cboLoaiKhach;
    private JComboBox<Object> cboMaTheKhach;
    private JButton btnRestore;
    private JButton btnLayXe;
    private JTextField txtSearch;
    private JButton btnLichSuGuiXe;
    private JButton btnGiaoDichDaXoa;
    private JButton btnXeDangGui;
    private JButton btnXuatFileExcel;
    private JButton btnQuetHinh;
    
    public void chuyenDeJDialog(){
        Components();
        init();
    }

    public void Components() {
        // Tạo panel
        JPanel panelCD = new JPanel(new GridLayout(1, 2,10, 10));
        JPanel LeftPanel = new JPanel(new BorderLayout());
        JPanel RightPanel = new JPanel(new BorderLayout());
        
        JPanel LeftPanelTong = new JPanel(new GridLayout(2,1));
        JPanel LeftPanelChung1 = new JPanel(new GridLayout(1, 2));
        JPanel LeftPanelChung2 = new JPanel(new GridLayout(1, 2));
        
        JPanel LeftPanelComponents1 = new JPanel(new GridLayout(6, 1));
        JPanel LeftPanelComponents2 = new JPanel(new GridLayout(6, 1));
        JPanel LeftPanelButton1 = new JPanel(new GridLayout(1, 4, 10, 10));
        JPanel LeftPanelButton2 = new JPanel(new GridLayout(1, 5, 5, 5));
        JPanel LeftPanelButton = new JPanel(new BorderLayout());
        
        JPanel LeftRightPanelComponents1 = new JPanel(new BorderLayout());
        JPanel LeftRightPanelComponents2 = new JPanel(new GridLayout(3, 1));
        JPanel LeftRightPanelHinh = new JPanel(new BorderLayout(10, 10));

        JPanel LeftPanelTrangThai = new JPanel(new GridLayout(2, 1));
        JPanel LeftPanelNhanVien = new JPanel(new GridLayout(2, 1));
        JPanel LeftPanelLoaiXe = new JPanel(new GridLayout(2, 1));
        JPanel LeftPanelBangGia = new JPanel(new GridLayout(2, 1));
        JPanel LeftPanelLoaiKhach = new JPanel(new GridLayout(2, 1));
        JPanel LeftPanelMaLoaiThe = new JPanel(new GridLayout(2, 1));
        
        JPanel LeftPanelRight1= new JPanel(new GridLayout(1, 2, 10, 10));
        JPanel LeftPanelRight2= new JPanel(new GridLayout(1, 2, 10, 10));
        JPanel LeftPanelRight3= new JPanel(new GridLayout(1, 2, 10, 10));
        
        JPanel RightPanelTable = new JPanel(new BorderLayout(20, 20));
        JPanel RightPanelSearch = new JPanel(new BorderLayout(20, 20));

        JPanel RightPanelRestore = new JPanel(new BorderLayout());
        JPanel RightPanelRestore1 = new JPanel(new GridLayout(1, 6, 10, 10));
        
        Font font = new Font("Arial", Font.BOLD, 18);
        
        // Cập nhật - Label
        JLabel lblGiaoDich = new JLabel("QUẢN LÝ GIAO DỊCH");
        JLabel lblCapNhat = new JLabel("CẬP NHẬT");
        JLabel lblDanhSach = new JLabel("DANH SÁCH");
        lblHinh = new JLabel();
        JLabel lblMaGiaoDich = new JLabel("Mã giao dịch");
        JLabel lblThoiGianVao = new JLabel("Thời gian vào");
        JLabel lblThoiGianRa = new JLabel("Thời gian ra");
        JLabel lblSoNgayGui = new JLabel("Số ngày gửi");
        JLabel lblBienSoXe = new JLabel("Biển số xe");
        JLabel lblPhiGuiXe = new JLabel("Phí gửi xe");
        JLabel lblTrangThai = new JLabel("Trạng thái");
        JLabel lblNhanVien = new JLabel("Nhân viên");
        JLabel lblMaLoaiXe = new JLabel("Loại xe");
        JLabel lblBangGia = new JLabel("Bảng giá");
        JLabel lblLoaiKhach = new JLabel("Loại khách");
        JLabel lblMaTheKhach = new JLabel("Mã thẻ khách");
        JLabel lblSearch = new JLabel("Tìm kiếm");
        
        // Text field
        txtMaGiaoDich = new JTextField();
         txtThoiGianVao = new JTextField();
         txtThoiGianRa = new JTextField();
         txtSoNgayGui = new JTextField("0");
         txtBienSoXe = new JTextField();
         txtPhiGuiXe = new JTextField();
         txtNhanVien = new JTextField();
         txtSearch = new JTextField();
         
         cboTrangThai = new JComboBox<>();
         cboTrangThai.addItem("Đang gửi");
         cboTrangThai.addItem("Đã lấy");
         cboMaLoaiXe = new JComboBox<>();
         cboMaLoaiXe.setEnabled(false);
         cboBangGia = new JComboBox<>();
         cboBangGia.setEnabled(false);
         cboLoaiKhach= new JComboBox<>();
         cboLoaiKhach.addItem("Khách vãng lai");
         cboLoaiKhach.addItem("Cư dân");
         cboMaTheKhach = new JComboBox<>();
         
         cboMaLoaiXe.addActionListener((ActionEvent e) -> {
             fillToBangGia();
        });
         
         cboLoaiKhach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillToMaTheKhachHang();
            }
         });
         
         cboBangGia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    txtPhiGuiXe.setText(String.valueOf(getTongTien()));
            }
         });
         
         cboMaTheKhach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    fillToLoaiXe();
            }
         });
        
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
        btnGiaoDichDaXoa = new JButton("Đã xóa");
        btnGiaoDichDaXoa.setFocusable(false);
        btnLayXe = new JButton("Lấy xe");
        btnLayXe.setFocusable(false);
        btnLichSuGuiXe = new JButton("Đã lấy");
        btnLichSuGuiXe.setFocusable(false);
        btnXeDangGui = new JButton("Đang gửi");
        btnXeDangGui.setFocusable(false);
        btnXuatFileExcel = new JButton("Xuất excel");
        btnXuatFileExcel.setFocusable(false);
        
        btnQuetHinh = new JButton("Quét");
        btnQuetHinh.setFocusable(false);
        btnQuetHinh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartChupHinh();
            }
        });
        
        // Thanh cuộn
        JScrollPane scroll1 = new JScrollPane();
        
        // Setting 
        lblHinh.setHorizontalAlignment(JLabel.CENTER);
        lblGiaoDich.setFont(font);
        lblGiaoDich.setForeground(Color.blue);
        lblGiaoDich.setHorizontalAlignment(JLabel.CENTER);
        
        lblCapNhat.setFont(font);
        lblCapNhat.setForeground(Color.blue);
        lblCapNhat.setHorizontalAlignment(JLabel.CENTER);
        
        lblDanhSach.setFont(font);
        lblDanhSach.setForeground(Color.blue);
        lblDanhSach.setHorizontalAlignment(JLabel.CENTER);
        
        lblHinh.setSize(10, 10);
        lblHinh.setBorder(new LineBorder(Color.DARK_GRAY));
        
        // DANH SÁCH
        tblDanhSach = new JTable();
        tblDanhSach.setRowHeight(22);
        tblDanhSach.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "MÃ", "THỜI GIAN VÀO", "THỜI GIAN RA", "BIỂN SỐ XE", "PHÍ GỬI XE", "TRẠNG THÁI"
            }
        ){
            Boolean[] canEdit = new Boolean[]{
                false, false, false, false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int row, int column) {
                return canEdit[column];
            }
        });
        scroll1.setViewportView(tblDanhSach);
        
        // Fix độ rộng column
        TableColumn columnSTT = tblDanhSach.getColumn("STT");
        TableColumn columnMa = tblDanhSach.getColumn("MÃ");
        TableColumn columnThoiGianVao = tblDanhSach.getColumn("THỜI GIAN VÀO");
        TableColumn columnThoiGianRa = tblDanhSach.getColumn("THỜI GIAN RA");
        TableColumn columnBienSoXe = tblDanhSach.getColumn("BIỂN SỐ XE");
        TableColumn columnPhiGuiXe = tblDanhSach.getColumn("PHÍ GỬI XE");
        
        columnSTT.setPreferredWidth(40);
        columnMa.setPreferredWidth(40);
        columnThoiGianVao.setPreferredWidth(150);
        columnThoiGianRa.setPreferredWidth(150);
        columnBienSoXe.setPreferredWidth(90);
        columnPhiGuiXe.setPreferredWidth(80);
        
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
        btnGiaoDichDaXoa.addActionListener(ac);
        btnLayXe.addActionListener(ac);
        btnLichSuGuiXe.addActionListener(ac);
        btnXeDangGui.addActionListener(ac);
        btnXuatFileExcel.addActionListener(ac);
        
        
        lblHinh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                chonHinh();
            }
        });
        
        tblDanhSach.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2){
                    row = tblDanhSach.getSelectedRow();
                    edit();
                }
            } 
        });
        
        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                switch (index1) {
                    case 0 -> fillTable();
                    case 1 -> fillTableLichSuGuiXe();
                    default -> fillToTableGiaoDichDaXoa();
                }
            }
        });
        
        // Thêm vào panel
        LeftRightPanelHinh.add(lblHinh, BorderLayout.CENTER);
        
        LeftPanelComponents1.add(lblMaGiaoDich);
        LeftPanelComponents1.add(txtMaGiaoDich);
        LeftPanelComponents1.add(lblThoiGianVao);
        LeftPanelComponents1.add(txtThoiGianVao);
        LeftPanelComponents1.add(lblThoiGianRa);
        LeftPanelComponents1.add(txtThoiGianRa);
        
        LeftPanelComponents2.add(lblSoNgayGui);
        LeftPanelComponents2.add(txtSoNgayGui);
        LeftPanelComponents2.add(lblBienSoXe);
        LeftPanelComponents2.add(txtBienSoXe);
        LeftPanelComponents2.add(lblPhiGuiXe);
        LeftPanelComponents2.add(txtPhiGuiXe);
        
        LeftRightPanelComponents1.add(LeftRightPanelHinh);
        
        LeftPanelTrangThai.add(lblTrangThai);
        LeftPanelTrangThai.add(cboTrangThai);
        LeftPanelNhanVien.add(lblNhanVien);
        LeftPanelNhanVien.add(txtNhanVien);
        LeftPanelRight1.add(LeftPanelTrangThai);
        LeftPanelRight1.add(LeftPanelNhanVien);
        
        LeftPanelLoaiKhach.add(lblLoaiKhach);
        LeftPanelLoaiKhach.add(cboLoaiKhach);
        LeftPanelMaLoaiThe.add(lblMaTheKhach);
        LeftPanelMaLoaiThe.add(cboMaTheKhach);
        LeftPanelRight2.add(LeftPanelLoaiKhach);
        LeftPanelRight2.add(LeftPanelMaLoaiThe);
        
        LeftPanelLoaiXe.add(lblMaLoaiXe);
        LeftPanelLoaiXe.add(cboMaLoaiXe);
        LeftPanelBangGia.add(lblBangGia);
        LeftPanelBangGia.add(cboBangGia);
        LeftPanelRight3.add(LeftPanelLoaiXe);
        LeftPanelRight3.add(LeftPanelBangGia);
        
        LeftRightPanelComponents2.add(LeftPanelRight1);
        LeftRightPanelComponents2.add(LeftPanelRight2);
        LeftRightPanelComponents2.add(LeftPanelRight3);
        
        LeftPanelButton1.add(btnThem);
        LeftPanelButton1.add(btnXoa);
        LeftPanelButton1.add(btnSua);
        LeftPanelButton1.add(btnMoi);

        LeftPanelButton2.add(btnQuetHinh);
        LeftPanelButton2.add(btnFirst);
        LeftPanelButton2.add(btnPre);
        LeftPanelButton2.add(btnNext);
        LeftPanelButton2.add(btnLast);
        LeftPanelButton.add(LeftPanelButton1,BorderLayout.WEST);
        LeftPanelButton.add(LeftPanelButton2,BorderLayout.EAST);
        
        // Thêm panelComponents vào panel
        LeftPanelChung1.add(LeftPanelComponents1);
        LeftPanelChung1.add(LeftRightPanelComponents1);
        LeftPanelChung2.add(LeftPanelComponents2);
        LeftPanelChung2.add(LeftRightPanelComponents2);
        
        LeftPanelTong.add(LeftPanelChung1);
        LeftPanelTong.add(LeftPanelChung2);
        

        LeftPanel.add(lblCapNhat, BorderLayout.NORTH);
        LeftPanel.add(LeftPanelTong, BorderLayout.CENTER);
        LeftPanel.add(LeftPanelButton, BorderLayout.SOUTH);
        
        // Right table
        RightPanelSearch.add(lblSearch, BorderLayout.WEST);
        RightPanelSearch.add(txtSearch, BorderLayout.CENTER);
        RightPanelTable.add(RightPanelSearch, BorderLayout.NORTH);
        RightPanelTable.add(scroll1, BorderLayout.CENTER);
        
        RightPanelRestore1.add(btnLayXe);
        RightPanelRestore1.add(btnXuatFileExcel);
        RightPanelRestore1.add(btnXeDangGui);
        RightPanelRestore1.add(btnLichSuGuiXe);
        RightPanelRestore1.add(btnGiaoDichDaXoa);
        RightPanelRestore1.add(btnRestore);
        RightPanelRestore.add(RightPanelRestore1, BorderLayout.CENTER);
        RightPanel.add(lblDanhSach, BorderLayout.NORTH);
        RightPanel.add(RightPanelTable, BorderLayout.CENTER);
        RightPanel.add(RightPanelRestore, BorderLayout.SOUTH);
        
        panelCD.add(LeftPanel);
        panelCD.add(RightPanel);
        
        ParkFrame.CenterPanel.removeAll();
        ParkFrame.CenterPanel.add(lblGiaoDich, BorderLayout.NORTH);
        ParkFrame.CenterPanel.add(panelCD, BorderLayout.CENTER);
        ParkFrame.CenterPanel.setVisible(false);
        ParkFrame.CenterPanel.setVisible(true);
    }
    
    private void init() {
        fillTable();
        row = -1;
        index1 = 0;
//        fillToLoaiXe();
        fillToMaTheKhachHang();
        upDateStatus();
    }
    
    public void fillToMaTheKhachHang() {
        DefaultComboBoxModel theKhachHangModel = (DefaultComboBoxModel) cboMaTheKhach.getModel();
        theKhachHangModel.removeAllElements();
        
        List<TheXeCuDan> listCuDan = theXeCuDanDAO.selectALL();
        List<TheXeKhachVangLai> listKhachVangLai = theXeKhachVangLaiDAO.selectALL();
        if(cboLoaiKhach.getSelectedIndex() == 0) {
            for (TheXeKhachVangLai theXeKhachVangLai : listKhachVangLai) {
                theKhachHangModel.addElement(theXeKhachVangLai);
            }    
            fillToLoaiXe();
        }
        else {
             for (TheXeCuDan theXeCuDan : listCuDan) {
                theKhachHangModel.addElement(theXeCuDan);
            }
             fillToLoaiXe();
        }
    }
    
    public void fillToLoaiXe() {
        DefaultComboBoxModel loaiXeModel = (DefaultComboBoxModel) cboMaLoaiXe.getModel();
        loaiXeModel.removeAllElements();
        
        if(cboLoaiKhach.getSelectedIndex() == 1){
            TheXeCuDan theXeCuDan = (TheXeCuDan) cboMaTheKhach.getSelectedItem();
            if(theXeCuDan != null){
                LoaiXe list = loaiXeDAO.selectByID(theXeCuDan.getMaLoaiXe());
                loaiXeModel.addElement(list);
            }
        } else {
            TheXeKhachVangLai theXeKhachVangLai = (TheXeKhachVangLai) cboMaTheKhach.getSelectedItem();
            if(theXeKhachVangLai != null){
                LoaiXe list = loaiXeDAO.selectByID(theXeKhachVangLai.getMaLoaiXe());
                loaiXeModel.addElement(list);
            }
        }
        fillToBangGia();
    }
    
    public void fillToBangGia() {
        DefaultComboBoxModel bangGiaModel = (DefaultComboBoxModel) cboBangGia.getModel();
        bangGiaModel.removeAllElements();
        LoaiXe loaiXe = (LoaiXe) cboMaLoaiXe.getSelectedItem();
        if(loaiXe != null){
            if(loaiXe.getTenLoaiXe().equalsIgnoreCase("Xe đạp"))
                txtBienSoXe.setEnabled(false);
            else
                txtBienSoXe.setEnabled(true);
            List<BangGia> list = bangGiaDAO.selectListByID(loaiXe.getMaLoaiGia());
            for (BangGia bangGia : list) {
                bangGiaModel.addElement(bangGia);
            }
        }
            txtPhiGuiXe.setText(String.valueOf(getTongTien()));
    }
    
    public void fillTable() {
        index1 = 0;
        DefaultTableModel model = (DefaultTableModel) tblDanhSach.getModel();
        model.setRowCount(0);
        String search = txtSearch.getText();
        try {
            List<GiaoDich> list = giaoDichdao.selectBySearch(search);
            for (int i=0; i < list.size(); i++) {
                GiaoDich giaoDich = list.get(i);
                Object row[] = {i + 1, giaoDich.getMaGiaoDich(), giaoDich.getThoiGianVao(), giaoDich.getThoiGianRa(), giaoDich.getBienSoXe(), 
                    numberFormat.format(giaoDich.getPhiGuiXe()), giaoDich.isTrangThai() ? "Đã lấy" : "Đang gửi"};
                model.addRow(row);
            }
        } catch (Exception e) {
            MessageBox.alert(null, "Lỗi truy cập dữ liệu!");
        }
    }

    private void upDateStatus() {
        Boolean edit = (row>=0);
        Boolean first = (row==0);
        Boolean last = (row == (tblDanhSach.getRowCount()-1));
        
        if(!Authenticated.isManage()){
            btnXoa.setEnabled(false);
            btnRestore.setEnabled(false);
            btnGiaoDichDaXoa.setEnabled(false);
        }
        else{
            btnXoa.setEnabled(edit);
        }
            txtMaGiaoDich.setEnabled(false);
            txtThoiGianVao.setEnabled(false);
            txtThoiGianRa.setEnabled(false);
            txtSoNgayGui.setEnabled(false);
            txtPhiGuiXe.setEnabled(false);
            txtNhanVien.setEnabled(false);
            cboTrangThai.setEnabled(false);
            txtBienSoXe.setEnabled(false);

        // Trạng thái form
        btnThem.setEnabled(!edit);
        btnSua.setEnabled(edit);
        
        // Trạng thái điều hướng
        btnFirst.setEnabled(edit && !first);
        btnPre.setEnabled(edit && !first);
        btnNext.setEnabled(edit && !last);
        btnLast.setEnabled(edit && !last);
    }
    
    private GiaoDich getForm() throws ParseException {
        GiaoDich giaoDich = new GiaoDich();

        NhanVien nhanVien = nhanVienDAO.selectByUserName(txtNhanVien.getText());
//        LoaiXe loaiXe = (LoaiXe) cboMaLoaiXe.getSelectedItem();
        
        if(!txtMaGiaoDich.getText().isEmpty()){
            giaoDich.setMaGiaoDich(Integer.parseInt(txtMaGiaoDich.getText()));
            giaoDich.setThoiGianVao(DateHelper.StringtoDate(txtThoiGianVao.getText(), "dd/MM/yyyy - HH:mm:ss"));
            if(!txtThoiGianRa.getText().isEmpty())
                giaoDich.setThoiGianRa(DateHelper.StringtoDate(txtThoiGianRa.getText(), "dd/MM/yyyy - HH:mm:ss"));

            giaoDich.setSoNgayGui(Integer.parseInt(txtSoNgayGui.getText()));
            if(!txtBienSoXe.getText().isEmpty())
                giaoDich.setBienSoXe(txtBienSoXe.getText());

            giaoDich.setPhiGuiXe(Double.parseDouble(txtPhiGuiXe.getText()));
            giaoDich.setTrangThai(getTrangThai());
            giaoDich.setMaNhanVien(nhanVien.getMaNhanVien());

            if(cboLoaiKhach.getSelectedIndex() == 1)
                giaoDich.setTheCuDanID(((TheXeCuDan) cboMaTheKhach.getSelectedItem()).getMaThe());
            else
                giaoDich.setTheKhachVangLaiID(((TheXeKhachVangLai) cboMaTheKhach.getSelectedItem()).getMaThe());

            if(lblHinh.getToolTipText() == null)
                giaoDich.setHinh(null);
            else
                giaoDich.setHinh(lblHinh.getToolTipText());
        }
        return giaoDich;
    }
    
    public boolean getTrangThai(){
        if(cboTrangThai.getSelectedIndex() == 0)
            return false;
        else
            return true;
    }
    
    public NhanVien selectByMaNhanVien(int maNhanVien){
        return nhanVienDAO.selectByID(maNhanVien);
    }
    
    private void setForm(GiaoDich giaoDich) {
        NhanVien nhanVien = selectByMaNhanVien(giaoDich.getMaNhanVien());
        TheXeCuDan theXeCuDan = theXeCuDanDAO.selectByID(giaoDich.getTheCuDanID());
        TheXeKhachVangLai theXeKhachVangLai = theXeKhachVangLaiDAO.selectByID(giaoDich.getTheKhachVangLaiID());
        
        txtMaGiaoDich.setText(String.valueOf(giaoDich.getMaGiaoDich()));
        txtThoiGianVao.setText(DateHelper.DatetoString(giaoDich.getThoiGianVao(), "dd/MM/yyyy - HH:mm:ss"));
        if(giaoDich.getThoiGianRa() == null)
            txtThoiGianRa.setText("");
        else
            txtThoiGianRa.setText(DateHelper.DatetoString(giaoDich.getThoiGianRa(), "dd/MM/yyyy - HH:mm:ss"));
        
        txtSoNgayGui.setText(String.valueOf(giaoDich.getSoNgayGuiXe()));
        txtBienSoXe.setText(giaoDich.getBienSoXe());
//        txtPhiGuiXe.setText(String.valueOf(giaoDich.getPhiGuiXe()));
        cboTrangThai.setSelectedIndex(giaoDich.isTrangThai() ? 1 : 0);
        if(nhanVien != null){
            txtNhanVien.setText(nhanVien.getTenDangNhap());
        }
        
        if(theXeCuDan != null){
            LoaiXe loaiXe = loaiXeDAO.selectByID(theXeCuDan.getMaLoaiXe());
            if(loaiXe != null) {
                BangGia bangGia = bangGiaDAO.selectByID(loaiXe.getMaLoaiGia());
                cboMaLoaiXe.setSelectedItem(loaiXe);
                cboBangGia.setSelectedItem(bangGia);
            }
        }
        if(theXeKhachVangLai != null){
            LoaiXe loaiXe = loaiXeDAO.selectByID(theXeKhachVangLai.getMaLoaiXe());
            if(loaiXe != null) {
                BangGia bangGia = bangGiaDAO.selectByID(loaiXe.getMaLoaiGia());
                cboMaLoaiXe.setSelectedItem(loaiXe);
                cboBangGia.setSelectedItem(bangGia);
            }
        }
        
        if(giaoDich.getTheCuDanID() != null){
            cboLoaiKhach.setSelectedIndex(1);
            cboMaTheKhach.setSelectedItem(theXeCuDan);
        }
        else{
            cboLoaiKhach.setSelectedIndex(0);
            cboMaTheKhach.setSelectedItem(theXeKhachVangLai);
        }

        lblHinh.setToolTipText(giaoDich.getHinh());
        if(giaoDich.getHinh() != null) {
//            lblHinh.setIcon(ImagesHelper.readImageFromDirectory(giaoDich.getHinh()));
            lblHinh.setIcon(ImagesHelper.readImageFromDirectory1(giaoDich.getHinh(),lblHinh.getWidth(), lblHinh.getHeight()));
            lblHinh.setText("");
        } else {
            lblHinh.setIcon(null);
            lblHinh.setText("NO AVATAR");
        }
    }
    
    public void clearForm() throws ParseException {
        GiaoDich giaoDich = new GiaoDich();
        giaoDich.setMaGiaoDich(getMaGiaoDichMax()+1);
        giaoDich.setHinh(null);
        cboMaLoaiXe.setSelectedIndex(0);
        setForm(giaoDich);
        txtNhanVien.setText(Authenticated.user.getTenDangNhap());
        txtBienSoXe.requestFocus();
        cboLoaiKhach.setSelectedIndex(0);
//        cboMaLoaiXe.setSelectedIndex(0);
        row = -1;
        upDateStatus();
        
    }
    
    private void edit() {
        int maGiaoDich1 = (int) tblDanhSach.getValueAt(row, 1);
        GiaoDich giaoDich = giaoDichdao.selectByID(maGiaoDich1);
        setForm(giaoDich);
        upDateStatus();
        tblDanhSach.setRowSelectionInterval(row, row);
    }

    public void insert() {
        if(check()){
            try {
                GiaoDich giaoDich = getForm();
                if(cboLoaiKhach.getSelectedIndex()==1)
                    giaoDichdao.insert(giaoDich);
                else
                    giaoDichdao.insert_KhachVangLai(giaoDich);
                clearForm();
                fillTable();
                MessageBox.alert(null, "Thêm mới thành công!");
            } catch (ParseException e) {
                MessageBox.alert(null, "Thêm mới thất bại!");
            }
        }
    }

    public void update() {
        if(check()){
            try {
                GiaoDich giaoDich = getForm();
                giaoDichdao.update(giaoDich);
                clearForm();
                fillTable();
                MessageBox.alert(null, "Cập nhật thành công!");
            } catch (ParseException e) {
                MessageBox.alert(null, "Cập nhật thất bại!");
            }
        }
    }

    public void delete() {
        if(!Authenticated.isManage())
            MessageBox.alert(null, "Bạn không có quyền xóa thông tin giao dịch!");
        else if(MessageBox.confirm(null, "Bạn thực sự muốn xóa giao dịch này này?")){
            try {
                String maGiaoDich1 = txtMaGiaoDich.getText();
//                this.maGiaoDich = maGiaoDich;
                giaoDichdao.delete(maGiaoDich1);
                clearForm();
                fillTable();
                MessageBox.alert(null, "Xóa giao dịch thành công!");
            } catch (Exception e) {
                MessageBox.alert(null, "Xóa giao dịch thất bại!");
            }
        }
    }
    
    public int getMaGiaoDichMax(){
        List<GiaoDich> list = giaoDichdao.selectALL();
        return list.get(list.size()-1).getMaGiaoDich();
    }

    public void first() {
        row = 0;
        this.edit();
    }

    public void previous() {
        if(row>0){
            row--;
            this.edit();
        }
    }

    public void next() {
        if(row<tblDanhSach.getRowCount()-1){
            row++;
            this.edit();
        }
    }

    public void last() {
        row = tblDanhSach.getRowCount()-1;
        this.edit();
    }

    public void chonHinh() {
        fileChooser.setDialogTitle("Chọn bảng số xe");
        if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
        File file = fileChooser.getSelectedFile();
        ImagesHelper.saveImageToDirectory(file); //lưu hình vào thư mục logos
//        ImageIcon icon = ImagesHelper.readImageFromDirectory(file.getName()); // Đọc hình từ logos
//        icon.getImage().getScaledInstance(lblHinh.getWidth(), lblHinh.getHeight(), Image.SCALE_DEFAULT);
        ImageIcon iconImage = ImagesHelper.readImageFromDirectory1(file.getName(), lblHinh.getWidth(), lblHinh.getHeight());
//        try {
//            Image img = ImageIO.read(file);
//            int width = lblHinh.getWidth();
//            int height = lblHinh.getHeight();
//            lblHinh.setIcon(new ImageIcon(img.getScaledInstance(width, height, 0)));
//        } catch (IOException e) {
//            throw new RuntimeException();
//        }
        lblHinh.setIcon(iconImage);
        lblHinh.setToolTipText(file.getName()); // giữ tên hình trong tooltip
        lblHinh.setText("");
//        if(iconImage != null){
//            quetHinh("D:\\images\\"+file.getName());
//        }
        }
    }
    
    public double getTongTien(){
        BangGia bangGia = (BangGia) cboBangGia.getSelectedItem();
        if(bangGia != null){
            int soNgayGui = Integer.parseInt(txtSoNgayGui.getText());
            int dem = 0;
            if(soNgayGui == 0)
                return bangGia.getGia();
            else {
                if(bangGia.getThoiGian().equalsIgnoreCase("Ngày")) {
                    return soNgayGui * bangGia.getGia();
                }
                else if(bangGia.getThoiGian().equalsIgnoreCase("Tuần")){
                    if(soNgayGui <= 7) {
                        return bangGia.getGia();
                    }
                    for (int i = 7; i < 365; i +=7) {
                        dem++;
                        if(soNgayGui < i){
                            return dem * bangGia.getGia();
                        }
                    }
                }
                else if(bangGia.getThoiGian().equalsIgnoreCase("Tháng")){
                    if(soNgayGui <= 30) {
                        return bangGia.getGia();
                    }
                    for (int i = 30; i < 3650; i +=30) {
                        dem++;
                        if(soNgayGui < i){
                            return dem * bangGia.getGia();
                        }
                    }
                }
                else {
                    if(soNgayGui <= 365) {
                        return bangGia.getGia();
                    }
                    for (int i = 365; i < 3650; i +=365) {
                        dem++;
                        if(soNgayGui < i){
                            return dem * bangGia.getGia();
                        }
                    }
                }
            }
        }
        return 0;
    }
    
    public void restore(){
        maGiaoDich = txtMaGiaoDich.getText();
        if(maGiaoDich.isEmpty())
            MessageBox.alert(null, "Vui lòng chọn giao dịch muốn khôi phục!");
        else if (giaoDichdao.selectBbyDeleted(maGiaoDich).isEmpty())
            MessageBox.alert(null, "Giao dịch này chưa xóa, không thể khôi phục!");
        else if(MessageBox.confirm(null, "Bạn muốn khôi phục giao dịch này?")) {
            try {
                giaoDichdao.restore(maGiaoDich);
                fillToTableGiaoDichDaXoa();
                clearForm();
                this.maGiaoDich = null;
                MessageBox.alert(null, "Khôi phục giao dịch thành công!");
            } catch (ParseException e) {
                MessageBox.alert(null, "Khôi phục giao dịch thất bại!");
            }
        }
    }
    
    public void fillToTableGiaoDichDaXoa(){
        index1 = 2;
        DefaultTableModel model = (DefaultTableModel) tblDanhSach.getModel();
        model.setRowCount(0);
        String search = txtSearch.getText();
        try {
            List<GiaoDich> list = giaoDichdao.selectBySearchGiaoDichDaXoa(search);
            for (int i=0; i < list.size(); i++) {
                GiaoDich giaoDich = list.get(i);
                Object row[] = {i + 1, giaoDich.getMaGiaoDich(), giaoDich.getThoiGianVao(), giaoDich.getThoiGianRa(), giaoDich.getBienSoXe(), 
                    numberFormat.format(giaoDich.getPhiGuiXe()), giaoDich.isTrangThai() ? "Đã lấy" : "Đang gửi"};
                model.addRow(row);
            }
        } catch (Exception e) {
            MessageBox.alert(null, "Lỗi truy cập dữ liệu!");
        }
    }
    
    public void fillTableLichSuGuiXe() {
        index1 = 1;
        DefaultTableModel model = (DefaultTableModel) tblDanhSach.getModel();
        model.setRowCount(0);
        String search = txtSearch.getText();
        try {
            List<GiaoDich> list = giaoDichdao.selectBySearchLichSuGuiXe(search);
            for (int i=0; i < list.size(); i++) {
                GiaoDich giaoDich = list.get(i);
                Object row[] = {i + 1, giaoDich.getMaGiaoDich(), giaoDich.getThoiGianVao(), giaoDich.getThoiGianRa(), giaoDich.getBienSoXe(), 
                    numberFormat.format(giaoDich.getPhiGuiXe()), giaoDich.isTrangThai() ? "Đã lấy" : "Đang gửi"};
                model.addRow(row);
            }
        } catch (Exception e) {
            MessageBox.alert(null, "Lỗi truy cập dữ liệu!");
        }
    }
    
    public void layXe(){
        GiaoDich giaoDich = giaoDichdao.selectByID(txtMaGiaoDich.getText());
        if(txtMaGiaoDich.getText().isEmpty())
            MessageBox.alert(null, "Vui lòng chọn giao dịch cần lấy!");
        else if(giaoDich != null) {
            if(giaoDich.isTrangThai())
                MessageBox.alert(null, "Xe này đã được lấy rồi!");
            else if(MessageBox.confirm(null, "Bạn muốn lấy xe này?")) {
                try {
                    giaoDich.setThoiGianRa(new Date());
                    txtSoNgayGui.setText(String.valueOf(giaoDich.getSoNgayGuiXe()));
                    double tongTien = getTongTien();
                    giaoDich.setPhiGuiXe(tongTien);
                    txtPhiGuiXe.setText(String.valueOf(tongTien));
                    
                    
                    TheXeCuDan theXeCuDan = theXeCuDanDAO.selectByID(getMaThe(giaoDich));
                    TheXeKhachVangLai theXeKhachVangLai = theXeKhachVangLaiDAO.selectByID(getMaThe(giaoDich));
                    
                    LoaiXe loaiXe = null;
                    if(theXeCuDan != null)
                        loaiXe = loaiXeDAO.selectByID(theXeCuDan.getMaLoaiXe());
                    else if(theXeKhachVangLai != null)
                        loaiXe = loaiXeDAO.selectByID(theXeKhachVangLai.getMaLoaiXe());
                    
                    if(loaiXe != null){
                        BangGia bangGia = bangGiaDAO.selectByID(loaiXe.getMaLoaiGia());
                        if(bangGia != null) {
                            if(MessageBox.confirm(null, "Quét bằng mã QR ?")) {
                                try {
                                    String qrCodeText = "Ma the: " + getMaThe(giaoDich) + "\n"
                                                        + "Loai xe: " + loaiXe.getMaLoaiXe() + "\n"
                                                        + "Bien so xe: " + giaoDich.getBienSoXe() + "\n"
                                                        + "Dang ky gui theo: " + bangGia.getThoiGian() + "\n"
                                                        + "Bang gia: " + bangGia.getGia() + " VND\n"
                                                        + "Thoi gian vao: " + DateHelper.DatetoString(giaoDich.getThoiGianVao(), "dd/MM/yyyy - HH:mm:ss") + "\n"
                                                        + "Thoi gian ra: " + DateHelper.DatetoString(giaoDich.getThoiGianRa(), "dd/MM/yyyy - HH:mm:ss") + "\n"
                                                        + "So ngay gui: " + giaoDich.getSoNgayGuiXe() + " Ngay \n"
                                                        + "Phi gui xe: " + tongTien + " VND";
                                    String filePath = getMaThe(giaoDich) + "_"+ loaiXe.getMaLoaiXe() + "_"+ giaoDich.getBienSoXe()+".jpg";
                                    File destination = new File("storeFiles", filePath);
                                    filePath = Paths.get(destination.getAbsolutePath()).toString();
                                    int size = 200;
                                    String fileType = "jpg";
                                    File qrFile = new File(filePath);
                                    QRCodeUtil.createQRImage(qrFile, qrCodeText, size, fileType);
                                    GiaoDichJDialog.qrCodeImage = filePath;
                                     new QRCodeJDialog(null, true).setVisible(true);
                                } catch (WriterException | IOException ex) {
                                }
                            }
                            else {
                                MessageBox.alert(null, "Mã thẻ: " + getMaThe(giaoDich) + "\n"
                                                + "Mã loại xe: " + loaiXe.getMaLoaiXe() + "\n"
                                                + "Biển số xe: " + giaoDich.getBienSoXe()+ "\n"
                                                + "Dang ky gui theo: " + bangGia.getThoiGian() + "\n"
                                                + "Bảng giá: " + bangGia.getGia()+ " VND\n"
                                                + "Thoi gian vao: " + DateHelper.DatetoString(giaoDich.getThoiGianVao(), "dd/MM/yyyy - HH:mm:ss") + "\n"
                                                + "Thoi gian ra: " + DateHelper.DatetoString(giaoDich.getThoiGianRa(), "dd/MM/yyyy - HH:mm:ss") + "\n"
                                                + "Số ngày gửi xe: " + giaoDich.getSoNgayGuiXe() + " Ngày\n"
                                                + "Phí gửi xe: " + tongTien + " VND");
                            }
                            giaoDichdao.layXe(giaoDich);
                            MessageBox.alert(null, "Lấy xe thành công!");
                        }
                    }
                    fillTable();
                    clearForm();
//                    MessageBox.alert(null, "Lấy xe thành công!");
                } catch (ParseException e) {
                    MessageBox.alert(null, "Lấy xe thất bại!");
                } 
            }
        }
    }
    
    public String getMaThe(GiaoDich giaoDich) {
        if(giaoDich.getTheCuDanID() == null)
            return giaoDich.getTheKhachVangLaiID();
        else
            return giaoDich.getTheCuDanID();
    }
    
    public boolean check(){  // 59 - G1 67890  51A - 138.83 
        if(txtBienSoXe.getText().isEmpty()){
            txtBienSoXe.setBorder(new LineBorder(Color.black));
            return true;
        } 
         if(txtBienSoXe.getText().length() < 8 || txtBienSoXe.getText().length() > 13){
             txtBienSoXe.requestFocus();
            txtBienSoXe.setBorder(new LineBorder(Color.red));
            MessageBox.alert(null, "Biển số xe từ 8 - 13 ký tự!");     
            return false;
        }
         txtBienSoXe.setBorder(new LineBorder(Color.black));
        return true;
    }
    
    public void xuatDanhSachGiaoDich(){
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setDialogTitle("Chọn thư mục lưu tệp excel");
        int result = fileChooser.showSaveDialog(null);
        
        
        try {
            XSSFWorkbook workBook = new XSSFWorkbook();
            XSSFSheet sheet = workBook.createSheet("Giao dịch gửi xe");
            XSSFRow row = null;
            Cell cell = null;
            
            row = sheet.createRow(3);
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("STT");
            
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("MÃ");
            
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("THỜI GIAN VÀO");
            
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("THỜI GIAN RA");
            
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("BIỂN SỐ XE");
            
            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("PHÍ GỬI XE");
            
            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("TRẠNG THÁI");
            
            cell = row.createCell(9, CellType.STRING);
            cell.setCellValue("LOẠI THẺ");
            
            String search = txtMaGiaoDich.getText();
            List<GiaoDich> list;
            
            if(index1 == 0){
                list = giaoDichdao.selectBySearch(search);
            }
            else if(index1 == 1){
                list = giaoDichdao.selectBySearchLichSuGuiXe(search);
            }
            else {
                list = giaoDichdao.selectBySearchGiaoDichDaXoa(search);
            }
            for (int i = 0; i < list.size(); i++) {
                GiaoDich giaoDich = list.get(i);
                
                row = sheet.createRow(4+i);
                cell = row.createCell(2, CellType.NUMERIC);
                cell.setCellValue(i+1);
                
                cell = row.createCell(3, CellType.NUMERIC);
                cell.setCellValue(giaoDich.getMaGiaoDich());
            
                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(DateHelper.DatetoString(giaoDich.getThoiGianVao(), "dd/MM/yyyy - HH:mm:ss"));
                
                cell = row.createCell(5, CellType.STRING);
                if(giaoDich.getThoiGianRa() == null)
                    cell.setCellValue("");
                else
                    cell.setCellValue(DateHelper.DatetoString(giaoDich.getThoiGianRa(), "dd/MM/yyyy - HH:mm:ss"));

                cell = row.createCell(6, CellType.STRING);
                cell.setCellValue(giaoDich.getBienSoXe());
                
                cell = row.createCell(7, CellType.NUMERIC);
                cell.setCellValue(giaoDich.getPhiGuiXe());
                
                cell = row.createCell(8, CellType.NUMERIC);
                cell.setCellValue(giaoDich.isTrangThai());
                
                cell = row.createCell(9, CellType.STRING);
                if(giaoDich.getTheCuDanID() == null)
                    cell.setCellValue(giaoDich.getTheKhachVangLaiID());
                else
                    cell.setCellValue(giaoDich.getTheCuDanID());
            }
            
//            File file = new File("D:\\HUU NGHIA\\java\\SOFT203_PS28127_JAVA3\\src\\park\\DanhSachGiaoDichGuiXe.xlsx");
            if(result == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file + ".xlsx");
                workBook.write(fileOutputStream);
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                MessageBox.alert(null, "Xuất danh sách thất bại! Bạn chưa đóng file excel!");
                return;
            }
            catch(IOException ex){
                throw new RuntimeException();
            }
            MessageBox.alert(null, "Xuất danh sách thành công!");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    
   
    
    
    
//    --------------------------------
    
    static {
        new RunOpenCV().start();
    }
    private DaemonThread myThread = null;
    private VideoCapture webSource = null;
    public final Mat frame = new Mat(1000, 1000, 1);
    public final MatOfByte mem = new MatOfByte();


    private class DaemonThread implements Runnable {

        protected volatile boolean runnable = false;
        private JLabel display;

        public DaemonThread(JLabel displayJLabel) {
            this.display = displayJLabel;
        }

        @Override
        public void run() {
            synchronized (this) {
                while (runnable) {
                    if (webSource.grab()) {
                        try {
                            webSource.retrieve(frame);
                            Imgcodecs.imencode(".bmp", frame, mem);

                            Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));

                            BufferedImage buff = (BufferedImage) im;
                            Graphics g = display.getGraphics();

                            if (g.drawImage(buff, 1, 1, display.getWidth(), display.getHeight(), null)) {
                                if (runnable == false) {
                                    this.wait();
                                }
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }
    }
     public void startCamere(){
       ComputerVision computerVision = new ComputerVision();
       webSource = new VideoCapture(0);
        myThread = new DaemonThread(lblHinh);
        Thread t = new Thread(myThread);
        t.setDaemon(true);
        myThread.runnable = true;
        t.start();
    }
    
    public void stopWebCamera() {
        if (myThread != null) {
            if (myThread.runnable == true) {
                myThread.runnable = false;
                webSource.release();
            }
        }
    }
    
    public  File get_image_file;
    public  final SecureRandom RAND = new SecureRandom();
    public  String IMAGE_FILE_NAME = null;
    
    private void setWebcamCapturedImageonLable(JLabel image) {
        try {
            stopWebCamera();
            if (get_image_file != null) {
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(IMAGE_FILE_NAME).getImage().getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_DEFAULT));
                image.setIcon(imageIcon);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Warning", 0);
        }
    }
    
    public void chupHinh(){
        stopWebCamera();
        int option = JOptionPane.showConfirmDialog(null, "Bạn Có Chắc Chắn Chụp Ảnh Không ?");
        if (option == 0) {
            try {
                File file = new File("Pictures");
                boolean flag = true;
                if (!file.isDirectory()) {
                    flag = file.mkdir();
                }
                if (!flag) {
                    throw new Exception("Folder not exist: Thư Mục Không Tồn Tại ");
                }
                int image_number = 1 + RAND.nextInt(999999999);
                IMAGE_FILE_NAME = file.getAbsolutePath() + "\\" + image_number + ".jpg";
                Imgcodecs.imwrite(IMAGE_FILE_NAME, frame);
                get_image_file = file;
                setWebcamCapturedImageonLable(lblHinh);
//                JOptionPane.showMessageDialog(null, "Image Saved  Successfully  : Đã Lưu Hình Ảnh Thành Công\nDirectory: Danh Mục " + IMAGE_FILE_NAME);
                String plateNumber = new CarPlateRecognize().recognize(IMAGE_FILE_NAME);
                txtBienSoXe.setText(plateNumber.toUpperCase());
                
                File fileName = new File(file.getAbsolutePath() + "\\" + image_number + ".jpg");
                ImagesHelper.saveImageToDirectory(fileName); // lưu hình vào file logos
                lblHinh.setToolTipText(image_number + ".jpg");
                try {
                    // Thực hiện việc thay thế file
                    Files.copy(Path.of(IMAGE_FILE_NAME), Path.of(file.getAbsolutePath() + "\\" + plateNumber.toUpperCase() + ".jpg"), StandardCopyOption.REPLACE_EXISTING);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                stopWebCamera();
                JOptionPane.showMessageDialog(null, e, "Wraning", 0);
            }

        }
    }
    
    public void StartChupHinh() {
        startCamere();
        Thread thread = new Thread(() -> {
            // Ngủ trong 5 giây
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            chupHinh();
        });

        // Khởi động luồng
        thread.start();
    }
    
    
//    public void quetHinh(String image){
//        File imageFile = new File(image);
//        ITesseract instance = new Tesseract();
//        instance.setLanguage("eng");
//        try {
//            instance.setDatapath("D:\\HUU NGHIA\\java\\Tess4J\\tessdata");
//            String result = instance.doOCR(imageFile);
//            txtBienSoXe.setText(result);
//            System.out.println("Bien so xe: "+ result);
//        } catch (TesseractException e) {
//            throw new RuntimeException();
//        }
//    }
}
