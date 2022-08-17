import java.util.List;

public class Movie {
    int Rank;
    String Title;
    List<String> Genre;
    String Description;
    String Director;
    String Actors;
    int Year;
    int Runtime;
    float Rating;
    int Votes;
    float Revenue;
    int Metascore;

    @Override
    public String toString() {
        return "Movie{" +
                "Rank=" + Rank +
                ", Title='" + Title + '\'' +
                ", Genre='" + Genre + '\'' +
                ", Description='" + Description + '\'' +
                ", Director='" + Director + '\'' +
                ", Actors='" + Actors + '\'' +
                ", Year=" + Year +
                ", Runtime=" + Runtime +
                ", Rating=" + Rating +
                ", Votes=" + Votes +
                ", Revenue=" + Revenue +
                ", Metascore=" + Metascore +
                '}';
    }

    public Movie(int Rank, String Title, List<String> Genre, String Description,
                 String Director, String Actors, int Year, int Runtime,
                 float Rating, int Votes, float Revenue, int Metascore){

        this.Rank = Rank;
        this.Title = Title;
        this.Genre = Genre;
        this.Description = Description;
        this.Director = Director;
        this.Actors = Actors;
        this.Year = Year;
        this.Runtime = Runtime;
        this.Rating = Rating;
        this.Votes = Votes;
        this.Revenue = Revenue;
        this.Metascore = Metascore;
    }


    public int getRank() {
        return Rank;
    }
    public void setRank(int rank) {
        Rank = rank;
    }
    public String getTitle() {
        return Title;
    }
    public void setTitle(String title) {
        Title = title;
    }
    public List<String> getGenre() {
        return Genre;
    }
    public void setGenre(List<String> genre) {
        Genre = genre;
    }
    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }
    public String getDirector() {
        return Director;
    }
    public void setDirector(String director) {
        Director = director;
    }
    public String getActors() {
        return Actors;
    }
    public void setActors(String actors) {
        Actors = actors;
    }
    public int getYear() {
        return Year;
    }
    public void setYear(int year) {
        Year = year;
    }
    public int getRuntime() {
        return Runtime;
    }
    public void setRuntime(int runtime) {
        Runtime = runtime;
    }
    public float getRating() {
        return Rating;
    }
    public void setRating(float rating) {
        Rating = rating;
    }
    public int getVotes() {
        return Votes;
    }
    public void setVotes(int votes) {
        Votes = votes;
    }
    public float getRevenue() {
        return Revenue;
    }
    public void setRevenue(float revenue) {
        Revenue = revenue;
    }
    public int getMetascore() {
        return Metascore;
    }
    public void setMetascore(int metascore) {
        Metascore = metascore;
    }
}

