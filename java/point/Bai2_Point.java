package point;
public class Bai2_Point {
    double x,y;
    public Bai2_Point(double x, double y ) {
        this.x = x;
        this.y = y;
   
    }
    public void move(double dx, double dy) {
        this.x +=dx;
        this.y +=dy;
    }
    public void print() {
        System.out.println(x+","+y);
    }
    public void distance(Bai2_Point ob1, Bai2_Point ob2) {
        double result;
        result = Math.sqrt(Math.pow((ob2.x-ob1.x),2)+Math.pow((ob2.y-ob1.y), 2));
        System.out.println(result);
    }
    public void straight(Bai2_Point ob1, Bai2_Point ob2, Bai2_Point ob3) {
        double x1,y1,x2,y2, i1,i2;
        x1 = ob2.x - ob1.x;
        y1 = ob2.y - ob1.y;
        x2 = ob3.x - ob1.x;
        y2 = ob3.y - ob1.y;
        i1 = x2/x1;
        i2 = y2/y1;
        if (x1 * i2 == x2 || y1 *i1 == y2) {
            System.out.println("3 diem thang hang");
            
        } else {
            System.out.println("3 diem khong thang hang");
            double area = 0.5 * Math.abs((ob2.x - ob1.x) * (ob3.y - ob1.y) - (ob3.x - ob1.x) * (ob2.y - ob1.y));
            System.out.println("\n Dien tich tam giac tao boi 3 diem khong thang hang la:" + area);
        }
        
    }
    public void phuongtrinh(Bai2_Point ob1) {
        if (this.x == ob1.x) {
            System.out.println("Phuong trinh duong thang la:x=" +x);
        }
        if (this.y == ob1.y) {
            System.out.println("Phuong trinh duong thang la:y=" +y);
        }
        if (this.x!=ob1.x && this.y!=ob1.y) {
            double a = (ob1.y-this.y)/(ob1.x-this.x);
            double b = this.y + (-this.x)/(ob1.x-this.x);
            System.out.println("Phuong trinh duong thang y = "+a+"x"+"+"+b);
        }
    }
    public static void main(String[] args) {
        Bai2_Point Point1  = new Bai2_Point(2,0);
        Bai2_Point Point2  = new Bai2_Point(0,2);
        Bai2_Point Point3 = new Bai2_Point(0,7);
  
        // Point3.distance(Point1, Point2);
        // Point3.straight(Point1, Point2, Point3);
        Point1.phuongtrinh(Point3);
    }
}
