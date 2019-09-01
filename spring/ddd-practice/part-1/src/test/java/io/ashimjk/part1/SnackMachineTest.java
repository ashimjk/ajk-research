package io.ashimjk.part1;

import org.junit.jupiter.api.Test;

import static io.ashimjk.part1.Money.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SnackMachineTest {

    @Test
    void return_money_empties_money_in_transaction() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.insertMoney(DOLLAR);

        snackMachine.returnMoney();

        assertEquals(0, snackMachine.getMoneyInTransaction().getAmount());
    }

    @Test
    void inserted_money_goes_to_money_in_transaction() {
        SnackMachine snackMachine = new SnackMachine();

        snackMachine.insertMoney(CENT);
        snackMachine.insertMoney(DOLLAR);

        assertEquals(1.01, snackMachine.getMoneyInTransaction().getAmount());
    }

    @Test
    void cannot_insert_more_than_one_coin_or_note_at_a_time() {
        SnackMachine snackMachine = new SnackMachine();
        Money twoCent = Money.add(CENT, CENT);

        assertThrows(IllegalArgumentException.class, () -> snackMachine.insertMoney(twoCent));
    }

    @Test
    void money_in_transaction_goes_to_money_inside_after_purchase() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.insertMoney(DOLLAR);
        snackMachine.insertMoney(DOLLAR);

        snackMachine.buySnack();

        assertEquals(NONE, snackMachine.getMoneyInTransaction());
        assertEquals(2, snackMachine.getMoneyInside().getAmount());
    }

}