package com.app.craniowake.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.app.craniowake.data.model.stimulation.ThresholdStimulation;
import com.app.craniowake.data.model.stimulation.ThresholdTest;

/**
 * data access object which handles all Queries for the {@link ThresholdTest} model.
 */
@Dao
public abstract class ThresholdDao {
    /**
     * equivalent to a SQL INSERT INTO statement
     *
     * @param thresholdTest object is saved as an insert entry to db
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long addThresholdTest(ThresholdTest thresholdTest);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long addStimulation(ThresholdStimulation stimulation);
}
