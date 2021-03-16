package com.app.craniowake.data.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.app.craniowake.data.model.Operation;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class OperationDao_Impl extends OperationDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Operation> __insertionAdapterOfOperation;

  public OperationDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfOperation = new EntityInsertionAdapter<Operation>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `operation_table` (`operationId`,`brainarea`,`operationMode`,`dateTime`,`fkPatientId`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Operation value) {
        stmt.bindLong(1, value.getOperationId());
        if (value.getBrainarea() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getBrainarea());
        }
        if (value.getOperationMode() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getOperationMode());
        }
        if (value.getDateTime() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDateTime());
        }
        stmt.bindLong(5, value.getFkPatientId());
      }
    };
  }

  @Override
  public long addOperation(final Operation operation) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfOperation.insertAndReturnId(operation);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<Operation> getOperationByDate(final String dateTime) {
    final String _sql = "SELECT * FROM operation_table WHERE dateTime = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (dateTime == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, dateTime);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"operation_table"}, false, new Callable<Operation>() {
      @Override
      public Operation call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfOperationId = CursorUtil.getColumnIndexOrThrow(_cursor, "operationId");
          final int _cursorIndexOfBrainarea = CursorUtil.getColumnIndexOrThrow(_cursor, "brainarea");
          final int _cursorIndexOfOperationMode = CursorUtil.getColumnIndexOrThrow(_cursor, "operationMode");
          final int _cursorIndexOfDateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "dateTime");
          final int _cursorIndexOfFkPatientId = CursorUtil.getColumnIndexOrThrow(_cursor, "fkPatientId");
          final Operation _result;
          if(_cursor.moveToFirst()) {
            final String _tmpBrainarea;
            _tmpBrainarea = _cursor.getString(_cursorIndexOfBrainarea);
            final String _tmpOperationMode;
            _tmpOperationMode = _cursor.getString(_cursorIndexOfOperationMode);
            final int _tmpFkPatientId;
            _tmpFkPatientId = _cursor.getInt(_cursorIndexOfFkPatientId);
            _result = new Operation(_tmpBrainarea,_tmpOperationMode,_tmpFkPatientId);
            final int _tmpOperationId;
            _tmpOperationId = _cursor.getInt(_cursorIndexOfOperationId);
            _result.setOperationId(_tmpOperationId);
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
