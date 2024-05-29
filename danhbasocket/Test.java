import java.util.*;
import java.io.*;
import java.net.*;
class LienHe {
    String hoten, tenthuonggoi, diachi, email;
    public List<String> arraySodienthoai;
    LienHe(String hoten,String tenthuonggoi, String diachi,String email,List<String> arraySodienthoai) {
        this.hoten = hoten;
        this.tenthuonggoi = tenthuonggoi;
        this.diachi = diachi;
        this.email = email;
        this.arraySodienthoai = new ArrayList<>(arraySodienthoai);
    }
    public void printLienhe() {
        System.out.println("LienHe{" +
        "hoTen='" + hoten + '\'' +
        ", tenThuongGoi='" + tenthuonggoi + '\'' +
        ", diaChi='" + diachi + '\'' +
        ", email='" + email + '\'' +
        ", soDienThoai=" + arraySodienthoai +
        '}');
    } 
}
public class Test {
    List<LienHe> danhBa;
    public final static int DEFAULT_PORT = 2007;

    Test() {
        this.danhBa = new ArrayList<>();
    }
    public void addLienhe(LienHe lienHe) {
        danhBa.add(lienHe);
    }
    public void printAllLienHe() {
        System.out.println("Tat ca lien he:");
        for (LienHe lienHe : this.danhBa) {
            System.out.println(
                "LienHe{" +
        "hoTen='" + lienHe.hoten + '\'' +
        ", tenThuongGoi='" + lienHe.tenthuonggoi + '\'' +
        ", diaChi='" + lienHe.diachi + '\'' +
        ", email='" + lienHe.email + '\'' +
        ", soDienThoai=" + lienHe.arraySodienthoai +
        '}'
            );
        }
    }
    public void addLienHeBySocket() {
        int port = DEFAULT_PORT;
        int i =0;
        List<String> sodienthoaimoi = new ArrayList<>();
        List<String> listInfo = new ArrayList<>();
        try {
            ServerSocket ss = new ServerSocket(port);
            System.out.println("Client danhba connected");
            Socket s = null;
            while(true) {
                try {
                    s = ss.accept();
                    PrintWriter pw  = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
                    BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    
                    while(true) {
                        String line = br.readLine();
                        if(line.equals("exit"))break;
                        if (i==5) {
                            sodienthoaimoi.add(listInfo.get(4));
                            LienHe newLienHe = new LienHe(listInfo.get(0), listInfo.get(1), listInfo.get(2), listInfo.get(3),sodienthoaimoi);
                            danhBa.add(newLienHe);
                         
                            break;
                        } else {
                            listInfo.add(line);
                        }
                        pw.println(listInfo.get(i));
                        pw.flush();
                        i++;
                        
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
    public static void main(String[] args) {
        DanhBa danhbacuatoi = new DanhBa();
        danhbacuatoi.addLienHeBySocket();
        danhbacuatoi.printAllLienHe();

    }
}    