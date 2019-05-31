package io.ashimjk.app.movie.type;

import io.ashimjk.app.movie.Movie;

public class NewReleaseMovie extends Movie {

    public NewReleaseMovie(String title) {
        super(title);
    }

    @Override
    public double determineAmount(int daysRented) {
        return daysRented * 3;
    }

    @Override
    public int determineFrequentRenterPoints(int daysRented) {
        return daysRented > 1 ? 2 : 1;
    }

}
