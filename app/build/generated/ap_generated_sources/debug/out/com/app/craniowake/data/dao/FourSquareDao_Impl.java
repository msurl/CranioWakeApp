package com.app.craniowake.data.dao;

import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.app.craniowake.data.model.gameModels.FourSquareGame;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

@SuppressWarnings({"unchecked", "deprecation"})
public final class FourSquareDao_Impl extends FourSquareDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<FourSquareGame> __insertionAdapterOfFourSquareGame;

  public FourSquareDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFourSquareGame = new EntityInsertionAdapter<FourSquareGame>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `four_square_test_table` (`FourSquareId`,`pictureName`,`answerSquare1`,`answerSquare2`,`answerSquare3`,`answerSquare4`,`dateTime`,`fkOperationId`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FourSquareGame value) {
        stmt.bindLong(1, value.getFourSquareId());
        if (value.getPictureName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getPictureName());
        }
        final int _tmp;
        _tmp = value.isAnswerSquare1() ? 1 : 0;
        stmt.bindLong(3, _tmp);
        final int _tmp_1;
        _tmp_1 = value.isAnswerSquare2() ? 1 : 0;
        stmt.bindLong(4, _tmp_1);
        final int _tmp_2;
        _tmp_2 = value.isAnswerSquare3() ? 1 : 0;
        stmt.bindLong(5, _tmp_2);
        final int _tmp_3;
        _tmp_3 = value.isAnswerSquare4() ? 1 : 0;
        stmt.bindLong(6, _tmp_3);
        if (value.getDateTime() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getDateTime());
        }
        stmt.bindLong(8, value.getFkOperationId());
      }
    };
  }

  @Override
  public void addFourSquareGame(final FourSquareGame fourSquareGame) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfFourSquareGame.insert(fourSquareGame);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }
}
