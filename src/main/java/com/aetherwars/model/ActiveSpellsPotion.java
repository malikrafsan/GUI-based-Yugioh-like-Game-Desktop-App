package com.aetherwars.model;

public class ActiveSpellsPotion {
    private SpellPotionCard card;
    private double healthPotion;
    private double attackPotion;
    private int duration;
    private int maxHealthPotion;

    public ActiveSpellsPotion(SpellPotionCard card) {
        this.card = card;
        this.healthPotion = card.getHp();
        this.attackPotion = card.getAttack();
        this.duration = card.getDuration();
        this.maxHealthPotion = card.getHp();
    }

    public void swap() {
        double temp;

        temp = this.healthPotion;
        this.healthPotion = this.attackPotion;
        this.attackPotion = temp;
    }

    public void healAll() {
        this.healthPotion = this.maxHealthPotion;
    }

    public double getAttackPotion() {
        return (this.attackPotion);
    }

    public double getHealthPotion() {
        return (this.healthPotion);
    }

    public double getDuration() {
        return (this.duration);
    }

    public void receiveDamage(double damage) { this.healthPotion = this.healthPotion - damage ;}

    public void newRound() {
        this.duration = this.duration - 1;
    }

    public void display() {
        System.out.println(this.card.getName());
        System.out.println("Attack: " + this.attackPotion);
        System.out.println("Health: " + this.healthPotion);
        System.out.print("Durasi: " + this.duration + "\n");
    }
}
