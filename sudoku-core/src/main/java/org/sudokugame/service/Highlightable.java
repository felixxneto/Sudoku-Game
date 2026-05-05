package org.sudokugame.service;

public interface Highlightable {
    void highlight();
    void clearHighlight();
    int getRow();
    int getCol();
}
