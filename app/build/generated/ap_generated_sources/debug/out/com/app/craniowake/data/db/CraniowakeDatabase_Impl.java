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
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `patient_table` (`patientId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `caseNumber` INTEGER NOT NULL, `lastname` TEXT, `firstname` TEXT, `birthDate` TEXT, `dateTime` TEXT, `sex` TEXT)");
        _db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_patient_table_caseNumber` ON `patient_table` (`caseNumber`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `operation_table` (`operationId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `brainarea` TEXT, `operationMode` TEXT, `dateTime` TEXT, `fkPatientId` INTEGER NOT NULL)");
        _db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_operation_table_dateTime` ON `operation_table` (`dateTime`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `calculus_test_table` (`calcGameId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `equation` TEXT, `answer` INTEGER NOT NULL, `dateTime` TEXT, `fkOperationId` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `digital_span_test_table` (`digitalSpanMemoryId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `answer` INTEGER NOT NULL, `dateTime` TEXT, `fkOperationId` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `four_square_test_table` (`FourSquareId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `pictureName` TEXT, `answerSquare1` INTEGER NOT NULL, `answerSquare2` INTEGER NOT NULL, `answerSquare3` INTEGER NOT NULL, `answerSquare4` INTEGER NOT NULL, `dateTime` TEXT, `fkOperationId` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `line_bisection_test_table` (`lineDissectionId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `distance_in_mm` REAL NOT NULL, `milisec` INTEGER NOT NULL, `dateTime` TEXT, `fkOperationId` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `picture_test_table` (`pictureId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `pictureName` TEXT, `answer` INTEGER NOT NULL, `dateTime` TEXT, `fkOperationId` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `pptt_test_table` (`ppttId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `answer` INTEGER NOT NULL, `pictureName` TEXT, `dateTime` TEXT, `fkOperationId` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `reaction_test_table` (`reactionId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `milisec` INTEGER NOT NULL, `dateTime` TEXT, `fkOperationId` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `read_test_table` (`readId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `mistakeCounter` INTEGER NOT NULL, `dateTime` TEXT, `fkOperationId` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `stroop_test_table` (`stroopId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `ink` TEXT, `answer` INTEGER NOT NULL, `dateTime` TEXT, `fkOperationId` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `token_test_table` (`tokenId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `token` TEXT, `answer` INTEGER NOT NULL, `dateTime` TEXT, `fkOperationId` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `trail_making_test_table` (`trailwayId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `correctAnswer` INTEGER NOT NULL, `dateTime` TEXT, `fkOperationId` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `complication_table` (`complicationId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `dateTime` TEXT, `fkOperationId` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '05536e831b43287151df0a09b391352c')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `patient_table`");
        _db.execSQL("DROP TABLE IF EXISTS `operation_table`");
        _db.execSQL("DROP TABLE IF EXISTS `calculus_test_table`");
        _db.execSQL("DROP TABLE IF EXISTS `digital_span_test_table`");
        _db.execSQL("DROP TABLE IF EXISTS `four_square_test_table`");
        _db.execSQL("DROP TABLE IF EXISTS `line_bisection_test_table`");
        _db.execSQL("DROP TABLE IF EXISTS `picture_test_table`");
        _db.execSQL("DROP TABLE IF EXISTS `pptt_test_table`");
        _db.execSQL("DROP TABLE IF EXISTS `reaction_test_table`");
        _db.execSQL("DROP TABLE IF EXISTS `read_test_table`");
        _db.execSQL("DROP TABLE IF EXISTS `stroop_test_table`");
        _db.execSQL("DROP TABLE IF EXISTS `token_test_table`");
        _db.execSQL("DROP TABLE IF EXISTS `trail_making_test_table`");
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
        final HashMap<String, TableInfo.Column> _columnsCalculusTestTable = new HashMap<String, TableInfo.Column>(5);
        _columnsCalculusTestTable.put("calcGameId", new TableInfo.Column("calcGameId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCalculusTestTable.put("equation", new TableInfo.Column("equation", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCalculusTestTable.put("answer", new TableInfo.Column("answer", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCalculusTestTable.put("dateTime", new TableInfo.Column("dateTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCalculusTestTable.put("fkOperationId", new TableInfo.Column("fkOperationId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCalculusTestTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCalculusTestTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCalculusTestTable = new TableInfo("calculus_test_table", _columnsCalculusTestTable, _foreignKeysCalculusTestTable, _indicesCalculusTestTable);
        final TableInfo _existingCalculusTestTable = TableInfo.read(_db, "calculus_test_table");
        if (! _infoCalculusTestTable.equals(_existingCalculusTestTable)) {
          return new RoomOpenHelper.ValidationResult(false, "calculus_test_table(com.app.craniowake.data.model.gameModels.CalculusGame).\n"
                  + " Expected:\n" + _infoCalculusTestTable + "\n"
                  + " Found:\n" + _existingCalculusTestTable);
        }
        final HashMap<String, TableInfo.Column> _columnsDigitalSpanTestTable = new HashMap<String, TableInfo.Column>(4);
        _columnsDigitalSpanTestTable.put("digitalSpanMemoryId", new TableInfo.Column("digitalSpanMemoryId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDigitalSpanTestTable.put("answer", new TableInfo.Column("answer", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDigitalSpanTestTable.put("dateTime", new TableInfo.Column("dateTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDigitalSpanTestTable.put("fkOperationId", new TableInfo.Column("fkOperationId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDigitalSpanTestTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDigitalSpanTestTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDigitalSpanTestTable = new TableInfo("digital_span_test_table", _columnsDigitalSpanTestTable, _foreignKeysDigitalSpanTestTable, _indicesDigitalSpanTestTable);
        final TableInfo _existingDigitalSpanTestTable = TableInfo.read(_db, "digital_span_test_table");
        if (! _infoDigitalSpanTestTable.equals(_existingDigitalSpanTestTable)) {
          return new RoomOpenHelper.ValidationResult(false, "digital_span_test_table(com.app.craniowake.data.model.gameModels.DigitalSpanMemoryGame).\n"
                  + " Expected:\n" + _infoDigitalSpanTestTable + "\n"
                  + " Found:\n" + _existingDigitalSpanTestTable);
        }
        final HashMap<String, TableInfo.Column> _columnsFourSquareTestTable = new HashMap<String, TableInfo.Column>(8);
        _columnsFourSquareTestTable.put("FourSquareId", new TableInfo.Column("FourSquareId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFourSquareTestTable.put("pictureName", new TableInfo.Column("pictureName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFourSquareTestTable.put("answerSquare1", new TableInfo.Column("answerSquare1", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFourSquareTestTable.put("answerSquare2", new TableInfo.Column("answerSquare2", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFourSquareTestTable.put("answerSquare3", new TableInfo.Column("answerSquare3", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFourSquareTestTable.put("answerSquare4", new TableInfo.Column("answerSquare4", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFourSquareTestTable.put("dateTime", new TableInfo.Column("dateTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFourSquareTestTable.put("fkOperationId", new TableInfo.Column("fkOperationId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFourSquareTestTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFourSquareTestTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFourSquareTestTable = new TableInfo("four_square_test_table", _columnsFourSquareTestTable, _foreignKeysFourSquareTestTable, _indicesFourSquareTestTable);
        final TableInfo _existingFourSquareTestTable = TableInfo.read(_db, "four_square_test_table");
        if (! _infoFourSquareTestTable.equals(_existingFourSquareTestTable)) {
          return new RoomOpenHelper.ValidationResult(false, "four_square_test_table(com.app.craniowake.data.model.gameModels.FourSquareGame).\n"
                  + " Expected:\n" + _infoFourSquareTestTable + "\n"
                  + " Found:\n" + _existingFourSquareTestTable);
        }
        final HashMap<String, TableInfo.Column> _columnsLineBisectionTestTable = new HashMap<String, TableInfo.Column>(5);
        _columnsLineBisectionTestTable.put("lineDissectionId", new TableInfo.Column("lineDissectionId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLineBisectionTestTable.put("distance_in_mm", new TableInfo.Column("distance_in_mm", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLineBisectionTestTable.put("milisec", new TableInfo.Column("milisec", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLineBisectionTestTable.put("dateTime", new TableInfo.Column("dateTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLineBisectionTestTable.put("fkOperationId", new TableInfo.Column("fkOperationId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLineBisectionTestTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLineBisectionTestTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLineBisectionTestTable = new TableInfo("line_bisection_test_table", _columnsLineBisectionTestTable, _foreignKeysLineBisectionTestTable, _indicesLineBisectionTestTable);
        final TableInfo _existingLineBisectionTestTable = TableInfo.read(_db, "line_bisection_test_table");
        if (! _infoLineBisectionTestTable.equals(_existingLineBisectionTestTable)) {
          return new RoomOpenHelper.ValidationResult(false, "line_bisection_test_table(com.app.craniowake.data.model.gameModels.LineBisectionGame).\n"
                  + " Expected:\n" + _infoLineBisectionTestTable + "\n"
                  + " Found:\n" + _existingLineBisectionTestTable);
        }
        final HashMap<String, TableInfo.Column> _columnsPictureTestTable = new HashMap<String, TableInfo.Column>(5);
        _columnsPictureTestTable.put("pictureId", new TableInfo.Column("pictureId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPictureTestTable.put("pictureName", new TableInfo.Column("pictureName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPictureTestTable.put("answer", new TableInfo.Column("answer", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPictureTestTable.put("dateTime", new TableInfo.Column("dateTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPictureTestTable.put("fkOperationId", new TableInfo.Column("fkOperationId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPictureTestTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPictureTestTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPictureTestTable = new TableInfo("picture_test_table", _columnsPictureTestTable, _foreignKeysPictureTestTable, _indicesPictureTestTable);
        final TableInfo _existingPictureTestTable = TableInfo.read(_db, "picture_test_table");
        if (! _infoPictureTestTable.equals(_existingPictureTestTable)) {
          return new RoomOpenHelper.ValidationResult(false, "picture_test_table(com.app.craniowake.data.model.gameModels.PictureGame).\n"
                  + " Expected:\n" + _infoPictureTestTable + "\n"
                  + " Found:\n" + _existingPictureTestTable);
        }
        final HashMap<String, TableInfo.Column> _columnsPpttTestTable = new HashMap<String, TableInfo.Column>(5);
        _columnsPpttTestTable.put("ppttId", new TableInfo.Column("ppttId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPpttTestTable.put("answer", new TableInfo.Column("answer", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPpttTestTable.put("pictureName", new TableInfo.Column("pictureName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPpttTestTable.put("dateTime", new TableInfo.Column("dateTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPpttTestTable.put("fkOperationId", new TableInfo.Column("fkOperationId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPpttTestTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPpttTestTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPpttTestTable = new TableInfo("pptt_test_table", _columnsPpttTestTable, _foreignKeysPpttTestTable, _indicesPpttTestTable);
        final TableInfo _existingPpttTestTable = TableInfo.read(_db, "pptt_test_table");
        if (! _infoPpttTestTable.equals(_existingPpttTestTable)) {
          return new RoomOpenHelper.ValidationResult(false, "pptt_test_table(com.app.craniowake.data.model.gameModels.PpttGame).\n"
                  + " Expected:\n" + _infoPpttTestTable + "\n"
                  + " Found:\n" + _existingPpttTestTable);
        }
        final HashMap<String, TableInfo.Column> _columnsReactionTestTable = new HashMap<String, TableInfo.Column>(4);
        _columnsReactionTestTable.put("reactionId", new TableInfo.Column("reactionId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReactionTestTable.put("milisec", new TableInfo.Column("milisec", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReactionTestTable.put("dateTime", new TableInfo.Column("dateTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReactionTestTable.put("fkOperationId", new TableInfo.Column("fkOperationId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysReactionTestTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesReactionTestTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoReactionTestTable = new TableInfo("reaction_test_table", _columnsReactionTestTable, _foreignKeysReactionTestTable, _indicesReactionTestTable);
        final TableInfo _existingReactionTestTable = TableInfo.read(_db, "reaction_test_table");
        if (! _infoReactionTestTable.equals(_existingReactionTestTable)) {
          return new RoomOpenHelper.ValidationResult(false, "reaction_test_table(com.app.craniowake.data.model.gameModels.ReactionGame).\n"
                  + " Expected:\n" + _infoReactionTestTable + "\n"
                  + " Found:\n" + _existingReactionTestTable);
        }
        final HashMap<String, TableInfo.Column> _columnsReadTestTable = new HashMap<String, TableInfo.Column>(4);
        _columnsReadTestTable.put("readId", new TableInfo.Column("readId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReadTestTable.put("mistakeCounter", new TableInfo.Column("mistakeCounter", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReadTestTable.put("dateTime", new TableInfo.Column("dateTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReadTestTable.put("fkOperationId", new TableInfo.Column("fkOperationId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysReadTestTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesReadTestTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoReadTestTable = new TableInfo("read_test_table", _columnsReadTestTable, _foreignKeysReadTestTable, _indicesReadTestTable);
        final TableInfo _existingReadTestTable = TableInfo.read(_db, "read_test_table");
        if (! _infoReadTestTable.equals(_existingReadTestTable)) {
          return new RoomOpenHelper.ValidationResult(false, "read_test_table(com.app.craniowake.data.model.gameModels.ReadGame).\n"
                  + " Expected:\n" + _infoReadTestTable + "\n"
                  + " Found:\n" + _existingReadTestTable);
        }
        final HashMap<String, TableInfo.Column> _columnsStroopTestTable = new HashMap<String, TableInfo.Column>(5);
        _columnsStroopTestTable.put("stroopId", new TableInfo.Column("stroopId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStroopTestTable.put("ink", new TableInfo.Column("ink", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStroopTestTable.put("answer", new TableInfo.Column("answer", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStroopTestTable.put("dateTime", new TableInfo.Column("dateTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStroopTestTable.put("fkOperationId", new TableInfo.Column("fkOperationId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysStroopTestTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesStroopTestTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoStroopTestTable = new TableInfo("stroop_test_table", _columnsStroopTestTable, _foreignKeysStroopTestTable, _indicesStroopTestTable);
        final TableInfo _existingStroopTestTable = TableInfo.read(_db, "stroop_test_table");
        if (! _infoStroopTestTable.equals(_existingStroopTestTable)) {
          return new RoomOpenHelper.ValidationResult(false, "stroop_test_table(com.app.craniowake.data.model.gameModels.StroopGame).\n"
                  + " Expected:\n" + _infoStroopTestTable + "\n"
                  + " Found:\n" + _existingStroopTestTable);
        }
        final HashMap<String, TableInfo.Column> _columnsTokenTestTable = new HashMap<String, TableInfo.Column>(5);
        _columnsTokenTestTable.put("tokenId", new TableInfo.Column("tokenId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTokenTestTable.put("token", new TableInfo.Column("token", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTokenTestTable.put("answer", new TableInfo.Column("answer", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTokenTestTable.put("dateTime", new TableInfo.Column("dateTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTokenTestTable.put("fkOperationId", new TableInfo.Column("fkOperationId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTokenTestTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTokenTestTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTokenTestTable = new TableInfo("token_test_table", _columnsTokenTestTable, _foreignKeysTokenTestTable, _indicesTokenTestTable);
        final TableInfo _existingTokenTestTable = TableInfo.read(_db, "token_test_table");
        if (! _infoTokenTestTable.equals(_existingTokenTestTable)) {
          return new RoomOpenHelper.ValidationResult(false, "token_test_table(com.app.craniowake.data.model.gameModels.TokenGame).\n"
                  + " Expected:\n" + _infoTokenTestTable + "\n"
                  + " Found:\n" + _existingTokenTestTable);
        }
        final HashMap<String, TableInfo.Column> _columnsTrailMakingTestTable = new HashMap<String, TableInfo.Column>(4);
        _columnsTrailMakingTestTable.put("trailwayId", new TableInfo.Column("trailwayId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrailMakingTestTable.put("correctAnswer", new TableInfo.Column("correctAnswer", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrailMakingTestTable.put("dateTime", new TableInfo.Column("dateTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrailMakingTestTable.put("fkOperationId", new TableInfo.Column("fkOperationId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTrailMakingTestTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTrailMakingTestTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTrailMakingTestTable = new TableInfo("trail_making_test_table", _columnsTrailMakingTestTable, _foreignKeysTrailMakingTestTable, _indicesTrailMakingTestTable);
        final TableInfo _existingTrailMakingTestTable = TableInfo.read(_db, "trail_making_test_table");
        if (! _infoTrailMakingTestTable.equals(_existingTrailMakingTestTable)) {
          return new RoomOpenHelper.ValidationResult(false, "trail_making_test_table(com.app.craniowake.data.model.gameModels.TrailMakingGame).\n"
                  + " Expected:\n" + _infoTrailMakingTestTable + "\n"
                  + " Found:\n" + _existingTrailMakingTestTable);
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
    }, "05536e831b43287151df0a09b391352c", "56da671a186aa1c8051aed2bf0d46f85");
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
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "patient_table","operation_table","calculus_test_table","digital_span_test_table","four_square_test_table","line_bisection_test_table","picture_test_table","pptt_test_table","reaction_test_table","read_test_table","stroop_test_table","token_test_table","trail_making_test_table","complication_table");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `patient_table`");
      _db.execSQL("DELETE FROM `operation_table`");
      _db.execSQL("DELETE FROM `calculus_test_table`");
      _db.execSQL("DELETE FROM `digital_span_test_table`");
      _db.execSQL("DELETE FROM `four_square_test_table`");
      _db.execSQL("DELETE FROM `line_bisection_test_table`");
      _db.execSQL("DELETE FROM `picture_test_table`");
      _db.execSQL("DELETE FROM `pptt_test_table`");
      _db.execSQL("DELETE FROM `reaction_test_table`");
      _db.execSQL("DELETE FROM `read_test_table`");
      _db.execSQL("DELETE FROM `stroop_test_table`");
      _db.execSQL("DELETE FROM `token_test_table`");
      _db.execSQL("DELETE FROM `trail_making_test_table`");
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
