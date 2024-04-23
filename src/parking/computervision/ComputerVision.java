/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package parking.computervision;

import parking.ui.GiaoDichJDialog;
import parking.carplaterecognize.CarPlateRecognize;
import parking.computervision.RunOpenCV;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.SecureRandom;
import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

/**
 *
 * @author trung
 */
public class ComputerVision extends javax.swing.JFrame {

    /**
     * Creates new form ComputerVision
     */
    public ComputerVision() {
        initComponents();
    }

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        display = new javax.swing.JLabel();
        startBtn = new javax.swing.JButton();
        stopBtn = new javax.swing.JButton();
        captureBtn = new javax.swing.JButton();
        btnHinh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(display, javax.swing.GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(display, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
        );

        startBtn.setText("Start Camera");
        startBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startBtnActionPerformed(evt);
            }
        });

        stopBtn.setText("Stop");
        stopBtn.setEnabled(false);
        stopBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopBtnActionPerformed(evt);
            }
        });

        captureBtn.setText("Capture");
        captureBtn.setEnabled(false);
        captureBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                captureBtnActionPerformed(evt);
            }
        });

        btnHinh.setText("Hình");
        btnHinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHinhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(stopBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(captureBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(startBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnHinh)
                        .addGap(20, 20, 20))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(startBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(stopBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                        .addComponent(captureBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnHinh))
                .addContainerGap(104, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void startBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startBtnActionPerformed
        webSource = new VideoCapture(0);
        myThread = new DaemonThread(display);
        Thread t = new Thread(myThread);
        t.setDaemon(true);
        myThread.runnable = true;
        t.start();
        startBtn.setEnabled(false);
        stopBtn.setEnabled(true);
        captureBtn.setEnabled(true);
    }//GEN-LAST:event_startBtnActionPerformed

    public void startCamere(){
        webSource = new VideoCapture(0);
        myThread = new DaemonThread(display);
        Thread t = new Thread(myThread);
        t.setDaemon(true);
        myThread.runnable = true;
        t.start();
//        startBtn.setEnabled(false);
//        stopBtn.setEnabled(true);
//        captureBtn.setEnabled(true);
    }
    
    public void stopWebCamera() {
        if (myThread != null) {
            if (myThread.runnable == true) {
                myThread.runnable = false;
                webSource.release();
            }
        }
    }

    private void stopBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopBtnActionPerformed
        stopWebCamera();
        startBtn.setEnabled(true);
        stopBtn.setEnabled(false);
        captureBtn.setEnabled(false);
    }//GEN-LAST:event_stopBtnActionPerformed

    public static File get_image_file;
    public static final SecureRandom RAND = new SecureRandom();
    public static String IMAGE_FILE_NAME = null;
    

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        stopWebCamera();
    }//GEN-LAST:event_formWindowClosing

    private void btnHinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHinhActionPerformed
        try {
            JFileChooser fileChoose = new JFileChooser();
            fileChoose.showOpenDialog(this);
            String fileHinh = fileChoose.getSelectedFile().getAbsolutePath();
            String plateNumber = new CarPlateRecognize().recognize(fileHinh);

            File file = new File("Pictures");
            boolean flag = true;
            if (!file.isDirectory()) {
                flag = file.mkdir();
            }
            if (!flag) {
                throw new Exception("Folder not exist: Thư Mục Không Tồn Tại ");
            }

            Files.copy(Path.of(fileHinh), Path.of(file.getAbsolutePath() + "\\" + plateNumber.toUpperCase() + ".jpg"), StandardCopyOption.REPLACE_EXISTING);

            System.out.println(plateNumber);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnHinhActionPerformed

    private void captureBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_captureBtnActionPerformed
        chupHinh();
    }//GEN-LAST:event_captureBtnActionPerformed

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
        int option = JOptionPane.showConfirmDialog(rootPane, "Bạn Có Chắc Chắn Chụp Ảnh Không ?");
        if (option == 0) {
            captureBtn.setEnabled(false);
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
                setWebcamCapturedImageonLable(display);
                JOptionPane.showMessageDialog(null, "Image Saved  Successfully  : Đã Lưu Hình Ảnh Thành Công\nDirectory: Danh Mục " + IMAGE_FILE_NAME);
                this.dispose();
                String plateNumber = new CarPlateRecognize().recognize(IMAGE_FILE_NAME);
                System.out.println("bien so12344:   "+ plateNumber);
                try {
                    // Thực hiện việc thay thế file
                    Files.copy(Path.of(IMAGE_FILE_NAME), Path.of(file.getAbsolutePath() + "\\" + plateNumber.toUpperCase() + ".jpg"), StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("File replaced successfully.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                stopWebCamera();
                JOptionPane.showMessageDialog(null, e, "Wraning", 0);
            }

        }
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ComputerVision.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ComputerVision.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ComputerVision.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ComputerVision.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ComputerVision().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHinh;
    private javax.swing.JButton captureBtn;
    private javax.swing.JLabel display;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton startBtn;
    private javax.swing.JButton stopBtn;
    // End of variables declaration//GEN-END:variables
}
