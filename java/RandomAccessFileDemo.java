import java.io.*;
public class RandomAccessFileDemo {
    public static void main(String[] args) {
        try {
            RandomAccessFile file = new RandomAccessFile("example.dat", "rw");

            file.writeInt(100);
            file.writeDouble(3.14);
            file.writeUTF("Hello, RandomAccessFile!");

            file.seek(0);

            int intValue = file.readInt();
            double doubleValue = file.readDouble();
            String stringValue = file.readUTF();

            System.out.println("Integer: " + intValue);
            System.out.println("Double: " + doubleValue);
            System.out.println("String: " + stringValue);

            file.seek(12); 

            file.writeUTF("Updated String!");

            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
