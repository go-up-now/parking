package parking.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class En_DecodeHelper {
    private static final String SECRET_KEY = "MxzetU3v+FaGo3vF1w3S9w==";

    private static void generateSecretKey(){
        try {
            // Tạo KeyGenerator với thuật toán AES
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");

            // Đặt độ dài khóa (128, 192, hoặc 256 bit)
            keyGenerator.init(128);

            // Tạo khóa bí mật
            SecretKey secretKey = keyGenerator.generateKey();

            // Chuyển đổi khóa thành chuỗi Base64 để lưu trữ hoặc truyền
            String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());

            System.out.println("Generated AES Key: " + encodedKey);
        } catch (NoSuchAlgorithmException e) {
        }
    }


    public static String encrypt(String data) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encryptedData = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encryptedData);
        } catch (Exception e) {
            return null;
        }
    }

    public static String decrypt(String encryptedData) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
            return new String(decryptedData);
        } catch (Exception e) {
            return null;
        }
    }
}
