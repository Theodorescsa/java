
public class bt1 {
    public void test(int number) {
        System.out.println(number);
    }
    public static void main(String[] args) {

        float min;
  
        float number[] = {2,3,1,6,4,7};
        System.out.println("Sap xep theo thu tu tang dan:\n");
        for (int i = 0;i< number.length;++i) {
            if (i == number.length - 1) {
                break;
            }
            if (number[i + 1] < number[i]) {
                min = number[i];
                number[i] = number[i+1];
                number[i+1] = min;
                i = -1;
            }
        }
        for (float num : number) {
            System.out.println(num);
        }
    }
}
