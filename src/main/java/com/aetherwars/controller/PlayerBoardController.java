package com.aetherwars.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.File;
import java.util.Observer;
import java.util.Observable;
import javafx.scene.layout.HBox;
import com.gluonhq.charm.glisten.control.ProgressBar;


public class PlayerBoardController {
    @FXML private ImageView playerImageView;
    @FXML private ImageView activeCard1;
    @FXML private ImageView activeCard2;
    @FXML private ImageView activeCard3;
    @FXML private ImageView activeCard4;
    @FXML private ImageView activeCard5;
    @FXML private ImageView[] activeCards;
    @FXML private ProgressBar progressBar;

    private final String IMG_DIR_PATH = "/com/aetherwars/card/image/character/";

    @FXML private void initialize() {
        this.activeCards = new ImageView[] {activeCard1, activeCard2, activeCard3, activeCard4, activeCard5};
        String playerImgPath = "Villager.png";
        String[] cardImgPaths = new String[] {"Zombie.png", null, "Skeleton.png", null, "Ghast.png"};

        try {
            File file = new File(getClass().getResource(this.IMG_DIR_PATH + playerImgPath).toURI());
            Image playerImg = new Image(file.toURI().toString());
            this.playerImageView.setImage(playerImg);
        } catch (Exception e) {
            System.out.println(e);
        }

        for (int i=0;i<cardImgPaths.length;i++) {
            try {
                if (cardImgPaths[i] != null) {
                    File file = new File(getClass().getResource(this.IMG_DIR_PATH + cardImgPaths[i]).toURI());
                    Image activeCardImg = new Image(file.toURI().toString());
                    this.activeCards[i].setImage(activeCardImg);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

//        this.progressBar.setProgress(50);
        int health = 50;
        int maxHealth = 80;
        this.progressBar.setProgress((float) health / maxHealth);
//        this.progressBar.se
    }
}
