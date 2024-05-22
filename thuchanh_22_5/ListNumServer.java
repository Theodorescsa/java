package thuchanh_22_5;
import java.net.*;
import java.io.*;
public class ListNumServer {
    public final static int DEFAULT_PORT = 2007;
    public static void main(String[] args) {
        int port = DEFAULT_PORT;
        try {
            ServerSocket ss = new ServerSocket(port);
            System.out.println("Client listnum connected");
            Socket s = null;
            try {
                s = ss.accept();
                int temp;
                ObjectInputStream objIn = new ObjectInputStream(s.getInputStream());
                int[] array = (int[]) objIn.readObject();
                System.out.print("Mang so nguyen nhan duoc: ");
                for (int i = 0; i < array.length; i++) {
                    if(i == array.length-1) {
                        break;
                    }
                    if (array[i+1]<array[i]) {
                        temp = array[i];
                        array[i] = array[i+1];
                        array[i+1] = temp;
                        i=-1;
                    }

                }
                for (int num : array) {
                    System.out.print(num + " ");
                }
                System.out.println();
            } catch (Exception e) {
                System.err.println(e);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    }