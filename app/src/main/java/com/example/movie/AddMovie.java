package com.example.movie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.movie.models.Movies;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=movieName.getText().toString();
                String rating=movieRating.getText().toString();
                String genre=movieGenre.getText().toString();
                String date=movieYear.getText().toString();
                String language=movieLang.getText().toString();
                addMovie(name,date,rating,language,genre);

            }
        });
    }

    public void addMovie(String name,String date,String rating,String lang,String genre){
        Movies new_movie=new Movies(name,rating,genre,lang,date);
        database.collection("movies")
                .add(new_movie)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(),"Movie Submitted",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Error. Try Again"+e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
    }
}