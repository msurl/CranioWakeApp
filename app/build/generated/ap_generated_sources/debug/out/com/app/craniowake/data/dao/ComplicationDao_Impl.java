package com.app.craniowake.data.dao;

import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.app.craniowake.data.model.Complication;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ComplicationDao_Impl extends ComplicationDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Complication> __insertionAdapterOfComplication;

  public ComplicationDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfComplication = new EntityInsertionAdapter<Complication>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `complication_table` (`complicationId`,`dateTime`,`fkOperationId`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Complication value) {
        stmt.bindLong(1, value.getComplicationId());
        if (value.getDateTime() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getDateTime());
        }
        stmt.bindLong(3, value.getFkOperationId());
      }
    };
  }

  @Override
  public void addComplication(final Complication complication) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfComplication.insert(complication);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }
}
