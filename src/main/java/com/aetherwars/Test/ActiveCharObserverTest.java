package com.aetherwars.Test;

import com.aetherwars.model.ActiveChar;
import com.aetherwars.model.ActiveCharObserver;
import com.aetherwars.model.CharType;
import com.aetherwars.model.CharacterCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActiveCharObserverTest {
    CharacterCard card = new CharacterCard(8, "Magma Cube", CharType.NETHER, "A magma cube is a hostile mob found in the Nether. A magma cube behaves similarly to a slime, but has higher jumps and more powerful hits.", "card/image/character/Magma Cube.png", 3, 3, 3, 5, 5);


    @Test
    void ActiveCharObserverTest() {
        ActiveCharObserver activeCharObserver = new ActiveCharObserver();
        assertEquals(activeCharObserver.empty(), true);

        activeCharObserver.addChar(card, 2);
        assertEquals(activeCharObserver.getActChar(2).getName(), card.getName());

        activeCharObserver.addExp(2, 2);
        assertEquals(activeCharObserver.getActChar(2).getLevel(), 2);
    }

}