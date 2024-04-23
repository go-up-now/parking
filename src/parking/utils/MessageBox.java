
package parking.utils;

import java.awt.Component;
import javax.swing.JOptionPane;


public class MessageBox {
    public static void alert(Component components, String Message){
        JOptionPane.showMessageDialog(components, Message, "Hệ thống quản lý gửi xe", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static boolean confirm(Component components, String Message){
        int result = JOptionPane.showConfirmDialog(components, Message, "Hệ thống quản lý gửi xe",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }
    
    public static String prompt(Component components, String Message){
        return JOptionPane.showInputDialog(components, Message, "Hệ thống quản lý gửi xe", JOptionPane.INFORMATION_MESSAGE);
    }
}
