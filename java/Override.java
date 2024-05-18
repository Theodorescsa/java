class B extends A {
    int k;
    B(int a, int b, int c) {
        super(a, b);
        k=c;
    }
    void show() {
        System.out.println("k="+k);
    }
}
class Override {
    public static void main(String args[]) {
        B b = new B(1, 2, 3);
        b.show();
    }
}
