package com.example.movie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movie.models.Movies;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MoviesView extends AppCompatActivity {

    TextView movieName;
    TextView movieRating;
    TextView movieLang;
    TextView movieYear;
    TextView movieGenre;
    String movie_name;
    FirebaseFirestore database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_view);
        database=FirebaseFirestore.getInstance();
        Bundle bundle = getIntent().getExtras();
        movie_name = bundle.getString("selectedItem");
        movieName=findViewById(R.id.name);
        movieRating=findViewById(R.id.rating);
        movieLang=findViewById(R.id.lang);
        movieYear=findViewById(R.id.year);
        movieGenre=findViewById(R.id.genre);
        getData();
    }

    public void getData(){

        database.collection("movies")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            String name="",genre="",rating="",lang="",year="";
                            for(DocumentSnapshot document:task.getResult()){
                                Movies movie=document.toObject(Movies.class);
                                if(movie.getName().equals(movie_name))
                                {
                                    name=movie.getName().toString();
                                    genre=movie.getGenre().toString();
                                    rating=movie.getRating().toString();
                                    lang=movie.getLanguage().toString();
                                    year=movie.getDate().toString();
                                }
                                movieName.setText(name);
                                movieGenre.setText(genre);
                                movieLang.setText(lang);
                                movieRating.setText(Rating);
                                movieYear.setText(year);
                            }
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });


    }
}