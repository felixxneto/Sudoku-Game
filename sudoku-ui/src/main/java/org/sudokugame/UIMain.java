package org.sudokugame;

import org.sudokugame.service.NotifierService;
import org.sudokugame.ui.custom.screen.MainScreen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class UIMain {

    public static void main(String[] args) throws IOException {

        var filePath = Path.of("board.txt");

        final var gameConfig = Files.readAllLines(filePath)
                .stream()
                .filter(line -> !line.isBlank())
                .collect(Collectors.toMap(
                        k -> k.split(";")[0],
                        v -> v.split(";")[1]
                ));

        var notifierService = new NotifierService();
        var mainScreen = new MainScreen(gameConfig, notifierService);
        mainScreen.buildMainScreen();
    }
}