package kata6.apps.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import kata6.control.Command;
import kata6.control.DownCommand;
import kata6.control.LeftCommand;
import kata6.control.RightCommand;
import kata6.control.UpCommand;
import kata6.model.Block;
import kata6.view.BlockDisplay;

public class Main extends JFrame {

    public static void main(String[] args) {
       new Main().execute();
    }
    private BlockDisplay blockDisplay;
    private Map<String, Command> commands = new HashMap<>();
    
    public Main() {
        this.setTitle("Block shifter");
        this.setSize(700, 762);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(blockPanel());
        this.add(toolbar(), BorderLayout.SOUTH);
        
        Block block = new Block(4, 4);
        blockDisplay.display(block);
        commands.put("left", new LeftCommand(block));
        commands.put("right", new RightCommand(block));
        commands.put("up", new UpCommand(block));
        commands.put("down", new DownCommand(block));
    }
    
    private void execute() {
        this.setVisible(true);
    }
    
    private JPanel blockPanel() {
        BlockPanel blockPanel = new BlockPanel();
        this.blockDisplay = blockPanel;
        return blockPanel;
    } 

    private JMenuBar toolbar() {
        JMenuBar toolbar = new JMenuBar();
        toolbar.add(button("left"));
        toolbar.add(button("right"));
        toolbar.add(button("up"));
        toolbar.add(button("down"));
        return toolbar;
    }

    private JButton button(String name) {
        JButton button = new JButton(name);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                commands.get(name).execute();
            }
        });
        return button;
    }
    
}
