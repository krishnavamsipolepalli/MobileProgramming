package com.example.movie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.movie.models.Language;
import com.example.movie.models.Movies;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MoviesList extends AppCompatActivity {

    ListView movie_list;
    FirebaseFirestore database;
    String lang="Temp";
    List<String> MovieArray=new ArrayList<String>();
    ArrayAdapter arraylistadpater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);
        movie_list=findViewById(R.id.MovieList);
        database=FirebaseFirestore.getInstance();
        Bundle bundle = getIntent().getExtras();
        lang = bundle.getString("selectedItem");
        getData();
        arraylistadpater=new ArrayAdapter(this,android.R.layout.simple_list_item_1,MovieArray);
    }

    public void getData(){

        database.collection("movies")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(DocumentSnapshot document:task.getResult()){
                                Movies movie=document.toObject(Movies.class);
                                if(movie.getLanguage().equals(lang))
                                {
                                MovieArray.add(movie.getName().toString());
                                }
                            }
                            movie_list.setAdapter(arraylistadpater);
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