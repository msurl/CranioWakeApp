package com.app.craniowake.databaseExport;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import androidx.sqlite.db.SupportSQLiteDatabase;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Can export an sqlite databse into a csv file.
 * <p>
 * The file has on the top dbVersion and on top of each table data the name of the table
 * <p>
 * https://stackoverflow.com/questions/31367270/exporting-sqlite-database-to-csv-file-in-android
 */
public class SqliteExporter {
    public static final String DB_BACKUP_DB_VERSION_KEY = "dbVersion";
    public static final String DB_BACKUP_TABLE_NAME = "table";
    private static final String TAG = SqliteExporter.class.getSimpleName();
    public static boolean DB_GENERATING_CSV = false;

    /**
     * exports CSV File to created directory
     *
     * @param db      Instance of a db wrapper to perform queries
     * @param context tells the compiler to which context activity or application the current belongs
     */
    public static String export(SupportSQLiteDatabase db, Context context) throws IOException {
        DB_GENERATING_CSV = true;
        if (!FileUtils.isExternalStorageWritable()) {
            throw new IOException("Cannot write to external storage");
        }
        File backupDir = FileUtils.createDirIfNotExist(FileUtils.getAppDir(context) + "/backup");
        String fileName = createBackupFileName();
        File backupFile = new File(backupDir, fileName);
        boolean success = backupFile.createNewFile();
        if (!success) {
            throw new IOException("Failed to create the backup file");
        }
        List<String> tables = getTablesFromDataBase(db);
        Log.d(TAG, "Started to fill the backup file in " + backupFile.getAbsolutePath());
        long starTime = System.currentTimeMillis();
        writeDbToCsv(backupFile, db, tables); // schleife -> write für jede einzelne table (backupfile + name)
        long endTime = System.currentTimeMillis();
        Log.d(TAG, "Creating backup took " + (endTime - starTime) + "ms.");
        return backupFile.getAbsolutePath();
    }

    private static String createBackupFileName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HHmm");
        return "db_backup_" + sdf.format(new Date()) + ".csv";
    }

    /**
     * Get all the table names we have in db and return as a List<String>
     *
     * @param db needed to perform queries
     */
    public static List<String> getTablesFromDataBase(SupportSQLiteDatabase db) {
        Cursor cursor = null;
        List<String> tables = new ArrayList<>();
        try {
            cursor = db.query("SELECT name FROM sqlite_master WHERE type='table'");
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    tables.add(cursor.getString(0));
                    cursor.moveToNext();
                }
            }
        } catch (Exception throwable) {
            Log.e(TAG, "Could not get the table names from db", throwable);
        } finally {
            closeCSVCursor(cursor);
        }
        return tables;
    }

    /**
     * iterates through database tables and writes entries into the csv file
     *
     * @param backupFile csv file to be written on
     * @param db         instance of db wrapper to perform queries on db
     * @param tables     list of all table names in db
     */
    private static void writeDbToCsv(File backupFile, SupportSQLiteDatabase db, List<String> tables) {
        CSVWriter csvWrite = null;
        Cursor cursorCSV = null;
        try {
            csvWrite = new CSVWriter(new FileWriter(backupFile));
            writeSingleValue(csvWrite, DB_BACKUP_DB_VERSION_KEY + "=" + db.getVersion());
            for (String table : tables) {
                writeSingleValue(csvWrite, DB_BACKUP_TABLE_NAME + "=" + table);
                cursorCSV = db.query("SELECT * FROM " + table);
                csvWrite.writeNext(cursorCSV.getColumnNames());
                while (cursorCSV.moveToNext()) {
                    int columns = cursorCSV.getColumnCount();
                    String[] columnArr = new String[columns];
                    for (int i = 0; i < columns; i++) {
                        columnArr[i] = cursorCSV.getString(i);
                    }
                    csvWrite.writeNext(columnArr);
                }
            }
        } catch (Exception sqlException) {
            Log.e(TAG, sqlException.getMessage(), sqlException);
        } finally {
            closeCSVWriter(csvWrite);
            closeCSVCursor(cursorCSV);
            DB_GENERATING_CSV = false;
        }
    }

    /**
     * Closes csvwriter
     *
     * @param csvWrite to be closed
     */
    private static void closeCSVWriter(CSVWriter csvWrite) {
        if (csvWrite != null) {
            try {
                csvWrite.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Closes cursor
     *
     * @param cursorCSV to be closed
     */
    private static void closeCSVCursor(Cursor cursorCSV) {
        if (cursorCSV != null) {
            cursorCSV.close();
        }
    }

    /**
     * @param writer writes value into csv
     * @param value  writes version key and tablename
     */
    private static void writeSingleValue(CSVWriter writer, String value) {
        writer.writeNext(new String[]{value});
    }
}