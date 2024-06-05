package bai3;
import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class ListAccount {
    List<user> listUser = new ArrayList<>();
    public void addUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Moi nhap ten nguoi dung:");
        String userName = scanner.nextLine();
        System.out.println("Moi nhap email:");
        String email = scanner.nextLine();
        System.out.println("Moi nhap so dien thoai");
        String phoneNumber = scanner.nextLine();
        System.out.println("Moi nhap dia chi:");
        String address = scanner.nextLine();
        System.out.println("Moi nhap mat khau");
        String password = scanner.nextLine();
        user newUser = new user(userName, email, phoneNumber, address, password);
        listUser.add(newUser);
    }
    public void signIn() {
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Moi nhap email:");
        String email = scanner2.nextLine();
        System.out.println("Moi nhap mat khau");
        String password = scanner2.nextLine();
        for (user user : listUser) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                System.out.println("ban da dang nhap thanh cong");
            } 
        }
    }
    public void filterUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Moi nhap ten nguoi dung muon tim kiem");
        String username = scanner.nextLine();
        for (user user : listUser) {
            if (user.getFullName().equals(username)) {
                System.out.println("Ho ten:"+user.getFullName()+",Email:"+user.getEmail()+",PhoneNumber:"+user.getPhoneNumber()+",Address:"+user.getAddress()+",Password:**********");
        }
        }
    }
    public void filterUserB() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Moi nhap ten nguoi dung muon tim kiem");
        String username = scanner.nextLine();
        for (user user : listUser) {
            if (user.getFullName().equals(username)) {
                System.out.println("Ho ten:"+user.getFullName()+",Email:"+user.getEmail()+",PhoneNumber:"+user.getPhoneNumber()+",Address:"+user.getAddress()+",Password:**********");
            }
        }
    }
        // Hàm băm SHA-256
    private String hashWithSHA256(String input) {
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hashedBytes = digest.digest(input.getBytes());
                System.out.println(bytesToHex(hashedBytes));
                return bytesToHex(hashedBytes);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return null;
            }
        }
    private String bytesToHex(byte[] bytes) {
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                result.append(String.format("%02x", b));
            }
            return result.toString();
    }
    public static void main(String[] args) {
            ListAccount lAccount = new ListAccount();
            String hashedPassword = lAccount.hashWithSHA256("hello");
            System.out.println(lAccount.bytesToHex(hashedPassword));
    }
}
