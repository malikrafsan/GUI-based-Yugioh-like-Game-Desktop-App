package com.aetherwars.controller;

import com.aetherwars.model.ActiveChar;

public class BattleManager {
    private ActiveChar self;
    private ActiveChar enemy;

    /**
     * Constructor for BattleManager class
     */
    public BattleManager() {
        self = null;
        enemy = null;
    }

    /**
     * mengeset penyerang
     * @param self penyerang
     */
    public void setSelf(ActiveChar self) {
        this.self = self;
    }

    /**
     * mengeset lawan
     * @param enemy
     */
    public void setEnemy(ActiveChar enemy) {
        this.enemy = enemy;
    }

    /**
     * cek penyerang bisa menyerang
     * @return
     */
    public boolean canBattle() {
        return self != null && enemy != null && self.canAttack();
    }

    /**
     * bertukar serangan dan update exp
     */
    public void battle() {
        Double selfMult, enemyMult;
        if(self.getType().equals(enemy.getType())) {
            selfMult = 1.0;
            enemyMult = 1.0;
        } else if(self.getType().ordinal() == (enemy.getType().ordinal()+1) %3) {
            selfMult = 2.0;
            enemyMult = 0.5;
        } else {
            selfMult = 0.5;
            enemyMult = 2.0;
        }

        enemy.receiveDamage(self.getAttack() * selfMult);
        self.receiveDamage(enemy.getAttack() * enemyMult);
        self.cannotAttack();
        if(enemy.getHealth()<=0 && self.getHealth()>0) {
            self.addExp(enemy.getLevel());
        }
    }
}
