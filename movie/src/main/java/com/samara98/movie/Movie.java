package com.samara98.movie;

import javax.persistence.*;

@Entity
@Table
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String overview;
    private String posterPath;
    private Double popularity;

    public Movie() {
    }

    public Movie(Long id, String title, String overview, String posterPath, Double popularity) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.posterPath = posterPath;
        this.popularity = popularity;
    }

    public Movie(String title, String overview, String posterPath, Double popularity) {
        this.title = title;
        this.overview = overview;
        this.posterPath = posterPath;
        this.popularity = popularity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", popularity=" + popularity +
                '}';
    }
}
