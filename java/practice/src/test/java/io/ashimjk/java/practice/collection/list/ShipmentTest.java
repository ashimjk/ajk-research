package io.ashimjk.java.practice.collection.list;

import org.junit.jupiter.api.Test;

import static io.ashimjk.java.practice.collection.list.ProductFixtures.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

class ShipmentTest {

    private Shipment shipment = new Shipment();

    @Test
    void shouldAddProducts() {
        this.shipment.add(door);
        this.shipment.add(window);

        assertThat(this.shipment, contains(door, window));
    }

    @Test
    void shouldReplaceProducts() {
        this.shipment.add(door);
        this.shipment.add(window);

        this.shipment.replace(door, floorPanel);

        assertThat(this.shipment, contains(floorPanel, window));
    }

    @Test
    void shouldNotReplaceMissingProducts() {
        this.shipment.add(window);

        this.shipment.replace(door, floorPanel);

        assertThat(this.shipment, contains(window));
    }

    @Test
    void shouldIdentifyVanRequirements() {
        this.shipment.add(door);
        this.shipment.add(window);
        this.shipment.add(floorPanel);

        this.shipment.prepare();

        assertThat(this.shipment.getLightVanProducts(), contains(window));
        assertThat(this.shipment.getHeavyVanProducts(), contains(floorPanel, door));
    }

}
