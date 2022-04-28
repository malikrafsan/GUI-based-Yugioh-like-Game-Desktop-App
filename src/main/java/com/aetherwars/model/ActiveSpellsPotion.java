package com.aetherwars.model;

public class ActiveSpellsPotion {
    private SpellPotionCard card;
    private double healthPotion;
    private double attackPotion;
    private int duration;

    public ActiveSpellsPotion() {
        this.card = new SpellPotionCard();
        this.healthPotion = this.card.getHp();
        this.attackPotion = this.card.getAttack();
        this.duration = this.card.getDuration();
    }

    public ActiveSpellsPotion(SpellPotionCard card) {
        this.card = card;
        this.healthPotion = this.card.getHp();
        this.attackPotion = this.card.getAttack();
        this.duration = this.card.getDuration();
    }

    public double getAttackPotion() {
        return (this.attackPotion);
    }

    public double getHealthPotion() {
        return (this.healthPotion);
    }

    public void getDamage(int damage) {
        this.healthPotion = this.healthPotion - damage;
    }

    public void setHealthPotion(double damage) { this.healthPotion = this.healthPotion - damage ;}

    public void newRound() {
        this.duration = this.duration - 1;
    }
}
