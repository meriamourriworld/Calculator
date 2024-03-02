package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class afficherClients extends AppCompatActivity {
    DrawerLayout mainDrawer;
    ImageView mainMenu;
    LinearLayout menuLogOut, menuCalc, menuClients, containerClient;
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
        containerClient = findViewById(R.id.containerClients);

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

        //Initialiser la dbHandler
        DataBaseHandler db = new DataBaseHandler(this);
        //Récupérer les clients depuis la Base de données
        ArrayList<Client> lsClients = new ArrayList<Client>(db.getClients());
        for(Client cl : lsClients)
        {
            LinearLayout clientContainer = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 10, 0, 20);
            clientContainer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            clientContainer.setOrientation(LinearLayout.VERTICAL);
            clientContainer.setBackgroundColor(Color.rgb(197,183,214));
            clientContainer.setPadding(60,50, 0 ,60);
            clientContainer.setTop(20);

            //Creation des textView
            TextView tvnom = new TextView(this);
            tvnom.setText(cl.getNom());
            tvnom.setTextSize(20);
            tvnom.setPadding(60,10, 0 ,0);


            TextView tvmail = new TextView(this);
            tvmail.setText(cl.getEmail());
            tvmail.setTextSize(20);
            tvmail.setPadding(60,10, 0 ,0);

            clientContainer.addView(tvnom);
            clientContainer.addView(tvmail);

            containerClient.addView(clientContainer,params);
        }
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