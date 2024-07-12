package it.unibo.risiko.model.game;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

import it.unibo.risiko.model.map.GameMap;

/**
 * @author Michele Farneti
 *         Implementation of the gameManager
 */
public class GameManagerImpl implements GameManager {

    private static final int MAX_SAVEGAMES = 3;

    private Optional<Game> currentGame = Optional.empty();
    private final Set<Game> saveGames;
    private final String resourcesPath;

    /**
     * Constructor for GameManagerImpl from a filepath, containing a json file with
     * all the saved games. If the file doesn't exist, the Set of saved games is
     * initalized as empty.
     * 
     * @param saveGamesFilePath filePath of the savegame file.
     */
    public GameManagerImpl(String saveGamesPath, String resourcesPath) {
        saveGames = new HashSet<Game>();
        try (JsonReader reader = new JsonReader(new FileReader(resourcesPath + saveGamesPath))) {
            Gson gson = new Gson();
            saveGames.addAll(gson.fromJson(reader, Game.class));
        } catch (JsonIOException | JsonSyntaxException | IOException e) {
        }
        this.resourcesPath = resourcesPath;
    }

    @Override
    public boolean isThereCurrentGame() {
        return currentGame.isPresent();
    }

    @Override
    public boolean AddNewCurrentGame(Game game) {
        if (isThereCurrentGame()) {
            if (!isThereSpaceToSave()) {
                return false;
            } else {
                saveGames.add(currentGame.get());
                currentGame = Optional.of(game);
            }
        }
        ;
        currentGame = Optional.of(game);
        return true;
    }

    @Override
    public void deleteSavegame(Game game) {
        saveGames.remove(game);
    }

    @Override
    public boolean saveCurrentGame() {
        if (isThereSpaceToSave()) {
            saveGames.add(currentGame.get());
            return true;
        }
        ;
        return false;
    }

    @Override
    public boolean isThereSpaceToSave() {
        return !(MAX_SAVEGAMES == saveGames.size());
    }

    @Override
    public Optional<Game> getCurrentGame() {
        return currentGame;
    }

    @Override
    public boolean saveGameOnFile(String saveGamesFilePath) {
        try (Writer writer = new FileWriter(saveGamesFilePath)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(saveGames, writer);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public Map<String, Integer> getAvailableMaps() {
        Map<String, Integer> availableMaps = new HashMap<>();
        var mapsFoldersLocations = resourcesPath + "maps";
        try {
            Files.list(Path.of(mapsFoldersLocations)).forEach(
                    p -> availableMaps.put(p.getFileName().toString(), GameMap.getMaxPlayers(mapsFoldersLocations + File.separator +p.getFileName().toString())));
        } catch (IOException e) {
        }
        return availableMaps;
    }

    @Override
    public String getResourcesPath() {
        return resourcesPath;
    }
}
