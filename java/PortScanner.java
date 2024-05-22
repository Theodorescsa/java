import java.io.IOException;
import java.net.*;
public class PortScanner {

    public static void main(String[] args) {
        String host = "localhost";
        if (args.length > 0) {
            host = args[0];

        }
        for (int i = 1024; i < 65535; i++) {
            try {
                Socket s = new Socket(host,i);
                System.out.println("Co mot server dang hoat dong"+i);
            } catch (UnknownHostException e) {
                // System.err.println(e);
            }
            catch (IOException e) {
                // System.err.println(e);
            }
        }
    }
}