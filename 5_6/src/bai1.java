import java.util.*;
public class bai1 {
    public static void main(String[] args) {
        int sum = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Moi nhap so muon kiem tra:");
        int intNum = Integer.parseInt(scanner.nextLine());
        for (int i = 1; i < intNum; i++) {
            if (intNum%i == 0) {
                sum+=i;
            }
        }
        if(sum == intNum) {
            System.out.println("Day la so hoan hao");
        }   else {
            System.out.println("Day khon gphai la so hoan hao");
        }
    }
}