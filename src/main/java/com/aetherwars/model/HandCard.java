package com.aetherwars.model;
import java.util.*;

public class HandCard extends Observable {
    private Card[] handCard;
    private int capacity;
    private int size;

    public HandCard() {
        this.capacity = 5;
        this.size = 0;
        this.handCard = new Card[this.capacity];
        for (int i = 0; i < this.capacity; i++) {
            this.handCard[i] = null;
        }
        // this.handCard[4] = new SpellMorphCard(2, "Sugondese", "...",
        //         "card/image/spell/morph/Sugondese.png", 7, 2);
        // this.handCard[2] = new SpellPotionCard(1, "Sadikin Elixir", "The best elixir in the world",
        // "card/image/spell/potion/Sadikin Elixir.png", 3, 5, 1, 5);
        // randomizeHandCard();
    }

    public void randomizeHandCard() {
        Random rand = new Random();
        CharType[] types = new CharType[] {CharType.END, CharType.OVERWORLD, CharType.NETHER};
        String[] imgPaths = new String[]{"card/image/spell/morph/Sugondese.png", "card/image/spell/potion/Sadikin Elixir.png", "card/image/character/Shulker.png", "card/image/character/Zombie.png", "card/data/image/character/Shulker.png"};

        for (int i = 0; i < this.handCard.length; i++) {
            if (rand.nextDouble() > 0.5) {
                if (rand.nextDouble() > 0.5) {
                    this.handCard[i] = (new CharacterCard(rand.nextInt(10), "INI NAMA" + rand.nextInt(10), types[rand.nextInt(types.length)], "...", imgPaths[rand.nextInt(imgPaths.length)], rand.nextInt(10), rand.nextInt(10), rand.nextInt(10), rand.nextInt(10), rand.nextInt(10)));
                } else {
                    if (rand.nextDouble() > 0.5) {
                        this.handCard[i] = (new SpellMorphCard(2, "Sugondese", "...", "card/image/spell/morph/Sugondese.png", 7, 2));
                    }else {
                        this.handCard[i] = (new SpellPotionCard(1, "Sadikin Elixir", "...", "card/image/spell/potion/Sadikin Elixir.png", 3, 5, 1, 5));
                    }
                } 
            }
        }
    }

    public void addCard(Card card) {
        if (this.size == 5) {
            // throw exception untuk lanjut ke pembuangan
        }

        for (int i=0; i<5; i++) {
            if (this.handCard[i] == null) {
                this.handCard[i] = card;
                this.size++;
                this.setChanged();
                this.notifyObservers();
                break;
            }
        }
    }

    public void addCard(Card card, int idx) {
        this.handCard[idx] = card;
        this.size++;
        this.setChanged();
        this.notifyObservers();
    }

    public Card getCard(int idx) {
        return this.handCard[idx];
    }

    public Card takeCard(int idx) {
        Card res = this.handCard[idx];
        this.handCard[idx] = null;
        this.size--;
        this.setChanged();
        this.notifyObservers();
        return res;
    }

    public int getSize() {
        return this.size;
    }

    // Just for debugging, deprecated soon
    public void getInfo() {
        for (int i=0; i<5; i++){
            if (this.handCard[i] == null){
                System.out.println(i + " Empty");
            } else {
                System.out.println(i + " " + this.handCard[i].getName());
            }
        }
    }

    public void sync() {
        System.out.println("\n\nSYNCRONIZED\n\n");
        this.setChanged();
        this.notifyObservers();
    }

    public Card[] getCards() {
        return this.handCard;
    }
}