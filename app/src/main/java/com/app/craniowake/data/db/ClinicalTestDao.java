package com.app.craniowake.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.app.craniowake.data.model.stimulation.ClinicalTest;

@Dao
public interface ClinicalTestDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addClinicalTest(ClinicalTest clinicalTest);
}
