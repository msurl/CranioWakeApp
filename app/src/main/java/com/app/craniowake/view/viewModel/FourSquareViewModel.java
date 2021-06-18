package com.app.craniowake.view.viewModel;

import android.app.Application;
import android.content.res.ColorStateList;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.app.craniowake.R;
import com.app.craniowake.data.model.gameModels.FourSquareGame;
import com.app.craniowake.data.repositories.FourSquareRepository;

import java.util.Random;

import lombok.Getter;
import lombok.Setter;

/**
 * stores and manages UI-related data of the FourSquareActivity and is used as an abstraction Layer
 */
public class FourSquareViewModel extends WithStimulationViewModel {
    private final FourSquareRepository fourSquareRepository;

    private final static ColorStateList GREEN = ColorStateList.valueOf(Color.GREEN);
    private final static ColorStateList GRAY = ColorStateList.valueOf(Color.GRAY);

    private final int[] pictureFourSquareCollection = {R.drawable.fs_bauarbeiter_geschenk_traktor_flugzeug, R.drawable.fs_baum_auto_leuchtturm_zahnarzt, R.drawable.fs_brotkorb_handschellen_spritze_baum, R.drawable.fs_erdbeere_zug_koch_blumen, R.drawable.fs_haus_fahrrad_apfel_leer, R.drawable.fs_herbstblaetter_wolke_schwan_schiff, R.drawable.fs_katze_buecher_ballerina_schiff, R.drawable.fs_lkw_pc_leer_jaeger, R.drawable.fs_sessel_kerze_kuchen_hase, R.drawable.fs_strand_erde_schloss_lampe, R.drawable.fs_stuhl_roller_tisch_stift};
    private final int[] pictureFourSquareCollectionFaces = {R.drawable.fs_bartsimpson_nowitzki_donaldduck_kahn, R.drawable.fs_einstein_mozart_mrbean_sissi, R.drawable.fs_gauck_steffigraf_borisbecker_obama, R.drawable.fs_guentherjauch_beatles_mickeymaus_leer, R.drawable.fs_kennedy_pipilangstrumpf_totehosen_beckenbauer, R.drawable.fs_monroe_klumm_kohl_gotschalk};

    private final Random random = new Random();

    private boolean faces = false;

    private final MutableLiveData<Boolean> recognized1;
    private final MutableLiveData<Boolean> recognized2;
    private final MutableLiveData<Boolean> recognized3;
    private final MutableLiveData<Boolean> recognized4;

    @Getter
    private LiveData<ColorStateList> firstButtonBackgroundColor;
    @Getter
    private LiveData<ColorStateList> secondButtonBackgroundColor;
    @Getter
    private LiveData<ColorStateList> thirdButtonBackgroundColor;
    @Getter
    private LiveData<ColorStateList> fourthButtonBackgroundColor;


    @Setter
    private Long operationId;

    @Getter
    private MutableLiveData<Integer> currentPic;

    //results
    @Getter
    private int gameRoundCounter = 0;
    @Getter
    private int counterSquare1 = 0;
    @Getter
    private int counterSquare2 = 0;
    @Getter
    private int counterSquare3 = 0;
    @Getter
    private int counterSquare4 = 0;

    @Getter
    private int gameRoundCounterFaces = 0;
    @Getter
    private int counterSquare1Faces = 0;
    @Getter
    private int counterSquare2Faces = 0;
    @Getter
    private int counterSquare3Faces = 0;
    @Getter
    private int counterSquare4Faces = 0;


    public FourSquareViewModel(@NonNull Application application) {
        super(application);
        fourSquareRepository = new FourSquareRepository(application);

        recognized1 = new MutableLiveData<>();
        recognized2 = new MutableLiveData<>();
        recognized3 = new MutableLiveData<>();
        recognized4 = new MutableLiveData<>();

        currentPic = new MutableLiveData<>();

        init();
        currentPic.setValue(R.drawable.fs_leer);

        firstButtonBackgroundColor = Transformations.map(recognized1, FourSquareViewModel::background);
        secondButtonBackgroundColor = Transformations.map(recognized2, FourSquareViewModel::background);
        thirdButtonBackgroundColor = Transformations.map(recognized3, FourSquareViewModel::background);
        fourthButtonBackgroundColor = Transformations.map(recognized4, FourSquareViewModel::background);
    }

    /**
     * passes the object to be saved in database to underlying repository
     *
     * @param fourSquareGame is generated in FourSquareActivity and send to be saved to db
     */
    public void addFourSquareGame(FourSquareGame fourSquareGame) {
        fourSquareRepository.insert(fourSquareGame);
    }


    public void nextPicture(Long operationId) {
        String pictureName = getApplication().getResources().getResourceName(currentPic.getValue());

        boolean square1 = recognized1.getValue();
        boolean square2 = recognized2.getValue();
        boolean square3 = recognized3.getValue();
        boolean square4 = recognized4.getValue();


        FourSquareGame game = new FourSquareGame(pictureName, recognized1.getValue(),
                recognized2.getValue(), recognized3.getValue(),
                recognized4.getValue(), getStimulation().getValue(), operationId);

        addFourSquareGame(game);

        if(faces) {
            gameRoundCounterFaces++;
            counterSquare1Faces += square1 ? 1:0;
            counterSquare2Faces += square2 ? 1:0;
            counterSquare3Faces += square3 ? 1:0;
            counterSquare4Faces += square4 ? 1:0;
        }
        else  {
            gameRoundCounter++;
            counterSquare1 += square1 ? 1:0;
            counterSquare2 += square2 ? 1:0;
            counterSquare3 += square3 ? 1:0;
            counterSquare4 += square4 ? 1:0;
        }

        newGame();
    }

    private static ColorStateList background(boolean clicked) {
        if(clicked) {
            return GREEN;
        }
        else {
            return GRAY;
        }
    }

    private void newGame() {
        init();
        nextPicture();
    }

    private void init() {
        recognized1.setValue(false);
        recognized2.setValue(false);
        recognized3.setValue(false);
        recognized4.setValue(false);
    }

    public void nextPicture() {
        if(faces)
            currentPic.setValue(pictureFourSquareCollectionFaces[random.nextInt(pictureFourSquareCollectionFaces.length)]);
        else
            currentPic.setValue(pictureFourSquareCollection[random.nextInt(pictureFourSquareCollection.length)]);
    }

    public void switchMode() {
        faces = !faces;
    }

    public void squareOneButton() {
        boolean recSquare1 = recognized1.getValue();
        recognized1.setValue(!recSquare1);
    }

    public void squareTwoButton() {
        boolean recSquare2 = recognized2.getValue();
        recognized2.setValue(!recSquare2);
    }

    public void squareThreeButton() {
        boolean recSquare3 = recognized3.getValue();
        recognized3.setValue(!recSquare3);
    }

    public void squareFourButton() {
        boolean recSquare4 = recognized4.getValue();
        recognized4.setValue(!recSquare4);
    }
}
