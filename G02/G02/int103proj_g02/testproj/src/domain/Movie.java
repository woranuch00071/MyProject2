package domain;

public class Movie {
    private int movieId;
    private String title;
    private String director;

    public Movie(int movieId, String title, String director) {
        this.movieId = movieId;
        this.title = title;
        this.director = director;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "MovieID: " + movieId + "\nTitle: " + title + "\nDirector: " + director + "\n";
    }
}
