package com.app.craniowake.view.games;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.app.craniowake.R;
import com.app.craniowake.data.model.gameModels.CalculusGame;
import com.app.craniowake.view.OperationActivity;
import com.app.craniowake.view.activityHelper.IntentHolder;
import com.app.craniowake.view.games.displayResults.BaseResultActivity;
import com.app.craniowake.view.viewModel.CalculatingViewModel;
import com.app.craniowake.view.viewModel.OperationViewModel;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * Implementation of the Calculus Test
 */
public class CalculusActivity extends OperationActivity {

    OperationViewModel operationViewModel;
    CalculatingViewModel calculatingViewModel;

    private int[] numberSpectrum;
    private String tmpOperator;
    private int firstNumber;
    private int secondNumber;

    private int correctAnswers = 0;
    private int wrongAnswers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_calc_test);
        initializeToolbar(R.id.toolbar_seekbar);
        initiateSeekbar();

        createEquation();
    }

    /**
     * generates array of numbers from 0-20 for equations
     */
    private void generateRangeOfNumbers() {
        this.numberSpectrum = new int[21];
        for (int i = 0; i < 21; i++) {
            this.numberSpectrum[i] = i;
        }
    }

    /**
     * generates entire equation and displays it
     */
    @SuppressLint("SetTextI18n")
    private void createEquation() {
        makeFieldEditable();
        generateRangeOfNumbers();
        setRandomNumbers();
        generateEquationOperator();
        if (tmpOperator.equals("-")) {
            checkIfPositiveOutcome();
        }
        displayNumbers();
    }

    /**
     * makes field to input answer editable
     */
    private void makeFieldEditable() {
        EditText givenAnswer = findViewById(R.id.calcAnswer);
        givenAnswer.setText("", TextView.BufferType.EDITABLE);
        givenAnswer.setHint("0");
    }

    /**
     * displays numbers for equation
     */
    @SuppressLint("SetTextI18n")
    private void displayNumbers() {
        TextView firstNumberView = findViewById(R.id.firstNumberView);
        TextView secondNumberView = findViewById(R.id.secondNumberView);
        firstNumberView.setText(Integer.toString(this.firstNumber));
        secondNumberView.setText(Integer.toString(this.secondNumber));
    }

    private int getRandomNumber() {
        Random randomNumber = new Random();
        return this.numberSpectrum[randomNumber.nextInt(numberSpectrum.length)];
    }

    /**
     * sets random numbers for equation from numberSpectrum array
     */
    private void setRandomNumbers() {
        this.firstNumber = getRandomNumber();
        this.secondNumber = getRandomNumber();
    }

    /**
     * generates and displays arithmetic operator for equation
     */
    private void generateEquationOperator() {
        String[] calcOperation;
        calcOperation = getResources().getStringArray(R.array.calcOperation);
        Random randomNumber = new Random();
        tmpOperator = calcOperation[randomNumber.nextInt(calcOperation.length)];
        displayEquationOperator();
    }

    private void displayEquationOperator() {
        TextView textViewOperator = findViewById(R.id.operatorView);
        textViewOperator.setText(tmpOperator);
    }

    private String getCurrentDisplayedTextView(int id) {
        TextView textView = findViewById(id);
        return textView.getText().toString();
    }

    /**
     * avoiding equations with negative result
     */
    private void checkIfPositiveOutcome() {
        if (firstNumber < secondNumber) {
            int tmp = firstNumber;
            firstNumber = secondNumber;
            secondNumber = tmp;
        }
    }

    /**
     * avoiding equations with negative result
     */
    private boolean checkIfEmpty() {
        EditText editText = findViewById(R.id.calcAnswer);
        if (TextUtils.isEmpty(editText.getText())) {
            Toast.makeText(this, "please give an answer", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * saves run of calculus test to database and generates a new equation
     *
     * @param view method is called when "next" button is clicked
     */
    public void onClickAnswer(View view) {
        int i = 0;
        while (i != 1) {
            if (!checkIfEmpty()) {
                break;
            } else {
                saveCalculatingAnswer(evaluateAnswer(getAnswerFromUiInput()),
                        getCurrentDisplayedTextView(R.id.firstNumberView),
                        getCurrentDisplayedTextView(R.id.secondNumberView),
                        getCurrentDisplayedTextView(R.id.operatorView));
                setTotalCorrectAndWrongAnswers(evaluateAnswer(getAnswerFromUiInput()));
                createEquation();
                i = 1;
            }
        }
    }

    /**
     * creates object of CalculusGame and saves equation and its answer to the database. Object is processed by the CalculatingViewModel
     *
     * @param answer    answer given by patient
     * @param firstNum, secondNum, operator - create current equation and are saved as a string
     */
    private void saveCalculatingAnswer(boolean answer, String firstNum, String secondNum, String operator) {
        calculatingViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(CalculatingViewModel.class);
        operationViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(OperationViewModel.class);
        operationViewModel.getOperationByDate((LocalDateTime) getCurrentOperationDate()).observe(this, operation -> {
            try {
                CalculusGame calculusGame = new CalculusGame(answer, equationToString(firstNum, secondNum, operator), operation.getOperationId());
                calculatingViewModel.addCalculatingGame(calculusGame);
            } catch (Exception e) {
                System.out.println("CalcGame has not been added to db");
            }
        });
    }

    /**
     * answer given by patient is added to general counter of correct answers.
     */
    private void setTotalCorrectAndWrongAnswers(boolean answer) {
        if (answer) {
            correctAnswers++;
        } else {
            wrongAnswers++;
        }
    }

    /**
     * LiveData has a delayed runtime so the equation has to be taken from the UI.
     *
     * @return Answer that was typed in by the user
     */
    private int getAnswerFromUiInput() {
        EditText givenAnswer = findViewById(R.id.calcAnswer);
        return Integer.parseInt(givenAnswer.getText().toString());
    }

    /**
     * checks if result of patient is true or false
     *
     * @return false or true answer of patient
     */
    private boolean evaluateAnswer(int answer) {
        switch (tmpOperator) {
            case "+":
                if (firstNumber + secondNumber == answer) {
                    return true;
                }
            case "-":
                if (firstNumber - secondNumber == answer) {
                    return true;
                }
            case "*":
                if (firstNumber * secondNumber == answer) {
                    return true;
                }
            default:
                break;
        }
        return false;
    }

    /**
     * generates equation string to be saved in database
     *
     * @return equation including first, second number and the operator
     */
    public String equationToString(String firstNumer, String secondNumber, String equation) {
        return firstNumer + equation + secondNumber;
    }

    /**
     * saves results in an Intent and opens new BaseResultActivity. The current Activity gets destroyed when left.
     *
     * @param view method is called when button "finish game" is clicked
     */
    public void finishCalcGame(View view) {
        Intent intent = new Intent(this, BaseResultActivity.class);

        intent.putExtra(IntentHolder.GAME_NAME, getString(R.string.math_test));
        intent.putExtra(IntentHolder.RUNS, (correctAnswers + wrongAnswers));
        intent.putExtra(IntentHolder.CORRECT_ANSWERS, correctAnswers);
        intent.putExtra(IntentHolder.WRONG_ANSWERS, wrongAnswers);

        finish();
        startActivity(intent);

    }

}