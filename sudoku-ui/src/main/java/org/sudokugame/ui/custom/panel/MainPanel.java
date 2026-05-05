package org.sudokugame.ui.custom.panel;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    public MainPanel(final Dimension dimension) {
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setLayout(new BorderLayout());
    }
}