import org.junit.Assert;
import org.junit.Test;
import ru.guwfa.PokerHand;

public class CardTest {
    @Test
    public void createIsNotValidPokerHandWrongNameCard() {
        RuntimeException thrownWrongCard = Assert.assertThrows(RuntimeException.class, () -> new PokerHand("JS JD 3 JH AD"));
        Assert.assertEquals("WRONG CARD",thrownWrongCard.getMessage());
    }

    @Test
    public void createIsNotValidPokerHandWrongRank() {
        RuntimeException thrownRank = Assert.assertThrows(RuntimeException.class, () -> new PokerHand("JS JD 1A JH AD"));
        Assert.assertEquals("WRONG RANK",thrownRank.getMessage());
    }

    @Test
    public void createIsNotValidPokerHandWrongSuit() {
        var thrownSuit = Assert.assertThrows(RuntimeException.class, () -> new PokerHand("JS JD 3G JQ AD"));
        Assert.assertEquals("WRONG SUIT",thrownSuit.getMessage());
    }

}
