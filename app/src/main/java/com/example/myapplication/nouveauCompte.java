package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class nouveauCompte extends AppCompatActivity {
    EditText edNAccNom, edNAccMail, edNAccMp;
    TextView txtNAccErr;
    Button btnNAccCreer, btnNAccRetour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouveau_compte);

        //Récupérer les composants
        edNAccNom = findViewById(R.id.edNewAccountNom);
        edNAccMail = findViewById(R.id.edNewAccountMail);
        edNAccMp = findViewById(R.id.edNewAccountMp);
        txtNAccErr = findViewById(R.id.txtNAccountError);
        btnNAccCreer = findViewById(R.id.btnNAccCreer);
        btnNAccRetour = findViewById(R.id.btnNewAccountRetour);

        //Initialisation de la Base de données
        DataBaseHandler db = new DataBaseHandler(this);

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
                long res;
                String msg = verifierChamps();
                txtNAccErr.setText("");

                if(msg.equals(""))
                {
                    res=db.ajouterClient(new Client(edNAccNom.getText().toString(), edNAccMail.getText().toString(), edNAccMp.getText().toString()));
                    if(res != -1)
                    {
                        Toast.makeText( nouveauCompte.this, "Le compte a été crée avec succès !", Toast.LENGTH_LONG).show();
                        redirectActivity(nouveauCompte.this, Authentification.class);
                    }else
                    {
                        Toast.makeText( nouveauCompte.this, "Erreur Lors de la création de compte !", Toast.LENGTH_LONG).show();
                    }
                }else{
                    txtNAccErr.setText(msg);
                }
            }
        });
    }
    public static void redirectActivity(Activity activite, Class activite1)
    {
        Intent intent = new Intent(activite, activite1);
        activite.startActivity(intent);
    }

    public String verifierChamps()
    {
        String nom = edNAccNom.getText().toString();
        String mail = edNAccMail.getText().toString();
        String pass = edNAccMp.getText().toString();
        if(nom.equals("")) return "Le nom est obligatoire !";
        if(mail.equals("")) return "L'E-mail est obligatoire !";
        if(pass.equals("")) return "Le mot de passe est obligatoire !";
        return "";
    }
}