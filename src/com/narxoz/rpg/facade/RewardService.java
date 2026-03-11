package com.narxoz.rpg.facade;

public class RewardService {
    public String determineReward(AdventureResult battleResult) {
        if (battleResult == null || battleResult.getWinner() == null) {
            return "No reward data available.";
        }
        if (!battleResult.getWinner().contains("Boss")){
            return "1000 Gold and the Epic Rune of Victory!";

        }

        return "Nothing. The dungeon claims another soul.";
    }
}
