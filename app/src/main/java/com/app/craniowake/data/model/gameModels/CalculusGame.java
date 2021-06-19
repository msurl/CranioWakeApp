package com.app.craniowake.data.model.gameModels;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.app.craniowake.data.model.Operation;

import lombok.Data;
import lombok.NoArgsConstructor;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Model of the Calculus Test
 */
@Data
@NoArgsConstructor
@Entity(tableName = "calculus_test_table")
public class CalculusGame extends Game {

    @PrimaryKey(autoGenerate = true)
    private long calcGameId;

    private Equation equation;

    private Integer answer;

    private boolean correct;

    @ForeignKey
            (entity = Operation.class,
                    parentColumns = "operationId",
                    childColumns = "fkOperationId",
                    onDelete = CASCADE
            )
    private long fkOperationId;

    @Ignore
    public CalculusGame(int numbScale) {
        super();
        randomiseEquation(numbScale);
        this.correct = false;
    }

    @Ignore
    public CalculusGame(double stimulation, String stimulationType, int numbScale) {
        super(stimulation, stimulationType);
        randomiseEquation(numbScale);
        this.correct = false;
    }

    @Ignore
    public CalculusGame(Integer answer, Equation equation, long fkOperationId) {
        super();
        this.correct = (equation.result() == answer);
        this.fkOperationId = fkOperationId;
        this.equation = equation;
    }

    @Ignore
    public CalculusGame(Integer answer, Equation equation, double stimulation, String stimulationType, long fkOperationId) {
        super(stimulation, stimulationType);
        this.correct = (equation.result() == answer);
        this.fkOperationId = fkOperationId;
        this.equation = equation;
    }

    public void randomiseEquation(int numbScale) {
        this.equation = Equation.random(numbScale);
    }

    public String getAnswerString() {
        return answer == null ?  "": answer.toString();
    }

    public void setAnswerString(String answerString) {
        if (answerString.equals("")){
            this.answer = null;
            this.correct = false;
        }
        else {
            this.answer = Integer.parseInt(answerString);
            this.correct = (answer == equation.result());
        }
    }

}
