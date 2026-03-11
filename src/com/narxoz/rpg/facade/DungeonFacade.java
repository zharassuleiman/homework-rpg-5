package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

public class DungeonFacade {
    private final PreparationService preparationService = new PreparationService();
    private final BattleService battleService = new BattleService();
    private final RewardService rewardService = new RewardService();

    public DungeonFacade setRandomSeed(long seed) {
        battleService.setRandomSeed(seed);
        return this;
    }

    public AdventureResult runAdventure(HeroProfile hero, BossEnemy boss, AttackAction action) {
        AdventureResult finalResult = new AdventureResult();
        String preparationSummary = preparationService.prepare(hero, boss, action);
        finalResult.addLine(preparationSummary);
        AdventureResult battleResult = battleService.battle(hero, boss, action);
        for (String line : battleResult.getLog()){
            finalResult.addLine(line);
        }
        finalResult.setWinner(battleResult.getWinner());
        finalResult.setRounds(battleResult.getRounds());
        String reward = rewardService.determineReward(finalResult);
        finalResult.setReward(reward);
        return finalResult;
    }
}
