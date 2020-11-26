package com.example.movie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

public class AddMovie extends AppCompatActivity {

    EditText movieName;
    EditText movieRating;
    EditText movieLang;
    EditText movieYear;
    EditText movieGenre;
    Button submit;

    FirebaseFirestore database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        movieName=findViewById(R.id.MovieName);
        movieRating=findViewById(R.id.MovieRating);
        movieLang=findViewById(R.id.MovieLanguage);
        movieYear=findViewById(R.id.MovieDate);
        movieGenre=findViewById(R.id.MovieGenre);
        submit=findViewById(R.id.sumitBtn);

        database=FirebaseFirestore.getInstance();
    }

    public void addMovie(String name,String date,String rating,String lang,String genre){
        database.collection("movies");
    }
}