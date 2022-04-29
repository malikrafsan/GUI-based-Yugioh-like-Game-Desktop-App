package com.aetherwars.controller;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.File;
import java.io.InputStream;

public class HandCardController {
    @FXML private Pane cardPane;
    @FXML private ImageView cardImageView;
    @FXML private Label labelMana;
    @FXML private Label labelAttr;
    private FadeTransition fadeIn;
    private FadeTransition fadeOut;

    public void unsetLabelMana() {
        this.labelMana.setText("");
    }

    public void setLabelMana(Integer value){
        this.labelMana.setText("MANA " + value.toString());
    }

    public void unsetLabelAttr() {
        this.labelAttr.setText("");
    }

    public void setLabelAttr(String value){
        this.labelAttr.setText(value);
    }

    public void unsetCardImageView() {
        this.cardImageView.setImage(null);
    }

    public void setCardImageView(String imagePath){
        try {
            // InputStream inputStream = getClass().getResourceAsStream(imagePath);
            File file = new File(getClass().getResource("/com/aetherwars/" +imagePath).toURI());
            Image cardImage  = new Image(file.toURI().toString());
            this.cardImageView.setImage(cardImage);
        }
        catch (Exception e) {
            System.out.println("HAND CARD CONTROLLER ERROR");
            System.out.println("IMAGEPATH: " + imagePath);
            System.out.println(e);
        }
    }

    @FXML
    private void initialize(){
        fadeIn = new FadeTransition();
        fadeIn.setNode(cardPane);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setDuration(Duration.millis(750));
        fadeOut = new FadeTransition();
        fadeOut.setNode(cardPane);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setDuration(Duration.millis(750));
    }

    public void fadeIn(){
        fadeIn.play();
    }

    public void fadeOut(){
        fadeOut.play();
    }

}
