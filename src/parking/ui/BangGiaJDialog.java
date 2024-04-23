
package parking.ui;

import parking.controller.BangGiaController;
import parking.dao.BangGiaDAO;
import parking.entity.BangGia;
import parking.utils.Authenticated;
import parking.utils.MessageBox;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class BangGiaJDialog {
    BangGiaDAO bangGiaDAO = new BangGiaDAO();
    BangGiaController ac = new BangGiaController(this);
    NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
    int row = -1;
    
    private  JTable tblDanhSach;
    private  JButton btnThem;
    private  JButton btnSua;
    private  JButton btnXoa;
    private  JButton btnMoi;
    private  JButton btnFirst;
    private  JButton btnPre;
    private  JButton btnNext;
    private  JButton btnLast;
    private JTextField txtMaLoaiGia;
    private JTextField txtGia;
    private JComboBox<Object> cboThoiGian;
    private DefaultTableModel bangGiaModel;
    private JLabel lblMaLoaiGiaThongBao2;
    private JLabel lblGiaThongBao2;

    public void BangGiaJDialog() {
        Combonensts();
        init();
    }
    
    private void Combonensts() {
        // Tạo panel
        JPanel panelNV = new JPanel(new GridLayout(1, 2,10, 10));
        JPanel LeftPanel = new JPanel(new BorderLayout());
        JPanel LeftPanelLoaiGia = new JPanel(new BorderLayout());
        JPanel LeftPanelLoaiGia1 = new JPanel(new GridLayout(1, 3));
        JPanel LeftPanelGia = new JPanel(new BorderLayout());
        JPanel LeftPanelGia1 = new JPanel(new GridLayout(1, 3));
        JPanel LeftPanelCom = new JPanel(new GridLayout(10, 1));
        JPanel LeftPanelButton1 = new JPanel(new GridLayout(1,4,10,10));
        JPanel LeftPanelButton2 = new JPanel(new GridLayout(1,4,10,10));
        JPanel LeftPanelButton = new JPanel(new BorderLayout());
        JPanel RightPanel = new JPanel(new BorderLayout());

        // Font
        Font font = new Font("Arial", Font.BOLD, 18);
        
        // Cập nhật - Label
        JLabel lblQLBangGia = new JLabel("QUẢN LÝ BẢNG GIÁ");
        JLabel lblCapNhat = new JLabel("CẬP NHẬT");
        JLabel lblDanhSach = new JLabel("DANH SÁCH");
        
        JLabel lblMaLoaiGia = new JLabel("Mã loại giá (VD: GOTO01) ");
        JLabel lblThoiGian = new JLabel("Thời gian");
        JLabel lblGia = new JLabel("Giá ");
        
        JLabel lblMaLoaiGiaThongBao1 = new JLabel("(*)");
        lblMaLoaiGiaThongBao2 = new JLabel();
        JLabel lblGiaThongBao1 = new JLabel("(*)");
        lblGiaThongBao2 = new JLabel();

        lblMaLoaiGiaThongBao1.setForeground(Color.red);
        lblMaLoaiGiaThongBao2.setForeground(Color.red);
        lblGiaThongBao1.setForeground(Color.red);
        lblGiaThongBao2.setForeground(Color.red);
        
        // Text field
        txtMaLoaiGia = new JTextField();
        txtGia = new JTextField();
        
        cboThoiGian = new JComboBox<>();
        cboThoiGian.addItem("Ngày");
        cboThoiGian.addItem("Tuần");
        cboThoiGian.addItem("Tháng");
        cboThoiGian.addItem("Năm");
        
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

        // Actionlistener
        btnThem.addActionListener(ac);
        btnSua.addActionListener(ac);
        btnXoa.addActionListener(ac);
        btnMoi.addActionListener(ac);
        btnFirst.addActionListener(ac);
        btnPre.addActionListener(ac);
        btnNext.addActionListener(ac);
        btnLast.addActionListener(ac);
        
        // Thanh cuộn
        JScrollPane scroll1 = new JScrollPane();
        
        // Setting 
        lblQLBangGia.setFont(font);
        lblQLBangGia.setForeground(Color.blue);
        lblQLBangGia.setHorizontalAlignment(JLabel.CENTER);
        
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
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "MÃ LOẠI GIÁ", "THỜI GIAN", "GIÁ"
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
        scroll1.setViewportView(tblDanhSach);
        
        tblDanhSach.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2){
                    row = tblDanhSach.getSelectedRow();
                    edit();
                }
            }
            
        });
        
        txtGia.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!Character.isDigit(c) && !Character.isISOControl(c))
                    e.consume();
            }
        });
        
        // Thêm vào panel
        LeftPanelLoaiGia1.add(lblMaLoaiGia);
        LeftPanelLoaiGia1.add(lblMaLoaiGiaThongBao1);
        LeftPanelLoaiGia1.add(lblMaLoaiGiaThongBao2);
        LeftPanelLoaiGia.add(LeftPanelLoaiGia1, BorderLayout.WEST);
        
        LeftPanelGia1.add(lblGia);
        LeftPanelGia1.add(lblGiaThongBao1);
        LeftPanelGia1.add(lblGiaThongBao2);
        LeftPanelGia.add(LeftPanelGia1, BorderLayout.WEST);
        
        LeftPanelCom.add(LeftPanelLoaiGia);
        LeftPanelCom.add(txtMaLoaiGia);
        LeftPanelCom.add(lblThoiGian);
        LeftPanelCom.add(cboThoiGian);
        LeftPanelCom.add(LeftPanelGia);
        LeftPanelCom.add(txtGia);
        
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
        LeftPanel.add(lblCapNhat, BorderLayout.NORTH);
        LeftPanel.add(LeftPanelCom, BorderLayout.CENTER);
        LeftPanel.add(LeftPanelButton, BorderLayout.SOUTH);
        
