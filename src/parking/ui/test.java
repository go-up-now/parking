package parking.ui;


import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class test extends JFrame {

    private JLabel usernameLabel;
    private JTextField usernameTextField;
    private JLabel codeLabel;
    private JTextField codeTextField;
    private JButton sendCodeButton;
    private JButton resetPasswordButton;

    private Properties properties;
    private Session session;

    public test() {
        super("Quên mật khẩu");

        // Thiết lập giao diện
        Container container = getContentPane();
        container.setLayout(new GridLayout(6, 1));

        usernameLabel = new JLabel("Tên đăng nhập:");
        usernameTextField = new JTextField(20);
        codeLabel = new JLabel("Mã xác nhận:");
        codeTextField = new JTextField(20);
        sendCodeButton = new JButton("Gửi mã xác nhận");
        resetPasswordButton = new JButton("Khôi phục mật khẩu");

        // Thêm các thành phần vào giao diện
        container.add(usernameLabel);
        container.add(usernameTextField);
        container.add(codeLabel);
        container.add(codeTextField);
        container.add(sendCodeButton);
        container.add(resetPasswordButton);

        // Thêm sự kiện cho nút gửi mã xác nhận
        sendCodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Gửi email xác nhận
                String username = usernameTextField.getText();

                if (username.isEmpty()) {
                    JOptionPane.showMessageDialog(test.this, "Vui lòng nhập tên đăng nhập!");
                    return;
                }
                
                Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
                Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("nghianhps28127@fpt.edu.vn", "pbwj kgzj nkau sqrp");
            }
        });

                // Tạo mã xác nhận
                String code = generateCode();

                try {
                    // Gửi email xác nhận
                    sendEmail(code);
                    JOptionPane.showMessageDialog(test.this, "Mã xác nhận đã được gửi đến địa chỉ email của bạn!");
                } catch (AddressException ex) {
                    JOptionPane.showMessageDialog(test.this, "Địa chỉ email không hợp lệ!");
                } catch (MessagingException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(test.this, "Đã xảy ra lỗi khi gửi email xác nhận!");
                }
            }
        });

        // Thêm sự kiện cho nút khôi phục mật khẩu
        resetPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Kiểm tra mã xác nhận
                String code = codeTextField.getText();

                if (code.isEmpty()) {
                    JOptionPane.showMessageDialog(test.this, "Vui lòng nhập mã xác nhận!");
                    return;
                }

                // Khôi phục mật khẩu
                String password = generatePassword();
                updatePassword(usernameTextField.getText(), password);

                JOptionPane.showMessageDialog(test.this, "Mật khẩu mới của bạn là: " + password);
            }
        });

        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    
        // Phương thức tạo mã xác nhận
            private String generateCode() {
            Random random = new Random();
            return String.valueOf(random.nextInt(9999));
        }

        // Phương thức gửi email xác nhận
        private void sendEmail(String code) throws AddressException, MessagingException {
            // Tạo thông điệp email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("nghianhps28127@fpt.edu.vn"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress("nguyenhuunghia19998@gmail.com"));
            message.setSubject("Mã xác nhận khôi phục mật khẩu");
            message.setText("Mã xác nhận của bạn là: " + code);

            // Gửi thông điệp email
            Transport.send(message);
        }

        // Phương thức khôi phục mật khẩu
        private void updatePassword(String username, String password) {
    // Khôi phục mật khẩu trong cơ sở dữ liệu
    // ...

    // Gửi email thông báo mật khẩu mới
    try {
        // Tạo thông điệp email
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("nghianhps28127@fpt.edu.vn"));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("nguyenhuunghia19998@gmail.com"));
        message.setSubject("Mật khẩu mới");
        message.setText("Mật khẩu mới của bạn là: " + password);

        // Gửi thông điệp email
        Transport.send(message);
    } catch (AddressException ex) {
        // ...
    } catch (MessagingException ex) {
        // ...
    }
}

        // Phương thức chính
        public static void main(String[] args) {
            new test().setVisible(true);
        }
        
        // Phương thức tạo mật khẩu ngẫu nhiên
private String generatePassword() {
    // Khởi tạo một mật khẩu ngẫu nhiên gồm 8 ký tự
    char[] chars = new char[8];
    for (int i = 0; i < chars.length; i++) {
        int n = (int) (Math.random() * 26);
        chars[i] = (char) ('a' + n);
    }

    // Trả về mật khẩu ngẫu nhiên
    return new String(chars);
}
    }

    
