public class Sort {
    public static void main(String[] args) {
        int a[] = {2,3,5,1,7,4};
        int i,j,temp;
        if (args.length > 0) {
            a = new int[args.length];
        }
        for (i=0;i<args.length;i++) {
            a[i] = Integer.parseInt(args[i]);
            System.out.println(a[i] + " ");
        }
        System.out.println();
        for (i=0;i<a.length - 1;i++) {
            for (j = 1;j < a.length;j++) {
                if (a[j] < a[i]) {
                temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        System.out.println();
        for (i = 0;i< a.length;i++) {
            System.out.println(a[i]);
        }
    }
}
}