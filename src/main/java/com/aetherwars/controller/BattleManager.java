package com.aetherwars.controller;

import com.aetherwars.model.ActiveChar;

public class BattleManager {
    private ActiveChar self;
    private ActiveChar enemy;

    public BattleManager() {
        self = null;
        enemy = null;
    }

    public void setSelf(ActiveChar self) {
        this.self = self;
    }

    public void setEnemy(ActiveChar enemy) {
        this.enemy = enemy;
    }

    public boolean canBattle() {
//        return self != null && enemy != null && self.canAttack();
        return false; // TODO:placeholder
    }

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

//        enemy.addHealth(-1*self.getAttack() * selfMult);
//        self.addHealth(-1*enemy.getAttack() * enemyMult);
    }
}
