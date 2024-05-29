import java.util.*;
import java.io.*;
import java.net.*;

class LienHe {
    String hoten, tenthuonggoi, diachi, email;
    public List<String> arraySodienthoai;

    LienHe(String hoten, String tenthuonggoi, String diachi, String email, List<String> arraySodienthoai) {
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

public class DanhBaServer {
    List<LienHe> danhBa;
    public final static int DEFAULT_PORT = 2007;

    DanhBaServer() {
        this.danhBa = new ArrayList<>();
    }

    public void addLienhe(LienHe lienHe) {
        danhBa.add(lienHe);
    }

    public void printAllLienHe() {
        System.out.println("Tat ca lien he:");
        for (LienHe lienHe : this.danhBa) {
            lienHe.printLienhe();
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
        try (ServerSocket serverSocket = new ServerSocket(DEFAULT_PORT)) {
            System.out.println("Server is listening on port " + DEFAULT_PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
   
               List<String> info = new ArrayList<>();
               for (int i = 0; i < 5; i++) {
                   String input = in.readLine();
                   if (input.equalsIgnoreCase("exit")) {
                       break;
                   }
                   info.add(input);
                   out.println(info.get(i));
               }
   
               if (info.size() == 5) {
                   List<String> phoneNumbers = Arrays.asList(info.get(4).split(","));
                   LienHe lienHe = new LienHe(info.get(0), info.get(1), info.get(2), info.get(3), phoneNumbers);
                   addLienhe(lienHe);
               }
   
               printAllLienHe();
           } catch (IOException e) {
               e.printStackTrace();
           }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public static void main(String[] args) {
        DanhBaServer danhBaServer = new DanhBaServer();
        danhBaServer.addLienHeBySocket();
    }
}
