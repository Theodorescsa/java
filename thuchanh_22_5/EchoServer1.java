package thuchanh_22_5;
import java.net.*;
import java.io.*;
public class EchoServer1 {
    public final static int DEFAULT_PORT = 2007;
    public static void main(String[] args) {
        int port = DEFAULT_PORT;
        try {
            ServerSocket ss = new ServerSocket(port);
            System.out.println("Client connected");
            Socket s = null;
            while(true) {
                try {
                    s = ss.accept();
                    PrintWriter pw  = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
                    BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    
                    while(true) {
                        String line = br.readLine();
                        if(line.equals("exit"))break;
                        String upper = line.toUpperCase();
                        pw.println(upper);
                        pw.flush();
                    }
                } 
                catch (IOException e) {}
                finally {
                    try {
                        if(s!=null) {
                            s.close();
                        }
                    } catch (IOException e) {}
                }
            }
        } catch (Exception e) {
        }
    }
}
