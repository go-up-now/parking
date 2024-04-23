
package parking.ui;

import parking.controller.TheXeController;
import parking.dao.CuDanDAO;
import parking.dao.LoaiXeDAO;
import parking.dao.TheXeCuDanDAO;
import parking.dao.TheXeKhachVangLaiDAO;
import parking.entity.CuDan;
import parking.entity.LoaiXe;
import parking.entity.TheXeCuDan;
import parking.entity.TheXeKhachVangLai;
import parking.utils.Authenticated;
import parking.utils.DateHelper;
import parking.utils.MessageBox;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class TheXeJDialog {
    TheXeCuDanDAO theXeCuDanDAO = new TheXeCuDanDAO();
    TheXeKhachVangLaiDAO theXeKhachVangLaiDAO = new TheXeKhachVangLaiDAO();
    LoaiXeDAO loaiXeDAO = new LoaiXeDAO();
    CuDanDAO cuDanDAO = new CuDanDAO();
    TheXeController ac = new TheXeController(this);
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
    private DefaultTableModel TheXeModel;
    private JButton btnTheXeCuDan;
    private JButton btnTheXeKhachVangLai;
    private JComboBox<Object> cboLoaiXe;

    public void TheXeJDialog() {
        Combonensts();
        init();
    }
    private JTextField txtMaThe;
    private JTextField txtThoiGianDangKy;
    private JComboBox<Object> cboLoaiKhachHang;
    private JComboBox<Object> cboMaKhachHang;
    

    private void Combonensts() {
        // Tạo panel
        JPanel panelTheXe = new JPanel(new GridLayout(1, 2,10, 10));
        JPanel LeftPanel = new JPanel(new BorderLayout());
        JPanel LeftPanelCom = new JPanel(new GridLayout(10, 1));
        JPanel LeftPanelButton1 = new JPanel(new GridLayout(1,4,10,10));
        JPanel LeftPanelButton2 = new JPanel(new GridLayout(1,4,10,10));
        JPanel LeftPanelButton = new JPanel(new BorderLayout());
        JPanel RightPanel = new JPanel(new BorderLayout());
        JPanel RightPanelButton = new JPanel(new BorderLayout());
        JPanel RightPanelButton1 = new JPanel(new GridLayout(1, 2, 10, 10));

        // Font
        Font font = new Font("Arial", Font.BOLD, 18);
        
        // Cập nhật - Label
        JLabel lblQLTheGuiXe = new JLabel("QUẢN LÝ THẺ GỬI XE");
        JLabel lblCapNhat = new JLabel("CẬP NHẬT");
        JLabel lblDanhSach = new JLabel("DANH SÁCH");
        
        JLabel lblMaThe = new JLabel("Mã thẻ (VD: TVL0001 hoặc TCD0001)");
        JLabel lblThoiGianDangKy = new JLabel("Thời gian đăng ký");
        JLabel lblLoaiKhachHang = new JLabel("Loại khách hàng");
        JLabel lblMaKhachHang = new JLabel("Mã khách hàng");
        JLabel lblLoaiXe = new JLabel("Loại xe");
        
        // Text field
        txtMaThe = new JTextField();
        txtThoiGianDangKy = new JTextField();
        
        cboLoaiKhachHang = new JComboBox<>();
        cboLoaiKhachHang.addItem("Khách vãng lai");
        cboLoaiKhachHang.addItem("Cư dân");
        cboMaKhachHang = new JComboBox<>();
        cboLoaiXe = new JComboBox<>();
        
        cboLoaiKhachHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillToMaKhachHang();
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
        btnTheXeCuDan = new JButton("Thẻ xe cư dân");
        btnTheXeCuDan.setFocusable(false);
        btnTheXeKhachVangLai = new JButton("Thẻ xe khách vãng lai");
        btnTheXeKhachVangLai.setFocusable(false);
        
        // Actionlistener
        btnThem.addActionListener(ac);
        btnSua.addActionListener(ac);
        btnXoa.addActionListener(ac);
        btnMoi.addActionListener(ac);
        btnFirst.addActionListener(ac);
        btnPre.addActionListener(ac);
        btnNext.addActionListener(ac);
        btnLast.addActionListener(ac);
        btnTheXeCuDan.addActionListener(ac);
        btnTheXeKhachVangLai.addActionListener(ac);
        
        // Thanh cuộn
        JScrollPane scroll1 = new JScrollPane();
        
        // Setting 
        lblQLTheGuiXe.setFont(font);
        lblQLTheGuiXe.setForeground(Color.blue);
        lblQLTheGuiXe.setHorizontalAlignment(JLabel.CENTER);
        
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
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "MÃ THẺ", "THỜI GIAN ĐĂNG KÝ", "LOẠI KHÁCH", "MÃ KHÁCH", "LOẠI XE"
            }
        ){
            Boolean[] canEdit = new Boolean[]{
                false,false,false,false,false, false
            };

            @Override
            public boolean isCellEditable(int row, int column) {
                return canEdit[column];
            }
        });
        scroll1.setViewportView(tblDanhSach);
        
        TableColumn columnThoiGian = tblDanhSach.getColumn("THỜI GIAN ĐĂNG KÝ");
        columnThoiGian.setPreferredWidth(150);
        
        tblDanhSach.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2){
                    row = tblDanhSach.getSelectedRow();
                    edit();
                }
            }
            
        });
        
        // Thêm vào panel
        LeftPanelCom.add(lblMaThe);
        LeftPanelCom.add(txtMaThe);
        LeftPanelCom.add(lblThoiGianDangKy);
        LeftPanelCom.add(txtThoiGianDangKy);
        LeftPanelCom.add(lblLoaiKhachHang);
        LeftPanelCom.add(cboLoaiKhachHang);
        LeftPanelCom.add(lblMaKhachHang);
        LeftPanelCom.add(cboMaKhachHang);
        LeftPanelCom.add(lblLoaiXe);
        LeftPanelCom.add(cboLoaiXe);
        
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
        
        RightPanelButton1.add(btnTheXeCuDan);
        RightPanelButton1.add(btnTheXeKhachVangLai);
        RightPanelButton.add(RightPanelButton1, BorderLayout.EAST);
        RightPanel.add(lblDanhSach, BorderLayout.NORTH);
        RightPanel.add(scroll1, BorderLayout.CENTER);
        RightPanel.add(RightPanelButton, BorderLayout.SOUTH);
        
        panelTheXe.add(LeftPanel);
        panelTheXe.add(RightPanel);
        
        ParkFrame.CenterPanel.removeAll();
        ParkFrame.CenterPanel.add(lblQLTheGuiXe, BorderLayout.NORTH);
        ParkFrame.CenterPanel.add(panelTheXe, BorderLayout.CENTER);
        ParkFrame.CenterPanel.setVisible(false);
        ParkFrame.CenterPanel.setVisible(true);
    }
    
    private void init(){
        fillTableCuDan();
        row = -1;
        upDateStatus();
        fillToMaKhachHang();
        fillToLoaiXe();
    }
    
    public void fillTableCuDan(){
        TheXeModel = (DefaultTableModel) tblDanhSach.getModel();
        TheXeModel.setRowCount(0);
        try {
            List<TheXeCuDan> list = theXeCuDanDAO.selectALL();
            for (int i =0; i < list.size(); i++) {
                TheXeCuDan theXeCuDan = list.get(i);
                Object row[] = {i + 1, theXeCuDan.getMaThe(), theXeCuDan.getThoiGianDangKy(), 
                                 "Cư dân", theXeCuDan.getMaCuDan(), theXeCuDan.getMaLoaiXe()};
                TheXeModel.addRow(row);
            }
        } catch (Exception e) {
            MessageBox.alert(null, "Lỗi truy vấn dữ liệu");
        }
    }
    
    public void fillTableKhachVangLai(){
        TheXeModel = (DefaultTableModel) tblDanhSach.getModel();
        TheXeModel.setRowCount(0);
        try {
            List<TheXeKhachVangLai> list = theXeKhachVangLaiDAO.selectALL();
            for (int i =0; i < list.size(); i++) {
                TheXeKhachVangLai theXeKhachVangLai = list.get(i);
                Object row[] = {i + 1, theXeKhachVangLai.getMaThe(), "", 
                                "Khách vãng lai", "", theXeKhachVangLai.getMaLoaiXe()};
                TheXeModel.addRow(row);
            }
        } catch (Exception e) {
            MessageBox.alert(null, "Lỗi truy vấn dữ liệu");
        }
    }
    
    public void fillToMaKhachHang() {
        DefaultComboBoxModel theKhachHangModel = (DefaultComboBoxModel) cboMaKhachHang.getModel();
        theKhachHangModel.removeAllElements();
        
        List<CuDan> listCuDan = cuDanDAO.selectALL();
        
        if(cboLoaiKhachHang.getSelectedIndex() == 1) {
            for (CuDan cuDan : listCuDan) {
                theKhachHangModel.addElement(cuDan.getMaCuDan());
            }
        }
        else {
            theKhachHangModel.addElement("Không có thông tin!");
        }
    }
    
    public void fillToLoaiXe(){
        DefaultComboBoxModel loaiXeModel = (DefaultComboBoxModel) cboLoaiXe.getModel();
        loaiXeModel.removeAllElements();
        List<LoaiXe> list = loaiXeDAO.selectALL();
        for (LoaiXe loaiXe : list) {
            loaiXeModel.addElement(loaiXe);
        }
    }
    
    public void upDateStatus(){
        Boolean edit = (row>=0);
        Boolean first = (row==0);
        Boolean last = (row == (tblDanhSach.getRowCount()-1));
        
        // Trạng thái form
        if(!Authenticated.isManage()){
            btnXoa.setEnabled(false);
        }
        else {
            btnXoa.setEnabled(edit);
        }
        btnThem.setEnabled(!edit);
        btnSua.setEnabled(edit);
        txtMaThe.setEnabled(false);
        txtThoiGianDangKy.setEditable(false);

        // Trạng thái điều hướng
        btnFirst.setEnabled(edit && !first);
        btnPre.setEnabled(edit && !first);
        btnNext.setEnabled(edit && !last);
        btnLast.setEnabled(edit && !last);
    }
    
    public TheXeCuDan getFormTheXeCuDan() throws ParseException{
        TheXeCuDan theXeCuDan = new TheXeCuDan();
        theXeCuDan.setMaThe(txtMaThe.getText());
        LoaiXe loaiXe = (LoaiXe) cboLoaiXe.getSelectedItem();
        theXeCuDan.setThoiGianDangKy(DateHelper.StringtoDate(txtThoiGianDangKy.getText(), "dd/MM/yyyy - HH:mm:ss"));
        theXeCuDan.setMaCuDan(String.valueOf(cboMaKhachHang.getSelectedItem()));
        theXeCuDan.setMaLoaiXe(loaiXe.getMaLoaiXe());
        return theXeCuDan;
    }
    
    public TheXeKhachVangLai getFormTheXeKhachVangLai(){
        LoaiXe loaiXe = (LoaiXe) cboLoaiXe.getSelectedItem();
        TheXeKhachVangLai theXeKhachVangLai = new TheXeKhachVangLai();
        theXeKhachVangLai.setMaThe(txtMaThe.getText());
        theXeKhachVangLai.setMaLoaiXe(loaiXe.getMaLoaiXe());
        return theXeKhachVangLai;
    }
    
    public void setFormTheXeCuDan(TheXeCuDan theXeCuDan){
        LoaiXe loaiXe = loaiXeDAO.selectByID(theXeCuDan.getMaLoaiXe());
        txtMaThe.setText(theXeCuDan.getMaThe());
        txtThoiGianDangKy.setText(DateHelper.DatetoString(theXeCuDan.getThoiGianDangKy(), "dd/MM/yyyy - HH:mm:ss"));
        cboLoaiKhachHang.setSelectedIndex(1);
        cboMaKhachHang.setSelectedItem(theXeCuDan.getMaCuDan());
        if(loaiXe != null)
            cboLoaiXe.setSelectedItem(loaiXe);
    }
    
    public void setFormTheXeKhachVangLai(TheXeKhachVangLai theXeKhachVangLai){
        LoaiXe loaiXe = loaiXeDAO.selectByID(theXeKhachVangLai.getMaLoaiXe());
        txtMaThe.setText(theXeKhachVangLai.getMaThe());
        txtThoiGianDangKy.setText(null);
        cboLoaiKhachHang.setSelectedIndex(0);
        cboMaKhachHang.setSelectedIndex(0);
        if(loaiXe != null)
            cboLoaiXe.setSelectedItem(loaiXe);
    }
    
    public void clearForm() {
        TheXeCuDan TheXeCuDan = new TheXeCuDan();
        setFormTheXeCuDan(TheXeCuDan);
        txtMaThe.requestFocus();
        cboLoaiKhachHang.setSelectedIndex(0);
        cboMaKhachHang.setSelectedIndex(0);
        cboLoaiXe.setSelectedIndex(0);
        row = -1;
        upDateStatus();
        txtMaThe.setEnabled(true);
    }
    
    private void edit() {
        String maThe = (String) tblDanhSach.getValueAt(row, 1);
        TheXeCuDan theXeCuDan = theXeCuDanDAO.selectByID(maThe);
        TheXeKhachVangLai theXeKhachVangLai = theXeKhachVangLaiDAO.selectByID(maThe);
        if(theXeCuDan != null)
            setFormTheXeCuDan(theXeCuDan);
        else
            setFormTheXeKhachVangLai(theXeKhachVangLai);
        tblDanhSach.setRowSelectionInterval(row, row);
        upDateStatus();
    }

    public void insert(){
        if(check()) {
            TheXeCuDan theXeCuDan1 = theXeCuDanDAO.selectByID(txtMaThe.getText());
            TheXeKhachVangLai theXeKhachVangLai1  = theXeKhachVangLaiDAO.selectByID(txtMaThe.getText());
            if(theXeCuDan1 != null || theXeKhachVangLai1 != null) {
                txtMaThe.requestFocus();
                txtMaThe.setBorder(new LineBorder(Color.red));
                MessageBox.alert(null, "Mã thẻ này đã tồn tại!");
                return;
            }
            try {
                TheXeKhachVangLai theXeKhachVangLai = getFormTheXeKhachVangLai();
                TheXeCuDan theXeCuDan = getFormTheXeCuDan();
                if(cboLoaiKhachHang.getSelectedIndex() == 0){
                    theXeKhachVangLaiDAO.insert(theXeKhachVangLai);
                    fillTableKhachVangLai();
                }
                else {
                    theXeCuDanDAO.insert(theXeCuDan);
                    fillTableCuDan();
                }
                clearForm();
                MessageBox.alert(null, "Thêm mới thành công!");
            } catch (ParseException e) {
                MessageBox.alert(null, "Thêm mới thất bại!");
            }
        }
    }
    
    public void upDate() {
        if(check()) {
            try {
                if(cboLoaiKhachHang.getSelectedIndex() == 0){
                    TheXeKhachVangLai theXeKhachVangLai = getFormTheXeKhachVangLai();
                    theXeKhachVangLaiDAO.update(theXeKhachVangLai);
                    fillTableKhachVangLai();
                }
                else {
                    TheXeCuDan theXeCuDan = getFormTheXeCuDan();
                    theXeCuDanDAO.update(theXeCuDan);
                    fillTableCuDan();
                }
                clearForm();
                MessageBox.alert(null, "Cập nhật thành công!");
            } catch (ParseException e) {
                MessageBox.alert(null, "Cập nhật thất bại!");
            }
        }
    }
    
    public void delete() {
        if(!Authenticated.isManage())
            MessageBox.alert(null, "Bạn không có quyền xóa thẻ xe!");
        else{
            String maThe = txtMaThe.getText();
            TheXeCuDan theXeCuDan =  theXeCuDanDAO.selectByID(maThe);
            try {
                if(theXeCuDan == null){
                    theXeKhachVangLaiDAO.delete(maThe);
                    fillTableKhachVangLai();
                } else {
                    theXeCuDanDAO.delete(maThe);
                    fillTableCuDan();
                }
                clearForm();
                MessageBox.alert(null, "Xóa thẻ xe thành công!");
            } catch (Exception e) {
                MessageBox.alert(null, "Xóa thẻ xe thất bại!");
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
    
    public boolean check(){
        String mauMaThe= "(TCD|TVL)\\d{4}";
        if(txtMaThe.getText().isEmpty()){
            txtMaThe.requestFocus();
            txtMaThe.setBorder(new LineBorder(Color.red));
            MessageBox.alert(null, "Vui lòng nhập mã thẻ!");
            return false;
        }
        else if(!txtMaThe.getText().matches(mauMaThe)) {
            txtMaThe.requestFocus();
            txtMaThe.setBorder(new LineBorder(Color.red));
            MessageBox.alert(null, "Mã thẻ không hợp lệ!");
            return false;
        }
        else {
            txtMaThe.setBorder(new LineBorder(Color.black));
            return true;
        }
    }

}
