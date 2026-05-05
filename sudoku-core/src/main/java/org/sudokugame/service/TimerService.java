package org.sudokugame.service;

import javax.swing.*;

public class TimerService {

    private int seconds = 0;
    private Timer timer;
    private JLabel timerLabel;

    public TimerService(JLabel timerLabel) {
        this.timerLabel = timerLabel;
        this.timer = new Timer(1000, e -> {
            seconds++;
            timerLabel.setText(getFormattedTime());
        });
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void reset() {
        timer.stop();
        seconds = 0;
        timerLabel.setText(getFormattedTime());
        timer.start();
    }

    private String getFormattedTime() {
        int minutes = seconds / 60;
        int secs = seconds % 60;
        return String.format("Tempo: %02d:%02d", minutes, secs);
    }
}