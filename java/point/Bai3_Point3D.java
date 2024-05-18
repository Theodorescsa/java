package point;
public class Bai3_Point3D extends Bai2_Point{
    protected double z;
    public Bai3_Point3D(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }
    public void straight(Bai3_Point3D ob1, Bai3_Point3D ob2, Bai3_Point3D ob3) {
        
    }
    public static void main(String[] args) {
        Bai3_Point3D point1 = new Bai3_Point3D(0, 0, 0);
        Bai3_Point3D point2 = new Bai3_Point3D(0, 0, 2);
        Bai3_Point3D point3 = new Bai3_Point3D(0, 0, 4);
    }
}
///lập phương trình đường thẳng 
//xem 3 điểm có thẳng hàng hay không