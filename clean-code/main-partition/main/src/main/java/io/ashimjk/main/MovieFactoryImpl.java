package io.ashimjk.main;

import io.ashimjk.app.movie.Movie;
import io.ashimjk.app.movie.MovieFactory;
import io.ashimjk.app.movie.type.ChildrenMovie;
import io.ashimjk.app.movie.type.NewReleaseMovie;
import io.ashimjk.app.movie.type.RegularMovie;

class MovieFactoryImpl implements MovieFactory {

    public Movie getMovie(MovieType type, String title) {
        switch (type) {
            case REGULAR:
                return new RegularMovie(title);
            case NEW_RELEASE:
                return new NewReleaseMovie(title);
            case CHILDREN:
                return new ChildrenMovie(title);
            default:
                throw new IllegalArgumentException("Not valid movie type...");
        }
    }

}
