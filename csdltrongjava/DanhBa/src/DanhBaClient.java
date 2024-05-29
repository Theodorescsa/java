import java.net.*;
import java.io.*;

public class DanhBaClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 2007;
        String[] listInfo = {"ho ten moi", "ten thuong goi", "dia chi", "email", "so dien thoai"};
        int i = 0;

        try {
            Socket socket = new Socket(serverAddress, serverPort);
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Connected to the server. Moi ban nhap muc muon chon cua danh ba (type 'exit' to quit):");

            while (i < listInfo.length) {
                System.out.println("Moi ban nhap " + listInfo[i] + ":");
                String input = userInput.readLine();
                out.println(input);

                if ("exit".equalsIgnoreCase(input)) {
                    break;
                }

                String response = in.readLine();
                System.out.println("Server response: Da them " + response + " thanh cong");
                i++;
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
