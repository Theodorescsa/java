import java.util.*;
public class cau1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int highestNum;
        int highestUocChung = 1;
        System.out.println("Moi ban nhap so thu nhat: ");
        int firstNum = Integer.parseInt(scanner.nextLine());
        System.out.println("Moi ban nhap so thu hai: ");
        int secondNum = Integer.parseInt(scanner.nextLine());
        if (firstNum >= secondNum) {
            highestNum = firstNum;
        } else {
            highestNum = secondNum;
        }
        for (int i = 1; i < highestNum; i++) {
            if (firstNum % i == 0 && secondNum % i == 0) {
                highestUocChung = i;
            }
        }
        System.out.println("Uoc chung lon nhat la : "+ highestUocChung);
    }
}