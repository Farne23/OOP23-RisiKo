package it.unibo.risiko.view.gameView;

/**
 * Observer used by the view to interact with the controller by calling it's
 * void methods
 * 
 * @author Michele Farneti
 * @author Manuele D'Ambrosio
 */
public interface GameViewObserver {
    /**
     * Tells the controller that the user is triyng to skip to the next game phase.
     * 
     * @author Michele Farneti
     */
    void skipTurn();

    /**
     * Method used to start a new game.
     * 
     * @param mapName - name of the map.
     * @param numberOfStandardPlayers - number of players controlled by humans.
     * @param numberOfAIPlayers - number of players controlled by the computer.
     * @author Manuele D'Ambrosio.
     */
    public void startNewGame(final String mapName, final int numberOfStandardPlayers, final int numberOfAIPlayers);

    /**
     * Method that compute the attack after setting the number of 
     * attacking armies and updates the view
     * 
     * @param numberOfAttackingAmies - Number of armies that are attacking.
     * @author Manuele D'Ambrosio.
     */
    void setAttackingArmies(int numberOfAttackingAmies);

    /**
     * Method used to set the number of armies to move in a 
     * conquered territory.
     * If a territory in not coquered during the attack
     * it simply closes the attack panel.
     * 
     * @param numberOfMovingArmies - Number of armies to move.
     * @author Manuele D'Ambrosio.
     */
    void setMovingArmies(int numberOfMovingArmies);

    /**
     * Tells the controller that the user clicked on a territory
     * 
     * @author Michele Farneti
     */
    void territorySelected(String territory);

}
