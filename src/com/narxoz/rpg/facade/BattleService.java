package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

import java.util.Random;
import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

public class BattleService {
    private Random random = new Random(1L);

    public BattleService setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public AdventureResult battle(HeroProfile hero, BossEnemy boss, AttackAction action) {
        AdventureResult result = new AdventureResult();
        int rounds =0;
        while (hero.isAlive() && boss.isAlive()) {
            rounds++;
            result.addLine("--- Round " + rounds + " ---");
            int heroDamage = action.getDamage();
            if (random.nextInt(10) < 2) {
                heroDamage += 5;
                result.addLine(">>> Lucky Strike! Extra damage applied.");
            }
            boss.takeDamage(heroDamage);
            result.addLine(hero.getName() + " hits for " + heroDamage + " dmg. Boss HP: " + boss.getHealth());
            if (!boss.isAlive()) break;
            int bossDamage = boss.getAttackPower();
            hero.takeDamage(bossDamage);
            result.addLine(boss.getName() + " retaliates for " + bossDamage + " dmg. Hero HP: " + hero.getHealth());
        }
        result.setWinner(hero.isAlive() ? hero.getName() : boss.getName());
        result.setRounds(rounds);
        return result;
    }
}
