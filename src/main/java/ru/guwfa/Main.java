package ru.guwfa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ArrayList<PokerHand> hands = new ArrayList<>(List.of(
                new PokerHand("AS 3H 2H 3D AC"),
                new PokerHand("2S 2H 4H 5S 4C"),
                new PokerHand("2S 2H 5H 5S 6C"),
                new PokerHand("AH AC 5H 6H 7S"),
                new PokerHand("TH JH KH QH AH"),
                new PokerHand("JS JD JC JH AD")
        ));

       Collections.sort(hands);
       hands.forEach(System.out::println);

    }
}