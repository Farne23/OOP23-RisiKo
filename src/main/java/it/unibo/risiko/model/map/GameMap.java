package it.unibo.risiko.model.map;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import it.unibo.risiko.model.cards.Deck;

/**
 * The gameMap class manages all of the enviroment features of the game map,
 * such us
 * keeping track of the territories'list and other settings.
 * 
 * @author Michele Farneti
 */

public interface GameMap {

    /**
     * @return The max number of players allowed to play a game in the map at the
     *         same time.
     */
    int getMaxPlayers();

    /**
     * @param nplayers Number of players playing in the map
     * @return The number of armies every player should start with.
     */
    int getStratingArmies(int nplayers);

    /**
     * @return The list of territories for the Map
     */
    List<Territory> getTerritories();

    /**
     * @return The list of continents in the map
     */
    public List<Continent> getContinents();

    /**
     * @return The map's name
     */
    public String getName();

    /**
     * 
     * @param mapPath The path for the map's folder in the file system.
     * @return The maxNUmberOfPLayers for the map
     */
    public static Integer getMaxPlayers(String mapPath){
        final Integer MAX_PLAYERS_SMALL_MAPS = 2;
        final Integer MAX_PLAYERS_BIG_MAPS = 6;
        final Integer BIG_MAP_LIMIT = 30;
        try{
            var territoriesNumber = Files.lines(Path.of(mapPath + File.separator + "territories.txt")).count()/2;
            if(territoriesNumber >= BIG_MAP_LIMIT){
                return MAX_PLAYERS_BIG_MAPS;
            }else{
                return MAX_PLAYERS_SMALL_MAPS;
            }
        }catch(IOException e){}
        return MAX_PLAYERS_SMALL_MAPS;  
    }

    /**
     * Checks if two territories are near in the gameMap
     * @param territory1
     * @param territory2
     * @return True if they are near, false otherwise.
     */
    public boolean areTerritoriesNear(String territory1, String territory2);

    /**
     * Returns the path to create the Game deck
     * @param resource
     * @return
     */
    public String getDeckPath();

    void addArmies(String territory, int nArmies);

    void removeArmies(String territory, int numberOfMovingArmies);

    void setOwner(String territory, String color_id);
}
