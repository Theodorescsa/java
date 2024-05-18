package datuyenjava;
import java.lang.*;
public class RunPingPong implements Runnable {
    String word;
    int delay;
    RunPingPong(String s, int d) {
        word = s;
        delay = d;
    }
    public void run() {
        try {
            for(;;) {
                System.out.println(word+" ");
                Thread.sleep(delay);
            }
        } catch (Exception e) {
            return;
        }
    }
    public static void main(String[] args) {
        RunPingPong obj1 = new RunPingPong("Hello", 1000);
        RunPingPong obj2 = new RunPingPong("My", 3000);
        new Thread(obj1).start();
        new Thread(obj2).start();
    }
}
