import java.util.*;
public class cau2 {
    public static void main(String[] args) {
        List<Integer> listNum = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            listNum.add(Integer.parseInt(args[i]));
        }
        for (int i = listNum.get(0); i < listNum.get(1) + 1; i++) {
            System.out.println(i);
        }
    }
}
