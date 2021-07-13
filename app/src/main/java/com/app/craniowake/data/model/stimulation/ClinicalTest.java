package com.app.craniowake.data.model.stimulation;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(tableName = "clinical_test_table")
public class ClinicalTest {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @Embedded
    private MotorFunction motorFunction;

    @Embedded(prefix = "front_pain_left_")
    private SensoryFunction frontPainLeft;
    @Embedded(prefix = "front_pain_right_")
    private SensoryFunction frontPainRight;
    @Embedded(prefix = "back_pain_left_")
    private SensoryFunction backPainLeft;
    @Embedded(prefix = "back_pain_right_")
    private SensoryFunction backPainRight;
    @Embedded(prefix = "front_paraesthesia_left_")
    private SensoryFunction frontParaesthesiaLeft;
    @Embedded(prefix = "front_paraesthesia_right_")
    private SensoryFunction frontParaesthesiaRight;
    @Embedded(prefix = "back_paraesthesia_left_")
    private SensoryFunction backParaesthesiaLeft;
    @Embedded(prefix = "back_paraesthesia_right_")
    private SensoryFunction backParaesthesiaRight;
    @Embedded(prefix = "front_anaesthesia_left_")
    private SensoryFunction frontAnaesthesiaLeft;
    @Embedded(prefix = "front_anaesthesia_right_")
    private SensoryFunction frontAnaesthesiaRight;
    @Embedded(prefix = "back_anaesthesia_left_")
    private SensoryFunction backAnaesthesiaLeft;
    @Embedded(prefix = "back_anaesthesia_right_")
    private SensoryFunction backAnaesthesiaRight;


    public ClinicalTest() {
        this.motorFunction = new MotorFunction();

        this.frontPainLeft = new SensoryFunction();
        this.frontPainRight = new SensoryFunction();
        this.backPainLeft = new SensoryFunction();
        this.backPainRight = new SensoryFunction();
        this.frontParaesthesiaLeft = new SensoryFunction();
        this.frontParaesthesiaRight = new SensoryFunction();
        this.backParaesthesiaLeft = new SensoryFunction();
        this.backParaesthesiaRight = new SensoryFunction();
        this.frontAnaesthesiaLeft = new SensoryFunction();
        this.frontAnaesthesiaRight = new SensoryFunction();
        this.backAnaesthesiaLeft = new SensoryFunction();
        this.backAnaesthesiaRight = new SensoryFunction();
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class MotorFunction {

        public MotorFunction() {
            latentArmPalsy = false;
            latentLegPalsy = false;

            upperExtremityRightShoulderAbduction = 5.0f;
            upperExtremityLeftShoulderAbduction = 5.0f;
            upperExtremityRightElbowFlexion = 5.0f;
            upperExtremityLeftElbowFlexion = 5.0f;
            upperExtremityRightElbowExtension = 5.0f;
            upperExtremityLeftElbowExtension = 5.0f;
            upperExtremityRightFingerExtension = 5.0f;
            upperExtremityLeftFingerExtension = 5.0f;
            upperExtremityRightFingerFlexion = 5.0f;
            upperExtremityLeftFingerFlexion = 5.0f;

            lowerExtremityRightHipFlexion = 5.0f;
            lowerExtremityLeftHipFlexion = 5.0f;
            lowerExtremityRightKneeFlexion = 5.0f;
            lowerExtremityLeftKneeFlexion = 5.0f;
            lowerExtremityRightKneeExtension = 5.0f;
            lowerExtremityLeftKneeExtension = 5.0f;
            lowerExtremityRightAnkleDorsiflexion = 5.0f;
            lowerExtremityLeftAnkleDorsiflexion = 5.0f;
            lowerExtremityRightAnklePlantarflexion = 5.0f;
            lowerExtremityLeftAnklePlantarflexion = 5.0f;
        }

        Boolean latentArmPalsy;
        Boolean latentLegPalsy;

        Float upperExtremityRightShoulderAbduction;
        Float upperExtremityLeftShoulderAbduction;

        Float upperExtremityRightElbowFlexion;
        Float upperExtremityLeftElbowFlexion;

        Float upperExtremityRightElbowExtension;
        Float upperExtremityLeftElbowExtension;

        Float upperExtremityRightFingerExtension;
        Float upperExtremityLeftFingerExtension;

        Float upperExtremityRightFingerFlexion;
        Float upperExtremityLeftFingerFlexion;


        Float lowerExtremityRightHipFlexion;
        Float lowerExtremityLeftHipFlexion;

        Float lowerExtremityRightKneeFlexion;
        Float lowerExtremityLeftKneeFlexion;

        Float lowerExtremityRightKneeExtension;
        Float lowerExtremityLeftKneeExtension;

        Float lowerExtremityRightAnkleDorsiflexion;
        Float lowerExtremityLeftAnkleDorsiflexion;

        Float lowerExtremityRightAnklePlantarflexion;
        Float lowerExtremityLeftAnklePlantarflexion;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class SensoryFunction {

        public SensoryFunction() {
            handInner = false;
            handOuter = false;
            feetInner = false;
            feetOuter = false;
            lowerLegInner = false;
            lowerLegMiddle = false;
            lowerLegOuter = false;
            thighInner = false;
            thighMiddle = false;
            thighOuter = false;
            forearmInner = false;
            forearmMiddle = false;
            forearmOuter = false;
            upperArmInner = false;
            upperArmMiddle = false;
            upperArmOuter = false;
        }

        Boolean handInner;
        Boolean handOuter;
        Boolean feetInner;
        Boolean feetOuter;
        Boolean lowerLegInner;
        Boolean lowerLegMiddle;
        Boolean lowerLegOuter;
        Boolean thighInner;
        Boolean thighMiddle;
        Boolean thighOuter;
        Boolean forearmInner;
        Boolean forearmMiddle;
        Boolean forearmOuter;
        Boolean upperArmInner;
        Boolean upperArmMiddle;
        Boolean upperArmOuter;
    }
}
