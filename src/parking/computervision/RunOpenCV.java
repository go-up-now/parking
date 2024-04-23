/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parking.computervision;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.opencv.core.Core;

public class RunOpenCV {

    public void start() {
        File dllFile = new File("x64");
        String sourceDirectory = dllFile.getAbsolutePath();  // Đường dẫn đến thư mục chứa file DLL nguồn
        String[] libraryPaths = System.getProperty("java.library.path").split(File.pathSeparator);

        for (String libraryPath : libraryPaths) {
//            System.out.println(libraryPath);
            try {
                Path source = Paths.get(sourceDirectory, "opencv_java480.dll");  // Đường dẫn đến file DLL nguồn
                Path destination = Paths.get(libraryPath, "opencv_java480.dll");  // Đường dẫn đến thư mục trong java.library.path

                // Kiểm tra nếu thư mục đích không tồn tại, bỏ qua
                if (!Files.exists(destination.getParent())) {
//                    System.out.println("Thư mục không tồn tại trong java.library.path: " + destination.getParent());
                    continue;
                }

                Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Đã sao chép file DLL thành công: " + source + " -> " + destination);
            } catch (Exception e) {
//                System.out.println("Lỗi khi sao chép file DLL: " + e.getMessage());
            }
        }

//        System.loadLibrary("opencv-java480");
        // Khởi tạo thư viện OpenCV
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
}
