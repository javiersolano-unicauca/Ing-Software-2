package presentation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.BasicStroke;
import javax.swing.JPanel;
/**
 *
 * @author laura
 */


public class RoundPanel extends JPanel {

    private int arcW = 30;
    private int arcH = 30; 
    private Color colorFondo = Color.WHITE; 
    private Color colorBorde = new Color(180,180,180); 
    private int grosorBorde = 2;

    public RoundPanel() {
        setOpaque(false); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int ancho = getWidth();
        int alto = getHeight();

        int x = grosorBorde;
        int y = grosorBorde;
        int w = ancho - 2*grosorBorde;
        int h = alto - 2*grosorBorde;

        // Fondo
        g2.setColor(colorFondo);
        g2.fillRoundRect(x, y, w, h, arcW, arcH);

        // Borde
        g2.setColor(colorBorde);
        g2.setStroke(new BasicStroke(grosorBorde));
        g2.drawRoundRect(x, y, w, h, arcW, arcH);

        g2.dispose();
    }

    public void setArc(int arcW, int arcH) {
        this.arcW = arcW;
        this.arcH = arcH;
        repaint();
    }

    public void setColorFondo(Color color) {
        this.colorFondo = color;
        repaint();
    }

    public void setColorBorde(Color color) {
        this.colorBorde = color;
        repaint();
    }

    public void setGrosorBorde(int grosor) {
        this.grosorBorde = grosor;
        repaint();
    }
}

