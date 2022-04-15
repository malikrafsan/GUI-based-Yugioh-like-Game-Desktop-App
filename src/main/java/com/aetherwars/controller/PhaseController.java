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

    private final String inactiveState = "-fx-background-color: rgb(240,236,236); -fx-border-color: black;";
    private final String activeState = "-fx-background-color: orange; -fx-border-color: black;";
    enum State {
        DRAW,
        PLAN,
        ATTACK,
        END
    }
    private State currentState;


    @FXML
    public void initialize(){
        labels[0] = labelDraw;
        labels[1] = labelPlan;
        labels[2] = labelAttack;
        labels[3] = labelEnd;
        currentState = State.DRAW;
        setActiveLabel(labelDraw);
    }

    private void setActiveLabel(Label labelToSet){
        for (Label label:labels){
            if (label == labelToSet){
                label.styleProperty().setValue(activeState);
            }
            else {
                label.styleProperty().setValue(inactiveState);
            }
        }
    }

    private void setNextState() {
        switch (currentState) {
            case DRAW:
                setActiveLabel(labelPlan);
                currentState = State.PLAN;
                break;
            case PLAN:
                setActiveLabel(labelAttack);
                currentState = State.ATTACK;
                break;
            case ATTACK:
                setActiveLabel(labelEnd);
                currentState = State.END;
                break;
            case END:
                setActiveLabel(labelDraw);
                currentState = State.DRAW;
                break;
        }
    }

    @FXML
    private void nextState(ActionEvent e){
        setNextState();
    }

}
