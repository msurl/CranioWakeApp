package com.app.craniowake.view.games;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.lifecycle.ViewModelProvider;

import com.app.craniowake.R;
import com.app.craniowake.data.model.gameModels.PpttGame;
import com.app.craniowake.view.OperationActivity;
import com.app.craniowake.view.activityHelper.IntentHolder;
import com.app.craniowake.view.games.displayResults.BaseResultActivity;
import com.app.craniowake.view.viewModel.OperationViewModel;
import com.app.craniowake.view.viewModel.PpttViewModel;

import java.util.Random;

/**
 * Implementation of the PPT Test
 */
public class PpttActivity extends OperationActivity {

    private final int[] pictureCollection = {R.drawable.pptt_adler_vogelklein_tiger, R.drawable.pptt_auge_brille_ohr, R.drawable.pptt_auto_busticket_bus, R.drawable.pptt_bartstoppel_rasierer_nase, R.drawable.pptt_bauarbeiter_opsaal_drsaal, R.drawable.pptt_bett_baby_babybett, R.drawable.pptt_bett_kissen_stuhl, R.drawable.pptt_biene_spinnennetz_spinne, R.drawable.pptt_boot_anker_schiff, R.drawable.pptt_boot_inuit_kayak, R.drawable.pptt_buch_brille_schere, R.drawable.pptt_clown_maske_mann, R.drawable.pptt_eifelturm_brandenburgertor_haus, R.drawable.pptt_esel_nuesse_schwein, R.drawable.pptt_eule_fledermaus_specht, R.drawable.pptt_fahrrad_schloss_auto, R.drawable.pptt_filmkamera_fotokamera_tacker, R.drawable.pptt_finger_ring_daumen, R.drawable.pptt_flugzeug_zug_auto, R.drawable.pptt_guarde_medallie_inuit, R.drawable.pptt_haende_weg_fuesse, R.drawable.pptt_hammer_baumstamm_saege, R.drawable.pptt_handschuhe_haende_schuhe, R.drawable.pptt_handy_pc_siftpapier, R.drawable.pptt_hase_kaese_maus, R.drawable.pptt_huhn_eier_schwan, R.drawable.pptt_hund_hundehuette_katze, R.drawable.pptt_hund_katze_schlange, R.drawable.pptt_hund_maus_katze, R.drawable.pptt_hunde_wolle_schaafe, R.drawable.pptt_iglo_inuit_haus, R.drawable.pptt_kaefig_maus_hundehuette, R.drawable.pptt_kaktus_gaensebluemchen_tulpe, R.drawable.pptt_katze_fisch_hund, R.drawable.pptt_kind_stecknadel_baby, R.drawable.pptt_kirche_kreuz_burg, R.drawable.pptt_kirche_nonne_haus, R.drawable.pptt_kuh_milch_bulle, R.drawable.pptt_lagerfeuer_luftpumpe_kerze, R.drawable.pptt_lagerfeuer_zelt_heizung, R.drawable.pptt_lampe_batterie_taschenlampe, R.drawable.pptt_lampe_streichholz_kerze, R.drawable.pptt_maus_fliege_schmetterling, R.drawable.pptt_maus_schaefer_schaafe, R.drawable.pptt_mond_globus_sonne, R.drawable.pptt_muetze_schal_scheere, R.drawable.pptt_nadel_fingerhut_faden, R.drawable.pptt_nagel_bohrmaschine_nadel, R.drawable.pptt_palme_pyramide_tannenbaum, R.drawable.pptt_schloss_waechter_burg, R.drawable.pptt_schmetterling_raupe_liebelle, R.drawable.pptt_spritze_pflaster_einstein, R.drawable.pptt_stern_rakete_nachthimmel, R.drawable.pptt_stiefel_sessel_hausschuhe, R.drawable.pptt_stift_tint_fueller, R.drawable.pptt_stuhl_tisch_auto, R.drawable.pptt_tannenbaum_sonnenbrille_strand, R.drawable.pptt_tee_wasser_burger, R.drawable.pptt_telefon_stift_papier, R.drawable.pptt_tisch_tafel_pult, R.drawable.pptt_tor_fussball_basketball, R.drawable.pptt_tuer_gardine_fenster, R.drawable.pptt_turm_auto_strasse, R.drawable.pptt_wolken_regen_sonne, R.drawable.pptt_wurm_hahn_schlange, R.drawable.pptt_wurst_banane_apfel, R.drawable.pptt_ziege_moehre_esel, R.drawable.pptt_ziege_sattel_pferd, R.drawable.pptt_zunge_stethoskop_herz, R.drawable.pptt_zwiebel_wald_apfel};
    OperationViewModel operationViewModel;
    PpttViewModel ppttViewModel;
    private int currentPic;

