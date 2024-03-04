package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

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

        fetchClients();

    }

    //Fonction de récupération des clients
    public void fetchClients()
    {
        //Initialiser la dbHandler
        DataBaseHandler db = new DataBaseHandler(this);
        //Récupérer les clients depuis la Base de données
        ArrayList<Client> lsClients = new ArrayList<Client>(db.getClients());
        Log.d("SIZE", String.valueOf(lsClients.size()));
        containerClient.removeAllViews();
        for(Client cl : lsClients)
        {
            LinearLayout clientContainer = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 10, 0, 20);
            clientContainer.setOrientation(LinearLayout.VERTICAL);
            clientContainer.setBackgroundColor(Color.rgb(53,24,90));
            clientContainer.setPadding(60,50, 0 ,60);
            clientContainer.setTop(20);

            //Creation Du conteneur du nom et icone de suppression
            LinearLayout myContainer = new LinearLayout(this);
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            myContainer.setOrientation(LinearLayout.HORIZONTAL);
            myContainer.setPadding(0,0,40,0);

            LinearLayout.LayoutParams paramsIcon = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            paramsIcon.weight =0;
            paramsIcon.topMargin = 10;
            //Icone de suppression
            ImageView deleteIcon = new ImageView(this);
            deleteIcon.setImageResource(R.drawable.baseline_delete_24);
            deleteIcon.setTooltipText(cl.getEmail());
            deleteIcon.setLayoutParams(paramsIcon);



            //Creation des textView
            LinearLayout.LayoutParams paramstvNom = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            paramstvNom.weight =1;
            TextView tvnom = new TextView(this);
            tvnom.setText(cl.getNom());
            tvnom.setTextSize(20);
            tvnom.setTextColor(Color.WHITE);
            tvnom.setLayoutParams(paramstvNom);
            tvnom.setPadding(60,10, 0 ,0);



            TextView tvmail = new TextView(this);
            tvmail.setText(cl.getEmail());
            tvmail.setTextSize(20);
            tvmail.setTextColor(Color.WHITE);
            tvmail.setPadding(60,10, 0 ,0);

            myContainer.addView(tvnom);
            myContainer.addView(deleteIcon);

            //Evénements
            deleteIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(db.supprimerClient(deleteIcon.getTooltipText().toString()) != -1)
                    {
                        Snackbar.make(containerClient, "Client supprimé avec succès", Snackbar.LENGTH_LONG)
                                .setBackgroundTint(Color.rgb(67, 160, 71))
                                .setTextColor(Color.WHITE)
                                .show();
                        fetchClients();
                    }
                    else
                    {
                        Snackbar.make(afficherClients.this.getCurrentFocus(), "Erreur lors de la suppression", Snackbar.LENGTH_LONG)
                                .setBackgroundTint(Color.RED)
                                .show();
                    }
                }
            });

            clientContainer.addView(myContainer, params1);
            clientContainer.addView(tvmail);

            containerClient.addView(clientContainer,params);

            //Ajouter événement info client layout
            clientContainer.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Client cl = db.getClientByMail(deleteIcon.getTooltipText().toString());
                    Intent intent = new Intent(afficherClients.this, modifierClient.class);
                    intent.putExtra("nom",cl.getNom());
                    intent.putExtra("email",cl.getEmail());
                    intent.putExtra("motPasse",cl.getMotPasse());
                    intent.putExtra("idClient",String.valueOf(cl.getIdClient()));
                    startActivity(intent);
                    return true;
                }
            });
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