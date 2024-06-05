import java.util.*;
public class bai2 {
    public static void main(String[] args) {
        Float[] listNum = new Float[args.length];
        float temp;
        for (int i = 0; i < args.length; i++) {
            listNum[i] = Float.parseFloat(args[i]);
        }
        for (int i = 0; i < listNum.length; i++) {
            if(i==listNum.length-1) {
                break;
            }
            if (listNum[i+1]< listNum[i]) {
                temp = listNum[i];
                listNum[i] = listNum[i+1];
                listNum[i+1] = temp;
                i=-1;
            }
        }
        System.out.println("So nho nhat la:"+listNum[0]);
    }
}
