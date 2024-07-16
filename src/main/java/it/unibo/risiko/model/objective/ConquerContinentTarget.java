package it.unibo.risiko.model.objective;

import it.unibo.risiko.model.map.Continent;
import it.unibo.risiko.model.map.Territory;
import it.unibo.risiko.model.player.Player;

public class ConquerContinentTarget extends BaseTarget {
    private Continent continent;
    
    public ConquerContinentTarget(Player player ,Continent continent) {
        super(player);
        this.continent=continent; 
    } 

    @Override
    public int remainingActions() {
        int i=0;
        for (Territory t : this.continent.getListTerritories()) {
            if (!this.getPlayer().isOwnedTerritory(t.getTerritoryName())) {
                i++;
            }
        }
        return i;
    }

    @Override
    public String remainingActionsToString() {
        return this.remainingActions()==0? 
        "Remainnig territories to conquer = 0. You won!":
        "You have to conquer "+this.remainingActions()+
        " territory(ies) of "+this.continent.getName()+
        "to win the game";
    }

    @Override
    public String showTargetDescription() {
        return "Conquer all the "+this.continent.getName()+" to win the game";
    }
}
