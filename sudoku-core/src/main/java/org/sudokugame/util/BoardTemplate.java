package org.sudokugame.util;

public final class BoardTemplate {

    private BoardTemplate() {
    }

    public final static String BOARD_TEMPLATE =
            "+-------+-------+-------+%n" +
                    "| %s %s %s | %s %s %s | %s %s %s |%n" +
                    "| %s %s %s | %s %s %s | %s %s %s |%n" +
                    "| %s %s %s | %s %s %s | %s %s %s |%n" +
                    "+-------+-------+-------+%n" +
                    "| %s %s %s | %s %s %s | %s %s %s |%n" +
                    "| %s %s %s | %s %s %s | %s %s %s |%n" +
                    "| %s %s %s | %s %s %s | %s %s %s |%n" +
                    "+-------+-------+-------+%n" +
                    "| %s %s %s | %s %s %s | %s %s %s |%n" +
                    "| %s %s %s | %s %s %s | %s %s %s |%n" +
                    "| %s %s %s | %s %s %s | %s %s %s |%n" +
                    "+-------+-------+-------+";
}