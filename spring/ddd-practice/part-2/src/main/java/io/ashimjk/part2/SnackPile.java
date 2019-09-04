package io.ashimjk.part2;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Getter
public class SnackPile extends ValueObject<SnackPile> {

    public static SnackPile EMPTY = new SnackPile(Snack.NONE, 0, 0);

    private Snack snack;
    private int quantity;
    private double price;

    SnackPile(Snack snack, int quantity, double price) {

        double calcPrice = BigDecimal.valueOf(price % 0.01)
                .setScale(2, RoundingMode.DOWN)
                .doubleValue();

        if (quantity < 0)
            throw new InvalidArgumentException();
        if (price < 0)
            throw new InvalidArgumentException();
        if (calcPrice > 0)
            throw new InvalidArgumentException();

        this.snack = snack;
        this.quantity = quantity;
        this.price = price;
    }

    SnackPile subtractOne() {
        return new SnackPile(snack, this.quantity - 1, this.price);
    }

    @Override
    public boolean equalsCore(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SnackPile snackPile = (SnackPile) o;
        return quantity == snackPile.quantity &&
                Double.compare(snackPile.price, price) == 0 &&
                snack.equals(snackPile.snack);
    }

    @Override
    public int hashCodeCore() {
        return Objects.hash(super.hashCode(), snack, quantity, price);
    }

}
