package it.unibo.risiko.model.objective;

import it.unibo.risiko.model.player.Player;

/**
 * ConquerTerritoriesTarget, a BaseTarget extension
 * @author Keliane Nana
 */
public class ConquerTerritoriesTarget extends BaseTarget{
    private final int territoryWantedNumber;

    public ConquerTerritoriesTarget(final Player player,final int territoryWantedNumber) {
        super(player);
        this.territoryWantedNumber=territoryWantedNumber;
    }

    @Override
    public int remainingActions() {
        return Math.max(0,this.territoryWantedNumber-this.getPlayer().getNumberOfTerritores());
    }

    @Override
    public String remainingActionsToString() {
        return this.remainingActions()==0? 
        "Remainnig territories to conquer = 0. You won!":
        "You have to conquer "+this.remainingActions()+
        " territory(ies) to win the game";
    }

    @Override
    public String showTargetDescription() {
        return "Conquer "+this.territoryWantedNumber+" territories to win the game";
    }
}
