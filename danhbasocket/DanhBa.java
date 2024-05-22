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
    // for (String string : arraySodienthoai) {
    //     System.out.println(string);
    // }
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
        int i =0;
        String hotenmoi = "dsadas"; 
        String tenthuonggoimoi = "sadsad";
        String diachimoi = "sdsad"; 
        String emailmoi = "sadsadas"; 
        List<String> sodienthoaimoi = new ArrayList<>();
        String[] listInfo = {hotenmoi, tenthuonggoimoi, diachimoi, emailmoi};

        try {
            ServerSocket ss = new ServerSocket(port);
            System.out.println("Client danhba connected");
            Socket s = null;
            while(true) {
                try {
                    s = ss.accept();
                    PrintWriter pw  = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
                    BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    
                    while(i<5) {
                     
                        String line = br.readLine();
                        if(line.equals("exit"))break;
                        if (i==4) {
                            sodienthoaimoi.add(line);
                        } else {
                            listInfo[i] = line;
                        }
                        pw.println("Server response: Da them" + line + "thanh cong");
                        pw.flush();
                        
                    }
                    LienHe newLienHe = new LienHe(hotenmoi, tenthuonggoimoi, diachimoi, emailmoi, sodienthoaimoi);
                    pw.println("Ten thuong goi"+listInfo[1]);
                    pw.println(listInfo[0]);
                    danhBa.add(newLienHe);
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
        // danhbacuatoi.printAllLienHe();
        // danhbacuatoi.editLienhe("01234", "Thai siuuuu", "Thai", "Phenikaa", "@gamil.com", std3);
        // danhbacuatoi.printAllLienHe();
        // // danhbacuatoi.printAllLienHe();
        // danhbacuatoi.filterLienHe("38213");
        // danhbacuatoi.filterByHoTen("Ngan Ha My");
        // danhbacuatoi.sortHoten();
        // danhbacuatoi.printAllLienHe();
        danhbacuatoi.addLienHeBySocket();
        danhbacuatoi.printAllLienHe();

    }
}    

