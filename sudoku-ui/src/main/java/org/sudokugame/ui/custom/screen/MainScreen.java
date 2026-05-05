package org.sudokugame.ui.custom.screen;

import org.sudokugame.model.Space;
import org.sudokugame.service.BoardService;
import org.sudokugame.service.HighlightManager;
import org.sudokugame.service.NotifierService;
import org.sudokugame.service.TimerService;
import org.sudokugame.ui.custom.button.Checkgamestatusbutton;
import org.sudokugame.ui.custom.button.FinishGameButton;
import org.sudokugame.ui.custom.button.ResetButton;
import org.sudokugame.ui.custom.frame.MainFrame;
import org.sudokugame.ui.custom.input.NumberText;
import org.sudokugame.ui.custom.panel.MainPanel;
import org.sudokugame.ui.custom.panel.SudokuSector;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static javax.swing.JOptionPane.*;
import static org.sudokugame.service.EventEnum.CLEAR_SPACE;

public class MainScreen {

    private final static Dimension dimension = new Dimension(600, 680);

    private final BoardService boardService;
    private final NotifierService notifierService;
    private TimerService timerService;

    private JButton checkGameStatusButton;
    private JButton finishGameButton;
    private JButton resetButton;

    public MainScreen(final Map<String, String> gameConfig, NotifierService notifierService) {
        this.boardService = new BoardService(gameConfig);
        this.notifierService = notifierService;
    }

    public void buildMainScreen() {
        JPanel mainPanel = new MainPanel(dimension);
        JFrame mainFrame = new MainFrame(dimension, mainPanel);

        // Painel do topo com timer e erros
        JPanel topPanel = new JPanel(new GridLayout(1, 1));

        JLabel timerLabel = new JLabel("Tempo: 00:00");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);


        topPanel.add(timerLabel);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Painel do tabuleiro no centro
        JPanel boardPanel = new JPanel();
        boardPanel.setPreferredSize(new Dimension(600, 540));
        for (int r = 0; r < 9; r += 3) {
            var endRow = r + 2;
            for (int c = 0; c < 9; c += 3) {
                var endCol = c + 2;
                var spaces = getSpacesFromSector(boardService.getSpaces(), c, endCol, r, endRow);
                JPanel sector = generateSection(spaces);
                boardPanel.add(sector);
            }
        }
        mainPanel.add(boardPanel, BorderLayout.CENTER);

        // Painel dos botões na base
        JPanel buttonPanel = new JPanel();
        addResetButton(buttonPanel);
        addCheckGameStatusButton(buttonPanel);
        addFinishGameButton(buttonPanel);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        timerService = new TimerService(timerLabel);
        timerService.start();

        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private List<Space> getSpacesFromSector(final List<List<Space>> spaces,
                                            final int initColl, final int endColl,
                                            final int initRow, final int endRow) {
        List<Space> spaceSector = new ArrayList<>();
        for (int r = initRow; r <= endRow; r++) {
            for (int c = initColl; c <= endColl; c++) {
                spaceSector.add(spaces.get(r).get(c));
            }
        }
        return spaceSector;
    }

    private JPanel generateSection(final List<Space> spaces) {
        List<NumberText> fields = new ArrayList<>(spaces.stream().map(NumberText::new).toList());
        fields.forEach(t -> notifierService.subscriber(CLEAR_SPACE, t));
        return new SudokuSector(fields);
    }

    private void addFinishGameButton(JPanel mainPanel) {
        finishGameButton = new FinishGameButton(e -> {
            if (boardService.gameIsFinished()) {
                timerService.stop();
                JOptionPane.showMessageDialog(null, "Parabéns você concluiu o jogo!");
                resetButton.setEnabled(false);
                checkGameStatusButton.setEnabled(false);
                finishGameButton.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "Seu jogo tem alguma inconsistência, ajuste e tente novamente");
            }
        });
        mainPanel.add(finishGameButton);
    }

    private void addCheckGameStatusButton(JPanel mainPanel) {
        checkGameStatusButton = new Checkgamestatusbutton(e -> {
            var hasErrors = boardService.hasErrors();
            var gameStatus = boardService.getGameStatus();
            var errorCount = HighlightManager.getInstance().getErrorCount(); // ← novo

            var message = switch (gameStatus) {
                case NON_STARTED -> "O jogo não foi iniciado.";
                case INCOMPLETE -> "O jogo está incompleto";
                case COMPLETE -> "O jogo está completo";
            };
            message += hasErrors
                    ? " e contém " + errorCount + " erro(s)" // ← mostra a quantidade
                    : " e não contém erros";

            showMessageDialog(null, message);
        });
        mainPanel.add(checkGameStatusButton);
    }

    private void addResetButton(JPanel mainPanel) {
        resetButton = new ResetButton(e -> {
            var dialogResult = showConfirmDialog(
                    null,
                    "Deseja realmente reiniciar o jogo?",
                    "Limpar o jogo",
                    YES_NO_OPTION,
                    QUESTION_MESSAGE
            );
            if (dialogResult == 0) {
                boardService.reset();
                notifierService.notify(CLEAR_SPACE);
                timerService.reset();
            }
        });
        mainPanel.add(resetButton);
    }
}