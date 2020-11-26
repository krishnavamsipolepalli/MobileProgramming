package com.example.movie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    TextView test;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_language);
        test=findViewById(R.id.test_data);
        language_list=findViewById(R.id.LanguageList);
        database=FirebaseFirestore.getInstance();
        getData();
        languageArray= Arrays.asList(test.getText().toString().split(","));
       ArrayAdapter arraylistadpater=new ArrayAdapter(this,android.R.layout.simple_list_item_1,languageArray);
       language_list.setAdapter(arraylistadpater);
    }

    public void getData(){

        database.collection("language")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            String result="";
                            for(DocumentSnapshot document:task.getResult()){
                                Language lang=document.toObject(Language.class);
                                result+= lang.getLanguageName()+",";
                            }
                            test.setText(result);
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