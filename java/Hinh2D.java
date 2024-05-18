/**
 * Hinh2D
 */
abstract public class Hinh2D {
    double a,b,r;
    public abstract double dientich();
    public abstract double chuvi();
}
class HinhTron extends Hinh2D {
    public HinhTron(double r) {
        this.r = r;
    }
    public double dientich() {
        return Math.PI * r * r;
    }
    public double chuvi() {
        return Math.PI * 2 * r;
    }
}
class HinhChuNhaT extends Hinh2D {
    public HinhChuNhaT(double a, double b) {
        this.a = a;
        this.b = b;
    }
    public double dientich() {
        return a * b;
    }
    public double chuvi() {
        return 2 * (a + b);
    }
}
class abstractDemo {
    public static void main(String args[]) {
        Hinh2D ht = new HinhTron(2);
        System.out.println("Chu vi hinh tron la:" +ht.chuvi()+" Dien tich hinh tron la: " + ht.dientich());
        Hinh2D hcn = new HinhChuNhaT(2,4);
        System.out.println("Chu vi hinh tron la:" +hcn.chuvi()+" Dien tich hinh tron la: " + hcn.dientich());


    }
}
