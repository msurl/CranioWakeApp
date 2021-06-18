package com.app.craniowake.data.model.gameModels;

public enum Operator {

    PLUS("+"),
    MINUS("-"),
    TIMES("*");


    private final String operator;

    private Operator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return operator;
    }
}
