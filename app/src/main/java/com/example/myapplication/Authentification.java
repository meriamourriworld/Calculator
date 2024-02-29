package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Authentification extends AppCompatActivity {

    Button btnAuthValider, btnNouveauCompte;
    EditText edAuthMail, edAuthMp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);

        //Récupérer les composants
        edAuthMail = findViewById(R.id.edAuthMail);
        edAuthMp = findViewById(R.id.edAuthmp);
        btnAuthValider = findViewById(R.id.btnAuthValider);
        btnNouveauCompte = findViewById(R.id.btnNouveauCompte);

        //ÉVÉNEMENTS
        btnNouveauCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(Authentification.this, nouveauCompte.class);
            }
        });
        btnAuthValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checking if empty or not a user
            }
        });
    }
    public static void redirectActivity(Activity activite, Class activite1)
    {
        Intent intent = new Intent(activite, activite1);
        activite.startActivity(intent);
    }
}