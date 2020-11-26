package com.example.movie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.movie.models.Language;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button addMovieBtn;
    Button ViewMovieBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addMovieBtn=findViewById(R.id.add);
        ViewMovieBtn=findViewById(R.id.View);
        addMovieBtn.setOnClickListener(this);
        ViewMovieBtn.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add:
                Intent intent=new Intent(this.getApplicationContext(), AddMovie.class);
                this.startActivity(intent);
                break;
            case R.id.View:
                intent=new Intent(this.getApplicationContext(), Language.class);
                this.startActivity(intent);
                break;

        }
    }
}