package com.app.craniowake.data.dao;

import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.app.craniowake.data.model.gameModels.ReactionGame;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ReactionDao_Impl extends ReactionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ReactionGame> __insertionAdapterOfReactionGame;

  public ReactionDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfReactionGame = new EntityInsertionAdapter<ReactionGame>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `reaction_game_table` (`reactionId`,`milisec`,`dateTime`,`fkOperationId`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ReactionGame value) {
        stmt.bindLong(1, value.getReactionId());
        stmt.bindLong(2, value.getMilisec());
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
  public void addReactionGame(final ReactionGame reactionGame) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfReactionGame.insert(reactionGame);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }
}
