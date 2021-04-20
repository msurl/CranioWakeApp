package com.app.craniowake.data.dao;

import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.app.craniowake.data.model.gameModels.CalculusGame;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

@SuppressWarnings({"unchecked", "deprecation"})
public final class CalculatingDao_Impl extends CalculatingDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CalculusGame> __insertionAdapterOfCalculusGame;

  public CalculatingDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCalculusGame = new EntityInsertionAdapter<CalculusGame>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `calculus_test_table` (`calcGameId`,`equation`,`answer`,`dateTime`,`fkOperationId`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CalculusGame value) {
        stmt.bindLong(1, value.getCalcGameId());
        if (value.getEquation() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getEquation());
        }
        final int _tmp;
        _tmp = value.isAnswer() ? 1 : 0;
        stmt.bindLong(3, _tmp);
        if (value.getDateTime() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDateTime());
        }
        stmt.bindLong(5, value.getFkOperationId());
      }
    };
  }

  @Override
  public void addCalculatingGame(final CalculusGame calculusGame) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCalculusGame.insert(calculusGame);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }
}
