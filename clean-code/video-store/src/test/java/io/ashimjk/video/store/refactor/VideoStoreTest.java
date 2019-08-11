package io.ashimjk.video.store.refactor;

import io.ashimjk.video.store.refactor.movie.Movie;
import io.ashimjk.video.store.refactor.movie.type.ChildrenMovie;
import io.ashimjk.video.store.refactor.movie.type.NewReleaseMovie;
import io.ashimjk.video.store.refactor.movie.type.RegularMovie;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VideoStoreTest {

    private final double DELTA = 0.001;
    private Statement statement;
    private Movie newReleaseMovie1;
    private Movie newReleaseMovie2;
    private Movie childrenMovie;
    private Movie regular1;
    private Movie regular2;
    private Movie regular3;

    @Before
    public void setUp() {
        statement = new Statement("Customer");

        newReleaseMovie1 = new NewReleaseMovie("New Release 1");
        newReleaseMovie2 = new NewReleaseMovie("New Release 2");

        childrenMovie = new ChildrenMovie("Children Movie");

        regular1 = new RegularMovie("Regular 1");
        regular2 = new RegularMovie("Regular 2");
        regular3 = new RegularMovie("Regular 3");
    }

    @Test
    public void testSingleNewReleaseStatementTotals() {
        statement.addRental(new Rental(newReleaseMovie1, 3));
        statement.generate();

        assertEquals(9.0, statement.getTotal(), DELTA);
        assertEquals(2, statement.getFrequentRenterPoints());
    }

    @Test
    public void testDualNewReleaseStatementTotals() {
        statement.addRental(new Rental(newReleaseMovie1, 3));
        statement.addRental(new Rental(newReleaseMovie2, 3));
        statement.generate();

        assertEquals(18.0, statement.getTotal(), DELTA);
        assertEquals(4, statement.getFrequentRenterPoints());
    }

    @Test
    public void testSingleChildrenStatementTotals() {
        statement.addRental(new Rental(childrenMovie, 3));
        statement.generate();

        assertEquals(1.5, statement.getTotal(), DELTA);
        assertEquals(1, statement.getFrequentRenterPoints());
    }

    @Test
    public void testSingleChildrenForMoreDaysStatementTotals() {
        statement.addRental(new Rental(childrenMovie, 4));
        statement.generate();

        assertEquals(3.0, statement.getTotal(), DELTA);
        assertEquals(1, statement.getFrequentRenterPoints());
    }

    @Test
    public void testMultipleRegularStatementTotals() {
        statement.addRental(new Rental(regular1, 1));
        statement.addRental(new Rental(regular2, 2));
        statement.addRental(new Rental(regular3, 3));

        statement.generate();

        assertEquals(7.5, statement.getTotal(), DELTA);
        assertEquals(3, statement.getFrequentRenterPoints());
    }

    @Test
    public void testMultipleRegularStatementFormat() {
        statement.addRental(new Rental(regular1, 1));
        statement.addRental(new Rental(regular2, 2));
        statement.addRental(new Rental(regular3, 3));

        assertEquals("Rental Record for Customer\n" +
                        "\tRegular 1\t2.0\n" +
                        "\tRegular 2\t2.0\n" +
                        "\tRegular 3\t3.5\n" +
                        "You owed 7.5\n" +
                        "You earned 3 frequent renter points\n",
                statement.generate());
    }

}