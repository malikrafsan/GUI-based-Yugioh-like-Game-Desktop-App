package com.aetherwars.Test;

import com.aetherwars.model.*;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class HandCardTest {
    // define card
    CharacterCard creeper = new CharacterCard(1, "Creeper", CharType.OVERWORLD, "A creeper is a common hostile mob that silently approaches players and explodes.", "card/image/creeper.png", 10, 2, 4, 1, 1);

    SpellMorphCard sheepify = new SpellMorphCard(301, "Sheepify","Turns any being into a measly sheep, even you.", "card/image/sheepify.png", 2, 4);

    SpellSwapCard catFood = new SpellSwapCard(201, "Cat Food", "Disclaimer: includes catnip", "card/image/catfood.png", 3, 3);

    SpellPotionCard deathlyMagic = new SpellPotionCard(101, "Deathly Magic", "The magic that is deadly", "card/image/spell/potion/Deathly Magic.png", 2, 2, 2, 2);

    SpellLevelCard levelplus = new SpellLevelCard(201, "Level Plus", "Increases your level by 1", "card/image/spell/level/Level Plus.png", 2, 1);



    @Test
    void addCard() {
        HandCard handCard = new HandCard();
        assertEquals(handCard.getSize(), 0);
        handCard.addCard(creeper);
        assertEquals(handCard.getSize(), 1);
        handCard.addCard(levelplus, 2);
        assertEquals(handCard.getSize(), 2);
        assertNull(handCard.getCard(1));
    }

    @Test
    void takeCard() {
        HandCard handCard = new HandCard();

        handCard.addCard(sheepify);
        handCard.addCard(creeper);
        handCard.addCard(deathlyMagic, 4);
        assertEquals(handCard.getSize(), 3);

        Card temp = handCard.takeCard(1);
        assertEquals(handCard.getSize(), 2);
        assertEquals(temp.toString(), creeper.toString());
    }
}