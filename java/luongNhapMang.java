import java.io.*;
public class luongNhapMang {
    public static void main(String[] args) {
        String sourceData = "Đây í dụ về nhập xuất mảng byte.";
        
        byte[] inputData = sourceData.getBytes();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(inputData);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        
        
        try {
            int bytesRead;
            while ((bytesRead = byteArrayInputStream.read(b)) != -1) {
                byteArrayOutputStream.write(b, 0, bytesRead);
            }
            
            byte[] outputData = byteArrayOutputStream.toByteArray();
            
            String outputString = new String(outputData);
            
            System.out.println("Dữ liệu sau khi ghi vào ByteArrayOutputStream: " + outputString);
        } catch (IOException e) {
            System.err.println(e);;
        } finally {
            try {
                byteArrayInputStream.close();
                byteArrayOutputStream.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}