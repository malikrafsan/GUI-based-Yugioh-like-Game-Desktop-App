package com.aetherwars.controller;

import com.aetherwars.model.GameState;
import com.aetherwars.model.Phase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Observable;

public class PhaseController {
    @FXML private Label labelDraw;
    @FXML private Label labelPlan;
    @FXML private Label labelAttack;
    @FXML private Label labelEnd;
    private Label[] labels = new Label[4];
    private AppController appController;

    private final String inactivePhase = "-fx-background-color: rgb(240,236,236); -fx-border-color: black;";
    private final String activePhase = "-fx-background-color: orange; -fx-border-color: black;";

//    private Phase currentPhase;


    @FXML
    public void initialize(){
        labels[0] = labelDraw;
        labels[1] = labelPlan;
        labels[2] = labelAttack;
        labels[3] = labelEnd;
        //currentPhase = Phase.DRAW;
        setActiveLabel(labelDraw);
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
        setPhase(Phase.ATTACK);
    }

    public void update(Observable obs, Object obj) {
        if (obs instanceof GameState) {
            GameState gs = (GameState) obs;
            Phase ps = gs.getPhase();

            this.setPhase(ps);
        }
    }
}
