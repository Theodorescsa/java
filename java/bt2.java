
import java.util.Scanner;
public class bt2 {
    public static void main(String[] args) {
        int firstNumber = 0;
        int secondNumber = 1;
        int thirdNumber;
        System.out.println("Nhap so luong phan tu cua day fibonacci ma ban muon:");

        Scanner quantityScanner = new Scanner(System.in);
        String stringQuantity = quantityScanner.nextLine();
        int intQuantity = Integer.parseInt(stringQuantity);
        int i = 0;
        System.out.println("Day so fibonacci:\n");
        while (i < intQuantity) {

            thirdNumber = firstNumber + secondNumber;
            i++;

            System.out.println(thirdNumber);
            firstNumber = secondNumber;
            secondNumber = thirdNumber;
        }
    }
}
