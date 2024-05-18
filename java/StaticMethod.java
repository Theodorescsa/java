class StaticMethod {
    static {                    //khối static1
        System.out.println("Khoi static 1");

    }
    public static void main(String[] args) { //phương thức static
        System.out.println("hello world");
    }
    static {                    // khối static 2
        System.out.println("Khoi static 2");
    }
}