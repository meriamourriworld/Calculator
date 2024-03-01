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

public class modeScientifique extends AppCompatActivity {

    DrawerLayout drawer;
    LinearLayout standard, scientifique, temperature;
    ImageView menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_scientifique);
        //Récupérer les composants menu
        drawer = findViewById(R.id.drawerLayout);
        menu = findViewById(R.id.mainMenu);
        standard = findViewById(R.id.menuStandard);
        scientifique = findViewById(R.id.menuScientifique);
        temperature = findViewById(R.id.menuTemperature);

        //Menu events
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer(drawer);
            }
        });
        standard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(modeScientifique.this, MainActivity.class);
            }
        });
        scientifique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });

        temperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(modeScientifique.this, modeTemperature.class);
            }
        });
    }


    //Fonctions drawer menu
    public static void openDrawer(DrawerLayout dl){dl.openDrawer(GravityCompat.START);}
    public static void closeDrawer(DrawerLayout dl){dl.closeDrawer(GravityCompat.START);}

    public static void redirectActivity(Activity activity, Class activity1)
    {
        Intent intent = new Intent(activity, activity1);
        activity.startActivity(intent);
    }
    protected void onPause() {
        super.onPause();
        closeDrawer(drawer);
    }
}