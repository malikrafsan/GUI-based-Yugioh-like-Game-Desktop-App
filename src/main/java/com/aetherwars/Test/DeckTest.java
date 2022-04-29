package com.aetherwars.Test;

import com.aetherwars.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    // define card
    CharacterCard creeper = new CharacterCard(1, "Creeper", CharType.OVERWORLD, "A creeper is a common hostile mob that silently approaches players and explodes.", "card/image/creeper.png", 10, 2, 4, 1, 1);

    SpellMorphCard sheepify = new SpellMorphCard(301, "Sheepify","Turns any being into a measly sheep, even you.", "card/image/sheepify.png", 2, 4);

    SpellSwapCard catFood = new SpellSwapCard(201, "Cat Food", "Disclaimer: includes catnip", "card/image/catfood.png", 3, 3);

    SpellPotionCard deathlyMagic = new SpellPotionCard(101, "Deathly Magic", "The magic that is deadly", "card/image/spell/potion/Deathly Magic.png", 2, 2, 2, 2);

    SpellLevelCard levelplus = new SpellLevelCard(201, "Level Plus", "Increases your level by 1", "card/image/spell/level/Level Plus.png", 2, 1);


    @Test
    void addCardtoDeck() {
        Deck deck = new Deck();
        assertEquals(deck.getSize(), 0);
        deck.addCard(deathlyMagic);
        deck.addCard(levelplus);
        deck.addCard(catFood);
        assertEquals(deck.getSize(), 3);

    }

    @Test
    void getThreeCard() {
        Deck deck = new Deck();

        deck.addCard(deathlyMagic);
        deck.addCard(levelplus);
        deck.addCard(catFood);
        deck.addCard(creeper);
        deck.draw();

        List<Card> res = deck.getThreeCard();
        assertEquals(res.size(), 3);
        assertEquals(deck.getSize(), 1);

        Card temp = deck.select(1);
        assertEquals(deck.getSize(), 3);
        assertEquals(deck.getThreeCard().size(), 0);

        deck.draw();
        temp = deck.select(0);
        assertEquals(deck.getSize(), 2);
        assertEquals(deck.getThreeCard().size(), 0);

        deck.draw();
        assertEquals(deck.getSize(), 0);
        assertEquals(deck.getThreeCard().size(), 2);
    }

}