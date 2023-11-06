package ru.guwfa.utils;

import ru.guwfa.Card;
import ru.guwfa.Result;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

public class Helpers {

    public static  final HashMap<Character,Integer> valueMap;

    static {
        valueMap = new HashMap<>();
        valueMap.put('2',2);  valueMap.put('3',3);  valueMap.put('4',4);  valueMap.put('5',5);
        valueMap.put('6',6);  valueMap.put('7',7);  valueMap.put('8',8);  valueMap.put('9',9);
        valueMap.put('T',10); valueMap.put('J',11); valueMap.put('Q',12); valueMap.put('K',13);
        valueMap.put('A',14);
    }

    private Helpers() {

    }

    public static boolean checkCard(String cardName) {
        if (cardName.length() != 2)
            throw new RuntimeException("WRONG CARD");
        if ( !"23456789TJQKA".contains( cardName.substring(0,1) ) )
            throw new RuntimeException("WRONG RANK");
        if ( !"SHDC".contains( cardName.substring(1,2) ) )
            throw new RuntimeException("WRONG SUIT");

        return true;
    }

    public static Result getHighCardResults(List<Card> firstCardList, List<Card> secondCardList) {
        int index = firstCardList.size() - 1;
        while (index >= 0) {
            if (firstCardList.get(index).getValue() > secondCardList.get(index).getValue()) {
                return Result.WIN;
            } else if (firstCardList.get(index).getValue() < secondCardList.get(index).getValue()) {
                return Result.LOSS;
            }
            index--;
        }
        return Result.TIE;
    }

    public static Result getHighCardResultsForValues(ArrayList<Integer> firstCardList, ArrayList<Integer> secondCardList) {
        int index = firstCardList.size() - 1;
        while (index >= 0) {
            if (firstCardList.get(index) > secondCardList.get(index)) {
                return Result.WIN;
            } else if (firstCardList.get(index) < secondCardList.get(index)) {
                return Result.LOSS;
            }
            index--;
        }
        return Result.TIE;
    }

    public static int getCountOfGroupOfASize(Map<Integer, List<Card>> map, int groupSize) {
        return (int) map.entrySet().stream().filter(x -> x.getValue().size() == groupSize).count();
    }

    public static Map<Integer, List<Card>> getValueMap(List<Card> cards) {
        return cards.stream().collect(groupingBy(Card::getValue));
    }
}

