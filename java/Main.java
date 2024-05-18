
class MyThread extends Thread {
        public void run() {
            System.out.println("MyThread is running");
        }
    }
    
public class Main {
        public static void main(String[] args) {
            MyThread thread = new MyThread();
            thread.start(); // Bắt đầu thực thi thread
        }
    }
    

