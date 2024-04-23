package parking.ui;

import parking.controller.DangNhapController;
//import park.dao.RememberDAO;
import parking.entity.NhanVien;
import parking.entity.Remember;
import parking.utils.Authenticated;
import parking.utils.MessageBox;
import parking.utils.ImagesHelper;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import parking.dao.NhanVienDAO;
import parking.dao.RememberDAO;
//import park.dao.NhanVienDAO;
//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;
import parking.utils.En_DecodeHelper;

//import org.slf4j.Logger;

public class DangNhapJDiaLog extends JDialog {
//    public static final Logger logger = Logger.getLogger(DangNhapJDiaLog.class);

    NhanVienDAO nhanVienDAO = new NhanVienDAO();
    RememberDAO rememberDAO = new RememberDAO();
    List<Remember> list = rememberDAO.selectALL();
    DangNhapController ac = new DangNhapController(this);

    private JTextField txtUser;
    private JPasswordField txtPass;
    private JCheckBox chkRemember;
    private JLabel lblQuenMatKhau;

    public DangNhapJDiaLog(Frame parent, boolean modal) {
        super(parent, modal);
        this.initComponents();
        this.init();
    }

    private void initComponents() {
        // Tạo panel 
        JPanel rightPanel = new JPanel(new GridLayout(7, 1)); // phần bên phải cửa sổ (components)
        JPanel tieuDePanel = new JPanel();
        JPanel LeftPanel = new JPanel(); // phần bên trái cửa sổ (hình)
        JPanel ButtonPanel = new JPanel(new GridLayout(1, 1, 10, 10)); // panel button
        JPanel rightPanel2 = new JPanel(new GridLayout());

        // Font
        Font font = new Font("Arial", Font.BOLD, 12);

        // Label 
        JLabel lblHinh = new JLabel(new ImageIcon(ImagesHelper.getImage("/parking/icon/car-parking-small.png")));
        JLabel lblTieuDe = new JLabel("ĐĂNG NHẬP");
        JLabel lbluser = new JLabel("Tài khoản");
        JLabel lblPass = new JLabel("Mật khẩu");

        // Text field
        txtUser = new JTextField(20);
        txtPass = new JPasswordField(20);

        chkRemember = new JCheckBox("Nhớ mật khẩu");
        lblQuenMatKhau = new JLabel("Quên mật khẩu");

        lblQuenMatKhau.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                quenMatKhau();
            }
        });

        // Button
        JButton btnDangNhap = new JButton("Đăng nhập");
        JButton btnKetThuc = new JButton("Kết thúc");

        // actionlistener
        btnDangNhap.addActionListener(ac);
        btnKetThuc.addActionListener(ac);
        
        txtUser.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                    dangNhap();
            }
        });
        
        txtPass.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                    dangNhap();
            }
        });

        // Setting
        lblTieuDe.setFont(new Font("Arial", Font.BOLD, 20));
        lblTieuDe.setForeground(new Color(0, 153, 51));
        lbluser.setFont(font);
        lblPass.setFont(font);

        // Thêm conponents vào panel
        LeftPanel.add(lblHinh);

        rightPanel2.add(chkRemember);
        rightPanel2.add(lblQuenMatKhau);

        tieuDePanel.add(lblTieuDe, BorderLayout.CENTER);
        rightPanel.add(tieuDePanel);
        rightPanel.add(lbluser);
        rightPanel.add(txtUser);
        rightPanel.add(lblPass);
        rightPanel.add(txtPass);
        rightPanel.add(rightPanel2);
        rightPanel.add(ButtonPanel);

        ButtonPanel.add(btnDangNhap);
        ButtonPanel.add(btnKetThuc);

        // Thêm vào Jdialog
        this.setLayout(new BorderLayout(20, 10));
        this.add(rightPanel, BorderLayout.CENTER);
        this.add(LeftPanel, BorderLayout.WEST);
    }

    private void init() {
        // check đăng nhập hệ thống khi nào
        
        
        this.setTitle("HỆ THỐNG QUẢN LÝ GỬI XE");
        this.setSize(610, 300);
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);

        
        if (!list.isEmpty()) {
            txtUser.setText(list.get(0).getUser());
            txtPass.setText(list.get(0).getPass()); 
            txtPass.requestFocus();
            chkRemember.setSelected(true);
        } else {
            txtUser.requestFocus();
            chkRemember.setSelected(false);
        }
        txtUser.requestFocus();
    }

    public void dangNhap() {
//        PropertyConfigurator.configure("src/park/Log4j/log4j.properties");
        String userName = txtUser.getText();
        String pass = new String(txtPass.getPassword());
        NhanVien nhanVien = nhanVienDAO.selectByUserName(userName);

        if (userName.isEmpty() && pass.isEmpty() || userName.isEmpty()) {
//            txtUser.requestFocus();
            MessageBox.alert(this, "Vui lòng nhập đầy đủ thông tin!");
        } else if (pass.isEmpty()) {
//            txtPass.requestFocus();
            MessageBox.alert(this, "Vui lòng nhập đầy đủ thông tin!");
        } else if (nhanVien == null || !pass.equals(En_DecodeHelper.decrypt(nhanVien.getMatKhau())) || !userName.equals(nhanVien.getTenDangNhap())) {
            txtUser.setText("");
            txtPass.setText("");
            txtUser.requestFocus();
            MessageBox.alert(this, "Tài khoản hoặc mật khẩu không hợp lệ!");
//            logger.warn("Sai tên đăng nhập hoặc mật khẩu!");
        } else {
            remember();
            Authenticated.user = nhanVien;
            ParkFrame.lblUser.setText(nhanVien.getTenDangNhap() + "   ");
            ParkFrame.lblVaiTro.setText(nhanVien.isVaiTro() ? "Admin" : "Nhân viên");
            this.dispose();
            
            // check dang nhap thanh cong
//            logger.info("Đăng nhập thành công:" + Authenticated.user.getTenDangNhap());
        }
    }

    public void Huy() {
        if (MessageBox.confirm(this, "Bạn muốn kết thúc ứng dụng?")) {
            System.exit(0);
        }
    }

    public void quenMatKhau() {
        new QuenMatKhauJDialog(null, true).setVisible(true);
    }

    public void remember() {
        String userName = txtUser.getText();
        String passWord = new String(txtPass.getPassword());

        Remember remember = new Remember();
        remember.setUser(userName);
        remember.setPass(passWord);
        if (!list.isEmpty()) {
            rememberDAO.delete(userName);
            rememberDAO.insert(remember);
        }
        else{
            rememberDAO.insert(remember);
        }
        
        if (!chkRemember.isSelected()) {
            rememberDAO.delete(remember);
        } else {
            rememberDAO.delete(userName);
            rememberDAO.insert(remember);
        }
    }

    public static void main(String[] args) {
//                PropertyConfigurator.configure("src/park/Log4j/log4j.properties");

        new DangNhapJDiaLog(null, true).setVisible(true);
    }
}
