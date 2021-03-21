package com.app.craniowake.data.dao;

import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.app.craniowake.data.model.gameModels.LineBisectionGame;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

@SuppressWarnings({"unchecked", "deprecation"})
public final class LineBisectionDao_Impl extends LineBisectionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<LineBisectionGame> __insertionAdapterOfLineBisectionGame;

  public LineBisectionDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLineBisectionGame = new EntityInsertionAdapter<LineBisectionGame>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `line_dissection_table` (`lineDissectionId`,`distance_in_mm`,`milisec`,`dateTime`,`fkOperationId`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, LineBisectionGame value) {
        stmt.bindLong(1, value.getLineDissectionId());
        stmt.bindDouble(2, value.getDistanceToMiddleOfTheLine());
        stmt.bindLong(3, value.getMilisec());
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
  public void addLineDissectionGame(final LineBisectionGame lineBisectionGame) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfLineBisectionGame.insert(lineBisectionGame);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }
}
