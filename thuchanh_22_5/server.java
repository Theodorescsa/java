package thuchanh_22_5;
import java.net.*;
import java.io.*;
public class server {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(2007);
            Socket s = ss.accept();
            System.out.println("client connected");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
