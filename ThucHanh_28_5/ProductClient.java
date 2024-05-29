package ThucHanh_28_5;
import java.net.*;
import java.io.*;

public class ProductClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 2007;
        String[] listInfo = {"ma san pham", "ten san pham", "dia chi", "mo ta", "chi tiet san pham"};
        String[] typeInfo = {"ten bien the","gia"}
        int i = 0;
        try {
            Socket socket = new Socket(serverAddress, serverPort);
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Connected to the server. Moi ban nhap muc muon chon (type 'exit' to quit):\n1/Them san pham\n2/Them bien the cua san pham");

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
