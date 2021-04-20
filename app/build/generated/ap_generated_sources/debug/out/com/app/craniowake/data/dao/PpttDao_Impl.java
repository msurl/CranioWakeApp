package com.app.craniowake.data.dao;

import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.app.craniowake.data.model.gameModels.PpttGame;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

@SuppressWarnings({"unchecked", "deprecation"})
public final class PpttDao_Impl extends PpttDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PpttGame> __insertionAdapterOfPpttGame;

  public PpttDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPpttGame = new EntityInsertionAdapter<PpttGame>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `pptt_test_table` (`ppttId`,`answer`,`pictureName`,`dateTime`,`fkOperationId`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PpttGame value) {
        stmt.bindLong(1, value.getPpttId());
        final int _tmp;
        _tmp = value.isAnswer() ? 1 : 0;
        stmt.bindLong(2, _tmp);
        if (value.getPictureName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getPictureName());
        }
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
  public void addPpttGame(final PpttGame ppttGame) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfPpttGame.insert(ppttGame);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }
}
