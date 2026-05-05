package org.sudokugame.ui.custom.button;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Checkgamestatusbutton extends JButton {

    public Checkgamestatusbutton(final ActionListener actionListener) {
        this.setText("Verificar jogo");
        this.addActionListener(actionListener);
    }

}
