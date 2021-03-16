package com.app.craniowake.data.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.app.craniowake.data.model.Patient;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class PatientDao_Impl extends PatientDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Patient> __insertionAdapterOfPatient;

  public PatientDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPatient = new EntityInsertionAdapter<Patient>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `patient_table` (`patientId`,`caseNumber`,`lastname`,`firstname`,`birthDate`,`dateTime`,`sex`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Patient value) {
        stmt.bindLong(1, value.getPatientId());
        stmt.bindLong(2, value.getCaseNumber());
        if (value.getLastname() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getLastname());
        }
        if (value.getFirstname() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getFirstname());
        }
        if (value.getBirthDate() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getBirthDate());
        }
        if (value.getDateTime() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getDateTime());
        }
        if (value.getSex() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getSex());
        }
      }
    };
  }

  @Override
  public void addPatient(final Patient patient) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfPatient.insert(patient);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<Patient>> getAllPatients() {
    final String _sql = "SELECT * FROM patient_table ORDER BY caseNumber DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"patient_table"}, false, new Callable<List<Patient>>() {
      @Override
      public List<Patient> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPatientId = CursorUtil.getColumnIndexOrThrow(_cursor, "patientId");
          final int _cursorIndexOfCaseNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "caseNumber");
          final int _cursorIndexOfLastname = CursorUtil.getColumnIndexOrThrow(_cursor, "lastname");
          final int _cursorIndexOfFirstname = CursorUtil.getColumnIndexOrThrow(_cursor, "firstname");
          final int _cursorIndexOfBirthDate = CursorUtil.getColumnIndexOrThrow(_cursor, "birthDate");
          final int _cursorIndexOfDateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "dateTime");
          final int _cursorIndexOfSex = CursorUtil.getColumnIndexOrThrow(_cursor, "sex");
          final List<Patient> _result = new ArrayList<Patient>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Patient _item;
            final long _tmpCaseNumber;
            _tmpCaseNumber = _cursor.getLong(_cursorIndexOfCaseNumber);
            final String _tmpLastname;
            _tmpLastname = _cursor.getString(_cursorIndexOfLastname);
            final String _tmpFirstname;
            _tmpFirstname = _cursor.getString(_cursorIndexOfFirstname);
            final String _tmpBirthDate;
            _tmpBirthDate = _cursor.getString(_cursorIndexOfBirthDate);
            final String _tmpSex;
            _tmpSex = _cursor.getString(_cursorIndexOfSex);
            _item = new Patient(_tmpCaseNumber,_tmpLastname,_tmpFirstname,_tmpBirthDate,_tmpSex);
            final int _tmpPatientId;
            _tmpPatientId = _cursor.getInt(_cursorIndexOfPatientId);
            _item.setPatientId(_tmpPatientId);
            final String _tmpDateTime;
            _tmpDateTime = _cursor.getString(_cursorIndexOfDateTime);
            _item.setDateTime(_tmpDateTime);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<Patient> getById(final int id) {
    final String _sql = "SELECT * FROM patient_table WHERE patientId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return __db.getInvalidationTracker().createLiveData(new String[]{"patient_table"}, false, new Callable<Patient>() {
      @Override
      public Patient call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPatientId = CursorUtil.getColumnIndexOrThrow(_cursor, "patientId");
          final int _cursorIndexOfCaseNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "caseNumber");
          final int _cursorIndexOfLastname = CursorUtil.getColumnIndexOrThrow(_cursor, "lastname");
          final int _cursorIndexOfFirstname = CursorUtil.getColumnIndexOrThrow(_cursor, "firstname");
          final int _cursorIndexOfBirthDate = CursorUtil.getColumnIndexOrThrow(_cursor, "birthDate");
          final int _cursorIndexOfDateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "dateTime");
          final int _cursorIndexOfSex = CursorUtil.getColumnIndexOrThrow(_cursor, "sex");
          final Patient _result;
          if(_cursor.moveToFirst()) {
            final long _tmpCaseNumber;
            _tmpCaseNumber = _cursor.getLong(_cursorIndexOfCaseNumber);
            final String _tmpLastname;
            _tmpLastname = _cursor.getString(_cursorIndexOfLastname);
            final String _tmpFirstname;
            _tmpFirstname = _cursor.getString(_cursorIndexOfFirstname);
            final String _tmpBirthDate;
            _tmpBirthDate = _cursor.getString(_cursorIndexOfBirthDate);
            final String _tmpSex;
            _tmpSex = _cursor.getString(_cursorIndexOfSex);
            _result = new Patient(_tmpCaseNumber,_tmpLastname,_tmpFirstname,_tmpBirthDate,_tmpSex);
            final int _tmpPatientId;
            _tmpPatientId = _cursor.getInt(_cursorIndexOfPatientId);
            _result.setPatientId(_tmpPatientId);
            final String _tmpDateTime;
            _tmpDateTime = _cursor.getString(_cursorIndexOfDateTime);
            _result.setDateTime(_tmpDateTime);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
