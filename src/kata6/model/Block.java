package kata6.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Block {
    public static final int MAX = 7;
    private int x;
    private int y;
    private final Timer timer;
    private final List<Observer> observers;
    
    public Block(int x, int y) {
        this.x = x;
        this.y = y;
        this.timer = new Timer();
        this.timer.schedule(task(), 1000, 500);
        this.observers = new ArrayList<>();
    }
    
    public void register(Observer observer) {
        this.observers.add(observer);
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }
    
    public void left() {
        if (x == 1) return;
        x--;
        changed();
    }
    
    public void right() {
        if (x == MAX) return;
        x++;
        changed();
    }
    
    public void up() {
        if (y == MAX) return;
        y++;
        changed();
    }
    
    public void down() {
        if (y == 1) return;
        y--;
        changed();
    }

    private TimerTask task() {
        return new TimerTask() {
            @Override
            public void run() {
                double r = Math.random();
                if (r > 0.4) return;
                if (r > 0.3) up();
                else if (r > 0.2) down();
                else if (r > 0.1) left();
                else right();
            }
            
        };
    }

    private void changed() {
        for (Observer observer : observers) {
            observer.changed();
        }
    }
    
    public interface Observer {
        void changed();
    }
}
