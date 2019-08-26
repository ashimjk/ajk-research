package io.ashimjk.spring.integration.poc.message.processing.model;

import java.util.Arrays;

/**
 * Models Domestic Cargo Message(s)
 */
public class DomesticCargoMessage extends CargoMessage {

    public enum Region {

        NORTH(1), SOUTH(2), EAST(3), WEST(4);

        private int value;

        Region(int value) {
            this.value = value;
        }

        public static Region fromValue(int value) {
            return Arrays.stream(Region.values())
                    .filter(region -> region.value == value)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("not.a.valid.region"));
        }

    }

    private final Region region;

    public DomesticCargoMessage(Cargo cargo, Region region) {
        super(cargo);
        this.region = region;
    }

    @Override
    public String toString() {
        return "DomesticCargoMessage [cargo=" + super.toString() + ", region=" + region + "]";
    }

}
