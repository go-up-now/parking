
package parking.ui;

import parking.controller.KhuGuiXeController;
import parking.dao.KhuGuiXeDAO;
import parking.entity.KhuGuiXe;
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
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class KhuGuiXeJDialog {
    KhuGuiXeDAO khuGuiXeDAO = new KhuGuiXeDAO();
    KhuGuiXeController ac = new KhuGuiXeController(this);
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
    private JTextField txtMaKhu;
    private JTextField txtTenKhu;
    private JTextField txtDiaChi;
    private JTextField txtSoLuongChoDau;
    private DefaultTableModel khuGuiXeModel;
    private JLabel lblMaKhuDauThongBao;
    private JLabel lblTenKhuDauThongBao;
    private JLabel lblSoChoDauThongBao;

    public void KhuGuiXeJDialog() {
        Combonensts();
        init();
    }
    
    private void Combonensts() {
        // Tạo panel
        JPanel panelKhuGuiXe = new JPanel(new GridLayout(1, 2,10, 10));
        JPanel LeftPanel = new JPanel(new BorderLayout());
        JPanel LeftPanelCom = new JPanel(new GridLayout(10, 1));
        JPanel LeftPanelButton1 = new JPanel(new GridLayout(1,4,10,10));
        JPanel LeftPanelButton2 = new JPanel(new GridLayout(1,4,10,10));
        JPanel LeftPanelButton = new JPanel(new BorderLayout());
        JPanel RightPanel = new JPanel(new BorderLayout());
        
        JPanel LeftPanelMaKhuThongBao = new JPanel(new GridLayout(1, 3));
        JPanel LeftPanelTenKhuThongBao = new JPanel(new GridLayout(1, 3));
        JPanel LeftPanelSoLuongChoDauThongBao = new JPanel(new GridLayout(1, 3));

        // Font
        Font font = new Font("Arial", Font.BOLD, 18);
        
        // Cập nhật - Label
        JLabel lblQLKhuGuiXe = new JLabel("QUẢN LÝ KHU GỬI XE");
        JLabel lblCapNhat = new JLabel("CẬP NHẬT");
        JLabel lblDanhSach = new JLabel("DANH SÁCH");
        
        JLabel lblMaKhu = new JLabel("Mã khu (VD: OT01 hoặc XD01)");
        JLabel lblTenKhu = new JLabel("Tên khu");
        JLabel lblDiaChi = new JLabel("Địa chỉ");
        JLabel lblSoLuongChoDau = new JLabel("Số lượng chỗ đậu");
        
        JLabel lblMaKhuDauSao= new JLabel("(*)");
        lblMaKhuDauSao.setForeground(Color.red);
        lblMaKhuDauThongBao= new JLabel();
        lblMaKhuDauThongBao.setForeground(Color.red);
        
        JLabel lblTenKhuDauSao= new JLabel("(*)");
        lblTenKhuDauSao.setForeground(Color.red);
        lblTenKhuDauThongBao= new JLabel();
        lblTenKhuDauThongBao.setForeground(Color.red);
        
        JLabel lblSoChoDauSao= new JLabel("(*)");
        lblSoChoDauSao.setForeground(Color.red);
        lblSoChoDauThongBao= new JLabel();
        lblSoChoDauThongBao.setForeground(Color.red);
        
        // Text field
        txtMaKhu = new JTextField();
        txtTenKhu = new JTextField();
        txtDiaChi = new JTextField();
        txtSoLuongChoDau = new JTextField();
        txtSoLuongChoDau.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!Character.isDigit(c) && !Character.isISOControl(c))
                    e.consume();
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
        lblQLKhuGuiXe.setFont(font);
        lblQLKhuGuiXe.setForeground(Color.blue);
        lblQLKhuGuiXe.setHorizontalAlignment(JLabel.CENTER);
        
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
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "MÃ KHU", "TÊN KHU", "ĐỊA CHỈ", "SỐ LƯỢNG CHỖ ĐẬU"
            }
        ){
            Boolean[] canEdit = new Boolean[]{
                false,false,false,false,false
            };

            @Override
            public boolean isCellEditable(int row, int column) {
                return canEdit[column];
            }
        });
        scroll1.setViewportView(tblDanhSach);
        
        TableColumn columnSTT = tblDanhSach.getColumn("STT");
        columnSTT.setPreferredWidth(40);
        TableColumn columnDiaChi = tblDanhSach.getColumn("ĐỊA CHỈ");
        columnDiaChi.setPreferredWidth(220);
        
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
        LeftPanelMaKhuThongBao.add(lblMaKhu);
        LeftPanelMaKhuThongBao.add(lblMaKhuDauSao);
        LeftPanelMaKhuThongBao.add(lblMaKhuDauThongBao);
        
        LeftPanelTenKhuThongBao.add(lblTenKhu);
        LeftPanelTenKhuThongBao.add(lblTenKhuDauSao);
        LeftPanelTenKhuThongBao.add(lblTenKhuDauThongBao);
        
        LeftPanelSoLuongChoDauThongBao.add(lblSoLuongChoDau);
        LeftPanelSoLuongChoDauThongBao.add(lblSoChoDauSao);
        LeftPanelSoLuongChoDauThongBao.add(lblSoChoDauThongBao);
        
        LeftPanelCom.add(LeftPanelMaKhuThongBao, BorderLayout.WEST);
        LeftPanelCom.add(txtMaKhu);
        LeftPanelCom.add(LeftPanelTenKhuThongBao, BorderLayout.WEST);
        LeftPanelCom.add(txtTenKhu);
        LeftPanelCom.add(lblDiaChi);
        LeftPanelCom.add(txtDiaChi);
        LeftPanelCom.add(LeftPanelSoLuongChoDauThongBao, BorderLayout.WEST);
        LeftPanelCom.add(txtSoLuongChoDau);
        
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
        

        RightPanel.add(lblDanhSach, BorderLayout.NORTH);
        RightPanel.add(scroll1, BorderLayout.CENTER);
        
        panelKhuGuiXe.add(LeftPanel);
        panelKhuGuiXe.add(RightPanel);
        
        ParkFrame.CenterPanel.removeAll();
        ParkFrame.CenterPanel.add(lblQLKhuGuiXe, BorderLayout.NORTH);
        ParkFrame.CenterPanel.add(panelKhuGuiXe, BorderLayout.CENTER);
        ParkFrame.CenterPanel.setVisible(false);
        ParkFrame.CenterPanel.setVisible(true);
    }
    
    private void init(){
        fillTable();
        row = -1;
        upDateStatus();
    }
    
    public void fillTable(){
        khuGuiXeModel = (DefaultTableModel) tblDanhSach.getModel();
        khuGuiXeModel.setRowCount(0);
        try {
            List<KhuGuiXe> list = khuGuiXeDAO.selectALL();
            for (int i =0; i < list.size(); i++) {
                KhuGuiXe khuGuiXe = list.get(i);
                Object row[] = {i + 1, khuGuiXe.getMaKhu(), khuGuiXe.getTenKhu(), 
                                 khuGuiXe.getDiaChi(), khuGuiXe.getSoLuongChoDau()};
                khuGuiXeModel.addRow(row);
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
            txtMaKhu.setEnabled(false);
            txtTenKhu.setEnabled(false);
            txtDiaChi.setEnabled(false);
            txtSoLuongChoDau.setEnabled(false);
        }
        else {
            btnXoa.setEnabled(edit);
            btnThem.setEnabled(!edit);
            btnSua.setEnabled(edit);
            btnMoi.setEnabled(true);
            txtMaKhu.setEnabled(true);
            txtTenKhu.setEnabled(true);
            txtDiaChi.setEnabled(true);
            txtSoLuongChoDau.setEnabled(true);
        }

        // Trạng thái điều hướng
        btnFirst.setEnabled(edit && !first);
        btnPre.setEnabled(edit && !first);
        btnNext.setEnabled(edit && !last);
        btnLast.setEnabled(edit && !last);
    }
    
    public KhuGuiXe getForm(){
        KhuGuiXe khuGuiXe = new KhuGuiXe();
        khuGuiXe.setMaKhu(txtMaKhu.getText());
        khuGuiXe.setTenKhu(txtTenKhu.getText());
        khuGuiXe.setDiaChi(txtDiaChi.getText());
        khuGuiXe.setSoLuongChoDau(Integer.parseInt(txtSoLuongChoDau.getText()));
        return khuGuiXe;
    }

    public void setForm(KhuGuiXe khuGuiXe){
        txtMaKhu.setText(khuGuiXe.getMaKhu());
        txtTenKhu.setText(khuGuiXe.getTenKhu());
        txtDiaChi.setText(khuGuiXe.getDiaChi());
        txtSoLuongChoDau.setText(String.valueOf(khuGuiXe.getSoLuongChoDau()));
    }
    
    public void clearForm() {
        KhuGuiXe khuGuiXe = new KhuGuiXe();
        setForm(khuGuiXe);
        row = -1;
        upDateStatus();
    }
    
    private void edit() {
        String maKhu = (String) tblDanhSach.getValueAt(row, 1);
        KhuGuiXe khuGuiXe = khuGuiXeDAO.selectByID(maKhu);
        if(khuGuiXe != null) {
            setForm(khuGuiXe);
            tblDanhSach.setRowSelectionInterval(row, row);
            upDateStatus();
        }
    }

    public void insert(){
        if(check() == 3) {
            KhuGuiXe khuGuiXe1 = khuGuiXeDAO.selectByID(txtMaKhu.getText());
            if(khuGuiXe1 != null) {
                txtMaKhu.requestFocus();
                txtMaKhu.setBorder(new LineBorder(Color.red));
                MessageBox.alert(null, "Mã khu này đã tồn tại!");
                return;
            }
            KhuGuiXe khuGuiXe = getForm();
            khuGuiXeDAO.insert(khuGuiXe);
            fillTable();
            clearForm();
            MessageBox.alert(null, "Thêm mới thành công!");
        }
    }
    
    public void upDate() {
        if(check() == 3) {
            txtMaKhu.setEnabled(false);
            KhuGuiXe khuGuiXe = getForm();
            khuGuiXeDAO.update(khuGuiXe);
            fillTable();
            clearForm();
            MessageBox.alert(null, "Sửa thành công!");
        }
    }
    
    public void delete() {
            String maKhu = txtMaKhu.getText();
            try {

                khuGuiXeDAO.delete(maKhu);
                fillTable();
                clearForm();
                MessageBox.alert(null, "Xóa thành công!");
            } catch (Exception e) {
                MessageBox.alert(null, "Xóa thất bại!");
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
    
    public int check(){
        int dem = 0;
        // check mã khu
        if(txtMaKhu.getText().isEmpty()){
            txtMaKhu.requestFocus();
            txtMaKhu.setBorder(new LineBorder(Color.red));
            lblMaKhuDauThongBao.setText("Vui lòng nhập mã khu!");
        }
        else if(txtMaKhu.getText().length() > 4) {
            txtMaKhu.requestFocus();
            txtMaKhu.setBorder(new LineBorder(Color.red));
            lblMaKhuDauThongBao.setText("Độ dài không được vượt quá 4 ký tự!");
        }
        else {
            txtMaKhu.setBorder(new LineBorder(Color.black));
            lblMaKhuDauThongBao.setText("");
            dem++;
        }
        
        // check ten khu
        if(txtTenKhu.getText().isEmpty()){
            txtTenKhu.requestFocus();
            txtTenKhu.setBorder(new LineBorder(Color.red));
            lblTenKhuDauThongBao.setText("Vui lòng nhập tên khu!");
        }
        else if(txtTenKhu.getText().length() < 6 ||txtTenKhu.getText().length() > 50) {
            txtTenKhu.requestFocus();
            txtTenKhu.setBorder(new LineBorder(Color.red));
            lblTenKhuDauThongBao.setText("Độ dài 6 đến 50 ký tự!");
        }
        else {
            txtTenKhu.setBorder(new LineBorder(Color.black));
            lblTenKhuDauThongBao.setText("");
            dem++;
        }
        
        // check Số lượng chỗ đậu
        if(txtSoLuongChoDau.getText().isEmpty()){
            txtSoLuongChoDau.requestFocus();
            txtSoLuongChoDau.setBorder(new LineBorder(Color.red));
            lblSoChoDauThongBao.setText("Vui lòng nhập số lượng chỗ đậu!");
        }
        else if(Integer.parseInt(txtSoLuongChoDau.getText()) < 100 || Integer.parseInt(txtSoLuongChoDau.getText()) > 2000) {
            txtSoLuongChoDau.requestFocus();
            txtSoLuongChoDau.setBorder(new LineBorder(Color.red));
            lblSoChoDauThongBao.setText("Số lượng từ 100 đến 2000!");
        }
        else {
            txtSoLuongChoDau.setBorder(new LineBorder(Color.black));
            lblSoChoDauThongBao.setText("");
            dem++;
        }
        return dem;
    }

}
