package it.unibo.risiko.game;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import it.unibo.risiko.map.GameMap;
import it.unibo.risiko.player.Player;


public class GameImpl implements Game {

    GameMap map;
    int activePlayer = 0;
    private final List<Player> players = new LinkedList<Player>();
   
    public void startGame(){
        Collections.shuffle(players);
        players.forEach( p -> p.setArmiesToPlace(map.getStratingArmies(players.size())));
    }

    public boolean NextTurn(){
        if(skipTurnPossible()){
            activePlayer = (activePlayer+1)%players.size();
            return true;
        }
        return false;
    }

    private boolean skipTurnPossible(){
        return false;
        
    }
}
