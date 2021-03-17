package com.app.craniowake.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.app.craniowake.data.dao.ReactionDao;
import com.app.craniowake.data.db.CraniowakeDatabase;
import com.app.craniowake.data.model.gameModels.ReactionGame;

/**
 * handles all data operations for the reaction model and dao.
 * Provides a clean API so that the rest of the app can retrieve this data.
 * It abstracts the data sources from the rest of the app.
 */
public class ReactionRepository {
    private final ReactionDao reactionDao;

    public ReactionRepository(Application application) {
        CraniowakeDatabase craniowakeDatabase = CraniowakeDatabase.getInstance(application);
        reactionDao = craniowakeDatabase.reactionDao();
    }

    /**
     * insert calls AsyncTask to handle background thread
     *
     * @param reactionGame objekt to be saved in database
     */
    public void insert(ReactionGame reactionGame) {
        new ReactionRepository.AddReactionGameAsyncTask(reactionDao).execute(reactionGame);
    }

    private static class AddReactionGameAsyncTask extends AsyncTask<ReactionGame, Void, Void> {
        private final ReactionDao reactionDao;

        private AddReactionGameAsyncTask(ReactionDao reactionDao) {
            this.reactionDao = reactionDao;
        }

        @Override
        protected Void doInBackground(ReactionGame... reactionGames) {
            reactionDao.addReactionGame(reactionGames[0]);
            return null;
        }
    }
}
