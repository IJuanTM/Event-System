package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {
    private final String userName;
    private final String userPassword;

    public static ArrayList<User> userArray = new ArrayList<>();

    public User(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public static String encryptPassword(String password) {
        try {
            StringBuilder encrypted = new StringBuilder();
            MessageDigest method = MessageDigest.getInstance("SHA");

            method.reset();
            method.update(password.getBytes());
            byte[] encodedPassword = method.digest();

            for (byte b : encodedPassword) {
                if ((b & 0xff) < 0x10) encrypted.append("0");
                encrypted.append(Long.toString(b & 0xff, 16));
            }

            return new String(encrypted);
        } catch (NoSuchAlgorithmException nsa) {
            System.out.println(Color.RED + "Encryption algorithm error!" + Color.RESET);
        }
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }
}