    private int correctAnswers = 0;
    private int wrongAnswers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pptt_test);
        setUiElements();
        displayNewPicture();
    }

    /**
     * returns random picture from pictureCollection array
     *
     * @return id of resource (picture)
     */
    private int returnRandomPictureId() {
        Random random = new Random();
        return pictureCollection[random.nextInt(pictureCollection.length)];
    }

    private void displayNewPicture() {
        currentPic = returnRandomPictureId();
        ImageView pptPicture = findViewById(R.id.pptt_test_view);
        pptPicture.setImageResource(currentPic);
    }

    /**
     * returns fileName of picture
     *
     * @param id id of resource (picture)
     * @return name of resource with given id
     */
    private String check(int id) { // R.id.bigGreenCircle
        return getResources().getResourceName(id);
    }

    @SuppressLint("NonConstantResourceId")
    public void setPatientRecognizedObjectsInPicture(View view) {
        boolean answer = evaluateAnswer(view.getId());
        saveAnswer(answer, check(currentPic));
        displayNewPicture();
    }

    /**
     * evaluates answer given by the id of the button which was clicked. Also increases general counter of correct or wrong answers
     *
     * @param id of button "true" or "false"
     */
    @SuppressLint("NonConstantResourceId")
    private boolean evaluateAnswer(int id) {
        switch (id) {
            case R.id.ppttTrueButton:
                correctAnswers++;
                return true;
            case R.id.ppttFalseButton:
                wrongAnswers++;
                return false;
            default:
                break;
        }
        return false;
    }

    /**
     * creates object of PpttGame and saves the answer to the database. Object is processed by the PpttViewModel
     *
     * @param answer      of patient if recognized picture
     * @param pictureName picture to be recognized
     */
    private void saveAnswer(boolean answer, String pictureName) {
        ppttViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(PpttViewModel.class);
        operationViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(OperationViewModel.class);
        operationViewModel.getOperationByDate(getCurrentOperationId()).observe(this, operation -> {
            try {
                PpttGame ppttGame = new PpttGame(pictureName, answer, operation.getOperationId());
                ppttViewModel.addPpttGame(ppttGame);
            } catch (Exception e) {
                System.out.println("pptt has not been added to db");
            }
        });
    }

    /**
     * returns string of dateTime when current operation was created t
     * its used as an identifier
     */
    private String getCurrentOperationId() {
        Intent intent = getIntent();
        return intent.getStringExtra(IntentHolder.OPERATION_DATE);
    }

    private void setUiElements() {
        initializeToolbar(R.id.toolbar_seekbar);
        initiateSeekbar();
    }

    /**
     * saves results in an Intent and opens new BaseResultActivity. The current Activity gets destroyed when left.
     *
     * @param view method is called when button "finish game" is clicked
     */
    public void finishPPTTGame(View view) {
        Intent intent = new Intent(this, BaseResultActivity.class);

        intent.putExtra(IntentHolder.GAME_NAME, getString(R.string.pptt_test));
        intent.putExtra(IntentHolder.RUNS, (correctAnswers + wrongAnswers));
        intent.putExtra(IntentHolder.CORRECT_ANSWERS, correctAnswers);
        intent.putExtra(IntentHolder.WRONG_ANSWERS, wrongAnswers);

        finish();
        startActivity(intent);
    }
}