
package parking.ui;

import parking.controller.DoiMatKhauController;
import parking.dao.NhanVienDAO;
import parking.utils.Authenticated;
import parking.utils.MessageBox;
import parking.utils.ImagesHelper;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class DoiMatKhauJDialog extends JDialog{
    NhanVienDAO dao = new NhanVienDAO();
    DoiMatKhauController ac = new DoiMatKhauController(this);

    private JTextField txtUser;
    private JPasswordField txtPass;
    private JPasswordField txtNewPass;
    private JPasswordField txtNewPass2;

    public DoiMatKhauJDialog(Frame owner, boolean modal) {
        super(owner, modal);
        this.components();
        this.init();
    }
    
    private void components(){
        
        // Tạo panel
        JPanel panelChung = new JPanel(new GridLayout(2, 1));
        JPanel panelTong = new JPanel(new GridLayout(1, 2));
        JPanel panelLeft = new JPanel(new GridLayout(4, 1));
        JPanel panelRight = new JPanel(new GridLayout(4, 1));
        JPanel panelButton = new JPanel();
        
        // Jlabel
        JLabel lblDoiMatKhau = new JLabel("ĐỔI MẬT KHẨU");
        JLabel lblUser = new JLabel("Tên đăng nhập");
        JLabel lblPass = new JLabel("Mật khẩu hiện tại");
        JLabel lblNewPass= new JLabel("Mật khẩu mới");
        JLabel lblNewPass2 = new JLabel("Xác nhận mật khẩu mới");
        
        // Text Field
         txtUser = new JTextField();
         txtPass = new JPasswordField();
         txtNewPass = new JPasswordField();
         txtNewPass2 = new JPasswordField();
        
        // Button
        JButton btnDongY = new JButton("Đồng ý");
        JButton btnHuy = new JButton("Hủy");
        
        // Actionlistener
        btnDongY.addActionListener(ac);
        btnHuy.addActionListener(ac);
        
        // Setting
        lblDoiMatKhau.setFont(new Font("Arial", Font.BOLD, 18));
        lblDoiMatKhau.setForeground(Color.BLUE);
        lblDoiMatKhau.setHorizontalAlignment(JLabel.CENTER);
        
        btnDongY.setIcon(new ImageIcon(ImagesHelper.getImage("/NewEduSys/Icon/Refresh.png")));
        btnDongY.setFocusable(false);
        btnDongY.setMargin(new java.awt.Insets(2, 17, 2, 17));
        
        btnHuy.setIcon(new ImageIcon(ImagesHelper.getImage("/NewEduSys/Icon/No.png")));
        btnHuy.setFocusable(false);
        btnHuy.setMargin(new java.awt.Insets(2, 17, 2, 17));
        
        // Thêm vào panel
        panelLeft.add(lblUser);
        panelLeft.add(txtUser);
        panelLeft.add(lblNewPass);
        panelLeft.add(txtNewPass);
        
        panelRight.add(lblPass);
        panelRight.add(txtPass);
        panelRight.add(lblNewPass2);
        panelRight.add(txtNewPass2);
        
        panelButton.add(btnDongY);
        panelButton.add(btnHuy);
        
        panelTong.add(panelLeft);
        panelTong.add(panelRight);
        panelChung.add(panelTong);
        panelChung.add(panelButton);


        this.setLayout(new BorderLayout(0,20));
        this.add(lblDoiMatKhau, BorderLayout.NORTH);
        this.add(panelChung, BorderLayout.CENTER);
        
    }

    private void init() {
        this.setTitle("Đổi mật khẩu");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(400, 250);
        this.setLocationRelativeTo(null);
        this.setIconImage(ImagesHelper.getImage("/NewEduSys/Icon/logo-small.png"));
    }
    
    public void doiPass(){
        String user = txtUser.getText();
        String pass = new String(txtPass.getPassword());
        String Newpass = new String(txtNewPass.getPassword());
        String Newpass2 = new String(txtNewPass2.getPassword());
        
        if(!Authenticated.user.getTenDangNhap().equals(user))
            MessageBox.alert(null, "Tên đăng nhập không chính xác!");
        else if(!Authenticated.user.getMatKhau().equals(pass))
            MessageBox.alert(null, "Mật khẩu không chính xác!");
        else if(Newpass.isEmpty())
            MessageBox.alert(null, "Bạn chưa nhập mật khẩu mới!");
        else if(Newpass2.isEmpty())
            MessageBox.alert(null, "Bạn chưa xác nhận mật khẩu mới!");
        else if(!Newpass.equals(Newpass2))
            MessageBox.alert(null, "Xác nhận mật khẩu không chính xác!");
        else{
            try {
                Authenticated.user.setMatKhau(Newpass);
                dao.update(Authenticated.user);
                MessageBox.alert(null, "Đổi mật khẩu thành công!");
            } catch (Exception e) {
                MessageBox.alert(null, "Đổi mật khẩu thất bại thất bại!");
            }
        }
    }
    
    public void huy(){
        if(MessageBox.confirm(null, "Bạn muốn hủy bỏ?"))
            this.dispose();
    }
    
    public static void main(String[] args) {
        new DoiMatKhauJDialog(null, true).setVisible(true);
    }
}
