package io.ashimjk.part2;

import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.ashimjk.part2.Money.*;

@Getter
class SnackMachine extends AggregateRoot {

    private Money moneyInside;
    private double moneyInTransaction;
    private List<Slot> slots;

    SnackMachine() {
        moneyInside = NONE;
        moneyInTransaction = 0;
        slots = new ArrayList<>();
        slots.add(new Slot(this, 1));
        slots.add(new Slot(this, 2));
        slots.add(new Slot(this, 3));
    }

    public List<SnackPile> getAllSnackPiles() {
        return slots
                .stream()
                .sorted()
                .map(Slot::getSnackPile)
                .collect(Collectors.toList());
    }

    void insertMoney(Money money) {

        List<Money> coinsAndNotes = Arrays.asList(
                CENT, TENCENT, QUARTER, DOLLAR, FIVEDOLLAR, TWENTYDOLLAR
        );

        if (!coinsAndNotes.contains(money))
            throw new IllegalArgumentException();

        this.moneyInTransaction += money.getAmount();
        this.moneyInside = Money.add(moneyInside, money);
    }

    void returnMoney() {
        Money moneyToReturn = this.moneyInside.allocate(moneyInTransaction);
        this.moneyInside = Money.minus(this.moneyInside, moneyToReturn);
        this.moneyInTransaction = 0;
    }

    void buySnack(int position) {
        if (!canBuySnack(position).equals(Strings.EMPTY))
            throw new InvalidArgumentException();

        Slot slot = getSlot(position);
        slot.setSnackPile(slot.getSnackPile().subtractOne());

        Money change = moneyInside.allocate(moneyInTransaction - slot.getSnackPile().getPrice());
        moneyInside = Money.minus(moneyInside, change);
        moneyInTransaction = 0;
    }

    private String canBuySnack(int position) {
        SnackPile snackPile = getSnackPile(position);

        if (snackPile.getQuantity() == 0)
            return "The snack pile is empty";

        if (moneyInTransaction < snackPile.getPrice())
            return "Not enough money";

        if (!moneyInside.canAllocate(moneyInTransaction - snackPile.getPrice()))
            return "Not enough change";

        return Strings.EMPTY;
    }

    SnackPile getSnackPile(int position) {
        return getSlot(position).getSnackPile();
    }

    private Slot getSlot(int position) {
        return slots
                .stream()
                .filter(x -> x.getPosition() == position)
                .findFirst()
                .orElseThrow(InvalidArgumentException::new);
    }

    void loadSnacks(int position, SnackPile snackPile) {
        Slot slot = getSlot(position);
        slot.setSnackPile(snackPile);
    }

    void loadMoney(Money money) {
        this.moneyInside = Money.add(moneyInside, money);
    }

}
