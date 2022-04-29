package com.aetherwars.controller;

import com.aetherwars.interfaces.IPhaseGetter;
import com.aetherwars.model.*;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.*;

import com.aetherwars.interfaces.*;
import com.aetherwars.event.PickCardEvent;
import javafx.util.Duration;

import static com.aetherwars.model.Phase.*;

public class SelectCardController implements Observer, ISubscriber {
    @FXML private Pane pane;
    @FXML private HBox cardContainer;
    @FXML private Label playerLabel;
    private Pane[] cardsToSelect = new Pane[3];
    private HandCardController[] cardController = new HandCardController[3];
    private Integer currentSelectCardCount;
    private final Background hoverBackground = new Background(new BackgroundFill(Color.LIGHTCORAL, null, null));
    private final Background normalBackground = new Background(new BackgroundFill(null, null, null));
    private FadeTransition fadeIn;
    private FadeTransition fadeOut;
    private ScaleTransition scaleIn;
    private ScaleTransition scaleOut;

    /**
     * Melakukan inisiasi GUI
     */
    @FXML
    public void initialize() {
        GameManager.getInstance().getEventBroker().addSubscriber("PICKCARD", this);
        GameManager.getInstance().addObserver("GAMESTATE", this);

        try {
            SelectCardController THIS = this;
            for (int i=0; i<3; i++){
                FXMLLoader loaderHandCard = new FXMLLoader(getClass().getResource("/view/HandCard.fxml"));
                this.cardsToSelect[i] = loaderHandCard.load();
                this.cardController[i] = loaderHandCard.getController();
                this.cardContainer.getChildren().add(this.cardsToSelect[i]);
                int finalI = i + 1;
                this.cardsToSelect[i].setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        cardsToSelect[finalI - 1].setBackground(hoverBackground);
                    }
                });
                this.cardsToSelect[i].setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        cardsToSelect[finalI - 1].setBackground(normalBackground);
                    }
                });
                this.cardsToSelect[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        System.out.println("CARD " + finalI + " IS CLICKED");
                        // TODO : CALL GAME MANAGER FOR PICKING CARD
                        GameManager.getInstance().clickPickCard(finalI - 1);
                    }
                });
            }
            this.currentSelectCardCount = 3;
        } catch (Exception e) {
            System.out.println("SELECT CARD CONTROLLER ERROR");
            System.out.println(e);
        }
        // testUpdateHandCards();
        setPlayer(1);
        initializeTransition();


    }


    /**
     * Menginisiasi animasi transisi
     */
    private void initializeTransition(){
        fadeIn = new FadeTransition();
        fadeIn.setNode(pane);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setDuration(Duration.millis(500));
        fadeOut = new FadeTransition();
        fadeOut.setNode(pane);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setDuration(Duration.millis(500));
        scaleIn = new ScaleTransition();
        scaleIn.setNode(pane);
        scaleIn.setByX(0.8);
        scaleIn.setByY(0.8);
        scaleIn.setToX(1);
        scaleIn.setToY(1);
        scaleIn.setDuration(Duration.millis(300));
        scaleOut = new ScaleTransition();
        scaleOut.setNode(pane);
        scaleOut.setByX(1);
        scaleOut.setByY(1);
        scaleOut.setToX(0.8);
        scaleOut.setToY(0.8);
        scaleOut.setDuration(Duration.millis(300));
    }

    /**
     * @param playerNo nomor player
     * Mengeset nomor player
     */
    public void setPlayer(Integer playerNo){
        this.playerLabel.setText("PLAYER " + playerNo.toString());
    }

    private void testUpdateHandCards(){
        ArrayList<Card> cards = new ArrayList<>();
        //cards.add(new CharacterCard(1, "Shulker", CharType.END, "...", "/com/aetherwars/card/image/character/Shulker.png", 10, 5, 3, 4, 3));
        //cards.add(new CharacterCard(2, "Zombie", CharType.OVERWORLD, "...", "/com/aetherwars/card/image/character/Zombie.png", 8, 4, 1, 4, 3));
        cards.add(new SpellMorphCard(2, "Sugondese", "...", "/com/aetherwars/card/image/spell/morph/Sugondese.png", 7, 2));
        cards.add(new SpellPotionCard(1, "Sadikin Elixir", "...", "/com/aetherwars/card/image/spell/potion/Sadikin Elixir.png", 3, 5, 1, 5));
        //cards.add(new CharacterCard(1, "Shulker", CharType.END, "...", "card/data/image/character/Shulker.png", 10, 5, 2, 4, 3));
        updateSelectCards(cards);
    }


    /**
     * @param listCards kartu untuk dipilih
     * Memperbarui kartu yang bisa dipilih
     */
    public void updateSelectCards(List<Card> listCards) {
        for (int i = 0; i < this.currentSelectCardCount; i++) {
            this.cardContainer.getChildren().remove(0);
        }

        this.currentSelectCardCount = Math.min(listCards.size(), 3);
        for (int i = 0; i < this.currentSelectCardCount; i++) {
            this.cardContainer.getChildren().add(this.cardsToSelect[i]);
            this.cardController[i].setLabelMana(listCards.get(i).getMana());
            this.cardController[i].setLabelAttr(listCards.get(i).preview());
            this.cardController[i].setCardImageView(listCards.get(i).getImagePath());
            this.cardController[i].fadeIn();
        }
    }


    /**
     * @param event event
     * Merespons event yang didapat
     */
    public void onEvent(IEvent event) {
        if (event instanceof PickCardEvent) {
            System.out.println("PICK CARD EVENT ON SELECT CARD");
            PickCardEvent pickCardEvent = (PickCardEvent) event;
            updateSelectCards(pickCardEvent.getData());
            drawPhaseSelectCard();
        }
    }

    /**
     * @param obs observable
     * @param obj object
     * Merespons terjadinya update pada observable
     */
    public void update(Observable obs, Object obj) {
        if (obs instanceof IPhaseGetter) {
            IPhaseGetter gs = (IPhaseGetter) obs;
            if (gs.getPhase() == DRAW && !gs.getHasPickCard()) {
                System.out.println("HERE");
                // drawPhaseSelectCard();
            } else {
                System.out.println("THERE");
                nonDrawPhaseSelectCard();
            }
        }
    }

    /**
     * Menampilkan pane draw phase
     */
    private void drawPhaseSelectCard() {
        this.pane.setVisible(true);
        this.pane.setDisable(false);
        this.pane.setMouseTransparent(false);
        this.pane.getParent().setMouseTransparent(false);
        fadeIn.play();
        scaleIn.play();
    }

    /**
     * Menyembunyikan pane draw phase
     */
    private void nonDrawPhaseSelectCard() {
        scaleOut.play();
        fadeOut.play();
        this.pane.setDisable(true);
        this.pane.setVisible(false);
//        this.boardPane.setEffect(null);
        this.pane.setMouseTransparent(true);
        this.pane.getParent().setMouseTransparent(true);
    }
}

