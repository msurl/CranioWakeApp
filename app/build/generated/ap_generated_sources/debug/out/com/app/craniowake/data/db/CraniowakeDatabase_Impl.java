package com.app.craniowake.data.db;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.app.craniowake.data.dao.CalculatingDao;
import com.app.craniowake.data.dao.CalculatingDao_Impl;
import com.app.craniowake.data.dao.ComplicationDao;
import com.app.craniowake.data.dao.ComplicationDao_Impl;
import com.app.craniowake.data.dao.DigitalSpanMemoryDao;
import com.app.craniowake.data.dao.DigitalSpanMemoryDao_Impl;
import com.app.craniowake.data.dao.FourSquareDao;
import com.app.craniowake.data.dao.FourSquareDao_Impl;
import com.app.craniowake.data.dao.LineBisectionDao;
import com.app.craniowake.data.dao.LineBisectionDao_Impl;
import com.app.craniowake.data.dao.OperationDao;
import com.app.craniowake.data.dao.OperationDao_Impl;
import com.app.craniowake.data.dao.PatientDao;
import com.app.craniowake.data.dao.PatientDao_Impl;
import com.app.craniowake.data.dao.PictureDao;
import com.app.craniowake.data.dao.PictureDao_Impl;
import com.app.craniowake.data.dao.PpttDao;
import com.app.craniowake.data.dao.PpttDao_Impl;
import com.app.craniowake.data.dao.ReactionDao;
import com.app.craniowake.data.dao.ReactionDao_Impl;
import com.app.craniowake.data.dao.ReadDao;
import com.app.craniowake.data.dao.ReadDao_Impl;
import com.app.craniowake.data.dao.StroopDao;
import com.app.craniowake.data.dao.StroopDao_Impl;
import com.app.craniowake.data.dao.TokenDao;
import com.app.craniowake.data.dao.TokenDao_Impl;
import com.app.craniowake.data.dao.TrailMakingDao;
import com.app.craniowake.data.dao.TrailMakingDao_Impl;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class CraniowakeDatabase_Impl extends CraniowakeDatabase {
  private volatile PatientDao _patientDao;

  private volatile OperationDao _operationDao;

  private volatile CalculatingDao _calculatingDao;

  private volatile DigitalSpanMemoryDao _digitalSpanMemoryDao;

  private volatile FourSquareDao _fourSquareDao;

  private volatile LineBisectionDao _lineBisectionDao;

  private volatile PictureDao _pictureDao;

  private volatile PpttDao _ppttDao;

  private volatile ReactionDao _reactionDao;

  private volatile ReadDao _readDao;

  private volatile StroopDao _stroopDao;

  private volatile TokenDao _tokenDao;

  private volatile TrailMakingDao _trailMakingDao;

  private volatile ComplicationDao _complicationDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `patient_table` (`patientId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `caseNumber` INTEGER NOT NULL, `lastname` TEXT, `firstname` TEXT, `birthDate` TEXT, `dateTime` TEXT, `sex` TEXT)");
        _db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_patient_table_caseNumber` ON `patient_table` (`caseNumber`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `operation_table` (`operationId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `brainarea` TEXT, `operationMode` TEXT, `dateTime` TEXT, `fkPatientId` INTEGER NOT NULL)");
        _db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_operation_table_dateTime` ON `operation_table` (`dateTime`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `calculcationGame_table` (`calcGameId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `equation` TEXT, `answer` INTEGER NOT NULL, `dateTime` TEXT, `fkOperationId` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `digital_span_game_table` (`digitalSpanMemoryId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `answer` INTEGER NOT NULL, `dateTime` TEXT, `fkOperationId` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `four_square_table` (`FourSquareId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `pictureName` TEXT, `answerSquare1` INTEGER NOT NULL, `answerSquare2` INTEGER NOT NULL, `answerSquare3` INTEGER NOT NULL, `answerSquare4` INTEGER NOT NULL, `dateTime` TEXT, `fkOperationId` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `line_dissection_table` (`lineDissectionId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `distance_in_mm` REAL NOT NULL, `milisec` INTEGER NOT NULL, `dateTime` TEXT, `fkOperationId` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `picture_game_table` (`pictureId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `pictureName` TEXT, `answer` INTEGER NOT NULL, `dateTime` TEXT, `fkOperationId` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `pptt_game_table` (`ppttId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `answer` INTEGER NOT NULL, `pictureName` TEXT, `dateTime` TEXT, `fkOperationId` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `reaction_game_table` (`reactionId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `milisec` INTEGER NOT NULL, `dateTime` TEXT, `fkOperationId` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `read_game_table` (`readId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `mistakeCounter` INTEGER NOT NULL, `dateTime` TEXT, `fkOperationId` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `stroop_game_table` (`stroopId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `ink` TEXT, `answer` INTEGER NOT NULL, `dateTime` TEXT, `fkOperationId` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `token_game_table` (`tokenId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `token` TEXT, `answer` INTEGER NOT NULL, `dateTime` TEXT, `fkOperationId` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `trailway_game_table` (`trailwayId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `correctAnswer` INTEGER NOT NULL, `dateTime` TEXT, `fkOperationId` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `complication_table` (`complicationId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `dateTime` TEXT, `fkOperationId` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'eddddb7781d6d88dfb29b9e9bb4e0a93')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `patient_table`");
        _db.execSQL("DROP TABLE IF EXISTS `operation_table`");
        _db.execSQL("DROP TABLE IF EXISTS `calculcationGame_table`");
        _db.execSQL("DROP TABLE IF EXISTS `digital_span_game_table`");
        _db.execSQL("DROP TABLE IF EXISTS `four_square_table`");
        _db.execSQL("DROP TABLE IF EXISTS `line_dissection_table`");
        _db.execSQL("DROP TABLE IF EXISTS `picture_game_table`");
        _db.execSQL("DROP TABLE IF EXISTS `pptt_game_table`");
        _db.execSQL("DROP TABLE IF EXISTS `reaction_game_table`");
        _db.execSQL("DROP TABLE IF EXISTS `read_game_table`");
        _db.execSQL("DROP TABLE IF EXISTS `stroop_game_table`");
        _db.execSQL("DROP TABLE IF EXISTS `token_game_table`");
        _db.execSQL("DROP TABLE IF EXISTS `trailway_game_table`");
        _db.execSQL("DROP TABLE IF EXISTS `complication_table`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsPatientTable = new HashMap<String, TableInfo.Column>(7);
        _columnsPatientTable.put("patientId", new TableInfo.Column("patientId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatientTable.put("caseNumber", new TableInfo.Column("caseNumber", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatientTable.put("lastname", new TableInfo.Column("lastname", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatientTable.put("firstname", new TableInfo.Column("firstname", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatientTable.put("birthDate", new TableInfo.Column("birthDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatientTable.put("dateTime", new TableInfo.Column("dateTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatientTable.put("sex", new TableInfo.Column("sex", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPatientTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPatientTable = new HashSet<TableInfo.Index>(1);
        _indicesPatientTable.add(new TableInfo.Index("index_patient_table_caseNumber", true, Arrays.asList("caseNumber")));
        final TableInfo _infoPatientTable = new TableInfo("patient_table", _columnsPatientTable, _foreignKeysPatientTable, _indicesPatientTable);
        final TableInfo _existingPatientTable = TableInfo.read(_db, "patient_table");
        if (! _infoPatientTable.equals(_existingPatientTable)) {
          return new RoomOpenHelper.ValidationResult(false, "patient_table(com.app.craniowake.data.model.Patient).\n"
                  + " Expected:\n" + _infoPatientTable + "\n"
                  + " Found:\n" + _existingPatientTable);
        }
        final HashMap<String, TableInfo.Column> _columnsOperationTable = new HashMap<String, TableInfo.Column>(5);
        _columnsOperationTable.put("operationId", new TableInfo.Column("operationId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOperationTable.put("brainarea", new TableInfo.Column("brainarea", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOperationTable.put("operationMode", new TableInfo.Column("operationMode", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOperationTable.put("dateTime", new TableInfo.Column("dateTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOperationTable.put("fkPatientId", new TableInfo.Column("fkPatientId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysOperationTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesOperationTable = new HashSet<TableInfo.Index>(1);
        _indicesOperationTable.add(new TableInfo.Index("index_operation_table_dateTime", true, Arrays.asList("dateTime")));
        final TableInfo _infoOperationTable = new TableInfo("operation_table", _columnsOperationTable, _foreignKeysOperationTable, _indicesOperationTable);
        final TableInfo _existingOperationTable = TableInfo.read(_db, "operation_table");
        if (! _infoOperationTable.equals(_existingOperationTable)) {
          return new RoomOpenHelper.ValidationResult(false, "operation_table(com.app.craniowake.data.model.Operation).\n"
                  + " Expected:\n" + _infoOperationTable + "\n"
                  + " Found:\n" + _existingOperationTable);
        }
        final HashMap<String, TableInfo.Column> _columnsCalculcationGameTable = new HashMap<String, TableInfo.Column>(5);
        _columnsCalculcationGameTable.put("calcGameId", new TableInfo.Column("calcGameId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCalculcationGameTable.put("equation", new TableInfo.Column("equation", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCalculcationGameTable.put("answer", new TableInfo.Column("answer", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCalculcationGameTable.put("dateTime", new TableInfo.Column("dateTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCalculcationGameTable.put("fkOperationId", new TableInfo.Column("fkOperationId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCalculcationGameTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCalculcationGameTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCalculcationGameTable = new TableInfo("calculcationGame_table", _columnsCalculcationGameTable, _foreignKeysCalculcationGameTable, _indicesCalculcationGameTable);
        final TableInfo _existingCalculcationGameTable = TableInfo.read(_db, "calculcationGame_table");
        if (! _infoCalculcationGameTable.equals(_existingCalculcationGameTable)) {
          return new RoomOpenHelper.ValidationResult(false, "calculcationGame_table(com.app.craniowake.data.model.gameModels.CalculusGame).\n"
                  + " Expected:\n" + _infoCalculcationGameTable + "\n"
                  + " Found:\n" + _existingCalculcationGameTable);
        }
        final HashMap<String, TableInfo.Column> _columnsDigitalSpanGameTable = new HashMap<String, TableInfo.Column>(4);
        _columnsDigitalSpanGameTable.put("digitalSpanMemoryId", new TableInfo.Column("digitalSpanMemoryId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDigitalSpanGameTable.put("answer", new TableInfo.Column("answer", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDigitalSpanGameTable.put("dateTime", new TableInfo.Column("dateTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDigitalSpanGameTable.put("fkOperationId", new TableInfo.Column("fkOperationId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDigitalSpanGameTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDigitalSpanGameTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDigitalSpanGameTable = new TableInfo("digital_span_game_table", _columnsDigitalSpanGameTable, _foreignKeysDigitalSpanGameTable, _indicesDigitalSpanGameTable);
        final TableInfo _existingDigitalSpanGameTable = TableInfo.read(_db, "digital_span_game_table");
        if (! _infoDigitalSpanGameTable.equals(_existingDigitalSpanGameTable)) {
          return new RoomOpenHelper.ValidationResult(false, "digital_span_game_table(com.app.craniowake.data.model.gameModels.DigitalSpanMemoryGame).\n"
                  + " Expected:\n" + _infoDigitalSpanGameTable + "\n"
                  + " Found:\n" + _existingDigitalSpanGameTable);
        }
        final HashMap<String, TableInfo.Column> _columnsFourSquareTable = new HashMap<String, TableInfo.Column>(8);
        _columnsFourSquareTable.put("FourSquareId", new TableInfo.Column("FourSquareId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFourSquareTable.put("pictureName", new TableInfo.Column("pictureName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFourSquareTable.put("answerSquare1", new TableInfo.Column("answerSquare1", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFourSquareTable.put("answerSquare2", new TableInfo.Column("answerSquare2", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFourSquareTable.put("answerSquare3", new TableInfo.Column("answerSquare3", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFourSquareTable.put("answerSquare4", new TableInfo.Column("answerSquare4", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFourSquareTable.put("dateTime", new TableInfo.Column("dateTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFourSquareTable.put("fkOperationId", new TableInfo.Column("fkOperationId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFourSquareTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFourSquareTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFourSquareTable = new TableInfo("four_square_table", _columnsFourSquareTable, _foreignKeysFourSquareTable, _indicesFourSquareTable);
        final TableInfo _existingFourSquareTable = TableInfo.read(_db, "four_square_table");
        if (! _infoFourSquareTable.equals(_existingFourSquareTable)) {
          return new RoomOpenHelper.ValidationResult(false, "four_square_table(com.app.craniowake.data.model.gameModels.FourSquareGame).\n"
                  + " Expected:\n" + _infoFourSquareTable + "\n"
                  + " Found:\n" + _existingFourSquareTable);
        }
        final HashMap<String, TableInfo.Column> _columnsLineDissectionTable = new HashMap<String, TableInfo.Column>(5);
        _columnsLineDissectionTable.put("lineDissectionId", new TableInfo.Column("lineDissectionId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLineDissectionTable.put("distance_in_mm", new TableInfo.Column("distance_in_mm", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLineDissectionTable.put("milisec", new TableInfo.Column("milisec", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLineDissectionTable.put("dateTime", new TableInfo.Column("dateTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLineDissectionTable.put("fkOperationId", new TableInfo.Column("fkOperationId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLineDissectionTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLineDissectionTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLineDissectionTable = new TableInfo("line_dissection_table", _columnsLineDissectionTable, _foreignKeysLineDissectionTable, _indicesLineDissectionTable);
        final TableInfo _existingLineDissectionTable = TableInfo.read(_db, "line_dissection_table");
        if (! _infoLineDissectionTable.equals(_existingLineDissectionTable)) {
          return new RoomOpenHelper.ValidationResult(false, "line_dissection_table(com.app.craniowake.data.model.gameModels.LineBisectionGame).\n"
                  + " Expected:\n" + _infoLineDissectionTable + "\n"
                  + " Found:\n" + _existingLineDissectionTable);
        }
        final HashMap<String, TableInfo.Column> _columnsPictureGameTable = new HashMap<String, TableInfo.Column>(5);
        _columnsPictureGameTable.put("pictureId", new TableInfo.Column("pictureId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPictureGameTable.put("pictureName", new TableInfo.Column("pictureName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPictureGameTable.put("answer", new TableInfo.Column("answer", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPictureGameTable.put("dateTime", new TableInfo.Column("dateTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPictureGameTable.put("fkOperationId", new TableInfo.Column("fkOperationId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPictureGameTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPictureGameTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPictureGameTable = new TableInfo("picture_game_table", _columnsPictureGameTable, _foreignKeysPictureGameTable, _indicesPictureGameTable);
        final TableInfo _existingPictureGameTable = TableInfo.read(_db, "picture_game_table");
        if (! _infoPictureGameTable.equals(_existingPictureGameTable)) {
          return new RoomOpenHelper.ValidationResult(false, "picture_game_table(com.app.craniowake.data.model.gameModels.PictureGame).\n"
                  + " Expected:\n" + _infoPictureGameTable + "\n"
                  + " Found:\n" + _existingPictureGameTable);
        }
        final HashMap<String, TableInfo.Column> _columnsPpttGameTable = new HashMap<String, TableInfo.Column>(5);
        _columnsPpttGameTable.put("ppttId", new TableInfo.Column("ppttId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPpttGameTable.put("answer", new TableInfo.Column("answer", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPpttGameTable.put("pictureName", new TableInfo.Column("pictureName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPpttGameTable.put("dateTime", new TableInfo.Column("dateTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPpttGameTable.put("fkOperationId", new TableInfo.Column("fkOperationId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPpttGameTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPpttGameTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPpttGameTable = new TableInfo("pptt_game_table", _columnsPpttGameTable, _foreignKeysPpttGameTable, _indicesPpttGameTable);
        final TableInfo _existingPpttGameTable = TableInfo.read(_db, "pptt_game_table");
        if (! _infoPpttGameTable.equals(_existingPpttGameTable)) {
          return new RoomOpenHelper.ValidationResult(false, "pptt_game_table(com.app.craniowake.data.model.gameModels.PpttGame).\n"
                  + " Expected:\n" + _infoPpttGameTable + "\n"
                  + " Found:\n" + _existingPpttGameTable);
        }
        final HashMap<String, TableInfo.Column> _columnsReactionGameTable = new HashMap<String, TableInfo.Column>(4);
        _columnsReactionGameTable.put("reactionId", new TableInfo.Column("reactionId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReactionGameTable.put("milisec", new TableInfo.Column("milisec", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReactionGameTable.put("dateTime", new TableInfo.Column("dateTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReactionGameTable.put("fkOperationId", new TableInfo.Column("fkOperationId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysReactionGameTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesReactionGameTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoReactionGameTable = new TableInfo("reaction_game_table", _columnsReactionGameTable, _foreignKeysReactionGameTable, _indicesReactionGameTable);
        final TableInfo _existingReactionGameTable = TableInfo.read(_db, "reaction_game_table");
        if (! _infoReactionGameTable.equals(_existingReactionGameTable)) {
          return new RoomOpenHelper.ValidationResult(false, "reaction_game_table(com.app.craniowake.data.model.gameModels.ReactionGame).\n"
                  + " Expected:\n" + _infoReactionGameTable + "\n"
                  + " Found:\n" + _existingReactionGameTable);
        }
        final HashMap<String, TableInfo.Column> _columnsReadGameTable = new HashMap<String, TableInfo.Column>(4);
        _columnsReadGameTable.put("readId", new TableInfo.Column("readId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReadGameTable.put("mistakeCounter", new TableInfo.Column("mistakeCounter", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReadGameTable.put("dateTime", new TableInfo.Column("dateTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReadGameTable.put("fkOperationId", new TableInfo.Column("fkOperationId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysReadGameTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesReadGameTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoReadGameTable = new TableInfo("read_game_table", _columnsReadGameTable, _foreignKeysReadGameTable, _indicesReadGameTable);
        final TableInfo _existingReadGameTable = TableInfo.read(_db, "read_game_table");
        if (! _infoReadGameTable.equals(_existingReadGameTable)) {
          return new RoomOpenHelper.ValidationResult(false, "read_game_table(com.app.craniowake.data.model.gameModels.ReadGame).\n"
                  + " Expected:\n" + _infoReadGameTable + "\n"
                  + " Found:\n" + _existingReadGameTable);
        }
        final HashMap<String, TableInfo.Column> _columnsStroopGameTable = new HashMap<String, TableInfo.Column>(5);
        _columnsStroopGameTable.put("stroopId", new TableInfo.Column("stroopId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStroopGameTable.put("ink", new TableInfo.Column("ink", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStroopGameTable.put("answer", new TableInfo.Column("answer", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStroopGameTable.put("dateTime", new TableInfo.Column("dateTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStroopGameTable.put("fkOperationId", new TableInfo.Column("fkOperationId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysStroopGameTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesStroopGameTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoStroopGameTable = new TableInfo("stroop_game_table", _columnsStroopGameTable, _foreignKeysStroopGameTable, _indicesStroopGameTable);
        final TableInfo _existingStroopGameTable = TableInfo.read(_db, "stroop_game_table");
        if (! _infoStroopGameTable.equals(_existingStroopGameTable)) {
          return new RoomOpenHelper.ValidationResult(false, "stroop_game_table(com.app.craniowake.data.model.gameModels.StroopGame).\n"
                  + " Expected:\n" + _infoStroopGameTable + "\n"
                  + " Found:\n" + _existingStroopGameTable);
        }
        final HashMap<String, TableInfo.Column> _columnsTokenGameTable = new HashMap<String, TableInfo.Column>(5);
        _columnsTokenGameTable.put("tokenId", new TableInfo.Column("tokenId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTokenGameTable.put("token", new TableInfo.Column("token", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTokenGameTable.put("answer", new TableInfo.Column("answer", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTokenGameTable.put("dateTime", new TableInfo.Column("dateTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTokenGameTable.put("fkOperationId", new TableInfo.Column("fkOperationId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTokenGameTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTokenGameTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTokenGameTable = new TableInfo("token_game_table", _columnsTokenGameTable, _foreignKeysTokenGameTable, _indicesTokenGameTable);
        final TableInfo _existingTokenGameTable = TableInfo.read(_db, "token_game_table");
        if (! _infoTokenGameTable.equals(_existingTokenGameTable)) {
          return new RoomOpenHelper.ValidationResult(false, "token_game_table(com.app.craniowake.data.model.gameModels.TokenGame).\n"
                  + " Expected:\n" + _infoTokenGameTable + "\n"
                  + " Found:\n" + _existingTokenGameTable);
        }
        final HashMap<String, TableInfo.Column> _columnsTrailwayGameTable = new HashMap<String, TableInfo.Column>(4);
        _columnsTrailwayGameTable.put("trailwayId", new TableInfo.Column("trailwayId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrailwayGameTable.put("correctAnswer", new TableInfo.Column("correctAnswer", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrailwayGameTable.put("dateTime", new TableInfo.Column("dateTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrailwayGameTable.put("fkOperationId", new TableInfo.Column("fkOperationId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTrailwayGameTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTrailwayGameTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTrailwayGameTable = new TableInfo("trailway_game_table", _columnsTrailwayGameTable, _foreignKeysTrailwayGameTable, _indicesTrailwayGameTable);
        final TableInfo _existingTrailwayGameTable = TableInfo.read(_db, "trailway_game_table");
        if (! _infoTrailwayGameTable.equals(_existingTrailwayGameTable)) {
          return new RoomOpenHelper.ValidationResult(false, "trailway_game_table(com.app.craniowake.data.model.gameModels.TrailMakingGame).\n"
                  + " Expected:\n" + _infoTrailwayGameTable + "\n"
                  + " Found:\n" + _existingTrailwayGameTable);
        }
        final HashMap<String, TableInfo.Column> _columnsComplicationTable = new HashMap<String, TableInfo.Column>(3);
        _columnsComplicationTable.put("complicationId", new TableInfo.Column("complicationId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComplicationTable.put("dateTime", new TableInfo.Column("dateTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComplicationTable.put("fkOperationId", new TableInfo.Column("fkOperationId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysComplicationTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesComplicationTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoComplicationTable = new TableInfo("complication_table", _columnsComplicationTable, _foreignKeysComplicationTable, _indicesComplicationTable);
        final TableInfo _existingComplicationTable = TableInfo.read(_db, "complication_table");
        if (! _infoComplicationTable.equals(_existingComplicationTable)) {
          return new RoomOpenHelper.ValidationResult(false, "complication_table(com.app.craniowake.data.model.Complication).\n"
                  + " Expected:\n" + _infoComplicationTable + "\n"
                  + " Found:\n" + _existingComplicationTable);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "eddddb7781d6d88dfb29b9e9bb4e0a93", "6972c0fbb1abd927bffc3ad658f7c099");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "patient_table","operation_table","calculcationGame_table","digital_span_game_table","four_square_table","line_dissection_table","picture_game_table","pptt_game_table","reaction_game_table","read_game_table","stroop_game_table","token_game_table","trailway_game_table","complication_table");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `patient_table`");
      _db.execSQL("DELETE FROM `operation_table`");
      _db.execSQL("DELETE FROM `calculcationGame_table`");
      _db.execSQL("DELETE FROM `digital_span_game_table`");
      _db.execSQL("DELETE FROM `four_square_table`");
      _db.execSQL("DELETE FROM `line_dissection_table`");
      _db.execSQL("DELETE FROM `picture_game_table`");
      _db.execSQL("DELETE FROM `pptt_game_table`");
      _db.execSQL("DELETE FROM `reaction_game_table`");
      _db.execSQL("DELETE FROM `read_game_table`");
      _db.execSQL("DELETE FROM `stroop_game_table`");
      _db.execSQL("DELETE FROM `token_game_table`");
      _db.execSQL("DELETE FROM `trailway_game_table`");
      _db.execSQL("DELETE FROM `complication_table`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public PatientDao patientDao() {
    if (_patientDao != null) {
      return _patientDao;
    } else {
      synchronized(this) {
        if(_patientDao == null) {
          _patientDao = new PatientDao_Impl(this);
        }
        return _patientDao;
      }
    }
  }

  @Override
  public OperationDao operationDao() {
    if (_operationDao != null) {
      return _operationDao;
    } else {
      synchronized(this) {
        if(_operationDao == null) {
          _operationDao = new OperationDao_Impl(this);
        }
        return _operationDao;
      }
    }
  }

  @Override
  public CalculatingDao calculatingDao() {
    if (_calculatingDao != null) {
      return _calculatingDao;
    } else {
      synchronized(this) {
        if(_calculatingDao == null) {
          _calculatingDao = new CalculatingDao_Impl(this);
        }
        return _calculatingDao;
      }
    }
  }

  @Override
  public DigitalSpanMemoryDao digitalSpanMemoryDao() {
    if (_digitalSpanMemoryDao != null) {
      return _digitalSpanMemoryDao;
    } else {
      synchronized(this) {
        if(_digitalSpanMemoryDao == null) {
          _digitalSpanMemoryDao = new DigitalSpanMemoryDao_Impl(this);
        }
        return _digitalSpanMemoryDao;
      }
    }
  }

  @Override
  public FourSquareDao fourSquareDao() {
    if (_fourSquareDao != null) {
      return _fourSquareDao;
    } else {
      synchronized(this) {
        if(_fourSquareDao == null) {
          _fourSquareDao = new FourSquareDao_Impl(this);
        }
        return _fourSquareDao;
      }
    }
  }

  @Override
  public LineBisectionDao lineDissectionDao() {
    if (_lineBisectionDao != null) {
      return _lineBisectionDao;
    } else {
      synchronized(this) {
        if(_lineBisectionDao == null) {
          _lineBisectionDao = new LineBisectionDao_Impl(this);
        }
        return _lineBisectionDao;
      }
    }
  }

  @Override
  public PictureDao pictureDao() {
    if (_pictureDao != null) {
      return _pictureDao;
    } else {
      synchronized(this) {
        if(_pictureDao == null) {
          _pictureDao = new PictureDao_Impl(this);
        }
        return _pictureDao;
      }
    }
  }

  @Override
  public PpttDao ppttDao() {
    if (_ppttDao != null) {
      return _ppttDao;
    } else {
      synchronized(this) {
        if(_ppttDao == null) {
          _ppttDao = new PpttDao_Impl(this);
        }
        return _ppttDao;
      }
    }
  }

  @Override
  public ReactionDao reactionDao() {
    if (_reactionDao != null) {
      return _reactionDao;
    } else {
      synchronized(this) {
        if(_reactionDao == null) {
          _reactionDao = new ReactionDao_Impl(this);
        }
        return _reactionDao;
      }
    }
  }

  @Override
  public ReadDao readDao() {
    if (_readDao != null) {
      return _readDao;
    } else {
      synchronized(this) {
        if(_readDao == null) {
          _readDao = new ReadDao_Impl(this);
        }
        return _readDao;
      }
    }
  }

  @Override
  public StroopDao stroopDao() {
    if (_stroopDao != null) {
      return _stroopDao;
    } else {
      synchronized(this) {
        if(_stroopDao == null) {
          _stroopDao = new StroopDao_Impl(this);
        }
        return _stroopDao;
      }
    }
  }

  @Override
  public TokenDao tokenDao() {
    if (_tokenDao != null) {
      return _tokenDao;
    } else {
      synchronized(this) {
        if(_tokenDao == null) {
          _tokenDao = new TokenDao_Impl(this);
        }
        return _tokenDao;
      }
    }
  }

  @Override
  public TrailMakingDao trailwayDao() {
    if (_trailMakingDao != null) {
      return _trailMakingDao;
    } else {
      synchronized(this) {
        if(_trailMakingDao == null) {
          _trailMakingDao = new TrailMakingDao_Impl(this);
        }
        return _trailMakingDao;
      }
    }
  }

  @Override
  public ComplicationDao complicationDao() {
    if (_complicationDao != null) {
      return _complicationDao;
    } else {
      synchronized(this) {
        if(_complicationDao == null) {
          _complicationDao = new ComplicationDao_Impl(this);
        }
        return _complicationDao;
      }
    }
  }
}
