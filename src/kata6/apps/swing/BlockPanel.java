package kata6.apps.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import kata6.view.BlockDisplay;

public class BlockPanel extends JPanel implements BlockDisplay {
    private final int size;
    private final int max;
    private int x;
    private int y;
    private Moved moved = new Moved.Null();

    public BlockPanel(int size, int max) {
        this.size = size;
        this.max = max;
        MouseHandler mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
    }
    
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        g.setColor(Color.black);
        int limit = size * max;
        for (int i = 0; i <= limit; i+=size) {
            g.drawLine(i, 0, i, limit);
            g.drawLine(0, i, limit, i);
        }
        
        g.setColor(Color.red);
        g.fillRect(x*size, y*size, size, size);
    }

    @Override
    public void displayBlock(int x, int y) {
        this.x = x;
        this.y = y;
        repaint();
    }

    @Override
    public void on(Moved moved) {
        this.moved = moved;
    }

    private class MouseHandler implements MouseListener, MouseMotionListener {
        private boolean grabbed = false;
        
        @Override
        public void mouseClicked(MouseEvent event) {
            
        }

        @Override
        public void mousePressed(MouseEvent event) {
            grabbed = event.getX()/size == x && event.getY()/size == y;
        }

        @Override
        public void mouseReleased(MouseEvent event) {
            grabbed = false;
        }

        @Override
        public void mouseEntered(MouseEvent event) {
        
        }

        @Override
        public void mouseExited(MouseEvent event) {
        
        }

        @Override
        public void mouseDragged(MouseEvent event) {
            if (!grabbed) return;
            if (moved == null) return;
            moved.to(event.getX()/size, event.getY()/size);
        }

        @Override
        public void mouseMoved(MouseEvent event) {
            
        }
    }
}
