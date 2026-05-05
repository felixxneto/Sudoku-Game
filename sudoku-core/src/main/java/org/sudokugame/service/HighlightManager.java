package org.sudokugame.service;

import java.util.ArrayList;
import java.util.List;

public class HighlightManager {

    private static HighlightManager instance;
    private final List<Highlightable> allFields = new ArrayList<>();

    private HighlightManager() {
    }

    public static HighlightManager getInstance() {
        if (instance == null) {
            instance = new HighlightManager();
        }
        return instance;
    }

    public void registerField(Highlightable field) {
        allFields.add(field);
    }

    public void highlight(int row, int col) {
        allFields.forEach(field -> {
            if (field.getRow() == row || field.getCol() == col) {
                field.highlight();
            } else {
                field.clearHighlight();
            }
        });
    }

    public long getErrorCount() {
        return allFields.stream()
                .filter(f -> f instanceof ErrorCheckable ec && ec.hasError())
                .count();
    }
}