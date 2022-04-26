package com.aetherwars.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class HandCardController {
    @FXML private ImageView cardImageView;
    @FXML private Label labelMana;
    @FXML private Label labelAttr;

    private final String IMG_DIR_PATH = "/com/aetherwars/card/image/";


    public void setLabelMana(Integer value){
        this.labelMana.setText("MANA " + value.toString());
    }

    public void setLabelAttr(String value){
        this.labelAttr.setText(value);
    }

    public void setCardImageView(String imagePath){
        try {
            File file = new File(getClass().getResource(this.IMG_DIR_PATH + imagePath).toURI());
            Image cardImage  = new Image(file.toURI().toString());
            this.cardImageView.setImage(cardImage);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

}
