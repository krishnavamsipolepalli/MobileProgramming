package com.example.movie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.movie.models.Language;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MovieLanguage extends AppCompatActivity {

    ListView language_list;
    FirebaseFirestore database;
    ArrayList<String> languageArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_language);
        language_list=findViewById(R.id.LanguageList);
        database=FirebaseFirestore.getInstance();
        getData();
        ArrayAdapter arraylistadpater=new ArrayAdapter(this,android.R.layout.simple_list_item_1, (List) language_list);
        language_list.setAdapter(arraylistadpater);
    }

    public void getData(){

        database.collection("language")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){


                            for(DocumentSnapshot document : task.getResult()){

                                Language language=document.toObject(Language.class);
                                languageArray.add(language.getLanguage_name());
                            }

                        }
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