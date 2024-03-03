package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class modifierClient extends AppCompatActivity {
    DrawerLayout mainDrawerMod;
    ImageView mainMenuMod;
    LinearLayout menuLogOutMod, menuCalcMod, menuClientsMod;
    EditText edModNom, edModMail, edModMp;
    TextView txtModErr;
    Button btnModifier;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_client);

        //Récupérer les composants
        edModNom = findViewById(R.id.edModNom);
        edModMail = findViewById(R.id.edModMail);
        edModMp = findViewById(R.id.edModMp);
        txtModErr = findViewById(R.id.txtModError);
        btnModifier = findViewById(R.id.btnModifier);

        //Récupérer les composants Menu
        mainDrawerMod = findViewById(R.id.mainDrawer);
        mainMenuMod = findViewById(R.id.mainMenu);
        menuLogOutMod = findViewById(R.id.menuLogout);
        menuCalcMod = findViewById(R.id.menuCalculatrice);
        menuClientsMod = findViewById(R.id.menuClients);

        //Initialisation de la Base de données
        DataBaseHandler db = new DataBaseHandler(this);

        //Chargement des info Client
        Intent intent = getIntent();
        edModNom.setText(intent.getStringExtra("nom"));
        edModMail.setText(intent.getStringExtra("email"));
        edModMp.setText(intent.getStringExtra("motPasse"));

        //ÉVÉNEMENTS BUTTON
        btnModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long res;
                String msg = verifierChamps();
                txtModErr.setText("");

                if(msg.equals(""))
                {
                    res=db.modifierClient(new Client(edModNom.getText().toString(), edModMail.getText().toString(), edModMp.getText().toString()));
                    if(res != -1)
                    {
                        Toast.makeText( modifierClient.this, "Le compte a été Modifié avec succès !", Toast.LENGTH_LONG).show();
                        redirectActivity(modifierClient.this, afficherClients.class);
                    }else
                    {
                        Toast.makeText( modifierClient.this, "Erreur Lors de la modification de compte !", Toast.LENGTH_LONG).show();
                    }
                }else{
                    txtModErr.setText(msg);
                }
            }
        });


        //ÉVÉNEMENTS MENU
        mainMenuMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openDrawer(mainDrawerMod);}
        });
        menuLogOutMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(modifierClient.this, Authentification.class);
            }
        });

        menuCalcMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(modifierClient.this, MainActivity.class);
            }
        });

        menuClientsMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });
    }


    public String verifierChamps()
    {
        String nom = edModNom.getText().toString();
        String mail = edModMail.getText().toString();
        String pass = edModMp.getText().toString();
        if(nom.equals("")) return "Le nom est obligatoire !";
        if(mail.equals("")) return "L'E-mail est obligatoire !";
        if(pass.equals("")) return "Le mot de passe est obligatoire !";
        return "";
    }

    //Fonctions drawer menu
    public static void openDrawer(DrawerLayout dl){dl.openDrawer(GravityCompat.START);}
    public static void closeDrawer(DrawerLayout dl){dl.closeDrawer(GravityCompat.START);}
    protected void onPause(){
        super.onPause();
        closeDrawer(mainDrawerMod);
    }
    public static void redirectActivity(Activity act1, Class act2)
    {
        Intent intent = new Intent(act1, act2);
        act1.startActivity(intent);
    }


}