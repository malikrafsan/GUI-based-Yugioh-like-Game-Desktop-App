package com.aetherwars.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;

import java.util.*;
import java.io.File;

import com.gluonhq.charm.glisten.control.ProgressBar;

import com.aetherwars.interfaces.*;
import com.aetherwars.model.*;

public class PlayerBoardController implements Observer {
    @FXML
    private Label playerLabel;
    @FXML
    private AnchorPane playerBoardContainer;
    @FXML
    private ImageView playerImageView;
    @FXML
    private ImageView activeCard1;
    @FXML
    private ImageView activeCard2;
    @FXML
    private ImageView activeCard3;
    @FXML
    private ImageView activeCard4;
    @FXML
    private ImageView activeCard5;
    @FXML
    private ImageView[] activeCards;
    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label atkLbl1;
    @FXML
    private ImageView atkIcon1;
    @FXML
    private Label dfnLbl1;
    @FXML
    private ImageView dfnIcon1;
    @FXML
    private Label expLvlLbl1;
    @FXML
    private Label atkLbl2;
    @FXML
    private ImageView atkIcon2;
    @FXML
    private Label dfnLbl2;
    @FXML
    private ImageView dfnIcon2;
    @FXML
    private Label expLvlLbl2;
    @FXML
    private Label atkLbl3;
    @FXML
    private ImageView atkIcon3;
    @FXML
    private Label dfnLbl3;
    @FXML
    private ImageView dfnIcon3;
    @FXML
    private Label expLvlLbl3;
    @FXML
    private Label atkLbl4;
    @FXML
    private ImageView atkIcon4;
    @FXML
    private Label dfnLbl4;
    @FXML
    private ImageView dfnIcon4;
    @FXML
    private Label expLvlLbl4;
    @FXML
    private Label atkLbl5;
    @FXML
    private ImageView atkIcon5;
    @FXML
    private Label dfnLbl5;
    @FXML
    private ImageView dfnIcon5;
    @FXML
    private Label expLvlLbl5;
    @FXML
    private ImageView[] atkIcons;
    @FXML
    private ImageView[] dfnIcons;
    @FXML
    private Label[] atkLbls;
    @FXML
    private Label[] dfnLbls;
    @FXML
    private Label[] expLvlLbls;

    private int ID_BOARD;
    private final int MAX_HEALTH_PLAYER = 80;
    private final String IMG_DIR_PATH = "/com/aetherwars/card/image/";

    private final String inactiveCardStyle = "-fx-border-color: black; -fx-border-width: 1px; -fx-border-radius: 16;";
    private final String activeCardStyle = "-fx-border-color: red; -fx-border-width: 3px; -fx-border-radius: 16;";
    private final String activePlayerCardStyle = "-fx-border-color: yellow; -fx-border-width: 3px; -fx-border-radius: 16;";

