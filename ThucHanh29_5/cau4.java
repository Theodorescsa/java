import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class cau4 {

    // Hàm kiểm tra số nguyên tố
    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java ExactPrime <input_file> <output_file>");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];

        List<Integer> primes = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(inputFile))) {
            while (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                if (isPrime(number)) {
                    primes.add(number);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + inputFile);
            return;
        }

        try (PrintWriter writer = new PrintWriter(new File(outputFile))) {
            for (int prime : primes) {
                writer.println(prime);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + outputFile);
        }
    }
}
