package com.narxoz.rpg;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.decorator.BasicAttack;
import com.narxoz.rpg.decorator.CriticalFocusDecorator;
import com.narxoz.rpg.decorator.FireRuneDecorator;
import com.narxoz.rpg.decorator.PoisonCoatingDecorator;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.facade.AdventureResult;
import com.narxoz.rpg.facade.DungeonFacade;
import com.narxoz.rpg.hero.HeroProfile;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Homework 5 Demo: Decorator + Facade ===\n");

        HeroProfile hero = new HeroProfile("Arthur the Brave ", 150);
        BossEnemy boss = new BossEnemy("Shadow Boss", 200, 30);

        AttackAction basic = new BasicAttack("Strike", 20);
        AttackAction critStrike = new CriticalFocusDecorator(basic);
        AttackAction enhanced = new FireRuneDecorator(
                new PoisonCoatingDecorator(
                        new CriticalFocusDecorator(basic)
                )
        );

        System.out.println("--- Decorator Preview ---");
        System.out.println("[Base action] " + basic.getActionName());
        System.out.println("Damage: " + basic.getDamage());
        System.out.println("Effects: " + basic.getEffectSummary() + "\n");

        System.out.println("[Decorated action 1] " + critStrike.getActionName());
        System.out.println("Damage: " + critStrike.getDamage());
        System.out.println("Effects: " + critStrike.getEffectSummary() + "\n");

        System.out.println("[Decorated action 2] " + enhanced.getActionName());
        System.out.println("Damage: " + enhanced.getDamage());
        System.out.println("Effects: " + enhanced.getEffectSummary() + "\n");


        System.out.println("\n--- Facade Preview ---");
        DungeonFacade facade = new DungeonFacade().setRandomSeed(42L);
        AdventureResult result = facade.runAdventure(hero, boss, enhanced);
        for (String line : result.getLog()){
            System.out.println(line);
        }
        System.out.println("\n[Dungeon Result Summary]");
        System.out.println("Winner: " + result.getWinner());
        System.out.println("Rounds: " + result.getRounds());
        System.out.println("Reward: " + result.getReward());

        System.out.println("\n=== Demo Complete ===");
    }
}
