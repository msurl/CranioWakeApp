package com.app.craniowake.data.model.gameModels;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Game {

    // TODO: Dies ist ein Wert, der nur vorrübergehend genutzt wird, solange das Feature, den Stimulationstyp wählen zu können, noch nicht implementiert ist.
    public static final String DEV_STIMULATION = "DEV";

    public static final String NO_STIMULATION = "NONE";
    private String stimulationType;
    private double stimulation;
    private LocalDateTime dateTime;

    public Game() {
        this(0.0, NO_STIMULATION);
    }

    public Game(double stimulation, String stimulationType) {
        this.stimulation = stimulation;
        this.dateTime = LocalDateTime.now();
    }
}
