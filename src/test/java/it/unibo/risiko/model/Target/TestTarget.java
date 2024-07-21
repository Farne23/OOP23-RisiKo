package it.unibo.risiko.model.Target;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import it.unibo.risiko.model.map.Continent;
import it.unibo.risiko.model.map.ContinentImpl;
import it.unibo.risiko.model.map.Territory;
import it.unibo.risiko.model.map.TerritoryImpl;
import it.unibo.risiko.model.objective.ConquerContinentTarget;
import it.unibo.risiko.model.objective.ConquerTerritoriesTarget;
import it.unibo.risiko.model.objective.DestroyPlayerTarget;
import it.unibo.risiko.model.objective.Target;
import it.unibo.risiko.model.player.Player;
import it.unibo.risiko.model.player.PlayerFactory;
import it.unibo.risiko.model.player.SimplePlayerFactory;

public class TestTarget {
    private Territory nigeria=new TerritoryImpl("Nigeria", "Africa", List.of("Cameroon","Tchad","Niger"));
    private Territory cameroon =new TerritoryImpl("Cameroon", "Africa", List.of("Nigeria","Tchad","Niger"));
    private Territory tchad=new TerritoryImpl("Tchad", "Africa", List.of("Cameroon","Nigeria","Niger"));
    private Territory italy=new TerritoryImpl("Italy", "Europe", List.of("Belgia","Francia"));

    @Test
    void testPlayerDestroyTarget(){
        testing remainingActions and isAchieved of DestroyPlayerTarget
        PlayerFactory pf=new SimplePlayerFactory();
        Player p1=pf.createStandardPlayer();
        p1.addTerritory(cameroon.getTerritoryName());
        p1.addTerritory(italy.getTerritoryName());
        Player p3=pf.createStandardPlayer();
        Target playerDestroyTarget=new DestroyPlayerTarget(p3, p1);
        p3.setTarget(playerDestroyTarget);
        assertEquals(2, playerDestroyTarget.remainingActions());
        assertFalse(playerDestroyTarget.isAchieved());
        p1.addTerritory(nigeria.getTerritoryName());
        assertEquals(3, playerDestroyTarget.remainingActions());
        assertFalse(playerDestroyTarget.isAchieved());
        p1.removeTerritory(cameroon.getTerritoryName());
        p1.removeTerritory(italy.getTerritoryName());
        assertEquals(1, playerDestroyTarget.remainingActions());
        assertFalse(playerDestroyTarget.isAchieved());
        p1.removeTerritory(nigeria.getTerritoryName());
        assertEquals(0, playerDestroyTarget.remainingActions());
        assertTrue(playerDestroyTarget.isAchieved());
    }

    @Test
    void testRemainingActions_ContinentTarget(){
        testing remainingActions and isAchieved of ConquerContinentTarget
        PlayerFactory pf=new SimplePlayerFactory();
        Player p2=pf.createStandardPlayer();
        Continent africa=new ContinentImpl("Africa",2);
        africa.addTerritory(nigeria);
        africa.addTerritory(cameroon);
        africa.addTerritory(tchad);
        Target continentTarget=new ConquerContinentTarget(p2, africa);
        p2.setTarget(continentTarget);
        assertEquals(3, continentTarget.remainingActions());
        assertFalse(continentTarget.isAchieved());
        p2.addTerritory(cameroon.getTerritoryName());
        p2.addTerritory(italy.getTerritoryName());
        assertEquals(2, continentTarget.remainingActions());
        assertFalse(continentTarget.isAchieved());
        p2.addTerritory(tchad.getTerritoryName());
        p2.addTerritory(nigeria.getTerritoryName());
        assertEquals(0, continentTarget.remainingActions());
        assertTrue(continentTarget.isAchieved());
    }
    
    @Test
    void testRemainingActions_TerritoryTarget(){
        testing remainingActions and isAchieved of ConquerTerritoryTarget
        PlayerFactory pf=new SimplePlayerFactory();
        Player p3=pf.createStandardPlayer();
        Target territoryTarget=new ConquerTerritoriesTarget(p3, 3);
        p3.setTarget(territoryTarget);
        assertEquals(3, territoryTarget.remainingActions());
        assertFalse(territoryTarget.isAchieved());
        p3.addTerritory(cameroon.getTerritoryName());
        assertEquals(2, territoryTarget.remainingActions());
        assertFalse(territoryTarget.isAchieved());
        p3.addTerritory(italy.getTerritoryName());
        p3.addTerritory(nigeria.getTerritoryName());
        assertEquals(0, territoryTarget.remainingActions());
        assertTrue(territoryTarget.isAchieved());
    }

}
