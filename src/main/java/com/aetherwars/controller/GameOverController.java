package com.aetherwars.controller;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class GameOverController {
    @FXML private Pane pane;
    private FadeTransition fadeIn;
    private FadeTransition fadeOut;
    private ScaleTransition scaleIn;
    private ScaleTransition scaleOut;


    @FXML
    public void initialize() {
        fadeIn = new FadeTransition();
        fadeIn.setNode(pane);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setDuration(Duration.millis(500));
        scaleIn = new ScaleTransition();
        scaleIn.setNode(pane);
        scaleIn.setByX(0.8);
        scaleIn.setByY(0.8);
        scaleIn.setToX(1);
        scaleIn.setToY(1);

        this.pane.setDisable(true);
        this.pane.setVisible(false);
        this.pane.setMouseTransparent(true);
    }




    /**
     * Memuncuklan pane game over
     */
    public void showGameOverPane(){
        this.pane.setVisible(true);
        this.pane.setDisable(false);
        this.pane.setMouseTransparent(false);
        this.pane.getParent().setMouseTransparent(false);
        fadeIn.play();
        scaleIn.play();
    }

}
