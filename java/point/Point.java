package point;

public class Point {
    protected double x,y;
    public void setValue(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public void move(double dx, double dy) {
        x = x + dx;
        y = y + dy;
    }
    public void print() {
        System.out.println(x);
        System.out.println(y);

    }
}

