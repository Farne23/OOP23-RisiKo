package it.unibo.risiko.model.player;

import java.util.List;

/**
 * Implementation of @PlayerFactory inteface.
 * 
 * @author Manuele D'Ambrosio
 */

public final class SimplePlayerFactory implements PlayerFactory {
    private static final int FIRST_COLOR_INDEX = 0;
    private final List<String> colorList;
    private int colorIndex;

    /**
     * Constructor with a color list defined by default.
     */
    public SimplePlayerFactory() {
        this.colorList = List.of("cyan", "blue", "green", "red", "pink", "yellow");
        this.colorIndex = FIRST_COLOR_INDEX;
    }

    @Override
    public Player createStandardPlayer() {
        return new StdPlayer(nextColor(), false);
    }

    @Override
    public Player createAIPlayer() {
        return new StdPlayer(nextColor(), true);
    }

    private String nextColor() {
        final int nextColor = colorIndex;
        colorIndex++;
        if (nextColor < colorList.size()) {
            return colorList.get(nextColor);
        }
        return colorList.get(nextColor) + Integer.toString(nextColor - colorList.size());
    }

}
