
package parking.ui;

import parking.controller.QuenMatKhauController;
import parking.dao.NhanVienDAO;
import parking.entity.NhanVien;
import parking.utils.Authenticated;
import parking.utils.MessageBox;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class QuenMatKhauJDialog extends JDialog{
    NhanVienDAO nhanVienDao = new NhanVienDAO();
    QuenMatKhauController ac = new QuenMatKhauController(this);

    private JTextField txtUser;
    private JTextField txtMa;
    private JButton btnLayMa;
    private JButton btnGuiThongTin;
    private JButton btnHuy;
    
    public QuenMatKhauJDialog(Frame parent, boolean modal){
        super(parent, modal);
        this.initComponents();
        this.init();
    }

    private void initComponents() {
        // Tạo panel 
        JPanel rightPanel = new JPanel(new GridLayout(3,1,10,10));
        JPanel LeftPanel = new JPanel(new GridLayout(3, 1,10,10));
        JPanel ButtonPanel = new JPanel(new GridLayout(1,1,10,10)); // panel button
        JPanel rightPanelMa = new JPanel(new GridLayout(1,2,10,10));
        
        // Font
        Font font = new Font("Arial",Font.BOLD, 12);
        
        // Label 
        JLabel lblQuenPass = new JLabel("QUÊN MẬT KHẨU");
        lblQuenPass.setFont(new Font("Arial", Font.BOLD, 18));
        lblQuenPass.setHorizontalAlignment(JLabel.CENTER);
        lblQuenPass.setForeground(Color.BLUE);
        JLabel lblUser = new JLabel("Nhập tài khoản của bạn:");
        JLabel lblMa = new JLabel("Nhập mã xác nhận:");
        
        // Jtext field
         txtUser = new JTextField(20);
        txtUser.setFont(font);
         txtMa = new JTextField(20);
        txtMa.setFont(font);
        
        // Button
         btnLayMa = new JButton("Lấy mã");
         btnGuiThongTin = new JButton("Gửi thông tin");
         btnHuy = new JButton("Hủy");
         
         // Actionlistener
         btnLayMa.addActionListener(ac);
         btnGuiThongTin.addActionListener(ac);
         btnHuy.addActionListener(ac);
        
        // Thêm conponents vào panel
        LeftPanel.add(lblUser);
        LeftPanel.add(lblMa);
        
        rightPanelMa.add(txtMa);
        rightPanelMa.add(btnLayMa);
        
        rightPanel.add(txtUser);
        rightPanel.add(rightPanelMa);
        rightPanel.add(ButtonPanel);
        
        ButtonPanel.add(btnGuiThongTin);
        ButtonPanel.add(btnHuy);
        
        // Thêm vào Jdialog
        this.setLayout(new BorderLayout(20, 10));
        this.add(lblQuenPass, BorderLayout.NORTH);
        this.add(rightPanel, BorderLayout.CENTER);
        this.add(LeftPanel, BorderLayout.WEST);
    }

    private void init() {
        this. setTitle("Quên mật khẩu");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setSize(500, 200);
        this.setLocationRelativeTo(null);
    }
    
    public void layMa() {
        NhanVien nhanVien = nhanVienDao.selectByUserName(txtUser.getText());
        if(nhanVien== null)
            MessageBox.alert(null, "Tài khoản này không tồn tại!");
        else{
            Authenticated.user = nhanVien;
            final String username = "nghianhps28127@fpt.edu.vn";
            final String password = "pbwj kgzj nkau sqrp";

            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true"); //TLS

            Session session;
            session = Session.getInstance(prop,
                    new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(
                        Message.RecipientType.TO,
                        InternetAddress.parse(nhanVien.getEmail())
                );
                Authenticated.quenMK = getCode();
                message.setSubject("Lấy mã xác nhận");
                message.setText("Mã xác nhận của bạn là: "+ Authenticated.quenMK);
                
                Transport.send(message);
                JOptionPane.showMessageDialog(this, "Gửi mã xác nhận thành công!");

            } catch (MessagingException e) {
                throw new RuntimeException();
            }
        }
    }
    
    private String getCode() {
    // Khởi tạo một mã code ngẫu nhiên gồm 6 ký tự
    char[] chars = new char[6];
    for (int i = 0; i < chars.length; i++) {
        int n = (int) (Math.random() * 26);
        chars[i] = (char) ('a' + n);
    }

    // Trả về mã code ngẫu nhiên
    return new String(chars);
    }
    
    public void huy(){
        if(MessageBox.confirm(null, "Bạn muốn hủy bỏ?"))
            this.dispose();
    }
    
    public void guiThongTin(){
        if(txtMa.getText().isEmpty())
            MessageBox.alert(rootPane, "Bạn chưa nhập mã xác nhận!");
        else if(!(txtMa.getText().equals(Authenticated.quenMK)))
            MessageBox.alert(rootPane, "Mã xác nhận không chính xác!");
        else{
            this.dispose();
            new MatKhauMoiJDialog(null, true).setVisible(true);
        }
    }
    
    public static void main(String[] args) {
        new QuenMatKhauJDialog(null, true).setVisible(true);
    }
}
