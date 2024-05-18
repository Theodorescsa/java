class Test {
    int a,b;
    Test(int i, int j) {
        this.a = i;
        this.b = j;
    }
    void meth(Test ob) {
        a = a + 1;
        b = b + 1;
    }
    
}
public class CallByValue {
    public static void main(String args[]) {
        Test ob = new Test(10,20);
        System.out.println("a and b before call:" + ob.a + " " + ob.b);
        ob.meth(ob);
        System.out.println("a and b after call:" + ob.a + " " + ob.b);
    }
}