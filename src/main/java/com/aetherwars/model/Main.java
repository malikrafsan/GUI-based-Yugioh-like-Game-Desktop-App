package com.aetherwars.model;

class Main{
    public static void main(String[] args) {
        CharacterCard creeper = new CharacterCard(1, "Creeper", CharType.OVERWORLD, "A creeper is a common hostile mob that silently approaches players and explodes.", "card/image/creeper.png", 10, 2, 4, 1, 1);
        System.out.println(creeper.toString());
        System.out.println();

        SpellMorphCard sheepify = new SpellMorphCard(301, "Sheepify","Turns any being into a measly sheep, even you.", "card/image/sheepify.png", 2, 4);
        System.out.println(sheepify.toString());
        System.out.println();

        SpellSwapCard CatFood = new SpellSwapCard(201, "Cat Food", "Disclaimer: includes catnip", "card/image/catfood.png", 3, 3);
        System.out.println(CatFood.toString());
        System.out.println();

        SpellPotionCard deathlyMagic = new SpellPotionCard(101, "Deathly Magic", "The magic that is deadly", "card/image/spell/potion/Deathly Magic.png", 2, 2, 2, 2);
        System.out.println(deathlyMagic.toString());
        System.out.println();

        SpellLevelCard levelplus = new SpellLevelCard(201, "Level Plus", "Increases your level by 1", "card/image/spell/level/Level Plus.png", 2, 1);
        System.out.println(levelplus.toString());
        System.out.println();
    }
}