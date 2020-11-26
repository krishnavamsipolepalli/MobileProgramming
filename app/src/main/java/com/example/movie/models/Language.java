package com.example.movie.models;

public class Language {
    String language_name;

    public Language(String language_name) {
        this.language_name = language_name;
    }
    public Language(){

    }
    public String getLanguage_name() {
        return language_name;
    }

    public void setLanguage_name(String language_name) {
        this.language_name = language_name;
    }
}
