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
public class DanhBa {
    List<LienHe> danhBa;
    public final static int DEFAULT_PORT = 2007;

    DanhBa() {
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
    public void filterLienHe(String num) {
        for (LienHe lienHe : this.danhBa) {
            for (String phoneNum : lienHe.arraySodienthoai) {
                if (phoneNum == num) {
                    System.out.println("day la so dien thoai cua:"+ lienHe.hoten);
                }
            }
        }
    }
    public void filterByHoTen(String Hoten) {
        for (LienHe lienHe : this.danhBa) {
                if (Hoten == lienHe.hoten) {
                    System.out.println("LienHe{" +
                    "hoTen='" + lienHe.hoten + '\'' +
                    ", tenThuongGoi='" + lienHe.tenthuonggoi + '\'' +
                    ", diaChi='" + lienHe.diachi + '\'' +
                    ", email='" + lienHe.email + '\'' +
                    ", soDienThoai=" + lienHe.arraySodienthoai +
                    '}');
                }
            }
        }
    public void sortHoten() {
        List<String> listName = new ArrayList<>();
        for (int i = 0; i < danhBa.size(); i++) {
            listName.add(danhBa.get(i).hoten);
        }

        Collections.sort(listName); 

        List<LienHe> sortedDanhBa = new ArrayList<>();
        for (String name : listName) {
            for (LienHe lienHe : danhBa) {
                if (lienHe.hoten == name) {
                    sortedDanhBa.add(lienHe);
                    break;
                }
            }
        }

        danhBa = sortedDanhBa;
    }
    public void editLienhe(String num, String hoten,String tenthuonggoi, String diachi,String email, List<String> arraySodienthoai) {
        int i = 0;
        for (LienHe lienHe : this.danhBa) {
            for (String phoneNum : lienHe.arraySodienthoai) {
                if (phoneNum == num) {
                    lienHe.hoten = hoten;
                    lienHe.tenthuonggoi = tenthuonggoi;
                    lienHe.diachi = diachi;
                    lienHe.email = email;
                    for (String numPhone : lienHe.arraySodienthoai) {
                        numPhone = arraySodienthoai.get(i);
                        i++;
                    }
                }
            }
        }
    }
    public void addLienHeByScanner() {
        List<String> sodienthoai = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Moi nhap ho ten cua lien he moi:");
        String hotennew = scanner.nextLine();
        System.out.println("Moi nhap ten thuong goi cua lien he moi:");
        String tenthuonggoinew = scanner.nextLine();
        System.out.println("Moi nhap ten dia chi cua lien he moi:");
        String diachinew = scanner.nextLine();
        System.out.println("Moi nhap ten email cua lien he moi:");
        String emailnew = scanner.nextLine();
        while (true) {
            System.out.println("Moi nhap so dien thoai cua lien he moi (nhap 'xong' de ket thuc):");
            String sodienthoainew = scanner.nextLine();
            if (sodienthoainew.equals("xong")) {
                break;
            }
            sodienthoai.add(sodienthoainew);
        }
        LienHe newLienhe = new LienHe(hotennew, tenthuonggoinew, diachinew, emailnew, sodienthoai);
        danhBa.add(newLienhe);
        scanner.close();
    }
    public void addLienHeBySocket() {
        int port = DEFAULT_PORT;
        
        try (ServerSocket ss = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);
    
            while (true) {
                try (Socket s = ss.accept();
                     PrintWriter pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);
                     BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()))) {
    
                    System.out.println("Client connected");
                    List<String> listInfo = new ArrayList<>();
                    List<String> sodienthoaimoi = new ArrayList<>();
                    int i = 0;
    
                    while (true) {
                        String line = br.readLine();
                        if (line == null || line.equalsIgnoreCase("exit")) {
                            break;
                        }
                        if (i == 4) {
                            sodienthoaimoi.addAll(Arrays.asList(line.split(",")));
                            LienHe newLienHe = new LienHe(listInfo.get(0), listInfo.get(1), listInfo.get(2), listInfo.get(3), sodienthoaimoi);
                            danhBa.add(newLienHe);
                            pw.println("Da them lien he thanh cong");
                            break;
                        } else {
                            listInfo.add(line);
                            pw.println("Received: " + line);
                            i++;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void main(String[] args) {
        DanhBa danhbacuatoi = new DanhBa();

        danhbacuatoi.addLienHeBySocket();
        danhbacuatoi.printAllLienHe();

    }
}    

