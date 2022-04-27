package com.aetherwars.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.aetherwars.model.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;

import java.io.InputStream;
import java.util.List;

public class CardInfoController {
    public Label card_name;
    public Label card_desc;
    public ImageView card_img;
    public Label attr1Label;
    public Label attr2Label;
    public Label attr3Label;
    public Label attr4Label;
    public Label attr5Label;
    private Label attrLabel[];

    public void setCardInfo(Card card){
        List<Pair<String,String>> cardInfo = card.displayInfo();

        this.card_name.setText(cardInfo.get(0).getValue());
        this.card_desc.setText(cardInfo.get(2).getValue());

        Integer attrCount = cardInfo.size() - 2;
        for (int i = 0; i < attrCount; i++){
            attrLabel[i].setText(cardInfo.get(i+2).getKey() + " : " + cardInfo.get(i+2).getValue());
        }
        for (int i = attrCount; i < 5; i++){
            attrLabel[i].setText("");
        }
        try {
            InputStream inputStream = getClass().getResourceAsStream(card.getImagePath());
            Image cardImage  = new Image(inputStream);
            this.card_img.setImage(cardImage);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void unsetCardInfo(){
        this.card_desc.setText("");
        this.card_name.setText("");
        for (int i = 0; i < 5; i++){
            attrLabel[i].setText("");
        }
        try {
            this.card_img.setImage(null);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }


    @FXML public void initialize(){
        this.attrLabel = new Label[]{attr1Label, attr2Label, attr3Label, attr4Label, attr5Label};
    }


}
