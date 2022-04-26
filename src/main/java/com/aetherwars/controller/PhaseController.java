package com.aetherwars.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PhaseController {
    @FXML private Label labelDraw;
    @FXML private Label labelPlan;
    @FXML private Label labelAttack;
    @FXML private Label labelEnd;
    private Label[] labels = new Label[4];
    private AppController appController;

    private final String inactivePhase = "-fx-background-color: rgb(240,236,236); -fx-border-color: black;";
    private final String activePhase = "-fx-background-color: orange; -fx-border-color: black;";
    enum Phase {
        DRAW,
        PLAN,
        ATTACK,
        END
    }
    private Phase currentPhase;


    @FXML
    public void initialize(){
        labels[0] = labelDraw;
        labels[1] = labelPlan;
        labels[2] = labelAttack;
        labels[3] = labelEnd;
        currentPhase = Phase.DRAW;
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

    public void setNextPhase() {
        switch (currentPhase) {
            case DRAW:
                setActiveLabel(labelPlan);
                currentPhase = Phase.PLAN;
                break;
            case PLAN:
                setActiveLabel(labelAttack);
                currentPhase = Phase.ATTACK;
                break;
            case ATTACK:
                setActiveLabel(labelEnd);
                currentPhase = Phase.END;
                break;
            case END:
                setActiveLabel(labelDraw);
                currentPhase = Phase.DRAW;
                appController.drawPhase();
                break;
        }
    }

    public void setAppController(AppController appController){
        this.appController = appController;
    }

    @FXML
    private void nextState(ActionEvent e){
        setNextPhase();
    }

}
