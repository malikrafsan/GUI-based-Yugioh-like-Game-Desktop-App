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

    /**
     * @brief Method untuk menambahkan sebuah kartu ke handcard
     * @param card Kartu yang akan ditambahkan pada handcard
     */
    public void addCard(Card card) {
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

    /**
     * @brief Method untuk menambahkan sebuah kartu ke handcard pada suatu posisi tertentu
     * @param card Kartu yang akan ditambahkan pada handcard
     * @param idx index yang akan diisi
     */
    public void addCard(Card card, int idx) {
        this.handCard[idx] = card;
        this.size++;
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * @param idx index kartu
     * @return Mengembalikan kartu pada handcard dengan index yang bersesuaian
     */
    public Card getCard(int idx) {
        return this.handCard[idx];
    }

    /**
     * @param idx index kartu
     * @return Mengembalikan kartu pada handcard dengan mengambilnya
     */
    public Card takeCard(int idx) {
        Card res = this.handCard[idx];
        this.handCard[idx] = null;
        this.size--;
        this.setChanged();
        this.notifyObservers();
        return res;
    }

    /**
     * @return Mengembalikan ukuran handcard
     */
    public int getSize() {
        return this.size;
    }

    /**
     * @brief Method untuk melakukan sinkronisasi
     */
    public void sync() {
        System.out.println("\n\nSYNCRONIZED\n\n");
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * @return Mengembalikan kartu-kartu yang ada di hand
     */
    public Card[] getCards() {
        return this.handCard;
    }
}