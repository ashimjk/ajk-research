package io.ashimjk.video.store.refactor.movie.type;

import io.ashimjk.video.store.refactor.movie.Movie;

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
