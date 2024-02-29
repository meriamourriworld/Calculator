package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class nouveauCompte extends AppCompatActivity {
    EditText edNAccNom, edNAccMail, edNAccMp;
    Button btnNAccCreer, btnNAccRetour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouveau_compte);

        //Récupérer les composants
        edNAccNom = findViewById(R.id.edNewAccountNom);
        edNAccMail = findViewById(R.id.edNewAccountMail);
        edNAccMp = findViewById(R.id.edNewAccountMp);
        btnNAccCreer = findViewById(R.id.btnNAccCreer);
        btnNAccRetour = findViewById(R.id.btnNewAccountRetour);

        //ÉVÉNEMENTS
        btnNAccRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(nouveauCompte.this, Authentification.class);
                finish();
            }
        });

        btnNAccCreer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public static void redirectActivity(Activity activite, Class activite1)
    {
        Intent intent = new Intent(activite, activite1);
        activite.startActivity(intent);
    }
}