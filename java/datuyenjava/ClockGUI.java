package datuyenjava;

import javax.swing.*;
import java.awt.*;


public class ClockGUI extends JPanel implements Runnable {
    int hour, minute, second;
    ClockGUI(int h, int m, int s) {
        hour = h;
        minute = m;
        second = s;
    }
    
    public void increase() {
        second++;
        if (second == 60) {
            minute += 1;
            second = 0;
        }
        if (minute == 60) {
            hour += 1;
            minute = 0;
        }
        if (hour == 24) {
            hour = 0;
        }
        repaint();  
    }
    
    @Override
    public void run() {
        for (;;) {
            try {
                Thread.sleep(1000);
                increase();
            } catch (Exception e) {
                return;
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Draw clock face
        int width = getWidth();
        int height = getHeight();
        int radius = Math.min(width, height) / 2 - 10;
        int centerX = width / 2;
        int centerY = height / 2;
        
        g.drawOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
        
        // Draw clock hands
        drawHand(g, centerX, centerY, second * 6, radius - 10, Color.RED);
        drawHand(g, centerX, centerY, minute * 6, radius - 20, Color.BLUE);
        drawHand(g, centerX, centerY, (hour % 12) * 30 + minute / 2, radius - 30, Color.BLACK); 
    }
    
    private void drawHand(Graphics g, int x, int y, double angle, int length, Color color) {
        double rad = Math.toRadians(angle - 90); 
        int xEnd = x + (int) (length * Math.cos(rad));
        int yEnd = y + (int) (length * Math.sin(rad));
        g.setColor(color);
        g.drawLine(x, y, xEnd, yEnd);
    }

    public static void main(String[] args) {
        ClockGUI clock = new ClockGUI(23, 59, 55);
        
        JFrame frame = new JFrame("Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.add(clock);
        frame.setVisible(true);
        
        new Thread(clock).start();
    }
}
