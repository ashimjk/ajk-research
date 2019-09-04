package io.ashimjk.part2;

import lombok.Getter;

@Getter
class Slot extends Entity {

    private SnackPile snackPile;
    private SnackMachine snackMachine;
    private int position;

    Slot(SnackMachine snackMachine, int position) {
        this.snackMachine = snackMachine;
        this.position = position;
        this.snackPile = SnackPile.EMPTY;
    }

    void setSnackPile(SnackPile snackPile) {
        this.snackPile = snackPile;
    }

}
