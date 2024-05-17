package it.unibo.risiko.player;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import it.unibo.risiko.Card;
import it.unibo.risiko.Territory;
import it.unibo.risiko.objective.Target;

/**
 * Implementation of Player interface.
 * @author Manuele D'Ambrosio
 */

/*TO DO:
Aggiungere la gestione degli obbietti.
 */

public class StdPlayer implements Player {

    private final String color_id;
    private Set<Territory> ownedTerritories = new HashSet<>();
    private int numberOfTerritores;
    private Set<Card> ownedCards = new HashSet<>();
    private int numberOfCards;
    private int armiesToPlace;
    private Target target;

    public StdPlayer (final String color, final int armiesToPlace, final Target target) {
        this.color_id = color;
        this.armiesToPlace = armiesToPlace;
        this.target = target;
        this.ownedCards = Collections.emptySet();
        this.ownedTerritories = Collections.emptySet();
        this.numberOfCards = 0;
        this.numberOfTerritores = 0;
    }

    public void setArmiesToPlace(final int armiesToPlace) {
        this.armiesToPlace = armiesToPlace;
    }

    public void setOwnedTerritories(final Set<Territory> newTerritories) {
        this.ownedTerritories.addAll(newTerritories);
        this.numberOfTerritores = newTerritories.size();
    }

    public void addTerritory(final Territory newTerritory) {
        this.ownedTerritories.add(newTerritory);
        incrementTerritories();
    }

    public void addCard (final Card newCard) {
        this.ownedCards.add(newCard);
        incrementCards();
    }

    public String getColor_id() {
        return this.color_id;
    }

    public int getArmiesToPlace() {
        return this.armiesToPlace;
    }

    public Collection<Territory> getOwnedTerritories() {
        return this.ownedTerritories;
    }

    public Collection<Card> getOwnedCards() {
        return this.ownedCards;
    }

    public int getNumberOfCards() {
        return this.numberOfCards;
    }

    public int getNumberOfTerritores() {
        return this.numberOfTerritores;
    }

    public Target getTarget() {
        return this.target;
    }

    public void decrementArmiesToPlace() {
        this.armiesToPlace--;
    }

    public boolean removeCard(final Card card) {
        if (isOwnedCard(card)) {
            this.ownedCards.remove(card);
            decrementCards();
            return true;
        }
        else {
            return false;
        }
    }

    public boolean removeTerritory(final Territory territory) {
        if (isOwnedTerritory(territory)) {
            this.ownedTerritories.remove(territory);
            decrementTerritories();
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isDefeated() {
        return this.ownedTerritories.isEmpty();
    }

    public String toString() {
        return "Color = " + this.color_id +
            "\nTarget = " + this.target + 
            "\nNumber of cards = " + this.numberOfCards +
            "\nArmies to place = " + this.armiesToPlace +
            "\nOwned territories = " + ownedTerritories.toString() +
            "\nOwned cards =" + ownedCards.toString();
    }

    private boolean isOwnedCard(final Card card) {
        return this.ownedCards.contains(card);
    }

    private boolean isOwnedTerritory(final Territory territory) {
        return this.ownedTerritories.contains(territory);
    }

    private void incrementTerritories() {
        this.numberOfTerritores++;
    }

    private void decrementTerritories() {
        this.numberOfTerritores--;
    }

    private void incrementCards() {
        this.numberOfCards++;
    }

    private void decrementCards() {
        this.numberOfCards--;
    }

}
