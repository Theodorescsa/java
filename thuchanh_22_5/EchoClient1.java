package thuchanh_22_5;
import java.net.*;
import java.io.*;

public class EchoClient1 {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // Địa chỉ của máy chủ
        int serverPort = 2007; // Cổng của máy chủ

        try {
            Socket socket = new Socket(serverAddress, serverPort); // Kết nối tới máy chủ
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true); // Tạo đối tượng PrintWriter để gửi dữ liệu tới máy chủ
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Tạo đối tượng BufferedReader để nhận dữ liệu từ máy chủ
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in)); // Đối tượng để đọc dữ liệu từ người dùng

            System.out.println("Connected to the server. Type messages to send to the server (type 'exit' to quit):");

            while (true) {
                String input = userInput.readLine(); // Đọc dữ liệu từ người dùng
                out.println(input); // Gửi dữ liệu tới máy chủ

                if ("exit".equalsIgnoreCase(input)) { // Nếu người dùng nhập "exit", thoát khỏi vòng lặp
                    break;
                }

                String response = in.readLine(); // Đọc phản hồi từ máy chủ
                System.out.println("Server response: " + response); // Hiển thị phản hồi từ máy chủ
            }

            socket.close(); // Đóng kết nối với máy chủ
        } catch (IOException e) {
            e.printStackTrace(); // Xử lý ngoại lệ nếu có lỗi xảy ra
        }
    }
}
