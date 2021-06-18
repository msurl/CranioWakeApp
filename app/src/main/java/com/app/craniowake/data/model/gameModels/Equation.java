package com.app.craniowake.data.model.gameModels;

import android.graphics.Path;

import java.util.Random;
import java.util.StringTokenizer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Equation {

    @Setter
    @Getter
    private Integer firstOperand;
    @Setter
    @Getter
    private Integer secondOperand;
    @Setter
    @Getter
    private Operator operator;

    public void setOperator(String operator) {
        this.operator = Operator.valueOf(operator);
    }

    @Override
    public String toString() {
        return firstOperand + " " + operator + " " + secondOperand;
    }

    public static Equation fromString(String e) {
        StringTokenizer tokenizer = new StringTokenizer(e);
        if(tokenizer.countTokens() != 3) {
            // TODO: might remove
            throw new IllegalArgumentException();
        }
        Integer firstOperand = Integer.parseInt(tokenizer.nextToken());
        Operator operator = Operator.valueOf(tokenizer.nextToken());
        Integer secondOperand = Integer.parseInt(tokenizer.nextToken());

        return new Equation(firstOperand, secondOperand, operator);
    }

    public Integer result() {
        if(operator == Operator.PLUS)
            return firstOperand + secondOperand;
        if (operator == Operator.MINUS)
            return firstOperand - secondOperand;
        else
            return firstOperand * secondOperand;
    }

    public static Equation random(int numbScale) {
        Random random = new Random();
        Integer first = random.nextInt(numbScale);
        Integer second = random.nextInt(numbScale);

        Operator[] operators = Operator.values();
        Operator operator = operators[random.nextInt(operators.length)];

        return new Equation(Integer.max(first, second), Integer.min(first, second), operator);
    }
}
