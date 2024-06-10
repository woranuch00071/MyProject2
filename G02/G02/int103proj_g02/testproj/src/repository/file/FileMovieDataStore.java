package repository.file;

import domain.Movie;
import repository.MovieDataStore;

import java.io.*;
import java.util.*;

public class FileMovieDataStore implements MovieDataStore {
    private static final String FILE_NAME = "movies.txt";
    private final Set<Integer> movieIds = new HashSet<>();



    @Override
    public void saveMovie(Movie movie) {
//        if (movieIds.isEmpty()) {System.out.println("Cannot add movie. Movie ID can not be empty.");}
        if (!movieIds.contains(movie.getMovieId())) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
                writer.write(movie.getMovieId() + "," + movie.getTitle() + "," + movie.getDirector());
                writer.newLine();
                movieIds.add(movie.getMovieId());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Cannot add movie. Movie ID already exists.");
        }
    }

    @Override
    public List<Movie> loadAllMovies() {
        List<Movie> movies = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int movieId = Integer.parseInt(parts[0]);
                    String title = parts[1];
                    String director = parts[2];
                    movies.add(new Movie(movieId, title, director));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        movies.sort(Comparator.comparingInt(Movie::getMovieId));
        return movies;
    }

    @Override
    public boolean deleteMovie(int movieId) {
        List<Movie> movies = loadAllMovies();
        boolean removed = movies.removeIf(movie -> movie.getMovieId() == movieId);
        if (removed) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
                for (Movie movie : movies) {
                    writer.write(movie.getMovieId() + "," + movie.getTitle() + "," + movie.getDirector());
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return removed;
    }

    @Override
    public boolean updateMovie(Movie movie) {
        List<Movie> movies = loadAllMovies();
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getMovieId() == movie.getMovieId()) {
                movies.set(i, movie);
                saveAllMovies(movies);
                return true;
            }
        }
        return false;
    }

    @Override
    public Movie loadMovieById(int movieIdToEdit) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int movieId = Integer.parseInt(parts[0]);
                if (movieId == movieIdToEdit) {
                    String title = parts[1];
                    String director = parts[2];
                    return new Movie(movieId, title, director);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void saveAllMovies(List<Movie> movies) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Movie movie : movies) {
                writer.write(movie.getMovieId() + "," + movie.getTitle() + "," + movie.getDirector());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}