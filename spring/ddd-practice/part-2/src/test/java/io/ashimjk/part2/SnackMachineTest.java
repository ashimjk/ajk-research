package io.ashimjk.part2;

import org.junit.jupiter.api.Test;

import static io.ashimjk.part2.Money.*;
import static io.ashimjk.part2.Snack.CHOCOLATE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SnackMachineTest {

    @Test
    void return_money_empties_money_in_transaction() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.insertMoney(DOLLAR);

        snackMachine.returnMoney();

        assertEquals(0, snackMachine.getMoneyInTransaction());
    }

    @Test
    void inserted_money_goes_to_money_in_transaction() {
        SnackMachine snackMachine = new SnackMachine();

        snackMachine.insertMoney(CENT);
        snackMachine.insertMoney(DOLLAR);

        assertEquals(1.01, snackMachine.getMoneyInTransaction());
    }

    @Test
    void cannot_insert_more_than_one_coin_or_note_at_a_time() {
        SnackMachine snackMachine = new SnackMachine();
        Money twoCent = add(CENT, CENT);

        assertThrows(IllegalArgumentException.class, () -> snackMachine.insertMoney(twoCent));
    }

    @Test
    void buySnack_trades_inserted_money_for_a_snack() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.loadSnacks(1, new SnackPile(CHOCOLATE, 10, 1));
        snackMachine.insertMoney(DOLLAR);

        snackMachine.buySnack(1);

        assertEquals(0, snackMachine.getMoneyInTransaction());
        assertEquals(1, snackMachine.getMoneyInside().getAmount());
        assertEquals(9, snackMachine.getSnackPile(1).getQuantity());
    }

    @Test
    void cannot_make_purchase_when_there_is_no_snacks() {
        SnackMachine snackMachine = new SnackMachine();

        assertThrows(InvalidArgumentException.class, () -> snackMachine.buySnack(1));
    }

    @Test
    void cannot_make_purchase_if_not_enough_money_inserted() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.loadSnacks(1, new SnackPile(CHOCOLATE, 1, 2));
        snackMachine.insertMoney(DOLLAR);

        assertThrows(InvalidArgumentException.class, () -> snackMachine.buySnack(1));
    }

    @Test
    void snack_machine_returns_money_with_highest_denomination_first() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.loadMoney(DOLLAR);

        snackMachine.insertMoney(QUARTER);
        snackMachine.insertMoney(QUARTER);
        snackMachine.insertMoney(QUARTER);
        snackMachine.insertMoney(QUARTER);
        snackMachine.returnMoney();

        assertEquals(4, snackMachine.getMoneyInside().getQuarterCount());
        assertEquals(0, snackMachine.getMoneyInside().getOneDollarCount());
    }

    @Test
    void after_purchase_change_is_returned() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.loadSnacks(1, new SnackPile(CHOCOLATE, 1, 0.5));
        snackMachine.loadMoney(Money.multiply(TENCENT, 10));

        snackMachine.insertMoney(DOLLAR);
        snackMachine.buySnack(1);

        assertEquals(1.5, snackMachine.getMoneyInside().getAmount());
        assertEquals(0, snackMachine.getMoneyInTransaction());
    }

    @Test
    void cannot_buy_snack_if_not_enough_change() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.loadSnacks(1, new SnackPile(CHOCOLATE, 1, 0.5));
        snackMachine.insertMoney(DOLLAR);

        assertThrows(InvalidArgumentException.class, () -> snackMachine.buySnack(1));
    }

}