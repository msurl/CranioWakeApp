package com.app.craniowake.data.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.app.craniowake.data.dao.CalculatingDao;
import com.app.craniowake.data.dao.ComplicationDao;
import com.app.craniowake.data.dao.DigitalSpanMemoryDao;
import com.app.craniowake.data.dao.FourSquareDao;
import com.app.craniowake.data.dao.LineBisectionDao;
import com.app.craniowake.data.dao.OperationDao;
import com.app.craniowake.data.dao.PatientDao;
import com.app.craniowake.data.dao.PictureDao;
import com.app.craniowake.data.dao.PpttDao;
import com.app.craniowake.data.dao.ReactionDao;
import com.app.craniowake.data.dao.ReadDao;
import com.app.craniowake.data.dao.StroopDao;
import com.app.craniowake.data.dao.TokenDao;
import com.app.craniowake.data.dao.TrailMakingDao;
import com.app.craniowake.data.model.Complication;
import com.app.craniowake.data.model.Operation;
import com.app.craniowake.data.model.Patient;
import com.app.craniowake.data.model.gameModels.CalculusGame;
import com.app.craniowake.data.model.gameModels.DigitalSpanMemoryGame;
import com.app.craniowake.data.model.gameModels.FourSquareGame;
import com.app.craniowake.data.model.gameModels.LineBisectionGame;
import com.app.craniowake.data.model.gameModels.PictureGame;
import com.app.craniowake.data.model.gameModels.PpttGame;
import com.app.craniowake.data.model.gameModels.ReactionGame;
import com.app.craniowake.data.model.gameModels.ReadGame;
import com.app.craniowake.data.model.gameModels.StroopGame;
import com.app.craniowake.data.model.gameModels.TokenGame;
import com.app.craniowake.data.model.gameModels.TrailMakingGame;

/**
 * all Tables inside of the database
 */
@androidx.room.Database(entities = {
        Patient.class,
        Operation.class,
        CalculusGame.class,
        DigitalSpanMemoryGame.class,
        FourSquareGame.class,
        LineBisectionGame.class,
        PictureGame.class,
        PpttGame.class,
        ReactionGame.class,
        ReadGame.class,
        StroopGame.class,
        TokenGame.class,
        TrailMakingGame.class,
        Complication.class
}
        , version = 3
)

/**
 * Database class of the App Craniowake which holds the database.
 * The CraniowakeDatabase defines the database configuration and serves as the main access point to the persisted data.
 */
public abstract class CraniowakeDatabase extends RoomDatabase {

    private static CraniowakeDatabase instance;
    private static final RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbWithUserAsyncTask(instance).execute();
        }
    };

    /**
     * Creates an instance of the database
     *
     * @param context Instance of the ApplicationContext (Activity) in which the Method will be called.
     */
    public static synchronized CraniowakeDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), CraniowakeDatabase.class, "patient_database")
                    .addCallback(roomCallback)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    /**
     * For each Dao that is associated with the database, the database class must define an abstract method that returns an instance of the Dao class.
     */

    public abstract PatientDao patientDao();

    public abstract OperationDao operationDao();

    public abstract CalculatingDao calculatingDao();

    public abstract DigitalSpanMemoryDao digitalSpanMemoryDao();

    public abstract FourSquareDao fourSquareDao();

    public abstract LineBisectionDao lineDissectionDao();

    public abstract PictureDao pictureDao();

    public abstract PpttDao ppttDao();

    public abstract ReactionDao reactionDao();

    public abstract ReadDao readDao();

    public abstract StroopDao stroopDao();

    public abstract TokenDao tokenDao();

    public abstract TrailMakingDao trailwayDao();

    public abstract ComplicationDao complicationDao();

    /**
     * A default user is added to the database in a background thread via AsyncTask
     */
    private static class PopulateDbWithUserAsyncTask extends AsyncTask<Void, Void, Void> {
        private final PatientDao patientDao;

        private PopulateDbWithUserAsyncTask(CraniowakeDatabase db) {
            patientDao = db.patientDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            patientDao.addPatient(new Patient(1L, "TRIAL", "USER", "13.12.1994", "w"));
            return null;
        }
    }
}
