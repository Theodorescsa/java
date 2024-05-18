public class Box {
    double width,height,depth;
    Box(double x, double y, double z) {
        this.width = x;
        this.height = y;
        this.depth = z;
    }
    Box(Box b) {
        width = b.width;
        height = b.height;
        depth = b.depth;
    }
    public void print() {
        System.out.println();
    }
}
