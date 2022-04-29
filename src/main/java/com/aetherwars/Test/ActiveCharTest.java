package com.aetherwars.Test;

import com.aetherwars.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActiveCharTest {
    CharacterCard card = new CharacterCard(8, "Magma Cube", CharType.NETHER, "A magma cube is a hostile mob found in the Nether. A magma cube behaves similarly to a slime, but has higher jumps and more powerful hits.", "card/image/character/Magma Cube.png", 3, 4, 3, 5, 5);
    SpellPotionCard deathlyMagic = new SpellPotionCard(101, "Deathly Magic", "The magic that is deadly", "card/image/spell/potion/Deathly Magic.png", 1, 2, 2, 2);
    SpellLevelCard levelPlus = new SpellLevelCard(201, "Level Plus", "Increases your level by 1", "card/image/spell/level/Level Plus.png", 2, 1);
    SpellLevelCard levelMinus = new SpellLevelCard(666, "Level Minus", "Decreases your level by 1", "card/image/spell/level/Level Plus.png", 2, -1);
    SpellSwapCard catFood = new SpellSwapCard(201, "Cat Food", "Disclaimer: includes catnip", "card/image/catfood.png", 1, 3);
    ActiveChar actChar = new ActiveChar(card);

    @Test
    void addSpellPotion() {
        actChar.addSpellPotion(deathlyMagic);
        assertEquals(actChar.getHealthPotion(), 2);
        assertEquals(actChar.getHealth(), 6);
    }

    @Test
    void addExp() {
        actChar.addExp(1);
        assertEquals(actChar.getLevel(), 2);
        assertEquals(actChar.getExp(), 0);
        assertEquals(actChar.getExpUp(), 3);
        actChar.addExp(1);
        assertEquals(actChar.getLevel(), 2);
        assertEquals(actChar.getExp(), 1);
        assertEquals(actChar.getExpUp(), 3);

    }

    @Test
    void healAll() {
        actChar.receiveDamage(1);
        actChar.healAll();
        assertEquals(actChar.getHealth(), 4);
        actChar.addSpell(deathlyMagic);
        actChar.receiveDamage(2);
        actChar.healAll();
        assertEquals(actChar.getHealth(), 6);
    }

    @Test
    void levelUp() {
        actChar.levelUp();
        assertEquals(actChar.getLevel(), 2);
        actChar.levelUp();
        actChar.levelUp();
        actChar.levelUp();
        actChar.levelUp();
        actChar.levelUp();
        actChar.levelUp();
        actChar.levelUp();
        actChar.levelUp();
        assertEquals(actChar.getLevel(), 10);
        actChar.levelUp();
        assertEquals(actChar.getLevel(), 10);
    }

    @Test
    void levelDown() {
        actChar.levelUp();
        actChar.levelDown();
        assertEquals(actChar.getLevel(), 1);
        actChar.levelDown();
        assertEquals(actChar.getLevel(), 1);
    }

    @Test
    void levelUpSpell() {
        actChar.levelUpSpell(levelPlus);
        assertEquals(actChar.getLevel(), 2);
        actChar.addExp(1);
        actChar.levelUpSpell(levelPlus);
        assertEquals(actChar.getLevel(), 3);
        assertEquals(actChar.getExp(), 0);
        assertEquals(actChar.getHealth(), 14);
        assertEquals(actChar.getAttack(), 13);
        actChar.levelUpSpell(levelMinus);
        assertEquals(actChar.getLevel(), 2);
        actChar.levelUpSpell(levelMinus);
        actChar.levelUpSpell(levelMinus);
        assertEquals(actChar.getLevel(), 1);
        assertEquals(actChar.getHealth(), 4);
        assertEquals(actChar.getAttack(), 3);

    }

    @Test
    void swapSpell() {
        assertEquals(actChar.getHealth(), 4);
        assertEquals(actChar.getAttack(), 3);
        actChar.swapSpell(catFood);
        assertEquals(actChar.getHealth(), 3);
        assertEquals(actChar.getAttack(), 4);
        actChar.addSpellPotion(deathlyMagic);
        assertEquals(actChar.getAttack(), 5);
        assertEquals(actChar.getHealth(), 5);
        actChar.receiveDamage(1);
        actChar.newRound();
        assertEquals(actChar.getHealth(), 5);
        assertEquals(actChar.getAttack(), 4);
    }

    @Test
    void receiveDamage() {
        actChar.receiveDamage(1);
        assertEquals(actChar.getHealth(), 3);
    }

    @Test
    void addSpell() {
        actChar.addSpell(deathlyMagic);
        assertEquals(actChar.getHealthPotion(), 2);
        assertEquals(actChar.getHealth(), 6);
        actChar.addSpell(levelPlus);
        assertEquals(actChar.getLevel(), 2);
        actChar.addSpell(catFood);
        assertEquals(actChar.getHealth(), 9);
        assertEquals(actChar.getAttack(), 11);
    }

    @Test
    void cannotAttack() {
        actChar.cannotAttack();
        assertEquals(actChar.canAttack(), false);
    }

    @Test
    void getHealthPotion() {
        actChar.addSpell(deathlyMagic);
        assertEquals(actChar.getHealthPotion(), 2);
    }

    @Test
    void getAttackPotion() {
        actChar.addSpell(deathlyMagic);
        assertEquals(actChar.getAttackPotion(), 1);
    }

    @Test
    void getBaseHealth() {
        assertEquals(actChar.getBaseHealth(), 4);
    }

    @Test
    void getBaseAttack() {
        assertEquals(actChar.getBaseAttack(), 3);
    }

    @Test
    void canAttack() {
        assertEquals(actChar.canAttack(), true);
    }

    @Test
    void getAttack() {
        assertEquals(actChar.getAttack(), 3);
        actChar.addSpell(deathlyMagic);
        assertEquals(actChar.getAttack(), 4);
    }

    @Test
    void getHealth() {
        assertEquals(actChar.getHealth(), 4);
        actChar.addSpell(deathlyMagic);
        assertEquals(actChar.getHealth(), 6);
    }

    @Test
    void getExp() {
        assertEquals(actChar.getExp(), 0);
        actChar.addExp(1);
        assertEquals(actChar.getExp(), 0);
        actChar.addExp(1);
        assertEquals(actChar.getExp(), 1);
    }

    @Test
    void getExpUp() {
        assertEquals(actChar.getExpUp(), 1);
        actChar.addExp(1);
        assertEquals(actChar.getExpUp(), 3);
    }

    @Test
    void getLevel() {
        assertEquals(actChar.getLevel(), 1);
        actChar.addExp(1);
        assertEquals(actChar.getLevel(), 2);
    }

    @Test
    void getImagePath() {
        assertEquals(actChar.getImagePath(), "card/image/character/Magma Cube.png");
    }

    @Test
    void getType() {
        assertEquals(actChar.getType(), CharType.NETHER);
    }

    @Test
    void getName() {
        assertEquals(actChar.getName(), "Magma Cube");
    }

    @Test
    void getDesc() {
        assertEquals(actChar.getDesc(), "A magma cube is a hostile mob found in the Nether. A magma cube behaves similarly to a slime, but has higher jumps and more powerful hits.");
    }
}