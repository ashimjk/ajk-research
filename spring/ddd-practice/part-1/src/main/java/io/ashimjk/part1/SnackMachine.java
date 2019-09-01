package io.ashimjk.part1;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

import static io.ashimjk.part1.Money.*;

@Getter
class SnackMachine extends Entity {

    private Money moneyInside = NONE;
    private Money moneyInTransaction = NONE;

    void insertMoney(Money money) {

        List<Money> coinsAndNotes = Arrays.asList(
                CENT, TENCENT, QUARTER, DOLLAR, FIVEDOLLAR, TWENTYDOLLAR
        );

        if (!coinsAndNotes.contains(money))
            throw new IllegalArgumentException();

        this.moneyInTransaction = Money.add(moneyInTransaction, money);
    }

    void returnMoney() {
        this.moneyInTransaction = NONE;
    }

    void buySnack() {
        this.moneyInside = Money.add(moneyInside, moneyInTransaction);
        this.moneyInTransaction = NONE;
    }

}
