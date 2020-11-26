package com.example.movie.models;

public class Movies {

    String name;
    String Rating;
    String genre;
    String language;
    String date;

    public Movies(String name, String rating, String genre, String language, String date) {
        this.name = name;
        Rating = rating;
        this.genre = genre;
        this.language = language;
        this.date = date;
    }

    public Movies()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
