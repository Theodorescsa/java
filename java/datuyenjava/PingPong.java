package datuyenjava;
public class PingPong extends Thread {
    String word;
    int delay;
    PingPong(String s, int d) {
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
        new PingPong("ping", 1000).start();
        new PingPong("Pong", 3000).start();

    }
}