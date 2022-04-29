package com.aetherwars.controller;

import com.aetherwars.interfaces.IPhaseGetter;
import com.aetherwars.model.GameState;
import com.aetherwars.model.Phase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Observable;
import java.util.Observer;
public class PhaseController implements Observer {
    @FXML private Label labelDraw;
    @FXML private Label labelPlan;
    @FXML private Label labelAttack;
    @FXML private Label labelEnd;
    private Label[] labels = new Label[4];

    private final String inactivePhase = "-fx-background-color: rgb(240,236,236); -fx-border-color: black;";
    private final String activePhase = "-fx-background-color: orange; -fx-border-color: black;";

    @FXML
    public void initialize(){
        labels[0] = labelDraw;
        labels[1] = labelPlan;
        labels[2] = labelAttack;
        labels[3] = labelEnd;
        // setActiveLabel(labelDraw);
        GameManager.getInstance().addObserver("GAMESTATE", this);
    }

    /**
     * @param labelToSet label yang akan di set
     * Meset label yang aktif menjadi labelToSet
     */
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

    /**
     * @param phase phase yang akan di set
     * Mengeset phase sesuai input
     */
    public void setPhase(Phase phase) {
        switch (phase) {
            case DRAW:
                setActiveLabel(labelDraw);
                break;
            case PLANNING:
                setActiveLabel(labelPlan);
                break;
            case ATTACK:
                setActiveLabel(labelAttack);
                break;
            case END:
                setActiveLabel(labelEnd);
                break;
        }
    }

    /**
     * @param e event
     * Lanjut ke phase selanjutnya
     */
    @FXML
    private void nextPhase(ActionEvent e){
        GameManager.getInstance().nextPhase();
    }




    public void update(Observable obs, Object obj) {
        if (obs instanceof IPhaseGetter) {
            IPhaseGetter pg = (IPhaseGetter) obs;
            Phase ps = pg.getPhase();
            this.setPhase(ps);
        }
    }

    //TODO : DELETE LATER
    private class MockPhaseGetter extends Observable implements IPhaseGetter{
        Phase currentPhase = Phase.DRAW;
        boolean hasPickCard = false;

        public Phase getPhase(){
            System.out.println("CURRENT PHASE :" + currentPhase);
            return currentPhase;
        }

        public void setPhase(Phase phase){
            currentPhase = phase;
            setChanged();
            notifyObservers();
        }

        public boolean getHasPickCard() {
            return this.hasPickCard;
        }
    }
}
