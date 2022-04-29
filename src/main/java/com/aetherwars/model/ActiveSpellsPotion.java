package com.aetherwars.model;

public class ActiveSpellsPotion {
    private SpellPotionCard card;
    private double healthPotion;
    private double attackPotion;
    private int duration;
    private int maxHealthPotion;

    /**
     * Constructor
     * @param card SpellPotionCard
     */
    public ActiveSpellsPotion(SpellPotionCard card) {
        this.card = card;
        this.healthPotion = card.getHp();
        this.attackPotion = card.getAttack();
        this.duration = card.getDuration();
        this.maxHealthPotion = card.getHp();
    }

    /**
     * Method to swap healthPotion and AttackPotion
     */
    public void swap() {
        double temp;

        temp = this.healthPotion;
        this.healthPotion = this.attackPotion;
        this.attackPotion = temp;
    }

    /**
     * Method to heal fully
     */
    public void healAll() {
        this.healthPotion = this.maxHealthPotion;
    }

    /**
     * getter
     * @return double
     */
    public double getAttackPotion() {
        return (this.attackPotion);
    }

    /**
     * getter
     * @return double
     */
    public double getHealthPotion() {
        return (this.healthPotion);
    }

    /**
     * getter
     * @return double
     */
    public double getDuration() {
        return (this.duration);
    }

    /**
     * Method to decrease healthPotion when damaged
     */
    public void receiveDamage(double damage) { this.healthPotion = this.healthPotion - damage ;}


    /**
     * Method for new round
     */
    public void newRound() {
        this.duration = this.duration - 1;
    }

    /**
     * Method for displaying information
     */
    public void display() {
        System.out.println(this.card.getName());
        System.out.println("Attack: " + this.attackPotion);
        System.out.println("Health: " + this.healthPotion);
        System.out.print("Durasi: " + this.duration + "\n");
    }
}
