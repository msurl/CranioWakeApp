package com.app.craniowake.data.model.gameModels;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Game {
    private double stimulation;
    private LocalDateTime dateTime;

    public Game() {
        this(0.0);
    }

    public Game(double stimulation) {
        this.stimulation = stimulation;
        this.dateTime = LocalDateTime.now();
    }
}
