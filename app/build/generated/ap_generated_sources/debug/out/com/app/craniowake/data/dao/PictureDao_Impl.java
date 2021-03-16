package com.app.craniowake.data.dao;

import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.app.craniowake.data.model.gameModels.PictureGame;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

@SuppressWarnings({"unchecked", "deprecation"})
public final class PictureDao_Impl extends PictureDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PictureGame> __insertionAdapterOfPictureGame;

  public PictureDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPictureGame = new EntityInsertionAdapter<PictureGame>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `picture_game_table` (`pictureId`,`pictureName`,`answer`,`dateTime`,`fkOperationId`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PictureGame value) {
        stmt.bindLong(1, value.getPictureId());
        if (value.getPictureName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getPictureName());
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
  public void addPictureGame(final PictureGame pictureGame) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfPictureGame.insert(pictureGame);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }
}
