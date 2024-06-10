package repository;

import domain.Movie;

import java.util.List;

public interface MovieDataStore {
    void saveMovie(Movie movie);
    List<Movie> loadAllMovies();
    boolean deleteMovie(int movieId);

    boolean updateMovie(Movie movie);

    Movie loadMovieById(int movieIdToEdit);


}
