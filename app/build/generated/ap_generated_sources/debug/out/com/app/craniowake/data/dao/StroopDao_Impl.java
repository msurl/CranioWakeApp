package com.app.craniowake.data.dao;

import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.app.craniowake.data.model.gameModels.StroopGame;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

@SuppressWarnings({"unchecked", "deprecation"})
public final class StroopDao_Impl extends StroopDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<StroopGame> __insertionAdapterOfStroopGame;

  public StroopDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfStroopGame = new EntityInsertionAdapter<StroopGame>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `stroop_game_table` (`stroopId`,`ink`,`answer`,`dateTime`,`fkOperationId`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, StroopGame value) {
        stmt.bindLong(1, value.getStroopId());
        if (value.getInk() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getInk());
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
  public void addStroopGame(final StroopGame stroopGame) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfStroopGame.insert(stroopGame);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }
}
