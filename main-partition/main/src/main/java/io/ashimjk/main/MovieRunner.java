package io.ashimjk.main;

import io.ashimjk.app.movie.Movie;
import io.ashimjk.app.movie.MovieFactory;

import static io.ashimjk.app.movie.MovieFactory.MovieType.CHILDREN;
import static io.ashimjk.app.movie.MovieFactory.MovieType.NEW_RELEASE;
import static io.ashimjk.app.movie.MovieFactory.MovieType.REGULAR;

public class MovieRunner {

	public static void main(String[] args) {

		int daysRented = 3;

		printMovieAmountAndRenterPoints(daysRented, NEW_RELEASE, "New Release");
		printMovieAmountAndRenterPoints(daysRented, REGULAR, "Regular");
		printMovieAmountAndRenterPoints(daysRented, CHILDREN, "Children");
	}

	private static void printMovieAmountAndRenterPoints(int daysRented, MovieFactoryImpl.MovieType movieType, String movieTitle) {

		MovieFactory movieFactory = new MovieFactoryImpl();
		Movie movie = movieFactory.getMovie(movieType, movieTitle);

		double amount = movie.determineAmount(daysRented);
		int renterPoints = movie.determineFrequentRenterPoints(daysRented);

		System.out.println(String.format("Movie Title : %s, Total Amount : %.1f, Renter Points : %d", movieTitle, amount, renterPoints));
	}

}
