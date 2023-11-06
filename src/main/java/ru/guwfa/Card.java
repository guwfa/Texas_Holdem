package ru.guwfa;

import ru.guwfa.utils.Helpers;

import static ru.guwfa.utils.Helpers.checkCard;

public class Card {
    private String cardName;
    private int value;
    private char suit;

    Card(String card) {
        cardName = String.copyValueOf(card.toCharArray());
        suit = card.charAt(1);
        value = calculateValue(card.charAt(0));
    }

    public int getValue() {
        return value;
    }

    String getCardName() {
        return String.copyValueOf(cardName.toCharArray());
    }

    char getSuit() {
        return suit;
    }

    private int calculateValue(char first) {
        return Helpers.valueMap.get(first);
    }

    @Override
    public String toString() {
        return  getCardName();
    }
}