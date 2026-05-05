package org.sudokugame.ui.custom.input;

import org.sudokugame.model.Space;
import org.sudokugame.service.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

import static java.awt.Font.PLAIN;
import static org.sudokugame.service.EventEnum.CLEAR_SPACE;

public class NumberText extends JTextField implements EventListener, Highlightable, ErrorCheckable {

    private static final Color HIGHLIGHT_COLOR = new Color(173, 216, 230);
    private static final Color FIXED_COLOR = new Color(220, 220, 220);
    private static final Color DEFAULT_COLOR = Color.WHITE;

    private final Space space;

    public NumberText(final Space space) {
        this.space = space;
        var dimension = new Dimension(50, 50);
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setVisible(true);
        this.setFont(new Font("Arial", PLAIN, 20));
        this.setHorizontalAlignment(CENTER);
        this.setDocument(new NumberTextLimit());
        this.setEditable(!space.isFixed());
        this.setBackground(space.isFixed() ? FIXED_COLOR : DEFAULT_COLOR);

        if (space.isFixed()) {
            this.setText(space.getActual().toString());
        }

        HighlightManager.getInstance().registerField(this);

        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                notifyHighlight();
            }
        });

        this.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changeSpace();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changeSpace();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changeSpace();
            }

            private void changeSpace() {
                if (getText().isEmpty()) {
                    space.clearSpace();
                } else {
                    space.setActual(Integer.parseInt(getText()));
                }
            }
        });
    }

    public Space getSpace() {
        return space;
    }

    public void highlight() {
        this.setBackground(HIGHLIGHT_COLOR);
    }

    public void clearHighlight() {
        this.setBackground(space.isFixed() ? FIXED_COLOR : DEFAULT_COLOR);
    }

    private void notifyHighlight() {
        HighlightManager.getInstance().highlight(space.getRow(), space.getCol());
    }

    @Override
    public void update(final EventEnum eventType) {
        if (eventType.equals(CLEAR_SPACE) && this.isEditable()) {
            this.setText("");
        }
    }

    @Override
    public int getRow() {
        return space.getRow();
    }

    @Override
    public int getCol() {
        return space.getCol();
    }

    @Override
    public boolean hasError() {
        var actual = space.getActual();
        return actual != null && !space.isFixed() && !actual.equals(space.getExpected());
    }
}


