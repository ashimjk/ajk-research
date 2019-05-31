package io.ashimjk.app.movie;

public interface MovieFactory {

    public Movie getMovie(MovieType type, String title);

    enum MovieType {
        REGULAR,
        NEW_RELEASE,
        CHILDREN
    }

}