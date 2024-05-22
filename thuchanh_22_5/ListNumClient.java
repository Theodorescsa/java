package thuchanh_22_5;
import java.io.*;
import java.net.*;
public class ListNumClient {
    public static void main(String[] args) {
        String serverName = "localhost";
        int serverPort = 2007;
        try {
            Socket socket = new Socket(serverName,serverPort);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            int[] listNum = {5,2,6,2,6,1,8,3};
            out.writeObject(listNum);
            out.flush();
            String response = in.readLine();
            System.out.println("Day so sau khi sap xep:" + response);
            socket.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
