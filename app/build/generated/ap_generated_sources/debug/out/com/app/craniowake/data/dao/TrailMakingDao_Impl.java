package com.app.craniowake.data.dao;

import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.app.craniowake.data.model.gameModels.TrailMakingGame;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TrailMakingDao_Impl extends TrailMakingDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TrailMakingGame> __insertionAdapterOfTrailMakingGame;

  public TrailMakingDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTrailMakingGame = new EntityInsertionAdapter<TrailMakingGame>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `trailway_game_table` (`trailwayId`,`correctAnswer`,`dateTime`,`fkOperationId`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TrailMakingGame value) {
        stmt.bindLong(1, value.getTrailwayId());
        final int _tmp;
        _tmp = value.isCorrectAnswer() ? 1 : 0;
        stmt.bindLong(2, _tmp);
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
  public void addTrailwayGame(final TrailMakingGame trailMakingGame) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTrailMakingGame.insert(trailMakingGame);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }
}
