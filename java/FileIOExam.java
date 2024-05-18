import java.io.*;
public class FileIOExam {

    public static void main(String[] args) {
        try {
            OutputStream os = new FileOutputStream(args[0]); // tao ra mpt luong xuat nhap tu dong lenh/nhap vao ten tep tin
            String s = "Thu nghiem voi luong xuat nhap tin";
            for (int i = 0; i < s.length(); i++) {
                os.write(s.charAt(i));  //ghi vao tep tin
            }
            FileWriter writeFile = new FileWriter("hello.txt");
            writeFile.write("this is String from code");
            writeFile.close();
            os.close();
            InputStream is = new FileInputStream(args[0]);
            int len = is.available();
            System.out.println("Luong nhap co"+len+"bytes");
            byte b[] = new byte[len];
            int sobyte = is.read(b,0,len);
            System.out.println(sobyte+"la so bytes da doc");
            System.out.println(new String(b));
            System.out.println("hello"+is);
            is.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}