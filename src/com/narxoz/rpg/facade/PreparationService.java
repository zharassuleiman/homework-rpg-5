package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

public class PreparationService {
    public String prepare(HeroProfile hero, BossEnemy boss, AttackAction action) {
        if (hero == null || boss == null || action == null) {
            return "Error: invalid preparation state";
        }
        return String.format("[PREPARATION] %s enters the dungeon to fight %s using %s!",
                hero.getName(), boss.getName(), action.getActionName());
    }
}
