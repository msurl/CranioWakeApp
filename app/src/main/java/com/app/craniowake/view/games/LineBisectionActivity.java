package com.app.craniowake.view.games;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.app.craniowake.R;
import com.app.craniowake.data.model.gameModels.LineBisectionGame;
import com.app.craniowake.view.OperationActivity;
import com.app.craniowake.view.activityHelper.IntentHolder;
import com.app.craniowake.view.activityHelper.StopWatch;
import com.app.craniowake.view.games.displayResults.BaseResultActivity;
import com.app.craniowake.view.viewModel.LineBisectionViewModel;
import com.app.craniowake.view.viewModel.OperationViewModel;

/**
 * Implementation of the Line Bisection Test
 */
public class LineBisectionActivity extends OperationActivity {

    LineBisectionViewModel lineBisectionViewModel;
    OperationViewModel operationViewModel;

    private Button btn;
    private StopWatch stopWatch;

    private int correctAnswers = 0;
    private int wrongAnswers = 0;

    /**
     * reacts on motion of patient when touched on screen
     * when touched down (action.down) the location of touchpoint is being evaluated
     */
    public View.OnTouchListener handleTouch = new View.OnTouchListener() {
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            float touchX = (int) event.getX();
            float touchY = (int) event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    evaluateAnswer(touchX, touchY, v);
                    break;
                case MotionEvent.ACTION_MOVE:
                case MotionEvent.ACTION_UP:
                    break;
            }
            resetOnTouchHandler();
            return false;
        }
    };

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_middle_of_the_line_test);

        initializeToolbar(R.id.toolbar_seekbar);
        initiateSeekbar();

        TextView txtTimer = findViewById(R.id.timerView_mol);
        btn = findViewById(R.id.timer_button_mol);
        Handler handler = new Handler();

        stopWatch = new StopWatch(handler);
        stopWatch.setTxtTimer(txtTimer);
    }

    /**
     * setups all the layouts to be touchable
     */
    @SuppressLint("ClickableViewAccessibility")
    private void setupViewsForTouch() {
        final FrameLayout myView = findViewById(R.id.middle_of_line_area);
        final View myViewLine = findViewById(R.id.middle_of_line_yellow_line);
        myViewLine.setBackgroundColor(Color.YELLOW);
        myView.setOnTouchListener(handleTouch);
        myViewLine.setOnTouchListener(handleTouch);
    }

    /**
     * starts timer of Line Bisection Test
     *
     * @param view method called when "start" button is clicked
     */
    @SuppressLint("ClickableViewAccessibility")
    public void startGame(View view) {
        setupViewsForTouch();
        if (btn.getText().toString().equals("start")) {
            btn.setText(R.string.stop);
            stopWatch.resetTimer();
            stopWatch.startTime();
        } else {
            btn.setText(R.string.start);
            stopWatch.stopTime();
        }
    }

    /**
     * stops timer of Line Bisection Test
     *
     * @param view method called when "stop" button is clicked
     */
    public void touchField(View view) {
        if (btn.getText().toString().equals("stop")) {
            btn.setText(R.string.start);
            stopWatch.stopTime();
        }
    }

    /**
     * distance always positive
     *
     * @param touchPoint  point touched by patient
     * @param middlePoint point in the middle of the line
     */
    private float calcOnTouchScreenDistance(float touchPoint, float middlePoint) {
        float distance;
        if (touchPoint < middlePoint) {
            distance = middlePoint - touchPoint;
        } else {
            distance = touchPoint - middlePoint;
        }
        return distance;
    }

    /**
     * answer is considered correct, with a radius of 50 dpi from middlepoint
     * if correct - line changes color to green
     */
    private void setAnswer(float distanceX, float distanceY) {
        final View myViewLine = findViewById(R.id.middle_of_line_yellow_line);
        if (distanceX <= 50 && distanceY <= 50) {
            myViewLine.setBackgroundColor(Color.GREEN);
            correctAnswers++;
        } else {
            wrongAnswers++;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void resetOnTouchHandler() {
        final FrameLayout myView = findViewById(R.id.middle_of_line_area);
        final View myViewLine = findViewById(R.id.middle_of_line_yellow_line);
        myView.setOnTouchListener(null);
        myViewLine.setOnTouchListener(null);
    }

    private void evaluateAnswer(float touchX, float touchY, View view) {
        float[] middlePoint = calcMiddlePoint(view);
        setAnswer(calcOnTouchScreenDistance(touchX, middlePoint[0]), calcOnTouchScreenDistance(touchY, middlePoint[1]));
        saveLineDissectionGame(getDistanceInCm(touchX, touchY, middlePoint[0], middlePoint[1]), stopWatch.getMilliSeconds());
    }

    private float getDistanceInCm(float distanceX1, float distanceY1, float distanceX2, float distanceY2) {
        return convertFromDpiToMm(calcEuclidianDistance(distanceX1, distanceY1, distanceX2, distanceY2));
    }

    /**
     * value needs to be saved in mm to db so it needs to be converted from dpi to mm
     *
     * @param dpiDistance the total distance calculated in dpi
     */
    private float convertFromDpiToMm(float dpiDistance) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        return dpiDistance / metrics.xdpi * 25.4f;
    }


    /**
     * calculates euclidean distance of touchPoint(distance X1, distanceY1) and middlePoint(distanceX2, distanceY2)
     */
    private float calcEuclidianDistance(float distanceX1, float distanceY1, float distanceX2, float distanceY2) {
        return (float) Math.sqrt(Math.pow((distanceX1 - distanceX2), 2) + Math.pow((distanceY1 - distanceY2), 2));
    }

    /**
     * get middlePoint from view
     */
    private float[] calcMiddlePoint(View view) {
        float[] middlePoint = new float[2];
        middlePoint[0] = view.getPivotX();
        middlePoint[1] = view.getPivotY();
        return middlePoint;
    }

    /**
     * creates object of LineBisectionGame and saves the answer to the database. Object is processed by the LineBisectionViewModel
     *
     * @param distance    from touchpoint to middlepoint
     * @param miliseconds time needed to touch screen
     */
    private void saveLineDissectionGame(float distance, int miliseconds) {
        lineBisectionViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(LineBisectionViewModel.class);
        operationViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(OperationViewModel.class);
        operationViewModel.getOperationByDate(getCurrentOperationId()).observe(this, operation -> {
            try {
                LineBisectionGame lineBisectionGame = new LineBisectionGame(distance, miliseconds, operation.getFkPatientId());
                lineBisectionViewModel.addLineDissectionGame(lineBisectionGame);
            } catch (Exception e) {
                System.out.println("Line Dissection Test has not been added to db");
            }
        });
    }

    /**
     * returns string of dateTime when current operation was created t
     * its used as an identifier
     */
    private String getCurrentOperationId() {
        Intent intent = getIntent();
        return intent.getStringExtra(IntentHolder.OPERATION_DATE);
    }

    /**
     * saves results in an Intent and opens new BaseResultActivity. The current Activity gets destroyed when left.
     *
     * @param view method is called when button "finish game" is clicked
     */
    public void finishMoLGame(View view) {
        Intent intent = new Intent(this, BaseResultActivity.class);
        intent.putExtra(IntentHolder.GAME_NAME, getString(R.string.mol_test));
        intent.putExtra(IntentHolder.RUNS, (correctAnswers + wrongAnswers));
        intent.putExtra(IntentHolder.CORRECT_ANSWERS, correctAnswers);
        intent.putExtra(IntentHolder.WRONG_ANSWERS, wrongAnswers);

        finish();
        startActivity(intent);
    }
}

