# Texas_Holdem
Задание: Создать ранжирование покерных рук (набор карт игрока).
Используемые средства: Java

Описание
-Реализовать класс для ранжирования покерных рук.
    Класс должен содержать конструктор, который принимает на вход строку, содержащую пять карт: PokerHand hand = new PokerHand("KS 2H 5C JD TD");
    Характеристики входной строки:
        В качестве разделителя используется пробел.
        Описание каждой карты состоит из двух символов:
        первый символ — это номинал карты. Допустимые значения: 2, 3, 4, 5, 6, 7, 8, 9, T(en), J(ack), Q(ueen), K(ing), A(ce);
        второй символ — масть. Допустимые значения: S(pades), H(earts), D(iamonds), C(lubs).
-Реализовать возможность сортировки рук по «силе» (рейтингу / рангу) от сильной к слабой:
        ArrayList<PokerHand> hands = new ArrayList<PokerHand>();
        hands.add(new PokerHand("KS 2H 5C JD TD"));
        hands.add(new PokerHand("2C 3C AC 4C 5C"));
        Collections.sort(hands);
    Для упрощения считать, что туз в комбинациях стрит или стрит-флэш может быть только наивысшей картой.
-Класс PokerHand должен быть покрыт unit-тестами (определение комбинаций и сравнение комбинаций).

Старшинство комбинаций по возрастанию:

    Кикер. Старшая карта.
    Пара. Две карты одного достоинства.
    Две пары. Две карты одного достоинства, две карты другого достоинства.
    Сет. Три карты одного достоинства.
    Стрит. Пять карт, которые выстроились по старшинству.
    Флеш. Пять карт одной масти.
    Фулл-хаус. Сет плюс пара.
    Каре. Четыре карты одного достоинства.
    Стрит-флеш. Пять карт одной масти, которые выстроились по старшинству.
    Флеш-рояль. Пять карт от 10 до туза одной масти.