    @FXML
    private void initialize() {
        this.ID_BOARD = Integer.parseInt(this.playerBoardContainer.getId());

        GameManager.getInstance().addObserver("PLAYER" + this.ID_BOARD, this);
        GameManager.getInstance().addObserver("CLICKOBJECT", this);
        GameManager.getInstance().addObserver("ACTIVECHAR" + this.ID_BOARD, this);
        GameManager.getInstance().addObserver("GAMESTATE", this);

        this.activeCards = new ImageView[] { activeCard1, activeCard2, activeCard3, activeCard4, activeCard5 };

        String playerImgPath;
        if (ID_BOARD == 1){
            playerImgPath = "player/steve.jpeg";
        }
        else {
            playerImgPath = "player/alex.jpeg";
        }

        //        String[] cardImgPaths = new String[]{"character/Zombie.png", null, "character/Skeleton.png", null, "character/Ghast.png"};

        int boardID = this.ID_BOARD;
        this.playerImageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("PLAYER " + boardID + " ICON MOUSE ENTER EVENT DETECTED");
            }
        });
        this.playerImageView.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("PLAYER " + boardID + " ICON MOUSE EXIT EVENT DETECTED");
            }
        });
        PlayerBoardController p = this;
        this.playerImageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("PLAYER " + boardID + " ICON MOUSE CLICK EVENT DETECTED");

                GameManager.getInstance().click(boardID, "PLAYER", 0);
            }
        });

        try {
            File file = new File(getClass().getResource(this.IMG_DIR_PATH + playerImgPath).toURI());
            Image playerImg = new Image(file.toURI().toString());
            this.playerImageView.setImage(playerImg);
        } catch (Exception e) {
            System.out.println("PLAYER BOARD CONTROLLER ERROR");
            System.out.println(e);
        }

        this.atkLbls = new Label[] { atkLbl1, atkLbl2, atkLbl3, atkLbl4, atkLbl5 };
        this.dfnLbls = new Label[] { dfnLbl1, dfnLbl2, dfnLbl3, dfnLbl4, dfnLbl5 };
        this.atkIcons = new ImageView[] { atkIcon1, atkIcon2, atkIcon3, atkIcon4, atkIcon5 };
        this.dfnIcons = new ImageView[] { dfnIcon1, dfnIcon2, dfnIcon3, dfnIcon4, dfnIcon5 };
        this.expLvlLbls = new Label[] { expLvlLbl1, expLvlLbl2, expLvlLbl3, expLvlLbl4, expLvlLbl5 };

        for (int i = 0; i < this.activeCards.length; i++) {
            int finalI = i;
            int boardId = this.ID_BOARD;
            Pane pane = (Pane) this.activeCards[i].getParent().getParent();

            ImageView cardImg = this.activeCards[i];
            this.activeCards[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("CARD " + (finalI + 1) + " FROM PLAYER " + boardId + " IS CLICKED");

                    if (cardImg.getImage() != null) {
                        GameManager.getInstance().click(boardId, "ACTIVECHAR", finalI);
                    }
                }
            });

            this.activeCards[i].setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (cardImg.getImage() != null) {
                        Background bg = new Background(new BackgroundFill(Color.MEDIUMVIOLETRED, null, null));
                        pane.setBackground(bg);
                        System.out.println("CARD " + (finalI + 1) + " FROM PLAYER " + boardId + " IS HOVERED");
    
                        GameManager.getInstance().hoverBoard(finalI,boardID);
                    }
                }
            });

            this.activeCards[i].setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (cardImg.getImage() != null) {
                        Background bg = new Background(new BackgroundFill(null, null, null));
                        pane.setBackground(bg);
                        System.out.println("CARD " + (finalI + 1) + " NO LONGER HOVERED");
    
                        GameManager.getInstance().unhover();
                    }
                }
            });
        }

        int health = 50;
        int maxHealth = 80;
        this.updateProgressBar(health, maxHealth);
    }
    
    private void setCardClickEffect(int idx) {
        Pane pane = (Pane) this.activeCards[idx].getParent().getParent();
        pane.styleProperty().setValue(this.activeCardStyle);
    }

    private void unsetCardClickEffect(int idx) {
        Pane pane = (Pane) this.activeCards[idx].getParent().getParent();
        pane.styleProperty().setValue(this.inactiveCardStyle);
    }

    private void unsetAllCardClickEffect() {
        for (int i = 0; i < this.activeCards.length; i++) {
            unsetCardClickEffect(i);
        }
    }

    private void setPlayerCardTurnEffect() {
        Pane pane = (Pane) this.playerImageView.getParent();
        pane.styleProperty().setValue(this.activePlayerCardStyle);
    }

    private void unsetPlayerCardTurnEffect() {
        Pane pane = (Pane) this.playerImageView.getParent();
        pane.styleProperty().setValue(this.inactiveCardStyle);
    }

    private void updateProgressBar(double health, int maxHealth) {
        this.progressBar.setProgress((double) health / maxHealth);
    }

    private void updateActiveChars(IActiveCharGetter[] lst) {
        try {
            File fileAtkIcon = new File(getClass().getResource(this.IMG_DIR_PATH + "/active_char/sword.png").toURI());
            Image atkImg = new Image(fileAtkIcon.toURI().toString());
            File fileDfnIcon = new File(getClass().getResource(this.IMG_DIR_PATH + "/active_char/shield.png").toURI());
            Image dfnImg = new Image(fileDfnIcon.toURI().toString());

            for (int i=0;i<5;i++) {
                IActiveCharGetter m = lst[i];

                if (m == null) {
                    System.out.println("NULL");
                    this.atkIcons[i].setImage(null);
                    this.dfnIcons[i].setImage(null);
                    this.atkLbls[i].setText("");
                    this.dfnLbls[i].setText("");
                    this.activeCards[i].setImage(null);
                    this.expLvlLbls[i].setText("");
                } else {
                    File file = new File(getClass().getResource(this.IMG_DIR_PATH + m.getImagePath()).toURI());
                    Image activeCardImg = new Image(file.toURI().toString());
                    this.atkIcons[i].setImage(atkImg);
                    this.dfnIcons[i].setImage(dfnImg);
                    this.atkLbls[i].setText(Double.toString(m.getAttack()));
                    this.dfnLbls[i].setText(Double.toString(m.getHealth()));
                    this.activeCards[i].setImage(activeCardImg);
                    this.expLvlLbls[i].setText(Integer.toString(m.getExp()) + "/" + Integer.toString(m.getExpUp()) + " [" + Integer.toString(m.getLevel()) + "]");
                }
            }
        } catch (Exception e) {
            System.out.println("PLAYER BOARD CONTROLLER ERROR");
            System.out.println(e);
        }
    }

    private void setPlayerLabel(double hp) {
        double roundOff = Math.round(hp * 100.0) / 100.0;
        if (this.ID_BOARD == 1){
            this.playerLabel.setText("Steve " + roundOff);
        }
        else {
            this.playerLabel.setText("Alex " + roundOff);
        }
    }

    private MockActiveChar[] mockActiveCharsData() {
        String[] imagePaths = new String[]{"character/Zombie.png", "character/Skeleton.png", "character/Slime.png", "character/Warden.png", "character/Wither Skeleton.png"};

        MockActiveChar[] lst = new MockActiveChar[5];

        Random rand = new Random();
        for (int i=0;i<5;i++) {
            if (rand.nextDouble() > 0.5) {
                lst[i] = new MockActiveChar(rand.nextInt(10),rand.nextInt(10),rand.nextInt(10),rand.nextInt(10),rand.nextInt(10), imagePaths[rand.nextInt(imagePaths.length)]);
            } else {
                lst[i] = null;
            }
        }

        return lst;
    }

    public void update(Observable obs, Object obj) {
        if (obs instanceof IActiveCharObserverGetter) {
            this.updateActiveChars(((IActiveCharObserverGetter) obs).getChars());
        } else if (obs instanceof ClickObject) {
            ClickObject co = (ClickObject) obs;

            this.unsetAllCardClickEffect();
            if (co.getName().equals("ACTIVECHAR") && co.getPlayer() == this.ID_BOARD) {
                this.setCardClickEffect(co.getIndex());
            }
        } else if (obs instanceof GameState) {
            Turn[] turns = new Turn[]{Turn.PLAYER1, Turn.PLAYER2};
            GameState gs = (GameState) obs;

            if (gs.getTurn() == turns[this.ID_BOARD - 1]) {
                this.setPlayerCardTurnEffect();
            } else {
                this.unsetPlayerCardTurnEffect();
            }
        } else if (obs instanceof Player) {
            Player p = (Player) obs;

            this.updateProgressBar(p.getHealth(), this.MAX_HEALTH_PLAYER);
            this.setPlayerLabel(p.getHealth());
        }
    }
}

class MockActiveChar implements IActiveCharGetter {
    private int attack;
    private int health;
    private int exp;
    private int expUp;
    private int level;
    private String imagePath;

    public MockActiveChar(int attack, int health, int exp, int expUp, int level, String imagePath) {
        this.attack = attack;
        this.health = health;
        this.exp = exp;
        this.expUp = expUp;
        this.level = level;
        this.imagePath = imagePath;
    }

    public double getAttack() { return this.attack;}
    public double getHealth() {return this.health;}
    public int getExp() {return this.exp; }
    public int getExpUp() { return this.expUp; }
    public int getLevel() { return this.level; }
    public String getImagePath() { return this.imagePath; }
}