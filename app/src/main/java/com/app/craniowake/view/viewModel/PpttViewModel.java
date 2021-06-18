package com.app.craniowake.view.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.app.craniowake.R;
import com.app.craniowake.data.model.gameModels.PpttGame;
import com.app.craniowake.data.repositories.PpttRepository;

import java.net.URI;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.function.Consumer;

import lombok.Getter;
import lombok.Setter;

/**
 * stores and manages UI-related data of the PpttActivity and is used as an abstraction Layer
 */
public class PpttViewModel extends WithStimulationViewModel {
    public static final int[] pictureCollection = {R.drawable.pptt_adler_vogelklein_tiger, R.drawable.pptt_auge_brille_ohr, R.drawable.pptt_auto_busticket_bus, R.drawable.pptt_bartstoppel_rasierer_nase, R.drawable.pptt_bauarbeiter_opsaal_drsaal, R.drawable.pptt_bett_baby_babybett, R.drawable.pptt_bett_kissen_stuhl, R.drawable.pptt_biene_spinnennetz_spinne, R.drawable.pptt_boot_anker_schiff, R.drawable.pptt_boot_inuit_kayak, R.drawable.pptt_buch_brille_schere, R.drawable.pptt_clown_maske_mann, R.drawable.pptt_eifelturm_brandenburgertor_haus, R.drawable.pptt_esel_nuesse_schwein, R.drawable.pptt_eule_fledermaus_specht, R.drawable.pptt_fahrrad_schloss_auto, R.drawable.pptt_filmkamera_fotokamera_tacker, R.drawable.pptt_finger_ring_daumen, R.drawable.pptt_flugzeug_zug_auto, R.drawable.pptt_guarde_medallie_inuit, R.drawable.pptt_haende_weg_fuesse, R.drawable.pptt_hammer_baumstamm_saege, R.drawable.pptt_handschuhe_haende_schuhe, R.drawable.pptt_handy_pc_siftpapier, R.drawable.pptt_hase_kaese_maus, R.drawable.pptt_huhn_eier_schwan, R.drawable.pptt_hund_hundehuette_katze, R.drawable.pptt_hund_katze_schlange, R.drawable.pptt_hund_maus_katze, R.drawable.pptt_hunde_wolle_schaafe, R.drawable.pptt_iglo_inuit_haus, R.drawable.pptt_kaefig_maus_hundehuette, R.drawable.pptt_kaktus_gaensebluemchen_tulpe, R.drawable.pptt_katze_fisch_hund, R.drawable.pptt_kind_stecknadel_baby, R.drawable.pptt_kirche_kreuz_burg, R.drawable.pptt_kirche_nonne_haus, R.drawable.pptt_kuh_milch_bulle, R.drawable.pptt_lagerfeuer_luftpumpe_kerze, R.drawable.pptt_lagerfeuer_zelt_heizung, R.drawable.pptt_lampe_batterie_taschenlampe, R.drawable.pptt_lampe_streichholz_kerze, R.drawable.pptt_maus_fliege_schmetterling, R.drawable.pptt_maus_schaefer_schaafe, R.drawable.pptt_mond_globus_sonne, R.drawable.pptt_muetze_schal_scheere, R.drawable.pptt_nadel_fingerhut_faden, R.drawable.pptt_nagel_bohrmaschine_nadel, R.drawable.pptt_palme_pyramide_tannenbaum, R.drawable.pptt_schloss_waechter_burg, R.drawable.pptt_schmetterling_raupe_liebelle, R.drawable.pptt_spritze_pflaster_einstein, R.drawable.pptt_stern_rakete_nachthimmel, R.drawable.pptt_stiefel_sessel_hausschuhe, R.drawable.pptt_stift_tint_fueller, R.drawable.pptt_stuhl_tisch_auto, R.drawable.pptt_tannenbaum_sonnenbrille_strand, R.drawable.pptt_tee_wasser_burger, R.drawable.pptt_telefon_stift_papier, R.drawable.pptt_tisch_tafel_pult, R.drawable.pptt_tor_fussball_basketball, R.drawable.pptt_tuer_gardine_fenster, R.drawable.pptt_turm_auto_strasse, R.drawable.pptt_wolken_regen_sonne, R.drawable.pptt_wurm_hahn_schlange, R.drawable.pptt_wurst_banane_apfel, R.drawable.pptt_ziege_moehre_esel, R.drawable.pptt_ziege_sattel_pferd, R.drawable.pptt_zunge_stethoskop_herz, R.drawable.pptt_zwiebel_wald_apfel};
    private final Random random = new Random();

    private final PpttRepository ppttRepository;

    @Getter
    private MutableLiveData<Integer> currentPicId;

    @Getter
    private int correctAnswers;
    @Getter
    private int wrongAnswers;

    // evtl. auch diese Variable (wie auch in den anderen ViewModels) entfernen, den onClickListener in die Activity schieben und dort die ID übergeben.
    @Setter
    private long  operationId;

    // evtl. hier rausnehmen und den OnClickListener eine Funktion aus der Activity callen lassen
    private Collection<Consumer<Boolean>> answerConsumers;

    public PpttViewModel(@NonNull Application application) {
        super(application);
        ppttRepository = new PpttRepository(application);

        currentPicId = new MutableLiveData<>();
        randomiseCurrentPic();
        correctAnswers = 0;
        wrongAnswers = 0;

        answerConsumers = new HashSet<>();
    }

    /**
     * passes the object to be saved in database to underlying repository
     *
     * @param ppttGame is generated in PpttActivity and send to be saved to db
     */
    public void addPpttGame(PpttGame ppttGame) {
        ppttRepository.insert(ppttGame);
    }

    private void randomiseCurrentPic() {
        currentPicId.setValue(pictureCollection[random.nextInt(pictureCollection.length)]);
    }

    public void addPpttGame(String pictureName, boolean correct) {
        addPpttGame(operationId, pictureName, correct);
    }

    public void addPpttGame(boolean correct) {
        addPpttGame(getApplication().getResources().getResourceName(currentPicId.getValue()), correct);
    }

    public void addPpttGame(Long operationId, String pictureName, boolean correct) {
        PpttGame game = new PpttGame(pictureName, correct, getStimulation().getValue(), operationId);
        ppttRepository.insert(game);
    }

    public void correctAnswer() {
        correctAnswers++;
        answer(true);
    }

    public void wrongAnswer() {
        wrongAnswers++;
        answer(false);
    }

    public void answer(boolean correct) {
        answerConsumers.forEach(c -> c.accept(correct));
        addPpttGame(correct);

        randomiseCurrentPic();
    }

    public void addAnswerConsumer(Consumer<Boolean> consumer) {
        answerConsumers.add(consumer);
    }

}
