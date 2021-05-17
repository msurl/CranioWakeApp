package com.app.craniowake.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.app.craniowake.data.model.stimulation.VerificationStimulation;
import com.app.craniowake.data.model.stimulation.VerificationTest;

/**
 * data access object which handles all Queries for the {@link VerificationTest} model.
 */
@Dao
public abstract class VerificationDao {
    /**
     * equivalent to a SQL INSERT INTO statement
     *
     * @param verificationTest object is saved as an insert entry to db
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long addVerificationTest(VerificationTest verificationTest);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void addStimulation(VerificationStimulation stimulation);

    @Query("SELECT * FROM verification_test_table WHERE fkOperationid = :id")
    public abstract LiveData<VerificationTest> getByOperationId(int id);
}
