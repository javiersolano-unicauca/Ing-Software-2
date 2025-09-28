package presentation;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author laura
 */


public class GradientPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        Color azulOscuro = new Color(15, 71, 151);
        Color azulClaro = new Color(100, 149, 237);

        
        GradientPaint gp = new GradientPaint(
                0, 0, azulOscuro, 
                0, getHeight(), azulClaro
        );

        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}

