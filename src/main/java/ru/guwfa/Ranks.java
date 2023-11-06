package ru.guwfa;

import ru.guwfa.utils.Helpers;

import java.util.*;

public enum Ranks {
    HIGH_CARD {
        @Override
        public int getRank() {
            return 1;
        }

        @Override
        public Result resolveConflict(PokerHand first, PokerHand second) {
            return Helpers.getHighCardResults(first.getCards(), second.getCards());
        }
    },
    ONE_PAIR {
        @Override
        public int getRank() {
            return 2;
        }

        @Override
        public Result resolveConflict(PokerHand first, PokerHand second) {
            List<Card> firstCards = new ArrayList<>();
            List<Card> secondCards = new ArrayList<>();

            int firstPairValue = getHandDetails(first, firstCards);

            int secondPairValue = getHandDetails(second, secondCards);

            if (firstPairValue > secondPairValue) {
                return Result.WIN;
            } else if (firstPairValue < secondPairValue) {
                return Result.LOSS;
            } else {
                return Helpers.getHighCardResults(firstCards, secondCards);
            }
        }

        private int getHandDetails(PokerHand hand, List<Card> cards) {
            int value = 0;

            for (Map.Entry<Integer, List<Card>> entry : hand.getCardsMap().entrySet()) {
                if (entry.getValue().size() == 1) {
                    cards.addAll(entry.getValue());
                } else {
                    value = entry.getKey();
                }
            }
            return value;
        }
    },
    TWO_PAIR {
        @Override
        public int getRank() {
            return 3;
        }

        @Override
        public Result resolveConflict(PokerHand first, PokerHand second) {

            ArrayList<Integer> firstValues = new ArrayList<>(first.getCards().stream().mapToInt(value -> value.getValue()).boxed().toList());
            firstValues.sort(Comparator.reverseOrder());
            ArrayList<Integer> secondValues = new ArrayList<>(second.getCards().stream().mapToInt(value -> value.getValue()).boxed().toList());
            secondValues.sort(Comparator.reverseOrder());
            return Helpers.getHighCardResultsForValues(firstValues, secondValues);
        }
    },
    THREE_OF_A_KIND {
        @Override
        public int getRank() {
            return 4;
        }

        @Override
        public Result resolveConflict(PokerHand first, PokerHand second) {
            return Helpers.getHighCardResults(first.getCards(), second.getCards());
        }
    },
    STRAIGHT {
        @Override
        public int getRank() {
            return 5;
        }

        @Override
        public Result resolveConflict(PokerHand first, PokerHand second) {
            return Helpers.getHighCardResults(first.getCards(), second.getCards());
        }
    },
    FLUSH {
        @Override
        public int getRank() {
            return 6;
        }

        @Override
        public Result resolveConflict(PokerHand first, PokerHand second) {
            return Helpers.getHighCardResults(first.getCards(), second.getCards());
        }
    },
    FULL_HOUSE {
        @Override
        public int getRank() {
            return 7;
        }

        @Override
        public Result resolveConflict(PokerHand first, PokerHand second) {
            return Helpers.getHighCardResults(first.getCards(), second.getCards());
        }
    },
    FOUR_OF_A_KIND {
        @Override
        public int getRank() {
            return 8;
        }

        @Override
        public Result resolveConflict(PokerHand first, PokerHand second) {
            return Helpers.getHighCardResults(first.getCards(), second.getCards());
        }
    },
    STRAIGHT_FLUSH {
        @Override
        public int getRank() {
            return 9;
        }

        @Override
        public Result resolveConflict(PokerHand first, PokerHand second) {
            return Helpers.getHighCardResults(first.getCards(), second.getCards());
        }
    },
    ROYAL_FLUSH {
        @Override
        public int getRank() {
            return 10;
        }

        @Override
        public Result resolveConflict(PokerHand first, PokerHand second) {
            return Helpers.getHighCardResults(first.getCards(), second.getCards());
        }
    };


    public abstract int getRank();

    public abstract Result resolveConflict(PokerHand first, PokerHand second);
}

