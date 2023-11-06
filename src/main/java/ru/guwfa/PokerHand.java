package ru.guwfa;

import ru.guwfa.utils.Helpers;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

public class PokerHand implements Comparable<PokerHand>{
    private final List<Card> cards;
    private final Map<Integer, List<Card>> cardsMap;

    public PokerHand(String handString) {
        cards = new ArrayList<>();
        ArrayList<String> pokerHandList = new ArrayList<>(Arrays.stream(handString.split(" ")).toList());
        if(pokerHandList.size() != 5) throw new RuntimeException("INVALID POKER HAND");

        Arrays.stream(handString.split(" "))
                .filter(Helpers::checkCard)
                .map(card -> new Card(card.trim().toUpperCase()))
                .forEach(cards::add);

        cards.sort(Comparator.comparingInt(Card::getValue));
        cardsMap = Helpers.getValueMap(cards);
    }

    Map<Integer, List<Card>> getCardsMap() {
        return cardsMap;
    }

    List<Card> getCards() {
        return cards;
    }

    @Override
    public int compareTo(PokerHand o) {
        if(o.rank().getRank() - rank().getRank() != 0) return o.rank().getRank() - rank().getRank();
        else if (o.compareWith(this) == Result.WIN) return 1;
        else return -1;
    }

    public Result compareWith(PokerHand pokerHand) {
       if (rank().getRank() > pokerHand.rank().getRank()) return Result.WIN;
       else if (rank().getRank() < pokerHand.rank().getRank()) return Result.LOSS;
       else return this.rank().resolveConflict(this,pokerHand);
    }

    public Ranks rank() {
        if (isRoyalFlush())  return Ranks.ROYAL_FLUSH;
        else if (isStraightFlush()) return Ranks.STRAIGHT_FLUSH;
        else if (isFourOfAKind()) return Ranks.FOUR_OF_A_KIND;
        else if (isFullHouse()) return Ranks.FULL_HOUSE;
        else if (isFlush())  return Ranks.FLUSH;
        else if (isStraight()) return Ranks.STRAIGHT;
        else if (isThreeOfAKind()) return Ranks.THREE_OF_A_KIND;
        else if (isPair(2,2)) return Ranks.TWO_PAIR;
        else if (isPair(2,1)) return Ranks.ONE_PAIR;
        else return Ranks.HIGH_CARD;
    }

    private boolean isPair(int countPair, int countGroupPair) {
        return Helpers.getCountOfGroupOfASize(this.cardsMap, countPair) == countGroupPair;
    }

    private boolean isThreeOfAKind() {
        return Helpers.getCountOfGroupOfASize(this.cardsMap, 3) == 1;
    }

    private boolean isFullHouse() {
        return isThreeOfAKind() && isPair(2,1);
    }

    private boolean isFourOfAKind() {
        return Helpers.getCountOfGroupOfASize(this.cardsMap, 4) == 1;
    }

    private boolean isStraightFlush() {
        return isStraight() && isFlush();
    }

    private boolean isStraight() {
        boolean isIncreasing = true;
        for (int i = 0; i < this.getCards().size() - 1; i++) {
            if (Math.abs(this.getCards().get(i).getValue() - this.getCards().get(i + 1).getValue()) != 1) {
                isIncreasing = false;
                break;
            }
        }
        return isIncreasing;
    }

    private boolean isFlush() {
        return this.getCards().stream().collect(groupingBy(Card::getSuit)).size() == 1;
    }

    private boolean isRoyalFlush() {
        return isStraight() && isFlush() && getCards().get(0).getValue() == Helpers.valueMap.get('T');
    }

    @Override
    public String toString() {
        return "Poker hand: " + cards.toString();
    }
}