class MockGameStateSelectCard extends Observable implements IPhaseGetter {
    private Phase phase;
    private boolean hasPickCard;

    public Phase getPhase() { return this.phase; }

    public void setPhase(Phase newPhase) {
        this.phase = newPhase;
        setChanged();
        notifyObservers();
    }

    public boolean getHasPickCard() {
        return this.hasPickCard;
    }
}

class MockDeck implements IPublisher {
    List<Card> deck;
//    private EventBroker eb;

    public MockDeck() {
        this.deck = new ArrayList<Card>();
//        this.eb = GameManager.getInstance().getEventBroker();
    }

    public void pickCard() {
        this.publish("PICKCARD", new PickCardEvent(this.getThreeCard()));
    }

    public void publish(String topic, IEvent event) {
        GameManager.getInstance().getEventBroker().sendEvent(topic, event);
    }

    private List<Card> getThreeCard() {
        List<Card> lst = new ArrayList<>();

        Random rand = new Random();
        CharType[] types = new CharType[] {CharType.END, CharType.OVERWORLD, CharType.NETHER};
        String[] imgPaths = new String[]{"/com/aetherwars/card/image/spell/morph/Sugondese.png", "/com/aetherwars/card/image/spell/potion/Sadikin Elixir.png", "/com/aetherwars/card/image/character/Shulker.png", "/com/aetherwars/card/image/character/Zombie.png", "card/data/image/character/Shulker.png"};

        for (int i=0;i<3;i++) {
            if (rand.nextDouble() > 0.5) {
                lst.add(new CharacterCard(rand.nextInt(10), "INI NAMA" + rand.nextInt(10), types[rand.nextInt(types.length)], "...", imgPaths[rand.nextInt(imgPaths.length)], rand.nextInt(10), rand.nextInt(10), rand.nextInt(10), rand.nextInt(10), rand.nextInt(10)));
            } else {
                if (rand.nextDouble() > 0.5) {
                    lst.add(new SpellMorphCard(2, "Sugondese", "...", "/com/aetherwars/card/image/spell/morph/Sugondese.png", 7, 2));
                }else {
                    lst.add(new SpellPotionCard(1, "Sadikin Elixir", "...", "/com/aetherwars/card/image/spell/potion/Sadikin Elixir.png", 3, 5, 1, 5));
                }
            }
        }

        return lst;
    }
}