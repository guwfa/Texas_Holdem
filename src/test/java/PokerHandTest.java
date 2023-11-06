import org.junit.Assert;
import org.junit.Test;

import ru.guwfa.PokerHand;
import ru.guwfa.Ranks;
import ru.guwfa.Result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PokerHandTest {

    @Test
    public void pokerHandSortRankingListTest() {
        ArrayList<PokerHand> pokerHandArrayList = new ArrayList<>(List.of(
                new PokerHand("AS 3H 2H 3D AC"),
                new PokerHand("2S 2H 4H 5S 4C"),
                new PokerHand("2S 2H 4H 5S 4C"),
                new PokerHand("AH AC 5H 6H 7S"),
                new PokerHand("JS JD JC JH AD")
        ));

        Collections.sort(pokerHandArrayList);

        ArrayList<PokerHand> expectedPokerHandArrayList = new ArrayList<>(List.of(
                new PokerHand("JS JD JC JH AD"),
                new PokerHand("2H 3H 3D AS AC"),
                new PokerHand("2S 2H 4H 4C 5S"),
                new PokerHand("2S 2H 4H 4C 5S"),
                new PokerHand("5H 6H 7S AH AC")
        ));

        Assert.assertNotNull("PokerHandArrayList is null",pokerHandArrayList);
        Assert.assertNotNull("ExpectedPokerHandArrayList is null",expectedPokerHandArrayList);
        Assert.assertEquals("Different size list",pokerHandArrayList.size(),expectedPokerHandArrayList.size());
    }

    @Test
    public void searchForHighPokerHands() {
        searchForHighPokerHandsInit("Highest straight flush wins", Result.LOSS, "2H 3H 4H 5H 6H", "KS AS TS QS JS");
        searchForHighPokerHandsInit("3 Of a kind wins of two pair", Result.LOSS, "2S 2H 4H 5S 4C", "AH AC 5H 6H AS");
        searchForHighPokerHandsInit("Pair wins of nothing", Result.LOSS, "2S AH 4H 5S KC", "AH AC 5H 6H 7S");
        searchForHighPokerHandsInit("Highest straight flush wins", Result.WIN, "7C 7S KH 2H 7H", "7C 7S 3S 7H 5S");
        searchForHighPokerHandsInit("Highest card loses", Result.LOSS, "2S 3H 6H 7S 9C", "7H 3C TH 6H 9S");
        searchForHighPokerHandsInit("Highest card wins", Result.WIN, "4S 5H 6H TS AC", "3S 5H 6H TS AC");
        searchForHighPokerHandsInit("Straight wins of three of a kind", Result.WIN, "2S 3H 4H 5S 6C", "AH AC 5H 6H AS");
        searchForHighPokerHandsInit("Straight flush wins of 4 of a kind", Result.WIN, "2H 3H 4H 5H 6H", "AS AD AC AH JD");
        searchForHighPokerHandsInit("Equal cards is tie", Result.TIE, "2S AH 4H 5S 6C", "AD 4C 5H 6H 2C");
        searchForHighPokerHandsInit("2 Pair wins of pair", Result.WIN, "2S 2H 4H 5S 4C", "AH AC 5H 6H 7S");
        searchForHighPokerHandsInit("2 Pair wins of 2 pair", Result.LOSS, "2S 2H 4H 5S 4C", "2S 2H 5H 5S 4C");
        searchForHighPokerHandsInit("2 Pair wins of 2 pair", Result.WIN, "2S 2H 5H 5S 4C", "2S 2H 4H 5S 4C");
        searchForHighPokerHandsInit("2 Pair tie of 2 pair", Result.TIE, "2S 2H 5H 5S 4C", "2S 2H 5H 5S 4C");
        searchForHighPokerHandsInit("Pair wins of pair", Result.TIE, "AH AC 5H 6H 7S", "AH AC 5H 6H 7S");
        searchForHighPokerHandsInit("Pair wins of pair", Result.LOSS, "KH KC 5H 6H 7S", "AH AC 5H 6H 7S");
        searchForHighPokerHandsInit("Highest pair wins", Result.LOSS, "6S AD 7H 4S AS", "AH AC 5H 6H 7S");
    }

    @Test
    public void isValidRangingPokerHandsHighCard() {
        isValidRangingPokerHandsInit("Is HIGH_CARD",Ranks.HIGH_CARD.getRank(),"4S 5H 6H TS AC");
        isValidRangingPokerHandsInit("Is HIGH_CARD",Ranks.HIGH_CARD.getRank(),"6S AD 7H 4S JS");
    }

    @Test
    public void isValidRangingPokerHandsOnePair() {
        isValidRangingPokerHandsInit("Is ONE_PAIR",Ranks.ONE_PAIR.getRank(),"AH AC 5H 6H 7S");
        isValidRangingPokerHandsInit("Is ONE_PAIR",Ranks.ONE_PAIR.getRank(),"2H AC 5H 7H 7S");
    }

    @Test
    public void isValidRangingPokerHandsTwoPair() {
        isValidRangingPokerHandsInit("Is TWO_PAIR",Ranks.TWO_PAIR.getRank(),"2S 2H 4H 5S 4C");
        isValidRangingPokerHandsInit("Is TWO_PAIR",Ranks.TWO_PAIR.getRank(),"2S 3H 4H 2S 3C");
    }

    @Test
    public void isValidRangingPokerHandsThreeOfAKind() {
        isValidRangingPokerHandsInit("Is THREE_OF_A_KIND",Ranks.THREE_OF_A_KIND.getRank(),"AH AC 5H 6H AS");
        isValidRangingPokerHandsInit("Is THREE_OF_A_KIND",Ranks.THREE_OF_A_KIND.getRank(),"2H 2C 5H 2H AS");
    }

    @Test
    public void isValidRangingPokerHandsFourOfAKind() {
        isValidRangingPokerHandsInit("Is FOUR_OF_A_KIND",Ranks.FOUR_OF_A_KIND.getRank(),"JS JD JC JH AD");
        isValidRangingPokerHandsInit("Is FOUR_OF_A_KIND",Ranks.FOUR_OF_A_KIND.getRank(),"9S JD 9C 9H 9D");
    }

    @Test
    public void isValidRangingPokerHandsStraight() {
        isValidRangingPokerHandsInit("Is STRAIGHT",Ranks.STRAIGHT.getRank(),"2S 3H 4H 5S 6C");
        isValidRangingPokerHandsInit("Is STRAIGHT",Ranks.STRAIGHT.getRank(),"5S 3H 4H 7S 6C");
    }

    @Test
    public void isValidRangingPokerHandsFlush() {
        isValidRangingPokerHandsInit("Is FLUSH",Ranks.FLUSH.getRank(),"3H 6H 8H AH KH");
        isValidRangingPokerHandsInit("Is FLUSH",Ranks.FLUSH.getRank(),"3S 5S 4S AS KS");
        isValidRangingPokerHandsInit("Is FLUSH",Ranks.FLUSH.getRank(),"3C JC 8C AC QC");
        isValidRangingPokerHandsInit("Is FLUSH",Ranks.FLUSH.getRank(),"TD 6D 8D AD KD");
    }

    @Test
    public void isValidRangingPokerHandsFullHouse() {
        isValidRangingPokerHandsInit("Is FULL_HOUSE",Ranks.FULL_HOUSE.getRank(),"3H 3H 3D AS AC");
        isValidRangingPokerHandsInit("Is FULL_HOUSE",Ranks.FULL_HOUSE.getRank(),"8H 8H JD JS 8C");
    }

    @Test
    public void isValidRangingPokerHandsStraightFlush() {
        isValidRangingPokerHandsInit("Is STRAIGHT_FLUSH",Ranks.STRAIGHT_FLUSH.getRank(),"2H 3H 4H 5H 6H");
        isValidRangingPokerHandsInit("Is STRAIGHT_FLUSH",Ranks.STRAIGHT_FLUSH.getRank(),"5H 6H 8H 9H 7H");
    }

    @Test
    public void isValidRangingPokerHandsRoyalFlush() {
        isValidRangingPokerHandsInit("Is ROYAL_FLUSH ",Ranks.ROYAL_FLUSH.getRank(),"TH JH KH QH AH");
    }

    @Test
    public void isNotValidPokerHandOfLengthCardName() {
        RuntimeException thrownWrongCardLimitLengthCardName = Assert.assertThrows(RuntimeException.class,
                () -> new PokerHand("JS JD 3D/ JH AD"));
        Assert.assertEquals("WRONG CARD",thrownWrongCardLimitLengthCardName.getMessage());
    }

    @Test
    public void isNotValidPokerHandOfLengthPokerHand() {
        RuntimeException thrownWrongCardLengthPokerHand = Assert.assertThrows(RuntimeException.class,
                () -> new PokerHand("JS JD 3D JH"));
        Assert.assertEquals("INVALID POKER HAND",thrownWrongCardLengthPokerHand.getMessage());
    }

    @Test
    public void isNotValidPokerHandOfCharOfSuit() {
        RuntimeException thrownWrongCardCharOfSuit = Assert.assertThrows(RuntimeException.class,
                () -> new PokerHand("JS JE 3D JH JH"));
        Assert.assertEquals("WRONG SUIT",thrownWrongCardCharOfSuit.getMessage());
    }

    @Test
    public void isNotValidPokerHandOfCharOfSuitNotEN() {
        RuntimeException thrownWrongCardCharOfSuit = Assert.assertThrows(RuntimeException.class,
                () -> new PokerHand("JS JE 3D JН JH"));
        Assert.assertEquals("WRONG SUIT",thrownWrongCardCharOfSuit.getMessage());
    }

    @Test
    public void isNotValidPokerHandOfCharOfRank() {
        RuntimeException thrownWrongCardCharOfRank = Assert.assertThrows(RuntimeException.class,
                () -> new PokerHand("JS 1D 3D JH JH"));
        Assert.assertEquals("WRONG RANK",thrownWrongCardCharOfRank.getMessage());
    }

    @Test
    public void isNotValidPokerHandOfCharOfRankNotEN() {
        RuntimeException thrownWrongCardCharOfRank = Assert.assertThrows(RuntimeException.class,
                () -> new PokerHand("JS АD 3D JH КH"));
        Assert.assertEquals("WRONG RANK",thrownWrongCardCharOfRank.getMessage());
    }
    

    private void searchForHighPokerHandsInit(String description, Result expected, String playerHand, String opponentHand) {
        PokerHand player = new PokerHand(playerHand);
        PokerHand opponent = new PokerHand(opponentHand);
        Assert.assertEquals(description + ":", expected, player.compareWith(opponent));
    }

    private void isValidRangingPokerHandsInit(String description, int expected, String pokerHand) {
        PokerHand actualHand = new PokerHand(pokerHand);
        Assert.assertEquals(description + " :", actualHand.rank().getRank(),expected);
    }

}
