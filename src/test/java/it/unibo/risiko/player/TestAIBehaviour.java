package it.unibo.risiko.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.risiko.model.cards.Card;
import it.unibo.risiko.model.cards.Deck;
import it.unibo.risiko.model.cards.DeckImpl;
import it.unibo.risiko.model.map.Territory;
import it.unibo.risiko.model.map.TerritoryImpl;
import it.unibo.risiko.model.player.AIBehaviour;
import it.unibo.risiko.model.player.AIBehaviourImpl;
import it.unibo.risiko.model.player.Player;
import it.unibo.risiko.model.player.PlayerFactory;
import it.unibo.risiko.model.player.SimplePlayerFactory;

/**
 * @author Manuele D'Ambrosio
 */

public class TestAIBehaviour {

    private Player player;
    private AIBehaviour ai;
    private Deck deck;
    private Territory t1;
    private Territory t2;
    private Territory t3;

    @BeforeEach
    void setUp() {
        final String SEP = File.separator;
        final String PATH = "src" + SEP + "main" + SEP + "resources" + SEP + "it" + SEP + "unibo" + SEP
                + "risiko" + SEP + "maps" + SEP + "standard" + SEP + "cards.txt";
        deck = new DeckImpl(PATH);
        PlayerFactory factory = new SimplePlayerFactory();
        player = factory.createAIPlayer();
        t1 = new TerritoryImpl("t1", "cont", List.of("t2", "t3"));
        t2 = new TerritoryImpl("t2", "cont", List.of("t1"));
        t3 = new TerritoryImpl("t3", "cont", List.of("t2", "t1"));
        t1.addArmies(10);
        t2.addArmies(2);
        t3.addArmies(10);

        player.addTerritory(t2);
        player.addTerritory(t1);

        ai = new AIBehaviourImpl(player);
    }

    @Test
    void testDecidePositioning() {
        assertTrue(t2.equals(ai.decidePositioning()));
        assertTrue(t1.equals(ai.decidePositioning()));
        assertTrue(t2.equals(ai.decidePositioning()));
    }

    @Test
    void testDecideAttack() {
        assertTrue(ai.decideAttack(List.of(t1, t2, t3)));
        assertTrue(ai.getNextAttackedTerritory().equals(t3));
        assertTrue(ai.getNextAttackingTerritory().equals(t1));
        assertEquals(3, ai.decideAttackingArmies());
        assertEquals(9, ai.getArmiesToMove());
    }

    @Test
    void TestCheckCardCombo() {
        List<Card> cardList = new ArrayList<>();
        int numberOfCards = 0;
        for (int i = 0; i < 10; i++) {
            do {
                player.addCard(deck.pullCard());
                numberOfCards++;
            } while (ai.checkCardCombo().isEmpty());
            assertEquals(3, ai.checkCardCombo().size());
            cardList = ai.checkCardCombo();
            deck.playCards(cardList.get(0), cardList.get(1), cardList.get(2), player);
            assertEquals(numberOfCards - 3, player.getNumberOfCards());
            numberOfCards = numberOfCards - 3;
        }
    }
}
