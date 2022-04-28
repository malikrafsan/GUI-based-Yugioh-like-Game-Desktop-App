package com.aetherwars.controller;

import com.aetherwars.interfaces.IPhaseGetter;
import com.aetherwars.model.GameState;
import com.aetherwars.model.Phase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import static com.aetherwars.model.Phase.*;
import static com.aetherwars.model.Phase.END;

public class PhaseController implements Observer {
    @FXML private Label labelDraw;
    @FXML private Label labelPlan;
    @FXML private Label labelAttack;
    @FXML private Label labelEnd;
    private Label[] labels = new Label[4];

    private final String inactivePhase = "-fx-background-color: rgb(240,236,236); -fx-border-color: black;";
    private final String activePhase = "-fx-background-color: orange; -fx-border-color: black;";

    //TODO : DELETE LATER
    private MockPhaseGetter mpg;


    @FXML
    public void initialize(){
        labels[0] = labelDraw;
        labels[1] = labelPlan;
        labels[2] = labelAttack;
        labels[3] = labelEnd;
        //currentPhase = Phase.DRAW;
        setActiveLabel(labelDraw);

        // TODO: DELETE LATER
        mpg = new MockPhaseGetter();
        mpg.addObserver(this);
    }

    private void setActiveLabel(Label labelToSet){
        for (Label label:labels){
            if (label == labelToSet){
                label.styleProperty().setValue(activePhase);
            }
            else {
                label.styleProperty().setValue(inactivePhase);
            }
        }
    }

//    public void setNextPhase() {
//        switch (currentPhase) {
//            case DRAW:
//                setActiveLabel(labelPlan);
//                currentPhase = Phase.PLANNING;
//                break;
//            case PLANNING:
//                setActiveLabel(labelAttack);
//                currentPhase = Phase.ATTACK;
//                break;
//            case ATTACK:
//                setActiveLabel(labelEnd);
//                currentPhase = Phase.END;
//                break;
//            case END:
//                setActiveLabel(labelDraw);
//                currentPhase = Phase.DRAW;
//
//                //testing
//                appController.drawPhase();
//
//                break;
//        }
//    }

    public void setPhase(Phase phase) {
        switch (phase) {
            case DRAW:
                setActiveLabel(labelDraw);
//                currentPhase = Phase.PLANNING;
                break;
            case PLANNING:
                setActiveLabel(labelPlan);
//                currentPhase = Phase.ATTACK;
                break;
            case ATTACK:
                setActiveLabel(labelAttack);
//                currentPhase = Phase.END;
                break;
            case END:
                setActiveLabel(labelEnd);
//                currentPhase = Phase.DRAW;

                //testing
//                appController.drawPhase();
                // TODO : use game manager
                break;
        }
    }



//    public void setAppController(AppController appController){
//        this.appController = appController;
//    }

    @FXML
    private void nextPhase(ActionEvent e){
        // TODO: call game manager
        Random rand = new Random();
        Phase[] lst = new Phase[]{DRAW, PLANNING, ATTACK, END};
        mpg.setPhase(lst[new Random().nextInt(lst.length)]);
        System.out.println("Button clicked");
    }




    public void update(Observable obs, Object obj) {
        if (obs instanceof IPhaseGetter) {
            IPhaseGetter pg = (IPhaseGetter) obs;
            System.out.println("Getting phase");
            Phase ps = pg.getPhase();
            this.setPhase(ps);
        }
    }

    //TODO : DELETE LATER
    private class MockPhaseGetter extends Observable implements IPhaseGetter{
        Phase currentPhase = Phase.DRAW;

        public Phase getPhase(){
            System.out.println("CURRENT PHASE :" + currentPhase);
            return currentPhase;
        }

        public void setPhase(Phase phase){
            currentPhase = phase;
            System.out.println("CHANGING PHASE TO :" + currentPhase);
            setChanged();
            notifyObservers();
        }
    }
}
