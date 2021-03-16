package com.app.craniowake.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.app.craniowake.data.dao.TokenDao;
import com.app.craniowake.data.db.CraniowakeDatabase;
import com.app.craniowake.data.model.gameModels.TokenGame;

/**
 * handles all data operations for the token model and dao.
 * Provides a clean API so that the rest of the app can retrieve this data.
 * It abstracts the data sources from the rest of the app.
 */
public class TokenRepository {
    private final TokenDao tokenDao;

    public TokenRepository(Application application) {
        CraniowakeDatabase craniowakeDatabase = CraniowakeDatabase.getInstance(application);
        tokenDao = craniowakeDatabase.tokenDao();
    }

    /**
     * insert calls AsyncTask to handle background thread
     * @param tokenGame objekt to be saved in database
     */
    public void insert(TokenGame tokenGame) {
        new TokenRepository.AddTokenGameAsyncTask(tokenDao).execute(tokenGame);
    }

    private static class AddTokenGameAsyncTask extends AsyncTask<TokenGame, Void, Void> {
        private final TokenDao tokenDao;

        private AddTokenGameAsyncTask(TokenDao tokenDao) {
            this.tokenDao = tokenDao;
        }

        @Override
        protected Void doInBackground(TokenGame... tokenGames) {
            tokenDao.addTokenGame(tokenGames[0]);
            return null;
        }
    }
}
