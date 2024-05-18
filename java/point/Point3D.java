package point;
public class Point3D extends Point {
    protected double z;
    public Point3D(double x, double y, double z) {
        super.setValue(x,y); 
        this.z = z;
    }
    public void move(double dx, double dy, double dz) {
        super.move(dx,dy);
        z+=dz;
    }
    public void print() {
        // super.print();
        System.out.println("x = " + x + ",y = " + y+",z = " + z);
    }
    public static void main(String args[]) {
        Point3D p = new Point3D(3,4,5);
        p.print();
        p.move(-1.5,2.0,3);
        p.print();
    } 
}

