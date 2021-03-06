package com.example.movie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movie.models.Language;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieLanguage extends AppCompatActivity {

    ListView language_list;
    FirebaseFirestore database;
  List<String> languageArray=new ArrayList<String>();
    ArrayAdapter arraylistadpater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_language);
        language_list=findViewById(R.id.LanguageList);
        database=FirebaseFirestore.getInstance();
        getData();
        arraylistadpater=new ArrayAdapter(this,android.R.layout.simple_list_item_1,languageArray);
        language_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),MoviesList.class);
                String selectedItem = (String) arraylistadpater.getItem(position);
                intent.putExtra("selectedItem", selectedItem);
                startActivity(intent);
            }
        });
    }

    public void getData(){

        database.collection("language")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(DocumentSnapshot document:task.getResult()){
                                Language lang=document.toObject(Language.class);
                                languageArray.add(lang.getLanguageName().toString());
                            }
                            language_list.setAdapter(arraylistadpater);
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