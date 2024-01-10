package util;

import java.security.MessageDigest;
import java.util.Formatter;

public class PasswordHash {
    public static String generateMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] digest = md.digest();

            StringBuilder myHash = new StringBuilder();
            try (Formatter formatter = new Formatter(myHash)) {
                for (byte b : digest) {
                    formatter.format("%02X", b);
                }
            }

            System.out.println("Hash: " + input + " -> " + myHash);

            return myHash.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {

        String myHash = PasswordHash.generateMD5("abcd1234");
        System.out.println(myHash);

    }
}
