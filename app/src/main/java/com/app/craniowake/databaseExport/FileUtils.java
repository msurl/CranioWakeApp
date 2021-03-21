package com.app.craniowake.databaseExport;

import android.content.Context;
import android.os.Environment;

import com.app.craniowake.R;

import java.io.File;

/**
 * Creates directory to save Files
 * https://stackoverflow.com/questions/31367270/exporting-sqlite-database-to-csv-file-in-android
 */
public class FileUtils {

    /**
     * Returns the path to files folder inside Android/data/data/CranioWake/ on the SD card
     *
     * @param context tells the compiler to which context activity or application the current belongs
     */
    public static String getAppDir(Context context) {
        return context.getApplicationContext().getExternalFilesDir(null) + "/" + context.getApplicationContext().getString(R.string.app_name);
    }

    /**
     * Creates directory if not exists yet
     *
     * @param path path to files folder on the SD card
     */
    public static File createDirIfNotExist(String path) {
        File directory = new File(path);
        boolean wasSuccessful;
        if (!directory.exists()) {
            wasSuccessful = directory.mkdirs();
            if (!wasSuccessful) {
                System.out.println("was not successful.");
                return null;
            }
        }
        return directory;
    }

    /**
     * Checks if external storage is available for read and write
     */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }
}
