package com.app.craniowake.view.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.app.craniowake.R;
import com.app.craniowake.data.model.gameModels.PictureGame;
import com.app.craniowake.data.repositories.PictureRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.function.Consumer;

import lombok.Getter;
import lombok.Setter;

/**
 * stores and manages UI-related data of the PictureActivity and is used as an abstraction Layer
 */
public class PictureViewModel extends WithStimulationViewModel {
    private static final int[] pictureCollection = {R.drawable.p_apfel, R.drawable.p_ampel, R.drawable.p_auto, R.drawable.p_bahn, R.drawable.p_ballerina, R.drawable.p_baum, R.drawable.p_baumkahl, R.drawable.p_blaetter, R.drawable.p_boot, R.drawable.p_buecher, R.drawable.p_erdbeere, R.drawable.p_erde, R.drawable.p_fahrrad, R.drawable.p_flamingo, R.drawable.p_flugzeug, R.drawable.p_frosch, R.drawable.p_geschenk, R.drawable.p_gluehbirne, R.drawable.p_hase, R.drawable.p_haus, R.drawable.p_katze, R.drawable.p_kerze, R.drawable.p_koch, R.drawable.p_kuchen, R.drawable.p_kuh, R.drawable.p_leuchtturm, R.drawable.p_lkw, R.drawable.p_loewe, R.drawable.p_pc, R.drawable.p_pferd, R.drawable.p_puzzle, R.drawable.p_roller, R.drawable.p_schiff, R.drawable.p_schloss, R.drawable.p_schwan, R.drawable.p_sessel, R.drawable.p_smily, R.drawable.p_smilyhappy, R.drawable.p_smilylove, R.drawable.p_smilynormal, R.drawable.p_spritze, R.drawable.p_staaten, R.drawable.p_stift, R.drawable.p_strand, R.drawable.p_stuhl, R.drawable.p_tisch, R.drawable.p_traktor, R.drawable.p_wolke};
    private static final int[] pictureFaceCollection = {R.drawable.pf_beatles, R.drawable.pf_borisbecker, R.drawable.pf_dirknowitzki, R.drawable.pf_einstein, R.drawable.pf_franzbeckenbauer, R.drawable.pf_guentherjauch, R.drawable.pf_heidiklumm, R.drawable.pf_helmutkohl, R.drawable.pf_jaoachimgauck, R.drawable.pf_johnfkennedy, R.drawable.pf_marilynmonroe, R.drawable.pf_mozart, R.drawable.pf_mrbean, R.drawable.pf_obama, R.drawable.pf_oliverkahn, R.drawable.pf_sissi, R.drawable.pf_steffigraf, R.drawable.pf_thomasgotschalk, R.drawable.pf_totenhosen};

    private final Random random = new Random();

    private final PictureRepository pictureRepository;

    @Getter
    private MutableLiveData<Integer> currentPictureId;

    @Getter
    private int correctAnswersFaceMode;
    @Getter
    private int wrongAnswersFaceMode;
    @Getter
    private int correctAnswersObjectMode;
    @Getter
    private int wrongAnswersObjectMode;

    private boolean faceMode;

    @Setter
    private long operationId;

    private Collection<Consumer<Boolean>> answerConsumers;

    public PictureViewModel(@NonNull Application application) {
        super(application);
        pictureRepository = new PictureRepository(application);

        correctAnswersFaceMode = 0;
        wrongAnswersFaceMode = 0;
        correctAnswersObjectMode = 0;
        wrongAnswersObjectMode = 0;

        faceMode = false;

        currentPictureId = new MutableLiveData<>();
        randomiseCurrentPicture();

        answerConsumers = new HashSet<>();
    }

    public void addAnswerConsumer(Consumer<Boolean> consumer) {
        answerConsumers.add(consumer);
    }

    /**
     * passes the object to be saved in database to underlying repository
     *
     * @param pictureGame is generated in PictureActivity and send to be saved to db
     */
    public void addPictureGame(PictureGame pictureGame) {
        pictureRepository.insert(pictureGame);
    }

    public void addPictureGame(String pictureName, boolean face, boolean correctAnswer, long operationId) {
        addPictureGame(new PictureGame(pictureName, face, correctAnswer, operationId, getStimulation().getValue()));
    }

    public void addPictureGame(boolean correctAnswer) {
        addPictureGame(getApplication().getResources().getResourceName(currentPictureId.getValue()), faceMode, correctAnswer, operationId);
    }

    public void switchMode() {
        faceMode = !faceMode;
        randomiseCurrentPicture();
    }

    public void randomiseCurrentPicture() {
        int randomId;
        if (faceMode) {
            randomId = pictureFaceCollection[random.nextInt(pictureFaceCollection.length)];
        }
        else {
            randomId = pictureCollection[random.nextInt(pictureCollection.length)];
        }

        currentPictureId.setValue(randomId);
    }

    // TODO: Hier auch nochmal drüber nachdenken, ob es nicht besser wäre, diese Methode in der Activity zu belassen. Dann müsste man das Attribut "operationId" nicht vorhalten
    public void answer(boolean correct) {
        if(faceMode) {
            correctAnswersFaceMode += correct ? 1:0;
            wrongAnswersFaceMode += correct ? 0:1;
        }
        else {
            correctAnswersObjectMode += correct ? 1:0;
            wrongAnswersObjectMode += correct ? 0:1;
        }

        answerConsumers.forEach(consumer -> consumer.accept(correct));

        addPictureGame(correct);
        randomiseCurrentPicture();
    }
}
