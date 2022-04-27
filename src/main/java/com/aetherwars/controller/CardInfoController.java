package com.aetherwars.controller;

import com.aetherwars.interfaces.Hoverable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.aetherwars.model.*;
import javafx.util.Pair;

import java.io.InputStream;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class CardInfoController implements Observer {
    public Label card_name;
    public Label card_desc;
    public ImageView card_img;
    public Label attr1Label;
    public Label attr2Label;
    public Label attr3Label;
    public Label attr4Label;
    public Label attr5Label;
    private Label attrLabel[];

    public void setCardInfo(Hoverable hoverable){
        List<Pair<String,String>> cardInfo = hoverable.displayInfo();

        this.card_name.setText(hoverable.getName());
        this.card_desc.setText(hoverable.getDesc());

        int attrCount = cardInfo.size();
        for (int i = 0; i < attrCount; i++){
            attrLabel[i].setText(cardInfo.get(i).getKey() + " : " + cardInfo.get(i).getValue());
        }
        for (int i = attrCount; i < 5; i++){
            attrLabel[i].setText("");
        }
        try {
            InputStream inputStream = getClass().getResourceAsStream(hoverable.getImagePath());
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

    public void update(Observable obs, Object obj){
        if (obs instanceof GameState){
            GameState gs = (GameState) obs;
            Hoverable h = gs.getHoverObject();
            if (h != null){
                setCardInfo(h);
            }
            else {
                unsetCardInfo();
            }
        }
    }


    @FXML public void initialize(){
        this.attrLabel = new Label[]{attr1Label, attr2Label, attr3Label, attr4Label, attr5Label};
    }
}
