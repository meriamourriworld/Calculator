package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class afficherClients extends AppCompatActivity {
    DrawerLayout mainDrawer;
    ImageView mainMenu;
    LinearLayout menuLogOut, menuCalc, menuClients;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_clients);

        //Récupérer les composants
        mainDrawer = findViewById(R.id.mainDrawer);
        mainMenu = findViewById(R.id.mainMenu);
        menuLogOut = findViewById(R.id.menuLogout);
        menuCalc = findViewById(R.id.menuCalculatrice);
        menuClients = findViewById(R.id.menuClients);

        //ÉVÉNEMENTS
        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openDrawer(mainDrawer);}
        });
        menuLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(afficherClients.this, Authentification.class);
            }
        });

        menuCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(afficherClients.this, MainActivity.class);
            }
        });

        menuClients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });
    }


    //Fonctions drawer menu
    public static void openDrawer(DrawerLayout dl){dl.openDrawer(GravityCompat.START);}
    public static void closeDrawer(DrawerLayout dl){dl.closeDrawer(GravityCompat.START);}
    protected void onPause(){
        super.onPause();
        closeDrawer(mainDrawer);
    }
    public static void redirectActivity(Activity act1, Class act2)
    {
        Intent intent = new Intent(act1, act2);
        act1.startActivity(intent);
    }
}