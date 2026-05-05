package org.sudokugame.ui.custom.frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame(final Dimension dimension, final JPanel panel) {
        super("Sudoku Game");
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(panel);
        this.setVisible(true);
    }
}
