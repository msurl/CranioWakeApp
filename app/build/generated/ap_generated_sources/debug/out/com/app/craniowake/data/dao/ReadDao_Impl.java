package com.app.craniowake.data.dao;

import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.app.craniowake.data.model.gameModels.ReadGame;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ReadDao_Impl extends ReadDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ReadGame> __insertionAdapterOfReadGame;

  public ReadDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfReadGame = new EntityInsertionAdapter<ReadGame>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `read_test_table` (`readId`,`mistakeCounter`,`dateTime`,`fkOperationId`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ReadGame value) {
        stmt.bindLong(1, value.getReadId());
        stmt.bindLong(2, value.getMistakeCounter());
        if (value.getDateTime() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDateTime());
        }
        stmt.bindLong(4, value.getFkOperationId());
      }
    };
  }

  @Override
  public void addReadGame(final ReadGame readGame) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfReadGame.insert(readGame);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }
}