//        RightPanelButton1.add(btnTheXeCuDan);
//        RightPanelButton1.add(btnTheXeKhachVangLai);
//        RightPanelButton.add(RightPanelButton1, BorderLayout.EAST);
        RightPanel.add(lblDanhSach, BorderLayout.NORTH);
        RightPanel.add(scroll1, BorderLayout.CENTER);
        
        panelNV.add(LeftPanel);
        panelNV.add(RightPanel);
        
        ParkFrame.CenterPanel.removeAll();
        ParkFrame.CenterPanel.add(lblQLBangGia, BorderLayout.NORTH);
        ParkFrame.CenterPanel.add(panelNV, BorderLayout.CENTER);
        ParkFrame.CenterPanel.setVisible(false);
        ParkFrame.CenterPanel.setVisible(true);
    }
    
    private void init(){
        fillTable();
        row = -1;
        upDateStatus();
    }
    
    public void fillTable(){
        bangGiaModel = (DefaultTableModel) tblDanhSach.getModel();
        bangGiaModel.setRowCount(0);
        try {
            List<BangGia> list = bangGiaDAO.selectALL();
            for (int i =0; i < list.size(); i++) {
                BangGia bangGia = list.get(i);
                Object row[] = {i + 1, bangGia.getMaLoaiGia(), bangGia.getThoiGian(), numberFormat.format(bangGia.getGia())};
                bangGiaModel.addRow(row);
            }
        } catch (Exception e) {
            MessageBox.alert(null, "Lỗi truy vấn dữ liệu");
        }
    }
    
    public void upDateStatus(){
        Boolean edit = (row>=0);
        Boolean first = (row==0);
        Boolean last = (row == (tblDanhSach.getRowCount()-1));
        
        // Trạng thái form
        if(!Authenticated.isManage()){
            btnXoa.setEnabled(false);
            btnThem.setEnabled(false);
            btnSua.setEnabled(false);
            btnMoi.setEnabled(false);
        }
        else {
             btnThem.setEnabled(!edit);
            btnSua.setEnabled(edit);
            btnXoa.setEnabled(edit);
        }
        txtMaLoaiGia.setEnabled(false);

        // Trạng thái điều hướng
        btnFirst.setEnabled(edit && !first);
        btnPre.setEnabled(edit && !first);
        btnNext.setEnabled(edit && !last);
        btnLast.setEnabled(edit && !last);
    }
    
    public BangGia getForm() throws ParseException{
        BangGia bangGia = new BangGia();
        bangGia.setMaLoaiGia(txtMaLoaiGia.getText());
        bangGia.setThoiGian(String.valueOf(cboThoiGian.getSelectedItem()));
        bangGia.setGia(Double.parseDouble(txtGia.getText()));
        return bangGia;
    }
    
    public void setForm(BangGia bangGia){
        txtMaLoaiGia.setText(bangGia.getMaLoaiGia());
        cboThoiGian.setSelectedItem(bangGia.getThoiGian());
        txtGia.setText(String.valueOf(bangGia.getGia()));
    }
    
    public void clearForm() {
        BangGia bangGia = new BangGia();
        setForm(bangGia);
        txtMaLoaiGia.requestFocus();
        cboThoiGian.setSelectedIndex(0);
        row = -1;
        upDateStatus();
        txtMaLoaiGia.setEnabled(true);
    }
    
    private void edit() {
        String maLoaiGia = (String) tblDanhSach.getValueAt(row, 1);
        BangGia bangGia = bangGiaDAO.selectByID(maLoaiGia);
        if(bangGia != null) {
            setForm(bangGia);
            tblDanhSach.setRowSelectionInterval(row, row);
            upDateStatus();
        }
    }

    public void insert(){
        if(check1() == 2) {
            BangGia bangGia1 = bangGiaDAO.selectByID(txtMaLoaiGia.getText());
            if(bangGia1 != null) {
                txtMaLoaiGia.requestFocus();
                MessageBox.alert(null, "Bảng giá này đã tồn tại");
                return;
            }
            try {
                BangGia bangGia = getForm();
                bangGiaDAO.insert(bangGia);
                fillTable();
                clearForm();
                MessageBox.alert(null, "Thêm mới thành công!");
            } catch (ParseException e) {
                MessageBox.alert(null, "Thêm mới thất bại!");
            }
        }
    }
    
    public void upDate() {
        if(check1() == 2) {
            if(MessageBox.confirm(null, "Bạn thực sự muốn sửa bảng giá này?")){
                try {
                    BangGia bangGia = getForm();
                    bangGiaDAO.update(bangGia);
                    fillTable();
                    clearForm();
                    MessageBox.alert(null, "Cập nhật thành công!");
                } catch (ParseException e) {
                    MessageBox.alert(null, "Cập nhật thất bại!");
                }
            }
        }
    }
    
    public void delete() {
        if(MessageBox.confirm(null, "Bạn thực sự muốn xóa bảng giá này?")){
            try {
                bangGiaDAO.delete(txtMaLoaiGia.getText());
                fillTable();
                clearForm();
                MessageBox.alert(null, "Xóa bảng giá thành công!");
            } catch (Exception e) {
                MessageBox.alert(null, "Xóa bảng giá thất bại!");
            }
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
    
//    public boolean check(){
//        if(txtMaLoaiGia.getText().isEmpty()){
//            txtMaLoaiGia.requestFocus();
//            txtMaLoaiGia.setBorder(new LineBorder(Color.red));
//            MessageBox.alert(null, "Vui lòng nhập mã thẻ!");
//            return false;
//        }
//        else if(txtMaLoaiGia.getText().length() > 6) {
//            txtMaLoaiGia.requestFocus();
//            txtMaLoaiGia.setBorder(new LineBorder(Color.red));
//            MessageBox.alert(null, "Độ dài ít hơn 7 ký tự!");
//            return false;
//        }
//        else {
//            txtMaLoaiGia.setBorder(new LineBorder(Color.black));
//            return true;
//        }
//    }
    
    public int check1(){
        int dem = 0;
        // Check mã loại giá
        String mauMaLoaiGia = "[A-Z0-9]{1,6}";
        if(txtMaLoaiGia.getText().isEmpty()){
            lblMaLoaiGiaThongBao2.setText("Vui lòng nhập mã loại giá!");
            txtMaLoaiGia.setBorder(BorderFactory.createLineBorder(Color.red));
        } else if(!txtMaLoaiGia.getText().matches(mauMaLoaiGia)){
            lblMaLoaiGiaThongBao2.setText("Độ dài từ 1 đến 6 ký tự, không dấu!");
            txtMaLoaiGia.setBorder(BorderFactory.createLineBorder(Color.red));
        }
        else {
            lblMaLoaiGiaThongBao2.setText("");
            txtMaLoaiGia.setBorder(BorderFactory.createLineBorder(Color.black));
            dem++;
        }
        
        // Check giá
        if(txtGia.getText().isEmpty()){
            lblGiaThongBao2.setText("Vui lòng nhập giá tiền!");
            txtGia.setBorder(BorderFactory.createLineBorder(Color.red));
        } 
        else {
            lblGiaThongBao2.setText("");
            txtGia.setBorder(BorderFactory.createLineBorder(Color.black));
            dem++;
        }
        return dem;
    }

}
