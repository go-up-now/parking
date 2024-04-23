
package parking.ui;

import parking.utils.ImagesHelper;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GioiThieuSP extends JDialog {

    public GioiThieuSP(Frame owner, boolean modal) {
        super(owner, modal);
        this.Components();
        this.init();
    }

    private void Components() {
        // Tạo panel
        JPanel panel = new JPanel(new GridLayout(1, 1));
        
        // Ẩn thanh tiêu đề
        this.setUndecorated(true);
        
        // Tạo label
        JLabel lblHinh = new JLabel();
        lblHinh.setIcon(new ImageIcon(ImagesHelper.getImage("/NewEduSys/Icon/gioiThieu.png")));

        // Thêm vào panel
        panel.add(lblHinh);
        
        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.CENTER);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2)
                    GioiThieuSP.this.setVisible(false);
            }
        });
        pack();
    }

    private void init() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
}
