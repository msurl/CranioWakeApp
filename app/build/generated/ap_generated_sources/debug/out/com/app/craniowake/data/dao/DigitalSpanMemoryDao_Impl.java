package com.app.craniowake.data.dao;

import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.app.craniowake.data.model.gameModels.DigitalSpanMemoryGame;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

@SuppressWarnings({"unchecked", "deprecation"})
public final class DigitalSpanMemoryDao_Impl extends DigitalSpanMemoryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DigitalSpanMemoryGame> __insertionAdapterOfDigitalSpanMemoryGame;

  public DigitalSpanMemoryDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDigitalSpanMemoryGame = new EntityInsertionAdapter<DigitalSpanMemoryGame>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `digital_span_game_table` (`digitalSpanMemoryId`,`answer`,`dateTime`,`fkOperationId`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DigitalSpanMemoryGame value) {
        stmt.bindLong(1, value.getDigitalSpanMemoryId());
        final int _tmp;
        _tmp = value.isAnswer() ? 1 : 0;
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
  public void addDigitalSpanMemoryGame(final DigitalSpanMemoryGame digitalSpanMemoryGame) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfDigitalSpanMemoryGame.insert(digitalSpanMemoryGame);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }
}
