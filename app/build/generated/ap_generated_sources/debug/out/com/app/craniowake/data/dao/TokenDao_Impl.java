package com.app.craniowake.data.dao;

import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.app.craniowake.data.model.gameModels.TokenGame;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TokenDao_Impl extends TokenDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TokenGame> __insertionAdapterOfTokenGame;

  public TokenDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTokenGame = new EntityInsertionAdapter<TokenGame>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `token_game_table` (`tokenId`,`token`,`answer`,`dateTime`,`fkOperationId`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TokenGame value) {
        stmt.bindLong(1, value.getTokenId());
        if (value.getToken() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getToken());
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
  public void addTokenGame(final TokenGame tokenGame) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTokenGame.insert(tokenGame);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }
}
