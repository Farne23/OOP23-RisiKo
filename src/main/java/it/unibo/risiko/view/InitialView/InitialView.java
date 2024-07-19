package it.unibo.risiko.view.initialview;

import java.awt.Component;
import java.awt.Dimension;

/**
 * An interface for the principal game view
 * @author Keliane Nana
 */
public interface InitialView {

    /**
     * This method updates the view that should be shown by the GameFrame
     * @param c the component to show
     */
    void updatePanel(Component  c);

    /**
     * Method used to get the dimensios of the GameFrame
     * @return GameFrame's risolutions
     */
    Dimension getFrameRisolution();
    
    /**
     * Method used to get controller associated to the view
     * @return the controller associated to the game view
     */
    //public InitialViewObserver getController();
    
    //method used to remove the initial frame
    void unshow();

    /**
     * This method is used to get the option menu panel
     * @return the OptionSubMenu of active GameFrame
     */
    //public JPanel getOptionSubMenu();

    /**
     * This method helps to set the GameFrame resolution
     * @param width the width of the GameFrame
     * @param height the height of the GameFrame
     */
    void setResolution(int int1, int int2);
}