package util;

import java.security.MessageDigest;

public class PasswordHash {
    public static String generateMD5(String input) {
        try {
            // Tạo một đối tượng MessageDigest với thuật toán MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Chuyển đổi input thành mảng byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Chuyển đổi mảng byte thành chuỗi hex
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
