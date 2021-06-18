package com.app.craniowake.view.activityHelper;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.TextView;

import lombok.Data;

/**
 * StopWatch that can start and stop time. Needed for Tests like Reaction or Line Bisection Test to track time.
 */
@Data
public class StopWatch {

    private final Handler handler;
    private TextView txtTimer;
    private int minutes;
    private int seconds;
    private int milliSeconds;
    private long startTime, timeInMilliseconds, timeBuff, updateTime = 0L;

    /**
     * thread to handle running time through handler var. Updates minutes seconds and milliseconds.
     */
    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updateTime = timeBuff + timeInMilliseconds;
            int milliSeconds = (int) (updateTime % 1000);
            int seconds = (int) (updateTime / 1000);
            int minutes = seconds / 60;
            seconds %= 60;
            setTimer(minutes, seconds, milliSeconds);
            handler.postDelayed(this, 0);
        }
    };

    public StopWatch(Handler handler) {
        this.handler = handler;
    }

    /**
     * resets timer to 00:00.000
     */
    public void resetTimer() {
        setTimer(0, 0, 0);
        updateTime = 0L;
        startTime = 0L;
        timeInMilliseconds = 0L;
        timeBuff = 0L;
    }

    /**
     * sets formatted time in a classVariable to be used in the class below
     */
    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    private void setTimer(int minutes, int seconds, int milliSeconds) {
        this.minutes = minutes;
        this.seconds = seconds;
        this.milliSeconds = milliSeconds;
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    public String getTimer() {
        return "" + minutes + ":" + String.format("%02d", seconds) + ":" + String.format("%03d", milliSeconds);
    }

    /**
     * stops timer
     */
    public void stopTime() {
        timeBuff += timeInMilliseconds;
        handler.removeCallbacks(runnable);
    }

    /**
     * starts timer
     */
    public void startTime() {
        resetTimer();
        startTime = SystemClock.uptimeMillis();
        handler.postDelayed(runnable, 0);
    }
}
