public class Bai1 {
    public static void main(String[] args) {
        double temp;
       
        double array[] = new double[args.length];
        for (int i = 0; i < args.length; i++) {
            array[i] = Double.parseDouble(args[i]);
        }

        for (int i = 0; i < args.length; i++) {
            array[i] = Double.parseDouble(args[i]);
        }
        for (int i = 0;i< array.length;i++) {
            if (i == array.length -1) {
                break;
            }
            if (array[i+1] < array[i]) {
                temp = array[i];
                array[i] = array[i+1];
                array[i+1] = temp;
                i = -1;
            }
        }
        System.out.println("\nDay so sau khi sap xep:");
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

}
