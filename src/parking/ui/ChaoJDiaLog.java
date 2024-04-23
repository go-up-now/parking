
package parking.ui;

import parking.utils.ImagesHelper;
import parking.utils.JdbcHelper;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

public class ChaoJDiaLog extends JDialog{
    JProgressBar pgbLoading;
    
    public ChaoJDiaLog( Frame parent, boolean modal){
        super(parent, modal);
        initCombonents();
        init();
    }

    private void initCombonents() {
        
        // Jlabel
        JLabel lblHinh = new JLabel(new ImageIcon(ImagesHelper.getImage("/parking/images/park1.png")));
        
        // Jprogress bar
        pgbLoading = new JProgressBar(0, 100);
        pgbLoading.setStringPainted(true);  // Hiện %
        
        // Ẩn thanh tiêu đề
        this.setUndecorated(true);
        
        // Thêm vào panel
        this.setLayout(new BorderLayout());
        this.add(lblHinh, BorderLayout.CENTER);
        this.add(pgbLoading, BorderLayout.SOUTH);
        
    }

    private void init() {
        this.setSize(600,364);
        this.setLocationRelativeTo(null);
        
        new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = pgbLoading.getValue();
                if(value < pgbLoading.getMaximum())
                    pgbLoading.setValue(value + 1);
                else
                    ChaoJDiaLog.this.dispose();
            }
        }).start();
        
    }
    
    public static void main(String[] args) {
        new ChaoJDiaLog(null, true).setVisible(true);
    }
}
