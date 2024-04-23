
package parking.ui;

import parking.controller.NewPassController;
import parking.dao.NhanVienDAO;
import parking.utils.Authenticated;
import parking.utils.MessageBox;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class MatKhauMoiJDialog extends JDialog{
    NhanVienDAO dao = new NhanVienDAO();
    NewPassController ac = new NewPassController(this);

    private JPasswordField txtPass;
    private JPasswordField txtPass2;
    private JButton btnDoiPass;
    private JButton btnHuy;
    private JLabel lblMatKhau_ThongBao;
    private JLabel lblMatKhau2_ThongBao;
    private JLabel lblRong;
    private JLabel lblRong2;

    public MatKhauMoiJDialog(Frame owner, boolean modal) {
        super(owner, modal);
        Components();
        init();
    }

    private void Components() {
        // Tạo panel 
        JPanel rightPanel = new JPanel(new GridLayout(5,1,10,10));
        JPanel LeftPanel = new JPanel(new GridLayout(5, 1,10,10));
        JPanel ButtonPanel = new JPanel(new GridLayout(1,1,10,10)); // panel button
        
        // Font
        Font font = new Font("Arial",Font.BOLD, 12);
        Font font_ThongBao = new Font("Arial", font.PLAIN, 10);
        
        // Label 
        JLabel lblNewPass = new JLabel("MẬT KHẨU MỚI");
        lblNewPass.setFont(new Font("Arial", Font.BOLD, 18));
        lblNewPass.setHorizontalAlignment(JLabel.CENTER);
        lblNewPass.setForeground(Color.BLUE);
        JLabel lblPass = new JLabel("Nhập mật khẩu mới:");
        JLabel lblPass2 = new JLabel("Nhập lại mật khẩu:");
        
        lblMatKhau_ThongBao = new JLabel();
        lblMatKhau2_ThongBao = new JLabel();
        lblRong = new JLabel();
        lblRong2 = new JLabel();
        
        lblMatKhau_ThongBao.setForeground(Color.red);
        lblMatKhau_ThongBao.setFont(font_ThongBao);
        lblMatKhau2_ThongBao.setForeground(Color.red);
        lblMatKhau2_ThongBao.setFont(font_ThongBao);
        
        // Jtext field
        txtPass = new JPasswordField();
        txtPass.setFont(font);
        txtPass2 = new JPasswordField();
        txtPass2.setFont(font);
        
        // Button
         btnDoiPass = new JButton("Đổi mật khẩu");
         btnHuy = new JButton("Hủy");
         
         // Actionlistener
         btnDoiPass.addActionListener(ac);
         btnHuy.addActionListener(ac);
        
        // Thêm conponents vào panel
        LeftPanel.add(lblRong);
        LeftPanel.add(lblPass);
        LeftPanel.add(lblRong2);
        LeftPanel.add(lblPass2);
        
        rightPanel.add(lblMatKhau_ThongBao);
        rightPanel.add(txtPass);
        rightPanel.add(lblMatKhau2_ThongBao);
        rightPanel.add(txtPass2);
        rightPanel.add(ButtonPanel);
        
        ButtonPanel.add(btnDoiPass);
        ButtonPanel.add(btnHuy);
        
        // Thêm vào Jdialog
        this.setLayout(new BorderLayout(20, 10));
        this.add(lblNewPass, BorderLayout.NORTH);
        this.add(rightPanel, BorderLayout.CENTER);
        this.add(LeftPanel, BorderLayout.WEST);
    }

    private void init() {
        this. setTitle("ĐỔI MẬT KHẨU MỚI");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setSize(500, 300);
        this.setLocationRelativeTo(null);
    }
    
    public void doiPass(){
        String pass = txtPass.getText();
        
//        if(pass.isEmpty()){
//            MessageBox.alert(null, "Vui lòng nhập mật khẩu muốn đổi!");
//            txtPass.requestFocus();
//        }
//        else if(!pass.equals(txtPass2.getText())) {
//            MessageBox.alert(null, "Nhập lại mật khẩu không hợp lệ!");
//            txtPass2.requestFocus();
//        } 
        if(check() == 2){
            try {
                Authenticated.user.setMatKhau(pass);
                dao.update(Authenticated.user);
                MessageBox.alert(null, "Đổi mật khẩu thành công!");
                this.dispose();
            } catch (Exception e) {
                MessageBox.alert(null, "Đổi mật khẩu thất bại thất bại!");
            }
        }
    }
    
    public void huy(){
        if(MessageBox.confirm(null, "Bạn muốn hủy bỏ?"))
            this.dispose();
    }
    
    public int check(){
        int dem = 0;
        // Check mật khẩu
        String mauMatKhau = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,30}$";
        String matKhau = new String(txtPass.getPassword());
        String matKhau2 = new String(txtPass2.getPassword());
        
        if(matKhau.isEmpty()){
            lblMatKhau_ThongBao.setText("Vui lòng nhập mật khẩu!");
            txtPass.setBorder(BorderFactory.createLineBorder(Color.red));
        } else if(!matKhau.matches(mauMatKhau)){
            lblMatKhau_ThongBao.setText("8-30 ký tự, ít nhất một chữ, một số và không dấu!");
            txtPass.setBorder(BorderFactory.createLineBorder(Color.red));
        }
        else {
            lblMatKhau_ThongBao.setText("");
            txtPass.setBorder(BorderFactory.createLineBorder(Color.black));
            dem++;
        }
        
        // Check xác nhận mật khẩu
        if(matKhau2.isEmpty()){
            lblMatKhau2_ThongBao.setText("Vui lòng xác nhận mật khẩu!");
            txtPass2.setBorder(BorderFactory.createLineBorder(Color.red));
        } else if(!matKhau2.equals(matKhau)){
            lblMatKhau2_ThongBao.setText("Mật khẩu không khớp!");
            txtPass2.setBorder(BorderFactory.createLineBorder(Color.red));
        }
        else {
            lblMatKhau2_ThongBao.setText("");
            txtPass2.setBorder(BorderFactory.createLineBorder(Color.black));
            dem++;
        }
        return dem;
    }
    
    public static void main(String[] args) {
        new MatKhauMoiJDialog(null, true).setVisible(true);
    }
}
