package com.app.craniowake.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.app.craniowake.data.model.Operation;

import java.time.LocalDateTime;

/**
 * data access object which handles all Queries for the Operation model.
 */
@Dao
public abstract class OperationDao {

    /**
     * equivalent to a SQL INSERT INTO statement
     *
     * @param operation object is saved as an insert entry to db
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long addOperation(Operation operation);

    /**
     * equivalent to a SQL SELECT Query. Selects all Operation Entries from the operation_table where the dateTime is equal to the param
     *
     * @param dateTime Time and Date of the operation used as an identifier
     */
    @Query("SELECT * FROM operation_table WHERE dateTime = :dateTime")
    public abstract LiveData<Operation> getOperationByDate(LocalDateTime dateTime);
}
