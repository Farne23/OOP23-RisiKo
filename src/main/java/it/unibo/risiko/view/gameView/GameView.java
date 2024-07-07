package it.unibo.risiko.view.gameView;

import java.util.List;
import it.unibo.risiko.model.map.Territory;
import it.unibo.risiko.model.player.Player;

/**
 * The view interface for the game phase of the application
 * @author Michele Farneti
 */
public interface GameView {

    /**
     * Sets the observer for the view
     * @param gameController Observer 
     * @author Michele Farneti
     */
    void setObserver(GameViewObserver gameController);

    /**
     * Starts the view  by setting up all of the main components
     * @author Michele Farneti
     */
    void start();

    /**
     * Activates the placing and the viewing of the tanks buttons over their respective territories
     * @param territories Territories list of the game map
     * @author Michele Farneti
     */
    void showTanks(List<Territory> territories);

    /**
     * Setups the turn's icon bar with the players' info
     * @param playersList
     * @author Michele Farneti
     */
    void showTurnIcons(List<Player> playersList);
    
    /**
     * Edits the view in order to show wich player is the current player.
     * @param player
     */
    void setCurrentPlayer(Player player);
}
