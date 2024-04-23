
package parking.utils;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImagesHelper {
    public static Image getImage(String src){
        URL url = ImagesHelper.class.getResource(src);
        return new ImageIcon(url).getImage();
    } 
    
    public static void saveImageToDirectory(File src){
        File file = new File("logos", src.getName());
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        try {
            Path from = Paths.get(src.getAbsolutePath()); // Lấy đỉa chỉ chọn hình
            Path to = Paths.get(file.getAbsolutePath()); // Lấy địa chỉ nơi lưu hình
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static ImageIcon readImageFromDirectory(String fileName){
        File path = new File("logos", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
    
    public static ImageIcon readImageFromDirectory1(String fileName, int width, int height){
        try {
            File path = new File("logos", fileName);
            Image img = ImageIO.read(path);
            return new ImageIcon(img.getScaledInstance(width, height, 0));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    
     public static File saveExel(File src){
        File dst = new File("storeFiles", src.getName());
        if(!dst.getParentFile().exists()){
            dst.getParentFile().mkdirs();
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
            return dst;
        } 
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
